package ui.label

import listener.mouse.DonationRankingPreviewLabelMouseListener
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Point
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel

class PreviewLabel(text: String, initPointX: Int, initPointY: Int): JLabel(text) {

    var pointX: Int = 0
    var pointY: Int = 0
    var labelWidth = 100
    var labelHeight = 50

    init {
        pointX = initPointX
        pointY = initPointY
        location = Point(pointX, pointY)
        size = Dimension(labelWidth, labelHeight)
        border = BorderFactory.createLineBorder(Color.BLACK)
    }

    fun addMouseAdapter(panel: JPanel) {
        val mouseAdapter = DonationRankingPreviewLabelMouseListener(this, panel)
        addMouseListener(mouseAdapter)
        addMouseMotionListener(mouseAdapter)
        addMouseWheelListener(mouseAdapter)
    }

    fun fitTestSizeOnLabel() {
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