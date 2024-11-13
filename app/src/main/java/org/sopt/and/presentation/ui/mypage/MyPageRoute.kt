package org.sopt.and.presentation.ui.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.viewmodel.MyPageViewModel

@Composable
fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val userHobby by viewModel.userHobby.collectAsStateWithLifecycle()

    MyPageScreen(userHobby)
}