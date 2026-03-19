package com.fdb.what_to_eat.domain

import jakarta.persistence.*

@Entity
@Table(name = "meal_type")
class MealType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String
)
