package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ItemRepository : JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item AS i WHERE i.id in :ids")
    fun findByIds(ids: List<Long>): List<Item>
}