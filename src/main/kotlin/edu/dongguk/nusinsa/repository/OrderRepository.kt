package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByCustomerId(id: Long, pageable: Pageable): Page<Order>
}