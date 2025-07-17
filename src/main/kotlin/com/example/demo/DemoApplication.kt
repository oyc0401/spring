package com.example.demo

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@OpenAPIDefinition(
    info = Info(title = "인잡 API 문서", version = "v1"),
    servers = [
        Server(url = "https://test.rokafmail.kr", description = "배포 도메인"),
        Server(url = "http://localhost:8080", description = "개발용 도메인")
    ]
)

@EnableJpaAuditing
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
