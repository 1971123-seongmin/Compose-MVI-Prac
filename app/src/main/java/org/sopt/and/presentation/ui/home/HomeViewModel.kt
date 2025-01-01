package org.sopt.and.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.sopt.and.domain.usecase.local.GetLocalHomeImageUseCase
import org.sopt.and.utils.LoadState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocalHomeImageUseCase: GetLocalHomeImageUseCase
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeUiState, HomeSideEffect>
        get() = container(initialState = HomeUiState())

    fun fetchHomeData() = intent {
        reduce {
            state.copy(
                loadState = LoadState.Loading
            )
        }
        getLocalHomeImageUseCase()
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