package org.sopt.and.presentation.ui.home

import org.sopt.and.presentation.data.HomeData
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiSideEffect
import org.sopt.and.presentation.utils.UiState
import org.sopt.and.utils.LoadState

class HomeContract {
    // 현재 상태의 View (화면 UI를 구성하기 위해 필요한 데이터들이 모여 있음)
    data class HomeUiState(
        val loadState: LoadState = LoadState.Idle,
        val homeData: HomeData = HomeData(),
    ) : UiState

    // 사이드 이펙트 - UI와 직접적으로 관련되지 않은 외부 작업
    // ex - 토스트 메시지 (단발성 이벤트)
    // 네비게이션 이동 코드 추가 예정
    sealed interface HomeSideEffect : UiSideEffect {
        data class ShowToastMsg(val message: String,) : HomeSideEffect
    }

    // Intent - 이벤트 정의, UI에서 발생하는 이벤트 정의 (사용자의 액션)
    // 아래의 경우 홈화면 데이터를 갱신하는 이벤트
    sealed class HomeEvent : UiEvent {
        data class FetchHomeData(
            val loadState: LoadState,
            val newHomeData: HomeData,
        ) : HomeEvent()
        data class OnBannerItemClick(val successMsg: String,): HomeEvent()
    }

}