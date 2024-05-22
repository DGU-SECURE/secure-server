package edu.dongguk.nusinsa.domain

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * 포인트 내역
 */
@Entity
@Table(name = "points")
class Point(
    /**
     * 적립 금액
     */
    private val saveAmount: Int,

    /**
     * 사용 금액
     */
    private val useAmount: Int,

    /**
     * 포인트 적립 시기
     */
    private val createdAt: LocalDateTime = LocalDateTime.now(),

    /**
     * 포인트가 적립되는 소비자
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private val customer: Customer,
) {
    /**
     * 포인트 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}