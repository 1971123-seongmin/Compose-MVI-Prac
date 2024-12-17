package org.sopt.and.utils

const val MAX_PWD_SIZE = 8

fun String.isValidLength(): Boolean {
    return this.length <= MAX_PWD_SIZE
}