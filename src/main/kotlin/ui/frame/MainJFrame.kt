package ui.frame

import constants.ImagePath
import container.FrameContainer
import listener.window.WindowCloseConfirmListener
import listener.window.WindowMoveListener
import ui.panel.MainJPanel
import java.awt.*
import javax.swing.*

class MainJFrame(
    private val backGroundImage: ImageIcon = ImageIcon(this::class.java.getResource(ImagePath.MAIN.path))
): AbstractJFrame() {

    private var panel: JPanel? = null

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