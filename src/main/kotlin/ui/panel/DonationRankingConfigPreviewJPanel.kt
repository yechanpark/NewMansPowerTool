package ui.panel

import container.FrameContainer
import ui.label.PreviewLabel
import java.awt.*
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * 도네이션 랭킹 설정 프리뷰 패널
 * */
class DonationRankingConfigPreviewJPanel(private val previewImage: ImageIcon): JPanel() {

    init {
        initProperties()
        createNickNameLabel()
    }

    /**
     * 배경화면 설정
     * */
    override fun paintComponent(g: Graphics?) {
        g?.drawImage(previewImage.image, 0, 0, width, height, null)
        isOpaque = false
        super.paintComponent(g)
    }

    private fun initProperties() {
        layout = null
        border = BorderFactory.createLineBorder(Color.black);
        val imageWidth = previewImage.iconWidth
        val imageHeight = previewImage.iconHeight
        size = Dimension(imageWidth, imageHeight)
        location = Point(insets.left, FrameContainer.donationRankingConfigJFrame!!.inputPanel.height)
    }

    private fun createNickNameLabel() {
        val outerPanel = this

        val nickNameLabel = PreviewLabel("닉네임", previewImage.iconHeight * 1/2, previewImage.iconHeight * 1/3).apply {
            addMouseAdapter(outerPanel)
            fitTestSizeOnLabel()
        }
        add(nickNameLabel)

        val amountLabel = PreviewLabel("1000000", previewImage.iconHeight * 1/2, previewImage.iconHeight * 2/3).apply {
            addMouseAdapter(outerPanel)
            fitTestSizeOnLabel()
        }
        add(amountLabel)
    }

    private fun a() {
        val label = JLabel("아아")
        val labelFont: Font = label.font
        val labelText: String = label.text

        val stringWidth: Int = label.getFontMetrics(labelFont).stringWidth(labelText)
        val componentWidth: Int = label.width

        // Find out how much the font can grow in width.
        val widthRatio = componentWidth.toDouble() / stringWidth.toDouble()

        val newFontSize = (labelFont.size * widthRatio).toInt()
        val componentHeight: Int = label.height

        // Pick a new font size so it will not be larger than the height of label.
        val fontSizeToUse = newFontSize.coerceAtMost(componentHeight)

        // Set the label's font size to the newly determined size.
        label.font = Font(labelFont.name, Font.PLAIN, fontSizeToUse)
    }

}