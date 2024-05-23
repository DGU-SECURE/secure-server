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

    /**
     * 포인트 사용 내역
     */
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL])
    private lateinit var points: MutableList<Point>

    fun getPoint() = this.pointBalance
    fun getBalance() = this.balance

    fun isEnoughPoint(point: Int) = this.pointBalance >= point
    fun isEnoughBalance(balance: Long) = this.balance >= balance
    fun updatePointAndBalance(point: Int, balance: Long) {
        this.pointBalance -= point
        this.balance -= balance
    }
}