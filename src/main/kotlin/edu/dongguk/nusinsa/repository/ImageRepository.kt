package edu.dongguk.nusinsa.repository

import edu.dongguk.nusinsa.domain.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long> {
}