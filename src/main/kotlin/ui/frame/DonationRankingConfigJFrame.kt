package ui.frame

import constants.ImagePath
import container.FrameContainer
import listener.window.WindowCloseInvisibleListener
import ui.panel.DonationRankingConfigPreviewJPanel
import java.awt.*
import javax.swing.*

class DonationRankingConfigJFrame(
    title: String = TITLE,
    private val previewImage: ImageIcon = ImageIcon(this::class.java.getResource(ImagePath.RANK_CARD.path)),
): AbstractJFrame(title) {

    lateinit var inputPanel: JPanel

    companion object {
        private const val INPUT_COMPONENTS_PADDING_VALUE = 5
        private val DEFAULT_INSET = Insets(
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE,
            INPUT_COMPONENTS_PADDING_VALUE, INPUT_COMPONENTS_PADDING_VALUE
        )
        const val TITLE = "도네 랭킹 설정"
    }

    init {
        FrameContainer.donationRankingConfigJFrame = this
        initPanel()
        initProperties()
        initListeners()
    }

    /**
     * MainJFrame 설정
     * */
    private fun initProperties() {
        layout = null
        isResizable = false

        val imageWidth = previewImage.iconWidth
        val imageHeight = previewImage.iconHeight
        size = Dimension(
            imageWidth + INPUT_COMPONENTS_PADDING_VALUE * 4,
            imageHeight + inputPanel.height + INPUT_COMPONENTS_PADDING_VALUE * 10
        )

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
        inputPanel = JPanel().apply {
            border = BorderFactory.createLineBorder(Color.black);
            layout = GridBagLayout()
            size = Dimension(previewImage.iconWidth, 100)
            location = Point(insets.left, insets.top)
        }

        add(inputPanel)

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
        inputPanel.add(JLabel("도네 랭킹 카드 미리보기"), gbc)
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
        val dataPanel = DonationRankingConfigPreviewJPanel(previewImage)
        add(dataPanel)
    }
}