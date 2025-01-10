package org.sopt.and.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.and.domain.repository.LocalHomeImageRepository
import org.sopt.and.utils.LoadState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localHomeImageRepository: LocalHomeImageRepository
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeUiState, HomeSideEffect> by lazy {
        container(initialState = HomeUiState())
    }

    fun fetchHomeData() = intent {
        reduce {
            state.copy(
                loadState = LoadState.Loading
            )
        }
        localHomeImageRepository.getHomeData()
            .onSuccess { homeData ->
                reduce {
                    state.copy(
                        loadState = LoadState.Success,
                        homeData = homeData
                    )
                }
            }
            .onFailure {
                reduce {
                    state.copy(
                        loadState = LoadState.Error,
                    )
                }
            }
    }

    fun onBannerItemClick(successMsg: String) = intent {
        postSideEffect(HomeSideEffect.ShowToastMsg(message = successMsg))
    }

}