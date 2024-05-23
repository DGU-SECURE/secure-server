package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Order
import edu.dongguk.nusinsa.domain.OrderItem
import edu.dongguk.nusinsa.domain.Point
import edu.dongguk.nusinsa.domain.type.PointGrade
import edu.dongguk.nusinsa.dto.response.PointBalanceDto
import edu.dongguk.nusinsa.dto.request.OrderItemsDto
import edu.dongguk.nusinsa.dto.response.OrderedItemDto
import edu.dongguk.nusinsa.dto.response.OrderedItemsDto
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.*
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val itemRepository: ItemRepository,
    private val pointRepository: PointRepository,
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) {
    /**
     * 잔액 및 포인트 조회
     */
    fun getPointAndBalance(id: Long): PointBalanceDto {
        val customer = customerRepository
            .findByIdOrNull(id)?: throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)
        return PointBalanceDto.of(customer)
    }

    /**
     * 상품 구매
     */
    fun orderItems(id: Long, orderItemsDto: OrderItemsDto): OrderedItemsDto {
        val customer = customerRepository.findByIdOrNull(id)?: throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)
        if (orderItemsDto.items.isEmpty())
            throw GlobalException(ErrorCode.NOT_CHOOSE_ITEMS)
        if (!customer.isEnoughPoint(orderItemsDto.point))
            throw GlobalException(ErrorCode.NOT_ENOUGH_POINT_BALANCE_ERROR)
        if (!customer.isEnoughBalance(orderItemsDto.totalPrice))
            throw GlobalException(ErrorCode.NOT_ENOUGH_BALANCE_ERROR)

        val point = Point((orderItemsDto.totalPrice*PointGrade.BRONZE.getRatio()).toInt(),
            orderItemsDto.point, customer)
        val order = Order(orderItemsDto.totalPrice, orderItemsDto.paymentType, customer, point)
        val items = itemRepository.findByIds(orderItemsDto.items.map { it.itemId })
        val orderItems = (items zip orderItemsDto.items).map { OrderItem(it.second.itemQuantity, order, it.first) }
        orderItems.forEach { it.getItem().orderItem(it.getCounts()) }
        customer.updatePointAndBalance(point.getUseAmount() - point.getSaveAmount(),
            orderItemsDto.totalPrice)

        pointRepository.save(point)
        orderRepository.save(order)
        orderItemRepository.saveAll(orderItems)
        return OrderedItemsDto.of(order, point, orderItems.map { OrderedItemDto.of(it) })
    }
}