package listener.button

import container.FrameContainer
import ui.frame.DonationRankingJFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class DonationRankingButtonListener: ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val donationRankingJFrame = FrameContainer.donationRankingJFrame ?: DonationRankingJFrame()

        donationRankingJFrame.apply {
            isVisible = !isVisible

            if (isVisible) {
                val mainJFrame = FrameContainer.mainJFrame
                setLocation(mainJFrame.x + mainJFrame.width, mainJFrame.y)
            }
        }
    }
}