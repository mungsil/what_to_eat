package com.fdb.what_to_eat.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "visitor")
class Visitor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(36)")
    val cookieId: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var lastVisitAt: LocalDateTime = LocalDateTime.now()
)
