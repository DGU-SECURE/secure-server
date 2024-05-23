package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.response.PointBalanceDto
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.CustomerRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class CustomerService(
    private val customerRepository: CustomerRepository,
) {

    fun getPointAndBalance(id: Long): PointBalanceDto {
        val customer = customerRepository
            .findByIdOrNull(id)?: throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)
        return PointBalanceDto.of(customer)
    }
}