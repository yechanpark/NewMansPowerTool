package tablemodel

import container.FrameContainer
import dto.DonateInfo
import ui.frame.DonationRankingCardJFrame
import java.util.*
import javax.swing.DefaultRowSorter
import javax.swing.RowSorter
import javax.swing.SortOrder
import javax.swing.table.DefaultTableModel
import kotlin.math.absoluteValue

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

    /**
     * 행 삭제 - 여러 행을 모두 삭제 후 정렬하기 위해 여기서 [fireTableDataChanged]를 호출하지 않음
     * */
    override fun removeRow(row: Int) {
        println("removeRow: $row")
        super.removeRow(row)
    }

    /**
     * 행 추가
     * */
    override fun addRow(rowData: Vector<*>) {
        println("addRow: $rowData")
        super.addRow(rowData)
        fireTableDataChanged()
    }

    override fun fireTableDataChanged() {
        super.fireTableDataChanged()

        val countOfModel = this.dataVector.size
        val countOfCards = FrameContainer.donationRankingCardJFrames.size
        val gapOfModelAndCards = (countOfModel - countOfCards).absoluteValue

        // 추가된 경우 -> Card 추가
        if (countOfModel > countOfCards) {
            for (i in 1..gapOfModelAndCards) {
                FrameContainer.donationRankingCardJFrames.add(DonationRankingCardJFrame())
            }
        }

        // 삭제된 경우 -> Card 제거
        else if (countOfModel < countOfCards) {
            for (i in 1 .. gapOfModelAndCards) {
                FrameContainer.donationRankingCardJFrames.removeLast().remove()
            }
        }

        updateCards()
    }

    /**
     * 행 수정 시 호출
     * */
    override fun fireTableCellUpdated(row: Int, column: Int) {
        super.fireTableCellUpdated(row, column)
        updateCards()
    }

    /**
     * 카드 내용 업데이트
     * */
    fun updateCards() {
        if (this.dataVector.size != FrameContainer.donationRankingCardJFrames.size) return

        val rowSorter = (FrameContainer.donationRankingJFrame!!.table.rowSorter!! as DefaultRowSorter<*, *>)

        val donateInfoFromModel = this.dataVector.map { DonateInfo(it[0].toString(), it[1].toString().toLong()) }
        val sortedDonateInfoList = when (rowSorter.sortKeys?.first()?.sortOrder) {
            SortOrder.ASCENDING -> donateInfoFromModel.sortedBy { it.amount }
            else -> donateInfoFromModel.sortedByDescending { it.amount }
        }

        // JTable 정렬
        rowSorter.sort()

        // JFrame 내용 업데이트 (정렬 및 데이터 적용)
        sortedDonateInfoList.forEachIndexed { index, donateInfo ->
            FrameContainer.donationRankingCardJFrames[index].apply {
                this.donateInfo = donateInfo
                update(index)
            }
        }
    }

}