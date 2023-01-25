package ui.panel

import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * 도네이션 랭킹 카드 패널
 * */
class DonationRankingCardJPanel(private val backGroundImage: ImageIcon): JPanel() {


    /**
     * 배경화면 설정
     * */
    override fun paintComponent(g: Graphics?) {
        g?.drawImage(backGroundImage.image, insets.left, insets.top, width - insets.left, height - insets.top, null)
        isOpaque = false
        super.paintComponent(g)
    }


}