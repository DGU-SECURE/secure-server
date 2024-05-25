package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Item
import edu.dongguk.nusinsa.domain.Store
import edu.dongguk.nusinsa.domain.type.ItemCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ItemRepository : JpaRepository<Item, Long> {

    // Page 객체는 nullable 하지 않음, 그치만 데이터가 없다면 비어있는 객체를 반환함
    override fun findAll(paging: Pageable): Page<Item>
    fun findAllByStoreId(store: Long, pageable: Pageable): Page<Item>
    fun findAllByStoreIdAndCategory(store: Long, category: ItemCategory, pageable: Pageable): Page<Item>
    fun findAllByStoreIdAndNameContaining(store: Long, name: String, pageable: Pageable): Page<Item>
    fun findAllByStoreIdAndCategoryAndNameContaining(store: Long, category: ItemCategory, name: String,paging: Pageable): Page<Item>
    fun findAllByCategory(category: ItemCategory,paging: Pageable): Page<Item>
    fun findAllByNameContaining(name: String, paging: Pageable): Page<Item>
    fun findAllByCategoryAndNameContaining(category: ItemCategory, name: String,paging: Pageable): Page<Item>
}