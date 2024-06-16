package edu.dongguk.nusinsa.dto.response

import edu.dongguk.nusinsa.domain.Customer

data class PointBalanceDto(
    val point: Int,
    val balance: Long
) {
    companion object {
        fun of(customer: Customer) =
            PointBalanceDto(
                customer.getPointBalance(),
                customer.getBalance()
            )
    }
}
