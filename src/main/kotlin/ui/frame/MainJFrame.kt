package ui.frame

import container.FrameContainer
import listener.window.WindowCloseConfirmListener
import listener.window.WindowMoveListener
import ui.panel.MainJPanel
import java.awt.*
import javax.swing.*

class MainJFrame(
    private val backGroundImage: ImageIcon = ImageIcon(this::class.java.getResource(BACKGROUND_IMG_PATH))
): AbstractJFrame() {

    private var panel: JPanel? = null
    companion object {
        const val BACKGROUND_IMG_PATH = "/img/main.jpg"
    }

    init {
        initLayout()
        initListeners()

        initPanel()

        FrameContainer.mainJFrame = this
    }

    /**
     * MainJFrame 설정
     * */
    private fun initLayout() {
        layout = GridLayout()
        isResizable = false
        minimumSize = Dimension(backGroundImage.image.getWidth(null), backGroundImage.image.getHeight(null))
        // 프로그램 실행 시 중앙에서 팝업
        setLocationRelativeTo(null)
    }

    /**
     * Panel 설정
     * */
    private fun initPanel() {
        panel = MainJPanel(backGroundImage)
        add(panel)
    }

    /**
     * Listener 설정
     * */
    private fun initListeners() {
        addWindowListener(WindowCloseConfirmListener())
        addComponentListener(WindowMoveListener())
    }

}