package listener.button

import container.FrameContainer
import ui.frame.DonationRankingConfigJFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class DonationRankingConfigButtonListener: ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val donationRankingConfigJFrame = FrameContainer.donationRankingConfigJFrame ?: DonationRankingConfigJFrame()

        donationRankingConfigJFrame.apply {
            isVisible = !isVisible

            if (isVisible) {
                val donationRankingJFrame = FrameContainer.donationRankingJFrame!!
                setLocation(donationRankingJFrame.x + donationRankingJFrame.width, donationRankingJFrame.y)
            }
        }
    }
}