package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Item
import edu.dongguk.nusinsa.domain.Store
import edu.dongguk.nusinsa.dto.ItemDto
import edu.dongguk.nusinsa.dto.response.StoreDto
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService (
    private val storeRepository: StoreRepository
) {
    fun findStores(name : String?): List<StoreDto> {
        var storeList : List<Store> = when {
            name == null -> {
                storeRepository.findAll()
            }
            else -> storeRepository.findByNameContaining(name)
        }
        if (storeList.isEmpty()) throw GlobalException(ErrorCode.NOT_FOUND_STORE)
        return storeList.map(Store::todto)

    }
}