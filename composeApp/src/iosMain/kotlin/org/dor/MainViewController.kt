package org.dor

import androidx.compose.ui.window.ComposeUIViewController
import org.dor.presentation.app.App

fun MainViewController() = ComposeUIViewController { App() }