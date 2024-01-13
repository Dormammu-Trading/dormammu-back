package com.dormammu.tradingsimulation.user

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String? = null,

    @Column
    var email: String = "",

    @Column
    @Enumerated(EnumType.STRING)
    var vendor: Vender = Vender.EMAIL,

    @Column
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.NOT_CHECK,

    @Column
    var brith: LocalDate,

    @Column
    val createdAt: LocalDateTime? = null,

    @Column
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
)
{
    enum class Vender(
        val vender: String
    ){
        EMAIL("이메일"),
        GOOGLE("구글"),
        NAVER("네이버"),
        KAKAO("카카오"),
        APPLE("애플")
    }
    enum class Gender(
        val gender: String
    ){
        NOT_CHECK("미선택"),
        MAN("남자"),
        WOMAN("여자")
    }
}