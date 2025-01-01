package org.sopt.and.presentation.ui.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.and.R
import org.sopt.and.utils.LoadState
import org.sopt.and.utils.showToastMessage
import timber.log.Timber

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // SideEffect 처리
    homeViewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is HomeSideEffect.ShowToastMsg -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
        }
    }

    // 이 Composable이 처음 생성될 때 로드되는 함수
    // 홈 데이터 로드
    LaunchedEffect(Unit) {
        homeViewModel.fetchHomeData()
    }

    when(uiState.loadState) {
        LoadState.Idle -> {
            Timber.tag("홈스크린").d("Home Idle")
        }
        LoadState.Loading -> {
            Timber.tag("홈스크린").d("Home Loading")
        }
        LoadState.Success -> {
            HomeScreen(
                homeData = uiState.homeData,
                onBannerItemClick = {
                    homeViewModel.onBannerItemClick(
                        successMsg = context.getString(R.string.home_success)
                    )
                }
            )
        }
        LoadState.Error -> {
            Timber.tag("홈스크린").d("Home Error")
        }
    }
}