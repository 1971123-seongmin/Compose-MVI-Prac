package org.sopt.and.data.remote.repository

import org.sopt.and.data.remote.datasource.local.LocalHomeImageDataSource
import org.sopt.and.domain.repository.LocalHomeImageRepository
import org.sopt.and.presentation.data.HomeData
import javax.inject.Inject

class LocalHomeImageRepositoryImpl @Inject constructor(
    private val localHomeImageDataSource: LocalHomeImageDataSource
) : LocalHomeImageRepository {
    override suspend fun getHomeData(): Result<HomeData> {
        return localHomeImageDataSource.getHomeData()
    }

}