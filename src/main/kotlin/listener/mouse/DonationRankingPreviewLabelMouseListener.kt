package listener.mouse

import ui.label.PreviewLabel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent
import javax.swing.JPanel

class DonationRankingPreviewLabelMouseListener(
    private val label: PreviewLabel, private val panel: JPanel
): MouseAdapter() {

    private fun translatePoint(e: MouseEvent) {
        e.translatePoint(e.component.location.x, e.component.location.y)
    }
    /**
     * 버튼 뗀 순간
     * */
    override fun mouseClicked(e: MouseEvent) {
        translatePoint(e)
        super.mouseClicked(e)
    }

    /**
     * 마우스 드래그할 때
     * TODO: JLabel 텍스트 위치 설정 기능 추가
     * TODO: 화면 밖으로 나가지 않도록 계산 필요
     * TODO: Label 시작 위치 보정 - 마우스 위치와 라벨 현재 위치를 계산해 보정하여 움직임 (드래그 시 Label 시작 위치가 마우스 클릭 위치로 오지 않게)
     * */
    override fun mouseDragged(e: MouseEvent) {
        return
        translatePoint(e)

        label.apply {
            val x = if (e.x !in panel.x .. panel.x + panel.width - this.labelWidth) this.x else e.x
            val y = if (e.y !in panel.x .. panel.y + panel.height - this.labelHeight) this.y else e.y

            setBounds(x, y, labelWidth, labelHeight)
        }
    }

    /**
     * 마우스가 영역 내로 들어갔을 때
     * */
    override fun mouseEntered(e: MouseEvent) {
        translatePoint(e)
        super.mouseEntered(e)
    }

    /**
     * 마우스가 영역 밖으로 나갔을 때
     * */
    override fun mouseExited(e: MouseEvent) {
        translatePoint(e)
        super.mouseExited(e)
    }

    /**
     * 버튼 누른 순간
     * */
    override fun mousePressed(e: MouseEvent) {
        translatePoint(e)
        super.mousePressed(e)
    }

    /**
     * 버튼 뗀 순간
     * */
    override fun mouseReleased(e: MouseEvent) {
        translatePoint(e)
        super.mouseReleased(e)
    }

    /**
     * 영역 상에서 마우스 움직일 때
     * */
    override fun mouseMoved(e: MouseEvent) {
        translatePoint(e)
        super.mouseMoved(e)
    }

    /**
     * 마우스 휠 움직일 때
     * */
    override fun mouseWheelMoved(e: MouseWheelEvent) {
        translatePoint(e)
        if (e.wheelRotation > 0)
            println("휠 내림")
        else
            println("휠 올림")
    }

}