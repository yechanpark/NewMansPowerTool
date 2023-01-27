package ui.panel

import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * 도네이션 랭킹 설정 프리뷰 패널
 * */
class DonationRankingConfigPreviewJPanel(
    private val backGroundImage: ImageIcon = ImageIcon(this::class.java.getResource(BACKGROUND_IMG_PATH)),
): JPanel() {

    companion object {
        private const val BACKGROUND_IMG_PATH = "/img/rankcard.jpg"
    }

    /**
     * 배경화면 설정
     * */
    override fun paintComponent(g: Graphics?) {
        g?.drawImage(backGroundImage.image, insets.left, insets.top, width - insets.left, height - insets.top, null)
        isOpaque = false
        super.paintComponent(g)
    }


}