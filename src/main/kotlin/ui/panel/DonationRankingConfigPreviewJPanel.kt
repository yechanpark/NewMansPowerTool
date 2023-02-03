package ui.panel

import container.FrameContainer
import ui.label.PreviewLabel
import java.awt.*
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * 도네이션 랭킹 설정 프리뷰 패널
 * */
class DonationRankingConfigPreviewJPanel(private val previewImage: ImageIcon): JPanel() {

    init {
        initProperties()
        createLabels()
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

    private fun createLabels() {
        val outerPanel = this

        val nickNameLabel = PreviewLabel(
            text = "닉네임",
            initPointX = 270, // previewImage.iconHeight * 1/8,
            initPointY = 30, // previewImage.iconHeight * 1/3,
            labelWidth = 600,
            labelHeight = 200
        ).apply {
            addMouseAdapter(outerPanel)
            fitTextSizeOnLabel()
        }
        add(nickNameLabel)

        val amountLabel = PreviewLabel(
            text = "1000000",
            initPointX = 100, // previewImage.iconHeight * 6/8,
            initPointY = 280, // previewImage.iconHeight * 2/3,
            labelWidth = 700,
            labelHeight = 200

        ).apply {
            addMouseAdapter(outerPanel)
            fitTextSizeOnLabel()
        }
        add(amountLabel)
    }

}