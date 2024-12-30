package org.sopt.and.data.remote.local

import org.sopt.and.domain.repository.local.LocalHomeImageRepository
import org.sopt.and.presentation.data.HomeData
import javax.inject.Inject

class LocalHomeImageRepositoryImpl @Inject constructor(
    private val localHomeImageDataSource: LocalHomeImageDataSource
) : LocalHomeImageRepository {
    override suspend fun getHomeData(): Result<HomeData>
            = localHomeImageDataSource.getHomeData()
}