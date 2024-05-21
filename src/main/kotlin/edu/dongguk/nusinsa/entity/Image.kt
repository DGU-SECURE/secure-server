package edu.dongguk.nusinsa.entity

import edu.dongguk.nusinsa.entity.type.Extension
import jakarta.persistence.*

/**
 * 이미지
 */
@Entity
@Table(name = "images")
class Image(
    /**
     * 사용자가 지정한 이미지명
     */
    private val originName: String,

    /**
     * 실제 디렉터리에 저장되는 이미지명
     */
    private val uuidName: String,

    /**
     * 파일 확장자
     */
    @Enumerated(EnumType.STRING)
    private val extension: Extension = Extension.JPG
) {
    /**
     * 이미지 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}