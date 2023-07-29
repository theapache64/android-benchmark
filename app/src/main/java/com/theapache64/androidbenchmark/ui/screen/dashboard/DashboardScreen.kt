package com.theapache64.androidbenchmark.ui.screen.dashboard

import androidx.activity.compose.ReportDrawn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.theapache64.androidbenchmark.R


@Composable
fun DashboardScreen() {
    ReportDrawn()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.label_hello_world),
            modifier = Modifier.testTag("label_hello_world"),
            fontSize = 50.sp
        )
    }
}