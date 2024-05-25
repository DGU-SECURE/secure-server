package edu.dongguk.nusinsa.domain

import edu.dongguk.nusinsa.domain.type.ItemCategory
import jakarta.persistence.*

/**
 * 상품
 */
@Entity
@Table(name = "items")
class Item(
    /**
     * 상품명
     */
    private var name: String,

    /**
     * 상품 가격
     */
    private var price: Long,

    /**
     * 재고
     */
    private var stock: Int = 100,

    /**
     * 상품 카테고리
     * 상의, 하의, 신발, 액세서리, 모자
     */
    @Enumerated(EnumType.STRING)
    private var category: ItemCategory,

    /**
     * 상품 이미지
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private val image: Image,

    /**
     * 상품 판매처
     */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private val store: Store,
) {
    /**
     * 상품 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    fun getId() = this.id
    fun getName() = this.name
    fun getImage() = this.image
    fun getPrice() = this.price
    fun orderItem(stock: Int) {
        this.stock -= stock
    }
    fun restockItem(stock: Int) {
        this.stock += stock
    }
}