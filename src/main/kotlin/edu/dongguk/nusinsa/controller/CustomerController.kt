package edu.dongguk.nusinsa.controller

import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.dto.response.PointBalanceDto
import edu.dongguk.nusinsa.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @GetMapping("/payment")
    fun getPointAndBalance(): ResponseDto<PointBalanceDto> {
        val id: Long = 1
        return ResponseDto.success(customerService.getPointAndBalance(id))
    }
}