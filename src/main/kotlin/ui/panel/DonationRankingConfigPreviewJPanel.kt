package ui.panel

import constants.ImagePath
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * 도네이션 랭킹 설정 프리뷰 패널
 * */
class DonationRankingConfigPreviewJPanel(
    private val backGroundImage: ImageIcon = ImageIcon(DonationRankingConfigPreviewJPanel::class.java.getResource(ImagePath.RANK_CARD.path)),
): JPanel() {

    /**
     * 배경화면 설정
     * */
    override fun paintComponent(g: Graphics?) {
        g?.drawImage(backGroundImage.image, insets.left, insets.top, width - insets.left, height - insets.top, null)
        isOpaque = false
        super.paintComponent(g)
    }


}