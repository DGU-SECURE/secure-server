package edu.dongguk.nusinsa.service

import edu.dongguk.nusinsa.domain.Item
import edu.dongguk.nusinsa.domain.Store
import edu.dongguk.nusinsa.domain.type.ItemCategory
import edu.dongguk.nusinsa.dto.ItemDto
import edu.dongguk.nusinsa.dto.ListResponseDto
import edu.dongguk.nusinsa.dto.PageInfoDto
import edu.dongguk.nusinsa.dto.ResponseDto
import edu.dongguk.nusinsa.exception.ErrorCode
import edu.dongguk.nusinsa.exception.GlobalException
import edu.dongguk.nusinsa.repository.ItemRepository
import edu.dongguk.nusinsa.repository.StoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional
class ItemService(
    private val itemRepository: ItemRepository,
    private val storeRepository: StoreRepository
){
    // readonly로 읽기 전용 모드로 제한, DB에서 데이터를 읽기만 하는 서비스기 때문
    // 데이터 일관성 높임
    @Transactional(readOnly = true)
    fun findItems(
        storeId:Long?,
        name:String?,
        category:String?,
        pageNum:Int
    ): ListResponseDto {
        // pagenum - 현재 페이지 번호, pageSize - 고정 item 개수 8개
        val paging: Pageable = PageRequest.of(pageNum, 8)
        val itemList: Page<Item> = when {
            storeId != null -> {
                when {
                    name != null && category != null -> {
                        val itemCategory: ItemCategory = try {
                            ItemCategory.valueOf(category)
                        } catch (e: IllegalArgumentException) {
                            throw GlobalException(ErrorCode.WRONG_CATEGORY_ERROR)
                        }
                        itemRepository.findAllByStoreIdAndCategoryAndNameContaining(storeId, itemCategory, name, paging)
                    }
                    name != null -> {
                        itemRepository.findAllByStoreIdAndNameContaining(storeId, name, paging)
                    }
                    category != null -> {
                        val itemCategory: ItemCategory = try {
                            ItemCategory.valueOf(category)
                        } catch (e: IllegalArgumentException) {
                            throw GlobalException(ErrorCode.WRONG_CATEGORY_ERROR)
                        }
                        itemRepository.findAllByStoreIdAndCategory(storeId, itemCategory, paging)
                    }
                    else -> {
                        itemRepository.findAllByStoreId(storeId, paging)
                    }
                }
            }
            category != null -> {
                val itemCategory: ItemCategory = try {
                    ItemCategory.valueOf(category)
                } catch (e: IllegalArgumentException) {
                    throw GlobalException(ErrorCode.WRONG_CATEGORY_ERROR)
                }
                when {
                    name != null -> {
                        itemRepository.findAllByCategoryAndNameContaining(itemCategory, name, paging)
                    }
                    else -> {
                        itemRepository.findAllByCategory(itemCategory, paging)
                    }
                }
            }
            name != null -> {
                itemRepository.findAllByNameContaining(name, paging)
            }
            else -> {
                itemRepository.findAll(paging)
            }
        }

        // itemList가 비어있는지 확인
        if(itemList.isEmpty) throw GlobalException(ErrorCode.NOT_FOUND_ITEM)

        val itemDtoList:List<ItemDto> = itemList
            .map(Item::todto).toList()

        val pageInfoDto = PageInfoDto(
            page = pageNum,
            size = itemList.size,
            totalPages = itemList.totalPages,
            totalElements = itemList.totalElements.toInt()
        )
        return ListResponseDto(itemDtoList,pageInfoDto)
    }
}