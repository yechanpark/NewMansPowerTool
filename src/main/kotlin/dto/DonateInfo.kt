package dto

/**
 * 도네 정보
 *
 * @property nickname 닉네임
 * @property amount   도네이션 금액
 * */
data class DonateInfo(
    val nickname: String,
    val amount: Long
)
