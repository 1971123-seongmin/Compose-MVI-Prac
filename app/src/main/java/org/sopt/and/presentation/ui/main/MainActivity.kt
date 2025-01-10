package org.sopt.and.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.domain.repository.google.GoogleSignInRepository
import org.sopt.and.presentation.ui.main.navigation.rememberMainNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var googleSignInRepository: GoogleSignInRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator = rememberMainNavigator()
            ANDANDROIDTheme {
                MainScreen(navigator, googleSignInRepository)
            }
        }
    }
}