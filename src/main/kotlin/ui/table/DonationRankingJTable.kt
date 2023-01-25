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

            // 정렬 변경 시 카드 업데이트
            it.addRowSorterListener { e ->
                if (e.type == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
                    donationRankingTableModel.updateCards()
                }
            }

            // RowSorter 의 정상적인 정렬을 위해 Integer 타입으로 인식하도록 하였지만, 입력 시 에는 Long 타입으로 인식되어
            // 'class java.lang.Integer cannot be cast to class java.lang.Long' 에러 발생.
            // 따라서 Comparator 를 커스터마이징해 타입을 일치시켜 에러 방지
            // 금액은 최대 Long 사이즈 (9,223,372,036,854,775,807) 까지 입력 가능
            it.setComparator(1, Comparator<Number> { a, b -> a.toLong().compareTo(b.toLong())} )
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