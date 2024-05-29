package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Store
import edu.dongguk.nusinsa.dto.response.StoreDto
import edu.dongguk.nusinsa.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository
) {
    fun findStores(name: String?): List<StoreDto> {
        var storeList: List<Store> = when {
            name == null -> {
                storeRepository.findAll()
            }

            else -> storeRepository.findByNameContaining(name)
        }
        return storeList.map(Store::toDto)

    }
}