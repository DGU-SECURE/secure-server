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
    private var orderState: OrderState = OrderState.DEPOSIT_CHECK

    /**
     * 주문 시점
     */
    private val createdAt: LocalDateTime = LocalDateTime.now()

    /**
     * 배송 완료 시점
     */
    private var arrivedAt: LocalDateTime? = null

    /**
     * 주문 상품 목록
     */
    @OneToMany(mappedBy = "order")
    private lateinit var orderItems: MutableList<OrderItem>

    fun getId() = this.id
    fun getTotalPrice() = this.totalPrice
    fun getOrderState() = this.orderState
    fun getCreatedAt() = this.createdAt
    fun getPaymentType() = this.paymentType
    fun getOrderItems() = this.orderItems
    fun getPoint() = this.point
    fun getCustomer() = this.customer
    fun updateOrderState() {
        this.orderState = when(orderState) {
            OrderState.DEPOSIT_CHECK -> OrderState.DEPOSIT_COMPLETE
            OrderState.DEPOSIT_COMPLETE -> OrderState.RELEASE_PROCESS
            OrderState.RELEASE_PROCESS -> OrderState.RELEASE_COMPLETE
            OrderState.REFUND_COMPLETE -> OrderState.DELIVERY
            OrderState.DELIVERY -> OrderState.DELIVERY_COMPLETE
            OrderState.REFUND_PROCESS -> OrderState.REFUND_COMPLETE
            else -> orderState
        }
    }
    fun refundOrder() {
        if (arrivedAt != null && LocalDateTime.now().isAfter(this.arrivedAt!!.plusDays(7)))
            this.orderState = OrderState.REFUND_NOT_AVAILABLE
        else
            this.orderState = OrderState.REFUND_PROCESS
    }
}