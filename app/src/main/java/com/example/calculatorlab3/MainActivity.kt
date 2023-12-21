package com.example.calculatorlab3

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.input)
        display.showSoftInputOnFocus = false

        display.setOnClickListener {
            if (getString(R.string.enter_a_value) == display.text.toString()) {
                display.setText("")
            }
        }
    }

    private fun updateText(strToAdd: String) {
        val oldStr = display.text.toString()
        val cursorPos = display.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        if (getString(R.string.enter_a_value) == display.text.toString()) {
            display.setText(strToAdd)
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
        }
        display.setSelection(cursorPos + 1)
    }

    fun equalsBTN(view: View) {
        val userExp = display.text.toString()

        try {
            val result = evaluateExpression(userExp)
            display.setText(result.toString())
            display.setSelection(display.text.length)
        } catch (e: Exception) {
            display.setText("Error")
        }
    }

    private fun evaluateExpression(expression: String): Double {
        // Split the expression into operands and operators
        val operandsAndOperators = expression.split(Regex("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])"))

        // Separate numbers from operators
        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<String>()

        for (item in operandsAndOperators) {
            if (item.isNotEmpty()) {
                if (item[0].isDigit() || (item.length > 1 && item[0] == '-' && item[1].isDigit())) {
                    // It's a number
                    numbers.add(item.toDouble())
                } else {
                    // It's an operator
                    operators.add(item)
                }
            }
        }

        // Perform calculations
        var result = numbers[0]
        var operatorIndex = 0

        for (i in 1 until numbers.size) {
            when (operators[operatorIndex]) {
                "+" -> result += numbers[i]
                "-" -> result -= numbers[i]
                "×" -> result *= numbers[i]
                "÷" -> result /= numbers[i]
                "^" -> result = Math.pow(result, numbers[i])
                "%" -> result %= numbers[i]
            }
            operatorIndex++
        }

        return result
    }


    fun backspaceBTN(view: View) {
        val cursorPos = display.selectionStart
        val textLen = display.text.length
        if (cursorPos != 0 && textLen != 0) {
            val selection = display.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            display.setText(selection)
            display.setSelection(cursorPos - 1)
        }
    }

    fun zeroBTN(view: View?) {
        updateText("0")
    }

    fun oneBTN(view: View?) {
        updateText("1")
    }

    fun twoBTN(view: View?) {
        updateText("2")
    }

    fun threeBTN(view: View?) {
        updateText("3")
    }

    fun fourBTN(view: View?) {
        updateText("4")
    }

    fun fiveBTN(view: View?) {
        updateText("5")
    }

    fun sixBTN(view: View?) {
        updateText("6")
    }

    fun sevenBTN(view: View?) {
        updateText("7")
    }

    fun eightBTN(view: View?) {
        updateText("8")
    }

    fun nineBTN(view: View?) {
        updateText("9")
    }


    fun clearBTN(view: View?) {
        this.display.setText("")
    }

    fun multiplyBTN(view: View?) {
        updateText("×")
    }

    fun minusBTN(view: View?) {
        updateText("-")
    }

    fun plusBTN(view: View?) {
        updateText("+")
    }

    fun pointBTN(view: View?) {
        updateText(".")
    }

    fun squareRootBTN(view: View?) {
        updateText("√")
    }


}