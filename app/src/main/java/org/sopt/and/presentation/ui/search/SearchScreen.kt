package org.sopt.and.presentation.ui.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.WavveBlue
import org.sopt.and.core.util.clearBackStackNavigateToHome

@Composable
fun SearchScreen(
    navHostController: NavHostController
) {
    BackHandler { // 시스템 뒤로 가기 이벤트 설정
        navHostController.clearBackStackNavigateToHome()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.bottom_search_title),
            style = TextStyle(fontSize = 16.sp, color = WavveBlue)
        )

    }

}