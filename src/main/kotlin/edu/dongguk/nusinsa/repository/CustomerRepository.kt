package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Customer
import edu.dongguk.nusinsa.security.UserLoginForm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun existsByLoginId(loginId: String): Boolean
    fun findByLoginId(loginId: String): Customer?

    @Query("SELECT c.id AS id, c.role AS role FROM Customer c WHERE c.id = :customerId AND c.isLogin = true AND c.refreshToken is not null")
    fun findByIdAndRefreshToken(@Param("customerId") customerId: Long): UserLoginForm?
}