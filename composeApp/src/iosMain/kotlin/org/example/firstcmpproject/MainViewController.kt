package org.example.firstcmpproject

import androidx.compose.ui.window.ComposeUIViewController
import org.example.firstcmpproject.core.persistence.getDatabaseBuilderIOS
import org.example.firstcmpproject.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}