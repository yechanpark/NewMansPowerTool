package ui.frame

import ui.table.DonationRankingJTable
import container.FrameContainer
import listener.textfield.InputRakingAddButtonListener
import listener.textfield.InputRankingJTextFieldKeyAdapter
import listener.window.WindowCloseInvisibleListener
import tablemodel.DonationRankingTableModel
import java.awt.*
import javax.swing.*

class DonationRankingJFrame(title: String = TITLE): AbstractJFrame(title) {
    companion object {
        private const val WIDTH = 500
        private const val HEIGHT = 500
        const val TITLE = "도네 랭킹"
    }

    private var panel: JPanel? = null
    private var tableModel = DonationRankingTableModel()
    private lateinit var table: DonationRankingJTable

    init {
        initProperties()
        initListeners()

        initPanel()

        FrameContainer.donationRankingJFrame = this
    }

    /**
     * MainJFrame 설정
     * */
    private fun initProperties() {
        layout = GridLayout()
        isResizable = false
        minimumSize = Dimension(WIDTH, HEIGHT)

        val mainJFrame = FrameContainer.mainJFrame
        setLocation(mainJFrame.let { it.x + it.width }, mainJFrame.y)
    }

    /**
     * 리스너 설정
     * */
    private fun initListeners() {
        addWindowListener(WindowCloseInvisibleListener(this))
    }

    private fun initPanel() {
        panel = JPanel().apply {
            layout = BorderLayout()
        }
        add(panel)

        initTable()
        initInputPanel()
    }

    /**
     * input panel 설정
     * */
    private fun initInputPanel() {
        val inputPanel = JPanel().apply {
            layout = FlowLayout()
        }
        panel?.add(inputPanel, BorderLayout.NORTH)

        val nickNameLabel = JLabel("닉네임")
        val amountLabel = JLabel("금액")
        val (nickNameTextField, amountTextField) = createTextFields()

        inputPanel.add(nickNameLabel)
        inputPanel.add(nickNameTextField)
        inputPanel.add(amountLabel)
        inputPanel.add(amountTextField)
        inputPanel.add(createAddButton(nickNameTextField, amountTextField))
        inputPanel.add(createRemoveButton())
    }

    /**
     * 텍스트 필드 생성
     * */
    private fun createTextFields(): Pair<JTextField, JTextField> {
        val nickNameTextField = JTextField(5)
        val amountTextField = JTextField(5)

        val actionListHandler = InputRankingJTextFieldKeyAdapter(nickNameTextField, amountTextField, tableModel)
        nickNameTextField.addKeyListener(actionListHandler)
        amountTextField.addKeyListener(actionListHandler)

        return Pair(nickNameTextField, amountTextField)
    }

    /**
     * 추가 버튼
     * */
    private fun createAddButton(nickNameTextField: JTextField, amountTextField: JTextField): JButton {
        val addListButton = JButton("추가(Enter)")
        addListButton.addActionListener(InputRakingAddButtonListener(nickNameTextField, amountTextField, tableModel))
        return addListButton
    }

    /**
     * 리스트 삭제 버튼
     * */
    private fun createRemoveButton(): JButton {
        val removeButton = JButton("삭제(Del)")
        removeButton.addActionListener { table.removeSelectedRows() }
        return removeButton
    }

    private fun initTable() {
        table = DonationRankingJTable(tableModel)

        JScrollPane(table).apply {
            panel?.add(this, BorderLayout.CENTER)
        }
    }
}