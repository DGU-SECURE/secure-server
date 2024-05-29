package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}