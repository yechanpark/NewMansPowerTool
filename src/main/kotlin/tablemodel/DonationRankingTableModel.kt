package tablemodel

import dto.DonateInfo
import javax.swing.table.DefaultTableModel

class DonationRankingTableModel: DefaultTableModel() {

    init {
        initHeaders()
    }

    private fun initHeaders() {
        DonateInfo.TABLE_HEADERS.forEach {
            addColumn(it)
        }
    }

    /**
     * 정렬하기 위한 컬럼의 인덱스의 타입을 설정
     * */
    override fun getColumnClass(columnIndex: Int): Class<*> {
        return when (columnIndex) {
            1 -> Integer::class.java
            else -> super.getColumnClass(columnIndex)
        }
    }
}