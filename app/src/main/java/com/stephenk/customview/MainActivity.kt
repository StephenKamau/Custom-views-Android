package com.stephenk.customview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colorSelector: ColorSelector = findViewById(R.id.colorSelector)
        colorSelector.setListener { color ->
            if (color != android.R.color.transparent)
                displaySnackBar(color)
        }
        val colorSlider: ColorSlider = findViewById(R.id.colorPicker)
        colorSlider.addListener { color ->
            if (color != android.R.color.transparent)
                displaySnackBar(color)
        }
        val colorDialView: ColorDialView = findViewById(R.id.colorDial)
        colorDialView.addListener { color ->
            if (color != Color.TRANSPARENT)
                displaySnackBar(color)
        }
    }

    private fun displaySnackBar(color: Int) {
        Snackbar.make(
            this@MainActivity,
            findViewById(R.id.colorSelector),
            "Color received",
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(color)
            .show()

    }
}