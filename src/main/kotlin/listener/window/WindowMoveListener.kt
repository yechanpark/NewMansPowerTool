package listener.window

import container.FrameContainer
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

/**
 * Frame 움직일 시 따라 움직이는 리스너
 * */
class WindowMoveListener: ComponentAdapter() {
    override fun componentMoved(e: ComponentEvent) {
        FrameContainer.donationRankingJFrame?.setLocation(e.component.x + e.component.width, e.component.y)
    }
}