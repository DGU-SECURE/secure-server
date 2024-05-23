package edu.dongguk.nusinsa.domain

import edu.dongguk.nusinsa.domain.type.OrderState
import edu.dongguk.nusinsa.domain.type.PaymentType
import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * 주문
 */
@Entity
@Table(name = "orders")
class Order(

    /**
     * 총 주문 금액
     */
    private val totalPrice: Long,

    /**
     * 결제 수단
     */
    @Enumerated(EnumType.STRING)
    private val paymentType: PaymentType = PaymentType.CARD,

    /**
     * 주문을 한 소비자
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private val customer: Customer,

    @OneToOne
    @JoinColumn(name = "point_id")
    private val point: Point
) {
    /**
     * 주문 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    /**
     * 주문 상태
     */
    @Enumerated(EnumType.STRING)
    private val orderState: OrderState = OrderState.DEPOSIT_CHECK

    /**
     * 주문 시점
     */
    private val createdAt: LocalDateTime = LocalDateTime.now()

    fun getTotalPrice() = this.totalPrice
    fun getOrderState() = this.orderState
    fun getCreatedAt() = this.createdAt
    fun getPaymentType() = this.paymentType
}