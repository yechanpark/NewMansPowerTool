package listener.textfield

import dto.DonateInfo
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

private fun afterInputRankingJTextFieldInput(
    nickNameTextField: JTextField, amountTextField: JTextField, tableModel: DefaultTableModel
) {
    val nickname = nickNameTextField.text
    if (nickname.isEmpty()) return

    val amountText = amountTextField.text
    if (amountText.toLongOrNull() == null) return

    val addData = DonateInfo(nickname, amountText.toLong())
    tableModel.addRow(addData.convertToStringArray())

    nickNameTextField.text = ""
    amountTextField.text = ""

    nickNameTextField.requestFocus()
}

class InputRankingJTextFieldKeyAdapter(
    private val nickNameTextField: JTextField,
    private val amountTextField: JTextField,
    private val tableModel: DefaultTableModel
): KeyAdapter() {
    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode != KeyEvent.VK_ENTER) return
        afterInputRankingJTextFieldInput(nickNameTextField, amountTextField, tableModel)
    }
}

class InputRakingAddButtonListener(
    private val nickNameTextField: JTextField,
    private val amountTextField: JTextField,
    private val tableModel: DefaultTableModel
): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        afterInputRankingJTextFieldInput(nickNameTextField, amountTextField, tableModel)
    }
}