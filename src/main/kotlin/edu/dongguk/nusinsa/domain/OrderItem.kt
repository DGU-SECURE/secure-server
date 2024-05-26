package edu.dongguk.nusinsa.domain

import jakarta.persistence.*
import java.util.UUID

/**
 * 주문 상품
 */
@Entity
@Table(name = "order_items")
class OrderItem(
    /**
     * 주문 수량
     */
    private val counts: Int,

    /**
     * 상품이 속해있는 주문 내역
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private val order: Order,

    /**
     * 주문한 상품
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private val item: Item,
) {
    /**
     * 주문 상품 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    /**
     * 주문 번호
     */
    private val orderCode: String = UUID.randomUUID().toString()

    fun getId() = this.id
    fun getOrderCode() = this.orderCode
    fun getCounts() = this.counts
    fun getOrder() = this.order
    fun getItem() = this.item
}