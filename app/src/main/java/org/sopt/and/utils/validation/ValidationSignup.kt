package org.sopt.and.utils.validation

const val MIN_PWD_SIZE = 8
const val MAX_PWD_SIZE = 20

// 이메일 검증
fun String.isValidEmail(): Boolean {
    return ValidationPatterns.EMAIL.matcher(this).matches()
}

// 비밀번호 검증
fun String.isValidPassword(): Boolean {

    if (this.length !in MIN_PWD_SIZE..MAX_PWD_SIZE) return false

    val validCheckNum = listOf(
        this.any { it.isUpperCase() },
        this.any { it.isLowerCase() },
        this.any { it.isDigit() },
        this.any { !it.isLetterOrDigit() }
    ).count { it }

    return validCheckNum >= 3
}