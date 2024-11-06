package org.sopt.and.enum

enum class Movie(val description: String) {
    NEW_CLASSIC("뉴클래식"),
    DRAMA("드라마"),
    MOVIE("영화"),
    ENTERTAINMENT("예능"),
    ANIMATION("애니"),
    FOREIGN_SERIES("해외시리즈");

    fun getGenre(): String {
        return description
    }
}