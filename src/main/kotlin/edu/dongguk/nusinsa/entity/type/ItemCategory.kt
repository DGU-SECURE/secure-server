package edu.dongguk.nusinsa.entity.type

/**
 * 의류 카테고리
 */
enum class ItemCategory(
    private val itemCategory: String
) {
    TOP("상의"),
    BOTTOM("하의"),
    SHOES("신발"),
    ACCESSORIES("액세서리"),
    HAT("모자")
}