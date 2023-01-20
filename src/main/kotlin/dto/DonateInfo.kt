package dto

import java.util.Vector

/**
 * 도네 정보
 *
 * @property nickname 닉네임
 * @property amount   도네이션 금액
 * */
data class DonateInfo(
    var nickname: String,
    val amount: Long
) {
    companion object {
        val TABLE_HEADERS = listOf("닉네임", "금액")
        fun convertFrom(vector: Vector<*>): DonateInfo {
            return DonateInfo(vector[0].toString(), vector[1].toString().toLong())
        }
    }
    fun convertToStringArray(): Array<Any> {
        return arrayOf(nickname, amount)
    }
}
