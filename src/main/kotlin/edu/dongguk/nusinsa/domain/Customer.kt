package edu.dongguk.nusinsa.domain

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

    fun getPointBalance() = this.pointBalance
    fun getBalance() = this.balance
    fun isEnoughPoint(point: Int) = this.pointBalance >= point
    fun isEnoughBalance(balance: Long) = this.balance >= balance
    fun usePointAndBalance(savePoint: Int, usePoint: Int, balance: Long) {
        this.pointBalance += savePoint - usePoint
        this.balance += usePoint - balance
    }

    fun refund(savePoint: Int, usePoint: Int, balance: Long) {
        this.pointBalance -= savePoint - usePoint
        this.balance -= usePoint - balance
    }
}