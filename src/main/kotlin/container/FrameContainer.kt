package container

import ui.frame.DonationRankingCardJFrame
import ui.frame.DonationRankingConfigJFrame
import ui.frame.DonationRankingJFrame
import ui.frame.MainJFrame

object FrameContainer {
    lateinit var mainJFrame: MainJFrame
    var donationRankingJFrame: DonationRankingJFrame? = null
    var donationRankingConfigJFrame: DonationRankingConfigJFrame? = null
    var donationRankingCardJFrames: ArrayList<DonationRankingCardJFrame> = arrayListOf()
}