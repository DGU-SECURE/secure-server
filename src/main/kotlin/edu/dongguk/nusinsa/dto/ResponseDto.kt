package edu.dongguk.nusinsa.dto

/**
 * 모든 응답은 해당 Dto에 담아서 응답
 * 에러 발생없이 성공적으로 데이터를 추출했으면 success 호출
 * 에러 발생 시 ExceptionHandler에서 fail 호출
 */
class ResponseDto<T> (
    val success: Boolean,
    val data: T? = null,
    val error: ErrorDto? = null
){
    companion object {
        fun <T> success(data: T) = ResponseDto(true, data, null)

        fun fail(error: ErrorDto) = ResponseDto(false, null, error)
    }
}