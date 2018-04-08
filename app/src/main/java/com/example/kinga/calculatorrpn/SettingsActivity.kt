package com.example.kinga.calculatorrpn

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val cp = ColorPicker(this@SettingsActivity, 91, 128, 91)
        precisionNr.minValue = 0
        precisionNr.maxValue = 10
        precisionNr.wrapSelectorWheel = true

        colorButton.setOnClickListener {
            cp.show()
        }

        applyButton.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("precision", precisionNr.value)
        i.putExtra("screenColor", precisionNr.value)
        setResult(Activity.RESULT_OK, i)
        super.finish()
    }

}
