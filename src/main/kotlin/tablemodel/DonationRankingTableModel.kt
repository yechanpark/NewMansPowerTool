package tablemodel

import container.FrameContainer
import dto.DonateInfo
import ui.frame.DonationRankingCardJFrame
import java.util.*
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

    override fun addRow(rowData: Vector<*>) {
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

        // 카드 내용 업데이트
        updateCards()
    }

    fun updateCards() {
        if (this.dataVector.size != FrameContainer.donationRankingCardJFrames.size) return

        val donateInfoFromModel = this.dataVector.map { DonateInfo(it[0].toString(), it[1].toString().toLong()) }
        val sortedDonateInfoList = when (FrameContainer.donationRankingJFrame!!.table.rowSorter?.sortKeys?.first()?.sortOrder) {
            SortOrder.ASCENDING -> donateInfoFromModel.sortedBy { it.amount }
            else -> donateInfoFromModel.sortedByDescending { it.amount }
        }

        sortedDonateInfoList.forEachIndexed { index, it ->
            FrameContainer.donationRankingCardJFrames[index].apply {
                donateInfo = it
                update()
            }
        }
    }

}