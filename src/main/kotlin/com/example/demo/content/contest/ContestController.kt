package com.example.demo.content.contest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "content.contest-controller", description = "공모전 콘텐츠 API")
@RequestMapping("/contests")
class ContestController(
    private val contestService: ContestService
) {

    @GetMapping("/")
    @Operation(summary = "모든 공모전 조회", description = "모든 공모전을 조회합니다.")
    fun getAllContests(): List<Contest> {
        return contestService.getAllContests()
    }

    @GetMapping("/{id}")
    @Operation(summary = "공모전 상세 조회", description = "특정 공모전의 상세 정보를 조회합니다.")
    fun getContest(@PathVariable id: Int): Contest {
        return contestService.getContest(id)
    }

    @PostMapping("/")
    @Operation(summary = "공모전 생성", description = "새로운 공모전을 생성합니다.")
    fun createContest(@RequestBody request: ContestService.ContestRequest): Contest {
        return contestService.createContest(request)
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "공모전 수정", description = "공모전 메타데이터를 부분 업데이트합니다.")
    fun updateContest(
        @PathVariable id: Int,
        @RequestBody dto: ContestUpdateDto
    ): Contest {
        return contestService.updateContest(id, dto)
    }
}
