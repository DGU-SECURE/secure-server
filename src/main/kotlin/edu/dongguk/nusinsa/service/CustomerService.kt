package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Order
import edu.dongguk.nusinsa.domain.OrderItem
import edu.dongguk.nusinsa.domain.Point
import edu.dongguk.nusinsa.domain.type.OrderState
import edu.dongguk.nusinsa.domain.type.PointGrade
import edu.dongguk.nusinsa.dto.PageInfoDto
import edu.dongguk.nusinsa.dto.request.OrderItemsDto
import edu.dongguk.nusinsa.dto.response.*
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.*
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.Direction.*
import org.springframework.data.domain.Sort.by
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
    private val pointRepository: PointRepository,
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

        val point = Point((orderItemsDto.totalPrice* PointGrade.BRONZE.getRatio()).toInt(),
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

    /**
     * 구매 내역 목록 조회
     */
    fun getOrderLogs(id: Long, page: Int, size: Int): OrderLogsDto {
        if (!customerRepository.existsById(id))
            throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)

        val pageable: Pageable = PageRequest.of(page, size, Sort.by(DESC, "createdAt"))
        val orders = orderRepository.findByCustomerId(id, pageable)
        val orderLogDtos = orders.map { OrderLogDto.of(it) }

        return OrderLogsDto(orderLogDtos.toList(),
            PageInfoDto(page, size, orders.totalElements.toInt(), orders.totalPages))
    }

    /**
     * 구매 내역 상세 조회
     */
    fun getDetailOrderLog(id: Long, orderId: Long): DetailOrderLogDto {
        if (!customerRepository.existsById(id))
            throw GlobalException(ErrorCode.NOT_FOUND_CUSTOMER)
        val order = orderRepository.findByIdOrNull(orderId)?: throw GlobalException(ErrorCode.NOT_FOUND_ORDER)

        return DetailOrderLogDto.of(order)
    }
}