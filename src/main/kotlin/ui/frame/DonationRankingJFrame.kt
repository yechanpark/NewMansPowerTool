package ui.frame

import container.FrameContainer
import listener.textfield.InputRakingAddButtonListener
import listener.textfield.InputRankingJTextFieldKeyAdapter
import listener.window.WindowCloseInvisibleListener
import tablemodel.DonationRankingTableModel
import ui.table.DonationRankingJTable
import java.awt.*
import javax.swing.*

class DonationRankingJFrame(title: String = TITLE): AbstractJFrame(title) {
    companion object {
        private const val WIDTH = 500
        private const val HEIGHT = 500
        const val TITLE = "도네 랭킹"
    }

    private var tableModel = DonationRankingTableModel()
    lateinit var table: DonationRankingJTable

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
        layout = GridBagLayout()
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
        initInputPanel()
        initDataPanel()
    }

    /**
     * input panel 설정
     * */
    private fun initInputPanel() {
        val inputPanel = JPanel().apply {
            border = BorderFactory.createLineBorder(Color.black);
        }

        val gbc = GridBagConstraints().apply {
            this.fill = GridBagConstraints.BOTH
            this.gridx = 0
            this.gridy = 0
            this.weightx = .1
            this.weighty = .1
        }

        add(inputPanel, gbc)

        val nickNameLabel = JLabel("닉네임")
        val amountLabel = JLabel("금액")
        val (nickNameTextField, amountTextField) = createTextFields()

        inputPanel.add(nickNameLabel)
        inputPanel.add(nickNameTextField)
        inputPanel.add(amountLabel)
        inputPanel.add(amountTextField)
        inputPanel.add(createAddButton(nickNameTextField, amountTextField))
        inputPanel.add(createRemoveButton())
        inputPanel.add(createConfigButton())
        inputPanel.add(createPreviewButton())
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

    /**
     * 설정 버튼
     * */
    private fun createConfigButton(): JButton {
        val configButton = JButton("설정")
        configButton.addActionListener { println("설정버튼") }
        return configButton
    }

    /**
     * 설정 버튼
     * */
    private fun createPreviewButton(): JButton {
        val previewButton = JButton("미리보기")
        previewButton.addActionListener { println("미리보기버튼") }
        return previewButton
    }

    private fun initDataPanel() {
        table = DonationRankingJTable(tableModel)

        val dataPanel = JPanel().apply {
            this.add(JScrollPane(table))
            border = BorderFactory.createLineBorder(Color.black);
        }

        val gbc = GridBagConstraints().apply {
            this.fill = GridBagConstraints.BOTH
            this.gridx = 0
            this.gridy = 1
            this.weightx = .1
            this.weighty = .9
        }

        add(dataPanel, gbc)
    }
}