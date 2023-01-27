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
        private const val INPUT_COMPONENTS_PADDING_VALUE = 5
        private val DEFAULT_INSET = Insets(
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE,
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE
        )
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
        /* 인풋 패널 설정 */
        val inputPanel = JPanel().apply {
            border = BorderFactory.createLineBorder(Color.black);
            layout = GridBagLayout()
        }

        val panelGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.BOTH
            this.gridx = 0
            this.gridy = 0
            this.weightx = .1
            this.weighty = .1
        }

        add(inputPanel, panelGBC)

        /* 라벨 설정 */
        createLabels(inputPanel)

        /* 텍스트필드 설정 */
        val (nickNameTextField, amountTextField) = createTextFields(inputPanel)

        /* 추가 버튼 설정 */
        createButtons(inputPanel, nickNameTextField, amountTextField)
    }

    /**
     * 라벨 생성
     * */
    private fun createLabels(inputPanel: JPanel) {
        val nickNameLabelGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 0
            this.gridy = 0
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(JLabel("닉네임"), nickNameLabelGBC)

        val amountLabelGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 0
            this.gridy = 1
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(JLabel("금액"), amountLabelGBC)
    }


    /**
     * 텍스트 필드 생성
     * */
    private fun createTextFields(inputPanel: JPanel): Pair<JTextField, JTextField> {
        val nickNameTextField = JTextField(5)
        val amountTextField = JTextField(5)

        val actionListHandler = InputRankingJTextFieldKeyAdapter(nickNameTextField, amountTextField, tableModel)
        nickNameTextField.addKeyListener(actionListHandler)
        amountTextField.addKeyListener(actionListHandler)

        val nickNameTextFieldGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 1
            this.gridy = 0
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(nickNameTextField, nickNameTextFieldGBC)
        val amountTextFieldGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 1
            this.gridy = 1
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(amountTextField, amountTextFieldGBC)

        return Pair(nickNameTextField, amountTextField)
    }

    /**
     * 버튼 생성
     * */
    private fun createButtons(inputPanel: JPanel, nickNameTextField: JTextField, amountTextField: JTextField) {
        val addButtonGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 2
            this.gridy = 0
            this.weightx = .1
            this.weighty = .1
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(createAddButton(nickNameTextField, amountTextField), addButtonGBC)

        /* 삭제 버튼 설정 */
        val removeButtonGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 3
            this.gridy = 0
            this.weightx = .1
            this.weighty = .1
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(createRemoveButton(), removeButtonGBC)

        /* 설정 버튼 설정 */
        val configButtonGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 4
            this.gridy = 0
            this.weightx = .1
            this.weighty = .1
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(createConfigButton(), configButtonGBC)
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

    private fun initDataPanel() {
        table = DonationRankingJTable(tableModel)

        val dataPanel = JPanel().apply {
            this.add(JScrollPane(table))
            border = BorderFactory.createLineBorder(Color.black);
        }

        val panelGBC = GridBagConstraints().apply {
            this.fill = GridBagConstraints.BOTH
            this.gridx = 0
            this.gridy = 1
            this.weightx = .1
            this.weighty = .9
        }

        add(dataPanel, panelGBC)
    }
}