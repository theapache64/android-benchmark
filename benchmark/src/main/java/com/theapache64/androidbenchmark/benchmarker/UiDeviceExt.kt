package com.theapache64.androidbenchmark.benchmarker

import android.os.SystemClock
import androidx.annotation.StringRes
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds

class WaitScope(
    private val testTag: String,
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

fun MacrobenchmarkScope.waitForUiElementByText(
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

fun MacrobenchmarkScope.waitForUiElement(
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

private fun MacrobenchmarkScope.waitForUiElement(
    identifier: String,
    onFound: (uiElement: UiObject2) -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 10.seconds,
    sleepTime: Duration = ZERO,
    findBy: () -> BySelector,
) {
    println("waitForUiElement: Waiting for `$identifier`")
    device.wait(
        Until.hasObject(findBy()),
        timeout.inWholeMilliseconds
    ).let { success ->
        if (success) {
            println("waitForUiElement: identifier '$identifier' found. Sleeping for ${sleepTime.inWholeMilliseconds}ms")
            SystemClock.sleep(sleepTime.inWholeMilliseconds)
            println("waitForUiElement: Sleeping done for $identifier. Trying to retrieve element")
            val uiElement = device.findObject(findBy())
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

fun MacrobenchmarkScope.waitForUiElementToDisappear(
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

private fun MacrobenchmarkScope.waitForUiElementToDisappear(
    identifier: String,
    onDisappear: () -> Unit,
    onTimeout: WaitScope.() -> Unit,
    timeout: Duration = 10.seconds,
    findBy: () -> BySelector,
) {
    println("waitForUiElementToDisappear: Waiting for disappear `$identifier`")
    device.wait(
        Until.gone(findBy()),
        timeout.inWholeMilliseconds
    ).let { success ->
        if (success) {
            val uiElement = device.findObject(findBy())
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

fun MacrobenchmarkScope.findObject(
    testTag: String,
    onFound: (uiObject: UiObject2) -> Unit,
    onError: WaitScope.() -> Unit,
) {
    device.findObject(By.res(testTag))?.let { element ->
        onFound(element)
    } ?: kotlin.run {
        onError(WaitScope(testTag))
    }
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
