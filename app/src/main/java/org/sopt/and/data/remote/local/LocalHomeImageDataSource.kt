package org.sopt.and.data.remote.local

import org.sopt.and.presentation.data.HomeData

interface LocalHomeImageDataSource {
    suspend fun getHomeData(): Result<HomeData>
}