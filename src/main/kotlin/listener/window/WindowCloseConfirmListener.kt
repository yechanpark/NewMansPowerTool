package listener.window

import ui.frame.AbstractJFrame
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JOptionPane
import kotlin.system.exitProcess

/**
 * 윈도우 닫을 시 확인 창 팝업 리스너
 * */
class WindowCloseConfirmListener: WindowAdapter() {
    override fun windowClosing(we: WindowEvent?) {
        val confirmButtons = arrayOf("Yes", "No")
        val promptResult = JOptionPane.showOptionDialog(
            null,
            "Exit?",
            AbstractJFrame.DEFAULT_TITLE,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            confirmButtons,
            confirmButtons[1]
        )
        if (promptResult == JOptionPane.YES_OPTION) {
            exitProcess(0)
        }
    }
}