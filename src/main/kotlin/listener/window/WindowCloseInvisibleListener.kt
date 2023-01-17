package listener.window

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

/**
 * 윈도우 닫을 시 invisible 처리 리스너
 * */
class WindowCloseInvisibleListener(private val frame: JFrame): WindowAdapter() {
    override fun windowClosing(we: WindowEvent?) {
        frame.isVisible = false
    }
}