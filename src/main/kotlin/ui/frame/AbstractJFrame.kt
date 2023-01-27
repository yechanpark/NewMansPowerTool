package ui.frame

import constants.ImagePath
import javax.swing.*

open class AbstractJFrame(
    titleText: String = DEFAULT_TITLE
): JFrame(titleText) {

    companion object {
        const val DEFAULT_TITLE = "신남성연대 Tool"
    }

    init {
        initIcon()
        initProperties()
    }

    /**
     * 타이틀 바 아이콘 이미지 설정
     * */
    private fun initIcon() {
        iconImage = ImageIcon(this::class.java.getResource(ImagePath.ICON.path)).image
    }

    /**
     * 프로퍼티 설정
     * */
    private fun initProperties() {
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
    }

    fun boot() {
        isVisible = true
    }

}