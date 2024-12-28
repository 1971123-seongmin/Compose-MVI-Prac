package org.sopt.and.presentation.ui.mypage

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.sopt.and.core.util.clearBackStackNavigateToHome
import org.sopt.and.presentation.viewmodel.MyPageViewModel

@Composable
fun MyPageRoute(
    navHostController: NavHostController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    BackHandler { // 시스템 뒤로 가기 이벤트 설정
        navHostController.clearBackStackNavigateToHome()
    }

    val userHobby by viewModel.userHobby.collectAsStateWithLifecycle()

    MyPageScreen(userHobby)
}