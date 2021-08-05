package com.stephenk.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class ColorSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var listOfColors = listOf(Color.RED, Color.BLUE, Color.GREEN)
    private var selectedColorIndex = 0
    private var colorSelectorArrowLeft: ImageView
    private var colorSelectorArrowRight: ImageView
    private var selectedColor: View
    private var colorSelectListener: ((Int) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorSelector)
        listOfColors = typedArray.getTextArray(R.styleable.ColorSelector_colors).map { color ->
            Color.parseColor(color.toString())
        }
        typedArray.recycle()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.color_selector, this)
        selectedColor = findViewById(R.id.selected_color)
        colorSelectorArrowRight = findViewById(R.id.selector_right)
        colorSelectorArrowLeft = findViewById(R.id.selector_left)
        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        colorSelectorArrowLeft.setOnClickListener {
            selectPreviousColor()
        }
        colorSelectorArrowRight.setOnClickListener {
            selectNextColor()
        }
    }

    private fun selectNextColor() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = listOfColors.lastIndex
        } else {
            selectedColorIndex--
        }
        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        broadCastColor()
    }

    private fun selectPreviousColor() {
        if (selectedColorIndex == listOfColors.lastIndex) {
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }
        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        broadCastColor()
    }

    private fun broadCastColor() {
        this.colorSelectListener?.let { function -> function(listOfColors[selectedColorIndex]) }
    }

    fun setListener(color: (Int) -> Unit) {
        this.colorSelectListener = color
    }
}

