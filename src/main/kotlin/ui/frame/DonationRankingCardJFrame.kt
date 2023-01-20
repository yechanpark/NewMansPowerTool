package ui.frame

import dto.DonateInfo
import java.awt.*
import java.awt.event.WindowEvent
import javax.swing.*


class DonationRankingCardJFrame(var donateInfo: DonateInfo? = null): JFrame() {
    companion object {
        private const val WIDTH = 50
        private const val HEIGHT = 50
    }

    private var panel: JPanel? = null
    private var nickNameJLabel = JLabel()
    private var amountJLabel = JLabel()

    init {
        initProperties()
        initPanel()
    }

    /**
     * MainJFrame 설정
     * */
    private fun initProperties() {
        layout = GridLayout()
        isResizable = false
        minimumSize = Dimension(WIDTH, HEIGHT)
        type = Type.UTILITY
        isUndecorated = true
    }

    private fun initPanel() {
        panel = JPanel().apply {
            layout = BorderLayout()
        }
        panel?.add(nickNameJLabel)
        panel?.add(amountJLabel)

        add(panel)
    }

    fun update() {
        nickNameJLabel.text = donateInfo?.nickname
        amountJLabel.text = donateInfo?.amount.toString()
    }

    fun remove() {
        this.dispatchEvent(WindowEvent(this, WindowEvent.WINDOW_CLOSING))
    }
}