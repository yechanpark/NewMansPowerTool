package ui.frame

import container.FrameContainer
import listener.window.WindowCloseInvisibleListener
import ui.panel.DonationRankingConfigPreviewJPanel
import java.awt.*
import javax.swing.*

class DonationRankingConfigJFrame(title: String = TITLE): AbstractJFrame(title) {
    companion object {
        private const val WIDTH = 500
        private const val HEIGHT = 500
        private const val INPUT_COMPONENTS_PADDING_VALUE = 5
        private val DEFAULT_INSET = Insets(
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE,
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE
        )
        const val TITLE = "도네 랭킹 설정"
    }

    init {
        initProperties()
        initListeners()
        initPanel()

        FrameContainer.donationRankingConfigJFrame = this
    }

    /**
     * MainJFrame 설정
     * */
    private fun initProperties() {
        layout = GridBagLayout()
        isResizable = false
        minimumSize = Dimension(WIDTH, HEIGHT)

        val donationRankingJFrame = FrameContainer.donationRankingJFrame!!
        setLocation(donationRankingJFrame.let { it.x + it.width }, donationRankingJFrame.y)
    }

    /**
     * 리스너 설정
     * */
    private fun initListeners() {
        addWindowListener(WindowCloseInvisibleListener(this))
    }

    private fun initPanel() {
        initInputPanel()
        initPreviewPanel()
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

        /* 버튼 설정 */
        createButtons(inputPanel)
    }

    /**
     * 라벨 생성
     * */
    private fun createLabels(inputPanel: JPanel) {
        val gbc = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 0
            this.gridy = 0
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }
        inputPanel.add(JLabel("도네 랭킹 카드 미리보기 (점선은 보이지 않음)"), gbc)
    }

    /**
     * 버튼 생성
     * */
    private fun createButtons(inputPanel: JPanel) {
        val gbc = GridBagConstraints().apply {
            this.fill = GridBagConstraints.HORIZONTAL
            this.gridx = 0
            this.gridy = 1
            this.weightx = .05
            this.weighty = .05
            this.insets = DEFAULT_INSET
        }

        val confirmButton = JButton("저장")
        confirmButton.addActionListener { println("설정 저장") }
        inputPanel.add(confirmButton, gbc)
    }

    private fun initPreviewPanel() {

        val dataPanel = DonationRankingConfigPreviewJPanel().apply {
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