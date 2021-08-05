package com.stephenk.customview

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
            Snackbar.make(
                this@MainActivity,
                findViewById(R.id.colorSelector),
                "Color received",
                Snackbar.LENGTH_LONG
            ).setBackgroundTint(color)
                .show()
        }
        val colorSlider: ColorSlider = findViewById(R.id.colorPicker)
        colorSlider.addListener { color ->
            if (color != android.R.color.transparent) {
                Snackbar.make(
                    this@MainActivity,
                    findViewById(R.id.colorSelector),
                    "Color received",
                    Snackbar.LENGTH_LONG
                ).setBackgroundTint(color)
                    .show()
            }
        }
    }
}