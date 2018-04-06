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
                Log.i(TAG, stack.size.toString())
                if (stack1Label.text == "1:") {
                    stack4Label.text = stack3Label.text
                    stack3Label.text = stack2Label.text
                    stack2Label.text = stack1Label.text
                    stack1Label.text = "->"
                    if (stack.size > 3) stack4Text.text = stack[stack.size-4].toString()
                    if (stack.size > 2) stack3Text.text = stack[stack.size-3].toString()
                    if (stack.size > 1) stack2Text.text = stack[stack.size-2].toString()
                    if (stack.size > 0) stack1Text.text = stack[stack.size-1].toString()
                } else if (stack1Label.text == "->"){
                    stack4Label.text = "4: "
                    stack3Label.text = "3: "
                    stack2Label.text = "2: "
                    stack1Label.text = "1: "
                    if (stack.size > 3) stack4Text.text = stack[stack.size-4].toString()
                    if (stack.size > 2) stack3Text.text = stack[stack.size-3].toString()
                    if (stack.size > 1) stack2Text.text = stack[stack.size-2].toString()
                    if (stack.size > 0) stack1Text.text = stack[stack.size-1].toString()
                }
                currValue = ""
            }
        }
    }

    fun updateScreen() {
        if (currValue.isNotEmpty()) {
            if (stack1Label.text != "->") {
                stack4Label.text = stack3Label.text
                stack3Label.text = stack2Label.text
                stack2Label.text = stack1Label.text
                stack1Label.text = "->"
                if (stack.size > 2) stack4Text.text = stack[stack.size-3].toString()
                if (stack.size > 1) stack3Text.text = stack[stack.size-2].toString()
                if (stack.size > 0) stack2Text.text = stack[stack.size-1].toString()
            }
            stack1Text.text = currValue
        }
    }

}
