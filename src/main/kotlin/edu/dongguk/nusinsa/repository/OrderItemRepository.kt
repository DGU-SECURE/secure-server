package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItem, Long> {
}