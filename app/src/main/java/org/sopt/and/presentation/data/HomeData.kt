package org.sopt.and.presentation.data

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.presentation.type.MovieType

data class HomeData(
    val categories: PersistentList<MovieType> = persistentListOf(),
    val bannerList: PersistentList<Int> = persistentListOf(),
    val posterList1: PersistentList<Int> = persistentListOf(),
    val posterList2: PersistentList<Int> = persistentListOf(),
    val posterList3: PersistentList<Int> = persistentListOf(),
    val posterList4: PersistentList<Int> = persistentListOf(),
    val top20List: PersistentList<Int> = persistentListOf(),
)
