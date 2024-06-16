package edu.dongguk.nusinsa.security

import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.CustomerRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import kotlin.collections.ArrayList

@Service
class CustomUserDetailService (
    private val customerRepository: CustomerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val roles: MutableCollection<SimpleGrantedAuthority> = ArrayList()
        roles.add(SimpleGrantedAuthority("ROLE_USER"))

        val user: UserLoginForm = customerRepository.findByIdAndRefreshToken(username.toLong())
            ?: throw GlobalException(ErrorCode.ACCESS_DENIED_ERROR)

        return CustomUserDetail.create(user)
    }

    fun loadUserByUsernameAndUserRole(username: String, role: String): CustomUserDetail {
        val roles: MutableCollection<SimpleGrantedAuthority> = ArrayList()
        roles.add(SimpleGrantedAuthority("ROLE_$role"))

        val user: UserLoginForm = customerRepository.findByIdAndRefreshToken(username.toLong())
                    ?: throw GlobalException(ErrorCode.ACCESS_DENIED_ERROR)

        return CustomUserDetail.create(user)
    }
}

