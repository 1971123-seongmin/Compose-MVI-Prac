package org.sopt.and.data.remote.local

import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.presentation.data.HomeData
import org.sopt.and.presentation.type.MovieType
import javax.inject.Inject

class LocalHomeImageDataSourceImpl @Inject constructor(): LocalHomeImageDataSource {
    private val categories = persistentListOf(
        MovieType.NEW_CLASSIC,
        MovieType.DRAMA,
        MovieType.MOVIE,
        MovieType.ENTERTAINMENT,
        MovieType.ANIMATION,
        MovieType.FOREIGN_SERIES
    )
    private val bannerList = persistentListOf(
        R.drawable.ic_banner_poster_tracer,
        R.drawable.ic_banner_poster_game_of_blood,
        R.drawable.ic_banner_poster_s_f_eight
    )
    private val posterList1 = persistentListOf(
        R.drawable.poster_madmax,
        R.drawable.poster_zootopia,
        R.drawable.poster_ready
    )
    private val posterList2 = persistentListOf(
        R.drawable.poster_parasite,
        R.drawable.poster_suicide_squad,
        R.drawable.poster_attack
    )
    private val posterList3 = persistentListOf(
        R.drawable.poster_chocolate,
        R.drawable.poster_inception,
        R.drawable.poster_sourcecode,
        R.drawable.poster_zootopia
    )

    private val posterList4 = persistentListOf(
        R.drawable.poster_spiderman3,
        R.drawable.poster_zootopia,
        R.drawable.poster_ready,
        R.drawable.ic_banner_poster_tracer
    )

    private val top20List = persistentListOf(
        R.drawable.poster_inception,
        R.drawable.poster_spiderman2,
        R.drawable.poster_spiderman3,
        R.drawable.poster_attack,
        R.drawable.poster_stepup4
    )
    override suspend fun getHomeData(): Result<HomeData> {
        return runCatching {
            HomeData(
                categories = categories,
                bannerList = bannerList,
                posterList1 = posterList1,
                posterList2 = posterList2,
                posterList3 = posterList3,
                posterList4 = posterList4,
                top20List = top20List
            )
        }.fold(
            onSuccess = { data -> Result.success(data) },
            onFailure = { exception ->  Result.failure(exception) }
        )
    }
}