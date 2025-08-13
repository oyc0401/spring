package com.example.demo.content.tag

import jakarta.persistence.*

@Entity
@Table(name = "tags")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "tag_name", nullable = false, unique = true)
    val tagName: String = ""
)