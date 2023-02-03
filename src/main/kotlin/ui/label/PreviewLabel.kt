package ui.label

import listener.mouse.DonationRankingPreviewLabelMouseListener
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Point
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class PreviewLabel(text: String, initPointX: Int, initPointY: Int, labelWidth: Int, labelHeight: Int): JLabel(text) {

    var pointX: Int = 0
    var pointY: Int = 0
    var labelWidth = 300
    var labelHeight = 200
    val color = Color.WHITE

    init {
        pointX = initPointX
        pointY = initPointY
        location = Point(pointX, pointY)
        this.labelWidth = labelWidth
        this.labelHeight = labelHeight
        size = Dimension(labelWidth, labelHeight)
        border = BorderFactory.createLineBorder(Color.BLACK)
        foreground = color
        horizontalAlignment = SwingConstants.CENTER
        verticalAlignment = SwingConstants.CENTER
    }

    fun addMouseAdapter(panel: JPanel) {
        val mouseAdapter = DonationRankingPreviewLabelMouseListener(this, panel)
        addMouseListener(mouseAdapter)
        addMouseMotionListener(mouseAdapter)
        addMouseWheelListener(mouseAdapter)
    }

    /**
     * 텍스트 크기를 라벨에 맞춤
     * */
    fun fitTextSizeOnLabel() {
        val stringWidth: Int = getFontMetrics(font).stringWidth(text)

        // Find out how much the font can grow in width.
        val widthRatio = width.toDouble() / stringWidth.toDouble()
        val newFontSize = (font.size * widthRatio).toInt()

        // Pick a new font size so it will not be larger than the height of label.
        val fontSizeToUse = newFontSize.coerceAtMost(height)

        // Set the label's font size to the newly determined size.
        font = Font(font.name, Font.PLAIN, fontSizeToUse)
    }
}