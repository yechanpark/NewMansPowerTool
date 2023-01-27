package listener.button

import container.FrameContainer
import ui.frame.DonationRankingConfigJFrame
import ui.frame.DonationRankingJFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class DonationRankingButtonListener: ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        if ( !toggleDonationRankingJFrameVisible() )
            setDonationRankingConfigJFrameInvisible()
    }

    private fun toggleDonationRankingJFrameVisible(): Boolean {
        val donationRankingJFrame = FrameContainer.donationRankingJFrame ?: DonationRankingJFrame()

        donationRankingJFrame.apply {
            isVisible = !isVisible

            if (isVisible) {
                val mainJFrame = FrameContainer.mainJFrame
                setLocation(mainJFrame.x + mainJFrame.width, mainJFrame.y)
            }
        }

        return donationRankingJFrame.isVisible
    }

    private fun setDonationRankingConfigJFrameInvisible() {
        val donationRankingConfigJFrame = FrameContainer.donationRankingConfigJFrame ?: DonationRankingConfigJFrame()

        donationRankingConfigJFrame.apply {
            isVisible = false

            val donationRankingJFrame = FrameContainer.donationRankingJFrame!!
            setLocation(donationRankingJFrame.x + donationRankingJFrame.width, donationRankingJFrame.y)
        }
    }
}