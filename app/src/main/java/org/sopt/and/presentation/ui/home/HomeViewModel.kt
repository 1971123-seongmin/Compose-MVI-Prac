package org.sopt.and.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.local.GetLocalHomeImageUseCase
import org.sopt.and.utils.LoadState
import org.sopt.and.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocalHomeImageUseCase: GetLocalHomeImageUseCase
) : BaseViewModel<HomeContract.HomeUiState,
        HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {

    override fun createInitialState(): HomeContract.HomeUiState
            = HomeContract.HomeUiState()

    override fun handleEvent(event: HomeContract.HomeEvent) {
        when(event) {
            is HomeContract.HomeEvent.FetchHomeData ->
                setState {
                    copy(loadState = event.loadState, homeData = event.newHomeData)
                }
            is HomeContract.HomeEvent.OnBannerItemClick ->
                setSideEffect(
                    HomeContract.HomeSideEffect.ShowToastMsg(message = event.successMsg)
                )
        }
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            setEvent(
                HomeContract.HomeEvent.FetchHomeData(
                    loadState = LoadState.Loading,
                    newHomeData = currentState.homeData
                )
            )
            getLocalHomeImageUseCase()
                .onSuccess { homeData ->
                    setEvent(
                        HomeContract.HomeEvent.FetchHomeData(
                            loadState = LoadState.Success,
                            newHomeData = homeData
                        )
                    )
                }
                .onFailure {
                    setEvent(
                        HomeContract.HomeEvent.FetchHomeData(
                            loadState = LoadState.Error,
                            newHomeData = currentState.homeData
                        )
                    )
                }
        }
    }
}