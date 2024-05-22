package edu.dongguk.nusinsa.exception

class GlobalException(
    private val errorCode: ErrorCode
) : RuntimeException() {
    fun getErrorCode() = errorCode
}