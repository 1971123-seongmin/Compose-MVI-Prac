package org.sopt.and.domain.repository

import org.sopt.and.presentation.data.HomeData

interface LocalHomeImageRepository {
    suspend fun getHomeData(): Result<HomeData>
}