package edu.dongguk.nusinsa.entity

import jakarta.persistence.*

/**
 * 주문 상품
 */
@Entity
@Table(name = "order_items")
class OrderItem(
    /**
     * 주문 수량
     */
    private val count: Int,

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
}