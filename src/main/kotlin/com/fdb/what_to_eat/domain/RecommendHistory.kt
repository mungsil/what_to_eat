package com.fdb.what_to_eat.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "recommend_history")
class RecommendHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id", nullable = false)
    val visitor: Visitor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu,

    val recommendedAt: LocalDate = LocalDate.now(),

    var memo: String? = null
)
