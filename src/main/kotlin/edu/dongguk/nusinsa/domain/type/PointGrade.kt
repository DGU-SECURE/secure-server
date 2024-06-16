package edu.dongguk.nusinsa.domain.type

enum class PointGrade(
    private val ratio: Double
) {
    BRONZE(0.01),
    SILVER(0.02),
    GOLD(0.03),
    PLATINUM(0.04),
    DIAMOND(0.05);

    fun getRatio() = this.ratio
}