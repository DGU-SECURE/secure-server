package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {
    fun findByNameContaining(name: String): List<Store>
}