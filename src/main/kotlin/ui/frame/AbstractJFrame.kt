package ui.frame

import javax.swing.*

open class AbstractJFrame(
    titleText: String = DEFAULT_TITLE
): JFrame(titleText) {

    companion object {
        private const val DEFAULT_ICON_IMG_PATH = "/img/icon.jpg"
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
        iconImage = ImageIcon(this::class.java.getResource(DEFAULT_ICON_IMG_PATH)).image
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