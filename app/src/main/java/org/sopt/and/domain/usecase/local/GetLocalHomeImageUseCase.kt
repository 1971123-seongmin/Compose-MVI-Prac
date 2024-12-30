package org.sopt.and.domain.usecase.local

import org.sopt.and.domain.repository.local.LocalHomeImageRepository
import org.sopt.and.presentation.data.HomeData

class GetLocalHomeImageUseCase(
    private val repository: LocalHomeImageRepository
) {
    suspend operator fun invoke() : Result<HomeData> {
        return runCatching {
            repository.getHomeData()
        }.fold(
            onSuccess = { data -> data },
            onFailure = { exception -> Result.failure(exception)  }
        )
    }
}