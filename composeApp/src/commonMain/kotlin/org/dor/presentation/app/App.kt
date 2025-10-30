package org.dor.presentation.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.dor.data.di.dataModule
import org.dor.domain.di.domainModule
import org.dor.presentation.di.viewModelModule
import org.dor.presentation.screen.search.SearchScreen
import org.dor.presentation.ui.theme.DorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    DorTheme {
        KoinApplication(
            application = {
                modules(
                    modules = arrayOf(
                        dataModule,
                        domainModule,
                        viewModelModule
                    )
                )
            }
        ) {
            DorBackground {
                SearchScreen()
            }
        }
    }
}

@Composable
fun DorBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(color = 0xFF0D47A1),
                        Color(color = 0xFF1976D2),
                        Color(color = 0xFF42A5F5)
                    )
                )
            )
    ) {
        content()
    }
}
