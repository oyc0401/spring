package com.example.demo.content

import org.springframework.data.jpa.repository.JpaRepository

interface ContentRepository : JpaRepository<Content, Int>
