package edu.dongguk.nusinsa.security

import edu.dongguk.nusinsa.annotation.UserId
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserIdArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserId::class.java) && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Long {
        val userId = webRequest.getAttribute("USER_ID", RequestAttributes.SCOPE_REQUEST)
            ?: throw GlobalException(ErrorCode.NOT_FOUND_ERROR)
        return userId.toString().toLong()
    }

}

