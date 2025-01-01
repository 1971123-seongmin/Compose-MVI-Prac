package org.sopt.and.presentation.ui.home

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
import org.sopt.and.R
import org.sopt.and.utils.LoadState
import org.sopt.and.utils.showToastMessage
import timber.log.Timber

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // 이 Composable이 처음 생성될 때 로드되는 함수
    // 홈 데이터 로드
    LaunchedEffect(Unit) {
        homeViewModel.fetchHomeData()
    }

    // 사이드 이펙트
    LaunchedEffect(homeViewModel.sideEffect, lifecycleOwner) {
        homeViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { homeSideEffect ->
                when (homeSideEffect) {
                    is HomeContract.HomeSideEffect.ShowToastMsg -> {
                        coroutineScope.launch {
                            context.showToastMessage(homeSideEffect.message)
                        }
                    }
                }
            }
    }

    LaunchedEffect(uiState.loadState, lifecycleOwner) {
        //if(uiState.loadState == LoadState.Success) { }
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
                    homeViewModel.setEvent(
                        HomeContract.HomeEvent.OnBannerItemClick(
                            successMsg = context.getString(R.string.home_success)
                        )
                    )
                }
            )
        }
        LoadState.Error -> {
            Timber.tag("홈스크린").d("Home Error")
        }
    }
}