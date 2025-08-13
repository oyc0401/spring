package com.example.demo.content.contest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "contest-controller", description = "콘테스트 관리 API")
@RequestMapping("/contests")
class ContestController(
    private val contestService: ContestService
) {

    @GetMapping("/")
    @Operation(summary = "모든 콘테스트 조회", description = "모든 콘테스트를 조회합니다.")
    fun getAllContests(): List<Contest> {
        return contestService.getAllContests()
    }

    @GetMapping("/{id}")
    @Operation(summary = "콘테스트 상세 조회", description = "특정 콘테스트의 상세 정보를 조회합니다.")
    fun getContest(@PathVariable id: Int): Contest {
        return contestService.getContest(id)
    }
//
//    @GetMapping("/active")
//    @Operation(summary = "진행중인 콘테스트 조회", description = "현재 진행중인 콘테스트들을 조회합니다.")
//    fun getActiveContests(): List<Contest> {
//        return contestService.getActiveContests()
//    }
//
//    @GetMapping("/upcoming")
//    @Operation(summary = "예정된 콘테스트 조회", description = "아직 시작하지 않은 콘테스트들을 조회합니다.")
//    fun getUpcomingContests(): List<Contest> {
//        return contestService.getUpcomingContests()
//    }
//
//    @GetMapping("/ended")
//    @Operation(summary = "종료된 콘테스트 조회", description = "이미 종료된 콘테스트들을 조회합니다.")
//    fun getEndedContests(): List<Contest> {
//        return contestService.getEndedContests()
//    }
//
//    @GetMapping("/search")
//    @Operation(summary = "콘테스트 검색", description = "키워드로 콘테스트를 검색합니다.")
//    fun searchContests(@RequestParam keyword: String): List<Contest> {
//        return contestService.searchContests(keyword)
//    }
//
//    @GetMapping("/writer/{writer}")
//    @Operation(summary = "작성자별 콘테스트 조회", description = "특정 작성자의 콘테스트들을 조회합니다.")
//    fun getContestsByWriter(@PathVariable writer: String): List<Contest> {
//        return contestService.getContestsByWriter(writer)
//    }
//
//    @GetMapping("/company/{companyType}")
//    @Operation(summary = "기업형태별 콘테스트 조회", description = "특정 기업형태의 콘테스트들을 조회합니다.")
//    fun getContestsByCompanyType(@PathVariable companyType: String): List<Contest> {
//        return contestService.getContestsByCompanyType(companyType)
//    }

    @PostMapping("/")
    @Operation(summary = "콘테스트 생성", description = "새로운 콘테스트를 생성합니다.")
    fun createContest(@RequestBody request: ContestService.ContestRequest): Contest {
        return contestService.createContest(request)
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "콘테스트 수정", description = "콘테스트 메타데이터를 부분 업데이트합니다.")
    fun updateContest(
        @PathVariable id: Int,
        @RequestBody dto: ContestUpdateDto
    ): Contest {
        return contestService.updateContest(id, dto)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "콘테스트 삭제", description = "콘테스트를 삭제합니다. (Content도 함께 삭제됩니다)")
    fun deleteContest(@PathVariable id: Int): ResponseEntity<Unit> {
        contestService.deleteContest(id)
        return ResponseEntity.ok().build()
    }
}
