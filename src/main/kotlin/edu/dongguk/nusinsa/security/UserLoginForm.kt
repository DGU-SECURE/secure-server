package edu.dongguk.nusinsa.security

import edu.dongguk.nusinsa.domain.type.Role

interface UserLoginForm {
    fun getId(): Long
    fun getRole(): Role
}