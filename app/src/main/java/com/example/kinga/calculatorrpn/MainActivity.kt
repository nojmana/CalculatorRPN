package com.example.kinga.calculatorrpn

import android.content.Intent
import android.util.Log
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.res.Configuration.ORIENTATION_LANDSCAPE

class MainActivity : AppCompatActivity() {

    private var stack = Stack<Double>()
    private var curValue = ""
    private val TAG = "StateChange"

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            curValue = savedInstanceState.getString("curVal")
            var doubleArr =  savedInstanceState.getDoubleArray("stack")
            for (i in 0 until doubleArr.size) {
                stack.push(doubleArr[i])
            }
            Log.i(TAG, curValue)
            if (curValue == "") printStack()
            else printStackWithCurVal()
        };

    }

    override fun onSaveInstanceState(outState: Bundle?) { //przy zamykaniu
        super.onSaveInstanceState(outState)
        outState!!.putString("curVal", curValue)
        outState.putDoubleArray("stack", stack.toDoubleArray())
        var arr = stack.toDoubleArray();
        Log.i(TAG, arr.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape)
        } else setContentView(R.layout.activity_main)

        button0.setOnClickListener {
            curValue += "0"
            updateScreen()
        }

        button1.setOnClickListener {
            curValue += "1"
            updateScreen()
        }

        button2.setOnClickListener {
            curValue += "2"
            updateScreen()
        }

        button3.setOnClickListener {
            curValue += "3"
            updateScreen()
        }

        button4.setOnClickListener {
            curValue += "4"
            updateScreen()
        }

        button5.setOnClickListener {
            curValue += "5"
            updateScreen()
        }

        button6.setOnClickListener {
            curValue += "6"
            updateScreen()
        }

        button7.setOnClickListener {
            curValue += "7"
            updateScreen()
        }

        button8.setOnClickListener {
            curValue += "8"
            updateScreen()
        }

        button9.setOnClickListener {
            curValue += "9"
            updateScreen()
        }

        button0.setOnClickListener {
            curValue += "0"
            updateScreen()
        }

        buttonDot.setOnClickListener {
            curValue += "."
            updateScreen()
        }

        buttonPlus.setOnClickListener {
            if (stack.size > 1) {
                stack.push(stack.pop() + stack.pop())
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonMinus.setOnClickListener {
            if (stack.size > 1) {
                val x = stack.pop()
                val y = stack.pop()
                stack.push(y - x)
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonMultiply.setOnClickListener {
            if (stack.size > 1) {
                stack.push(stack.pop() * stack.pop())
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonDivide.setOnClickListener {
            if (stack.size > 1) {
                val x = stack.pop()
                val y = stack.pop()
                stack.push(y / x)
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonExp.setOnClickListener {
            if (stack.size > 1) {
                val x = stack.pop()
                val y = stack.pop()
                stack.push(Math.pow(y, x))
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonSqrt.setOnClickListener {
            if (stack.size > 0) {
                stack.push(Math.sqrt(stack.pop()))
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonPercent.setOnClickListener {
            if (stack.size > 1) {
                val x = stack.pop()
                val y = stack.pop()
                stack.push(y*0.01*x)
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonChangeSign.setOnClickListener {
            if (stack.size > 0) stack[stack.size - 1] = - stack[stack.size - 1]
            if (curValue == "") printStack()
            else printStackWithCurVal()
        }

        buttonEnter.setOnClickListener {
            if (curValue.isNotEmpty()) {
                stack.push(curValue.toDouble())
                if (stack1Label.text == "1:") printStack()
                else if (stack1Label.text == "->") printStack()
                curValue = ""
            }
        }

        buttonDrop.setOnClickListener {
            if (stack.size > 0) stack.removeAt(stack.size - 1)
            if (curValue == "") printStack()
            else printStackWithCurVal()
        }

        buttonSwap.setOnClickListener {
            if (stack.size > 1) {
                val temp = stack[stack.size-1]
                stack[stack.size - 1] = stack[stack.size - 2]
                stack[stack.size - 2] = temp
                if (curValue == "") printStack()
                else printStackWithCurVal()
            }
        }

        buttonAC.setOnClickListener {
            stack.clear()
            if (curValue == "") printStack()
            else printStackWithCurVal()
        }

        buttonUndo.setOnClickListener {
            if (curValue.isNotEmpty()) {
                curValue = curValue.substring(0, curValue.length - 1)
                updateScreen()
            }
            if (curValue == "") changeLabelsToStack()
        }

        buttonSettings.setOnClickListener {
            val i = Intent(this, SettingsActivity::class.java)
            i.putExtra("Parametr", "Twoje dane")
            startActivity(i)
        }
    }

    fun updateScreen() {
        if (stack1Label.text != "->") {
            changeLabelsToNewNumber()
            if (stack.size > 2) stack4Text.text = stack[stack.size-3].toString()
            if (stack.size > 1) stack3Text.text = stack[stack.size-2].toString()
            if (stack.size > 0) stack2Text.text = stack[stack.size-1].toString()
        }
        stack1Text.text = curValue
    }

    fun changeLabelsToNewNumber() {
        stack4Label.text = stack3Label.text
        stack3Label.text = stack2Label.text
        stack2Label.text = stack1Label.text
        stack1Label.text = "->"
    }

    fun changeLabelsToStack() {
        stack4Label.text = "4: "
        stack3Label.text = "3: "
        stack2Label.text = "2: "
        stack1Label.text = "1: "
    }

    fun printStack() {
        if (stack.size > 3) stack4Text.text = stack[stack.size - 4].toString()
        else stack4Text.text = ""
        if (stack.size > 2) stack3Text.text = stack[stack.size - 3].toString()
        else stack3Text.text = ""
        if (stack.size > 1) stack2Text.text = stack[stack.size - 2].toString()
        else stack2Text.text = ""
        if (stack.size > 0) stack1Text.text = stack[stack.size - 1].toString()
        else stack1Text.text = ""
        changeLabelsToStack()
    }

    fun printStackWithCurVal() {
        if (stack.size > 2) stack4Text.text = stack[stack.size - 3].toString()
        else stack4Text.text = ""
        if (stack.size > 1) stack3Text.text = stack[stack.size - 2].toString()
        else stack3Text.text = ""
        if (stack.size > 0) stack2Text.text = stack[stack.size - 1].toString()
        else stack2Text.text = ""
        stack1Text.text = curValue
        changeLabelsToNewNumber()
    }
}
