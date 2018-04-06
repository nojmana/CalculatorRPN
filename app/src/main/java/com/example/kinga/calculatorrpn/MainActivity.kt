package com.example.kinga.calculatorrpn

import android.util.Log
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private var stack = Stack<Double>()
    private var currValue = ""
    val TAG = "StateChange"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnClickListener {
            currValue += "0"
            updateScreen()
        }

        button1.setOnClickListener {
            currValue += "1"
            updateScreen()
        }

        button2.setOnClickListener {
            currValue += "2"
            updateScreen()
        }

        button3.setOnClickListener {
            currValue += "3"
            updateScreen()
        }

        button4.setOnClickListener {
            currValue += "4"
            updateScreen()
        }

        button5.setOnClickListener {
            currValue += "5"
            updateScreen()
        }

        button6.setOnClickListener {
            currValue += "6"
            updateScreen()
        }

        button7.setOnClickListener {
            currValue += "7"
            updateScreen()
        }

        button8.setOnClickListener {
            currValue += "8"
            updateScreen()
        }

        button9.setOnClickListener {
            currValue += "9"
            updateScreen()
        }

        button0.setOnClickListener {
            currValue += "0"
            updateScreen()
        }

        buttonEnter.setOnClickListener {
            if (currValue.isNotEmpty()) {
                stack.push(currValue.toDouble())
                //Log.i(TAG, stack.size.toString())
                if (stack1Label.text == "1:") {
                    changeLabelsToNewNumber()
                    printStack()
                } else if (stack1Label.text == "->"){
                    stack4Label.text = "4: "
                    stack3Label.text = "3: "
                    stack2Label.text = "2: "
                    stack1Label.text = "1: "
                    printStack()
                }
                currValue = ""
            }
        }

        buttonDrop.setOnClickListener {
            if (stack.size > 0) stack.removeAt(stack.size - 1)
                printStack()
        }

        buttonSwap.setOnClickListener {
            if (stack.size > 1) {
                var temp = stack[stack.size-1]
                stack[stack.size - 1] = stack[stack.size - 2]
                stack[stack.size - 2] = temp
                printStack()
            }
        }

        buttonAC.setOnClickListener {
            stack.clear()
            printStack()
        }

        buttonChangeSign.setOnClickListener {
            if (stack.size > 1) stack[stack.size - 1] = - stack[stack.size - 1]
            printStack()
        }

        buttonUndo.setOnClickListener {
            if (currValue.length > 0) {
                currValue = currValue.substring(0, currValue.length - 1)
                updateScreen()
            }
        }
    }

    fun updateScreen() {
        if (currValue.isNotEmpty()) {
            if (stack1Label.text != "->") {
                changeLabelsToNewNumber()
                if (stack.size > 2) stack4Text.text = stack[stack.size-3].toString()
                if (stack.size > 1) stack3Text.text = stack[stack.size-2].toString()
                if (stack.size > 0) stack2Text.text = stack[stack.size-1].toString()
            }
            stack1Text.text = currValue
        }
    }

    fun changeLabelsToNewNumber() {
        stack4Label.text = stack3Label.text
        stack3Label.text = stack2Label.text
        stack2Label.text = stack1Label.text
        stack1Label.text = "->"
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
    }

}
