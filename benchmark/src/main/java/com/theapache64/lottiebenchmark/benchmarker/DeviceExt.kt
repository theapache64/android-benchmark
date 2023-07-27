package com.theapache64.lottiebenchmark.benchmarker
import android.os.SystemClock
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


class WaitScope(
    private val testTag: String
) {
    fun errorNotFound() {
        error("Couldn't find element with testTag '$testTag'")
    }

    fun errorNotDisappear() {
        error("Element stays on screen with testTag '$testTag'")
    }

    fun errorScrolling() {
        error("Element scrolling has an error: '$testTag'")
    }
}


@OptIn(ExperimentalTime::class)
fun UiDevice.waitForUiElementByText(
    text: String,
    onFound: (uiElement: UiObject2) -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 10.seconds,
    sleepTime: Duration = ZERO,
) {
    waitForUiElement(
        identifier = text,
        onFound = onFound,
        onTimeout = onTimeout,
        timeout = timeout,
        sleepTime = sleepTime,
        findBy = {
            By.text(text)
        }
    )
}

fun UiDevice.waitForUiElement(
    testTag: String,
    onFound: (uiElement: UiObject2) -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 20.seconds,
    sleepTime: Duration = ZERO,
) {
    waitForUiElement(
        identifier = testTag,
        onFound = onFound,
        onTimeout = onTimeout,
        timeout = timeout,
        sleepTime = sleepTime,
        findBy = {
            By.res(testTag)
        }
    )
}

private fun UiDevice.waitForUiElement(
    identifier: String,
    onFound: (uiElement: UiObject2) -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 10.seconds,
    sleepTime: Duration = ZERO,
    findBy: () -> BySelector
) {
    println("waitForUiElement: Waiting for `$identifier`")
    wait(
        Until.hasObject(findBy()),
        timeout.inWholeMilliseconds
    ).let { success ->
        if (success) {
            println("waitForUiElement: identifier '$identifier' found. Sleeping for ${sleepTime.inWholeMilliseconds}ms")
            SystemClock.sleep(sleepTime.inWholeMilliseconds)
            println("waitForUiElement: Sleeping done for $identifier. Trying to retrieve element")
            val uiElement = findObject(findBy())
            require(uiElement != null) {
                "uiElement is null for `$identifier`"
            }
            println("waitForUiElement: Element found for $identifier")
            onFound(uiElement)
        } else {
            println("waitForUiElement: Timed for for tag $identifier")
            onTimeout(WaitScope(identifier))
        }
    }
}

fun UiDevice.waitForUiElementToDisappear(
    testTag: String,
    onDisappear: () -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 20.seconds,
) {
    waitForUiElementToDisappear(
        identifier = testTag,
        onDisappear = onDisappear,
        onTimeout = onTimeout,
        timeout = timeout,
        findBy = {
            By.res(testTag)
        }
    )
}

private fun UiDevice.waitForUiElementToDisappear(
    identifier: String,
    onDisappear: () -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 10.seconds,
    findBy: () -> BySelector
) {
    println("waitForUiElementToDisappear: Waiting for disappear `$identifier`")
    wait(
        Until.gone(findBy()),
        timeout.inWholeMilliseconds
    ).let { success ->
        if (success) {
            val uiElement = findObject(findBy())
            require(uiElement == null) {
                "uiElement is NOT null for `$identifier`"
            }
            println("waitForUiElement: Element Disappeared found for $identifier")
            onDisappear()
        } else {
            println("waitForUiElementToDisappear: Timed for for tag $identifier")
            onTimeout(WaitScope(identifier))
        }
    }
}

fun UiDevice.findObject(
    testTag: String,
    onFound: (uiObject: UiObject2) -> Unit,
    onError: WaitScope.() -> Unit,
) {
    findObject(By.res(testTag))?.let { element ->
        onFound(element)
    } ?: kotlin.run {
        onError(WaitScope(testTag))
    }
}

fun UiDevice.scrollOnTray(
    rootScrollable: String,
    testTag: String,
    onFound: (uiObject: UiObject2) -> Unit,
    onError: WaitScope.() -> Unit,
    maxScroll: Int = 10
) {
    val trayScrollable = UiScrollable(UiSelector().scrollable(true).resourceId(rootScrollable))
    var tray: UiObject2? = null
    var scrollNum = 0
    while (scrollNum < maxScroll) {
        findObject(By.res(rootScrollable))?.let { traySpace ->
            tray = traySpace.findObject(By.res(testTag))
        }

        tray?.let {
            onFound(it)
            return
        } ?: run {
            trayScrollable.scrollForward()
            scrollNum++
        }
        waitForIdle()
    }
    onError(WaitScope("Max scroll number exceeded"))
}

/**
 * UI version of `Thread.sleep`
 */
fun UiDevice.waitFor(timeout: Duration) {
    // waiting for object doesn't exist
    wait(Until.findObject(By.res("object-that-doesnt-exist")), timeout.inWholeMilliseconds)
}

/**
 * To get string from stringRes id
 */
fun stringRes(@StringRes id: Int): String {
    return InstrumentationRegistry.getInstrumentation().targetContext.getString(id)
}

fun waitForScrollable(
    testTag: String,
    onFound: (uiScrollable: UiScrollable) -> Unit,
    onError: WaitScope.() -> Unit,
) {
    val scrollable = UiScrollable(UiSelector().resourceId(testTag).scrollable(true))
    val found = scrollable.waitForExists(50)
    if (found) {
        onFound(scrollable)
    } else {
        onError(WaitScope(testTag))
    }
}

fun UiScrollable.swipeRepeated(direction: Direction, repeat: Int = 10) {
    repeat(repeat) {

        println("##### Fully visible: ${ensureFullyVisible(this)}")

        when (direction) {
            Direction.LEFT -> this.swipeLeft(10)
            Direction.RIGHT -> this.swipeRight(10)
            Direction.UP -> this.swipeUp(10)
            Direction.DOWN -> this.swipeDown(10)
        }
    }
}

fun UiObject2.swipeRepeated(direction: Direction, repeat: Int = 10, percent: Float = 0.9f) {
    repeat(repeat) {
        this.swipe(direction, percent)
    }
}