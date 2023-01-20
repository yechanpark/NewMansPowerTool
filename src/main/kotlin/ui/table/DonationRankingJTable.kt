package ui.table

import tablemodel.DonationRankingTableModel
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.DefaultRowSorter
import javax.swing.JTable
import javax.swing.ListSelectionModel
import javax.swing.event.RowSorterEvent

class DonationRankingJTable(
    private val donationRankingTableModel: DonationRankingTableModel
): JTable(donationRankingTableModel) {
    init {
        initListeners()
        initRowSorter()

        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
    }

    /**
     * 리스너 설정
     * */
    private fun initListeners() {
        addKeyListener(object: KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode != KeyEvent.VK_DELETE) return
                removeSelectedRows()
            }
        })
    }

    /**
     * rowSorter 설정
     * */
    private fun initRowSorter() {
        autoCreateRowSorter = true
        rowSorter.let {
            // 0번 컬럼은 정렬기능 disable
            (it as DefaultRowSorter<*, *>).setSortable(0, false)

            // 1번 컬럼 2번 토글 -> 기본적으로 내림차순 정렬로 되어있음
            it.toggleSortOrder(1)
            it.toggleSortOrder(1)
            it.sort()

            it.addRowSorterListener { e ->
                if (e.type == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
                    donationRankingTableModel.updateCards()
                }
            }
        }
    }

    /**
     * 선택된 항목 모두 삭제
     * */
    fun removeSelectedRows() {
        this.selectedRows.sortedDescending().forEach {
            donationRankingTableModel.removeRow(convertRowIndexToModel(it))
        }
        clearSelection()
        donationRankingTableModel.fireTableDataChanged()
    }
}