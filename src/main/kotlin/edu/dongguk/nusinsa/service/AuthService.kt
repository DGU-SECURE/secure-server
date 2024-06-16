package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Customer
import edu.dongguk.nusinsa.domain.type.Role
import edu.dongguk.nusinsa.dto.request.JoinDto
import edu.dongguk.nusinsa.dto.request.LoginDto
import edu.dongguk.nusinsa.dto.response.LoginResultDto
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.CustomerRepository
import edu.dongguk.nusinsa.security.JwtProvider
import edu.dongguk.nusinsa.security.JwtToken
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
@Transactional
class AuthService(
    private val customerRepository: CustomerRepository,
    private val bcryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun join(joinDto: JoinDto): Boolean {
        customerRepository.findByLoginId(
                    joinDto.loginId
                )?.let { throw GlobalException(ErrorCode.DUPLICATION_CUSTOMER) }

                customerRepository.save(
                    Customer(
                        loginId = joinDto.loginId,
                        password = bcryptPasswordEncoder.encode(joinDto.password),
                        name = joinDto.name,
                        address = joinDto.address
                    )
                )
        return true
    }

    fun login(loginDto: LoginDto): LoginResultDto {
        val loginCustomer =
            customerRepository.findByLoginId(loginDto.loginId) ?: throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)
        val jwtToken: JwtToken = jwtProvider.createTotalToken(loginCustomer.getId()!!, loginCustomer.getRole())
        loginCustomer.setLogin(jwtToken.refreshToken)

        return LoginResultDto(
            name = loginCustomer.getName(),
            access_token = jwtToken.accessToken,
            refresh_token = jwtToken.refreshToken,
            address = loginCustomer.getAddress()
        )
    }
}