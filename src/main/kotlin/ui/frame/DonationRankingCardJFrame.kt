package ui.frame

import constants.ImagePath
import dto.DonateInfo
import ui.panel.DonationRankingCardJPanel
import java.awt.*
import java.awt.event.WindowEvent
import javax.swing.*

class DonationRankingCardJFrame(
    var donateInfo: DonateInfo? = null,
    private val backGroundImage: ImageIcon = ImageIcon(DonationRankingCardJFrame::class.java.getResource(ImagePath.RANK_CARD.path)),
    private val textFont: Font = Font("Serif", Font.PLAIN, 14)
): JFrame() {

    /* Panels */
    private var panel: JPanel? = null

    /* Labels */
    private var nickNameJLabel = getDefaultFontTextLabel()
    private var amountJLabel = getDefaultFontTextLabel()

    /* Image Properties */
    private var imageWidth = backGroundImage.iconWidth
    private var imageHeight = backGroundImage.iconHeight

    init {
        initProperties()
        initPanel()
    }

    private fun getDefaultFontTextLabel(): JLabel {
        return JLabel().apply { font = textFont }
    }

    /**
     * MainJFrame 설정
     * */
    private fun initProperties() {
        layout = GridLayout()
        isResizable = false
        minimumSize = Dimension(imageWidth, imageHeight)
        type = Type.UTILITY
        isUndecorated = true
        isVisible = true
    }

    private fun initPanel() {
        panel = DonationRankingCardJPanel(backGroundImage)
        panel?.add(nickNameJLabel)
        panel?.add(amountJLabel)

        add(panel)
    }

    fun update(index: Int) {
        setLocation(index * imageWidth, 0)
        nickNameJLabel.text = donateInfo?.nickname
        amountJLabel.text = donateInfo?.amount.toString()
    }

    fun remove() {
        this.dispatchEvent(WindowEvent(this, WindowEvent.WINDOW_CLOSING))
    }

    fun updateNickNameLabel(
        newFont: Font = textFont, newPointX: Int, newPointY: Int, newWidth: Int, newHeight: Int, color: Color?
    ) {
        nickNameJLabel.apply {
            font = newFont
            setBounds(newPointX, newPointY, newWidth, newHeight)
            color?.let {
                this.foreground = it
            }
        }
    }

    fun updateAmountLabel(
        newFont: Font = textFont, newPointX: Int, newPointY: Int, newWidth: Int, newHeight: Int, color: Color?
    ) {
        amountJLabel.apply {
            font = newFont
            setBounds(newPointX, newPointY, newWidth, newHeight)
            color?.let {
                this.foreground = it
            }
        }
    }
}