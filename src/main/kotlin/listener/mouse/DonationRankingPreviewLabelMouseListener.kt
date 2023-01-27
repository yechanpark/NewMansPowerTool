package listener.mouse

import java.awt.Cursor
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent
import javax.swing.JLabel
import javax.swing.JPanel

class DonationRankingPreviewLabelMouseListener(private val label: JLabel, private val panel: JPanel): MouseAdapter() {
    /**
     * 버튼 뗀 순간
     * */
    override fun mouseClicked(e: MouseEvent) {
        super.mouseClicked(e)
    }

    /**
     * 마우스 드래그할 때
     * */
    override fun mouseDragged(e: MouseEvent) {

        label.apply {
            // TODO: 화면 밖으로 나가지 않도록 계산 필요
            location = Point(e.point)
        }

        println(label.location)
    }

    /**
     * 마우스가 영역 내로 들어갔을 때
     * */
    override fun mouseEntered(e: MouseEvent) {
        label.apply {
            cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)
        }
    }

    /**
     * 마우스가 영역 밖으로 나갔을 때
     * */
    override fun mouseExited(e: MouseEvent) {
        super.mouseExited(e)
    }

    /**
     * 버튼 누른 순간
     * */
    override fun mousePressed(e: MouseEvent) {
        super.mousePressed(e)
    }

    /**
     * 버튼 뗀 순간
     * */
    override fun mouseReleased(e: MouseEvent) {
        super.mouseReleased(e)
    }

    /**
     * 영역 상에서 마우스 움직일 때
     * */
    override fun mouseMoved(e: MouseEvent) {
        label.apply {
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        }
    }

    /**
     * 마우스 휠 움직일 때
     * */
    override fun mouseWheelMoved(e: MouseWheelEvent) {
        if (e.wheelRotation > 0)
            println("휠 내림")
        else
            println("휠 올림")
    }

}