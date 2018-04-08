package com.example.kinga.calculatorrpn

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_settings.*
import android.widget.ArrayAdapter
import android.widget.Spinner


class SettingsActivity : AppCompatActivity() {
    private val TAG = "StateChange"
    private var colorCode = "5b805b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        precisionNr.minValue = 0
        precisionNr.maxValue = 10
        precisionNr.wrapSelectorWheel = true

        val staticSpinner = findViewById<Spinner>(R.id.colorMenu)
        val staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item)
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.adapter = staticAdapter;

        applyButton.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        val color = colorMenu.selectedItem
        colorCode = when (color) {
            "Red" -> "f44242"
            "Green" -> "62f441"
            "Blue" -> "414ff4"
            "Yellow" -> "e8f441"
            else -> "5b805b"
        }
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("precision", precisionNr.value)
        i.putExtra("screenColor", colorCode)
        setResult(Activity.RESULT_OK, i)
        super.finish()
    }

}
