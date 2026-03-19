package com.fdb.what_to_eat.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)
