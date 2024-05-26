package edu.dongguk.nusinsa.domain

import edu.dongguk.nusinsa.dto.ItemDto
import edu.dongguk.nusinsa.dto.response.StoreDto
import jakarta.persistence.*

/**
 * 매장
 */
@Entity
@Table(name = "stores")
class Store(
    /**
     * 매장명
     */
    private var name: String,

    /**
     * 매장 주소
     */
    private var address: String,

    /**
     * 매장 전화번호
     */
    private var callNumber: String,

    /**
     * 매장 이미지
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private val image: Image
) {
    /**
     * 매장 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
    fun todto() : StoreDto = StoreDto(
        storeId = this.id!!,
        storeName = this.name
    )
    fun getId() = this.id
    fun getName(): String = name
}