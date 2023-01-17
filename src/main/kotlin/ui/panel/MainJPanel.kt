package ui.panel

import listener.button.DonationRankingButtonListener
import ui.frame.DonationRankingJFrame
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

/**
 * 메인 패널
 * */
class MainJPanel(private val backGroundImage: ImageIcon): JPanel() {
    companion object {
        private const val BUTTON_ABOUT = "ABOUT"
    }

    init {
        initButtons()
    }

    /**
     * 배경화면 설정
     * */
    override fun paintComponent(g: Graphics?) {
        g?.drawImage(backGroundImage.image, insets.left, insets.top, width - insets.left, height - insets.top, null)
        isOpaque = false
        super.paintComponent(g)
    }

    /**
     * 버튼 설정
     * */
    private fun initButtons() {
        val donationRankingButton = JButton(DonationRankingJFrame.TITLE)
        donationRankingButton.addActionListener(DonationRankingButtonListener())
        add(donationRankingButton)

        val aboutButton = JButton(BUTTON_ABOUT)
        add(aboutButton)
    }

}