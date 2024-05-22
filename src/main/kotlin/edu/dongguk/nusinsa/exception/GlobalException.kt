package edu.dongguk.nusinsa.exception

/**
 * 공통 에러
 */
class GlobalException(
    private val errorCode: ErrorCode
) : RuntimeException() {
    fun getErrorCode() = errorCode
}