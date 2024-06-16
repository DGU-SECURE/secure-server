package edu.dongguk.nusinsa.domain

import edu.dongguk.nusinsa.domain.type.Role
import jakarta.persistence.*

/**
 * 소비자
 */
@Entity
@Table(name = "customers")
class Customer(
    /**
     * 이름
     */
    private var name: String,

    /**
     * 주소
     */
    private var address: String,

    /**
     * 로그인 아이디
     */
    private val loginId: String,

    /**
     * 비밀번호
     */
    private val password: String,

    /**
     * 사용자 유형
     */
    @Enumerated(EnumType.STRING)
    private val role: Role = Role.CUSTOMER
) {
    /**
     * 소비자 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    /**
     * 잔고
     */
    private var balance: Long = 100000000

    /**
     * 포인트잔고
     */
    private var pointBalance: Int = 0

    /**
     * 리프레쉬 토큰
     */
    private var refreshToken: String? = null

    /**
     * 로그인 여부
     */
    private var isLogin: Boolean = false

    fun getPointBalance() = this.pointBalance

    fun getBalance() = this.balance
    fun getRole() = this.role
    fun getName() = this.name
    fun getPassword() = this.password
    fun getLoginId() = this.loginId
    fun getId() = this.id
    fun getAddress() = this.address

    fun isEnoughPoint(point: Int) = this.pointBalance >= point

    fun isEnoughBalance(balance: Long) = this.balance >= balance

    fun setLogin(refreshToken: String) {
        this.refreshToken = refreshToken
        this.isLogin = true
    }

    fun usePointAndBalance(savePoint: Int, usePoint: Int, balance: Long) {
        this.pointBalance += savePoint - usePoint
        this.balance += usePoint - balance
    }

    fun refund(savePoint: Int, usePoint: Int, balance: Long) {
        this.pointBalance -= savePoint - usePoint
        this.balance -= usePoint - balance
    }
}