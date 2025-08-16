package com.example.demo.user

// 공용 확장 함수
import org.openapitools.jackson.nullable.JsonNullable

import org.mapstruct.*
import org.springframework.stereotype.Component

inline fun <T> JsonNullable<T>.ifPresent(apply: (T?) -> Unit) {
    if (isPresent) apply(this.orElse(null))
}

@Mapper(componentModel = "spring")
abstract class UserMapper {

    /**
     * 부분 업데이트: DTO에서 isPresent인 필드만 엔티티에 반영
     */
    @BeanMapping(ignoreByDefault = true)
    abstract fun partialUpdate(@MappingTarget user: User, dto: UserUpdateDto)

    /**
     * JsonNullable 처리는 @AfterMapping에서 일괄 적용
     */
    @AfterMapping
    protected fun after(@MappingTarget user: User, dto: UserUpdateDto) {
        dto.name.ifPresent { it?.let { v -> user.name = v } } // name은 null 불가 → null이면 무시(또는 검증 오류 처리)
        dto.email.ifPresent { user.email = it }
        dto.school.ifPresent { user.school = it }
        dto.schoolStatus.ifPresent { user.schoolStatus = it }
        dto.major.ifPresent { user.major = it }
        dto.grade.ifPresent { user.grade = it }
        dto.isGradePublic.ifPresent { user.isGradePublic = it ?: false }
        dto.residenceCity.ifPresent { user.residenceCity = it }
        dto.residenceCountry.ifPresent { user.residenceCountry = it }
        dto.isResidencePublic.ifPresent { user.isResidencePublic = it ?: false }
        dto.bio.ifPresent { user.bio = it }
        dto.vision.ifPresent { user.vision = it }
    }
}

