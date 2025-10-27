package org.example.firstcmpproject

import androidx.compose.ui.window.ComposeUIViewController
import org.example.firstcmpproject.core.persistence.IOSDatabaseDriverFactory

fun MainViewController() = ComposeUIViewController {
    App(IOSDatabaseDriverFactory())
}