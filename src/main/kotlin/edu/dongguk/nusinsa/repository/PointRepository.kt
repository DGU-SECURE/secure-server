package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Point
import org.springframework.data.jpa.repository.JpaRepository

interface PointRepository : JpaRepository<Point, Long> {
}