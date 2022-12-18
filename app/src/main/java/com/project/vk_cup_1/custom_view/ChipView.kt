package com.project.vk_cup_1.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.vk_cup_1.R
import kotlinx.android.synthetic.main.chip_view.view.*



class ChipView: ConstraintLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        inflate(context, R.layout.chip_view, this)

        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ChipView,
            defStyleAttr, 0)

        try {
            text = attributes.getString(R.styleable.ChipView_text)
            imageResource = attributes.getDrawable(R.styleable.ChipView_imageSrc)
            imageResourceSelected = attributes.getDrawable(R.styleable.ChipView_imageSrcSelected)
            selectedState = attributes.getString(R.styleable.ChipView_selectedState)

            containerSelected = attributes.getDrawable(R.styleable.ChipView_containerSelected)
            containerDisSelected = attributes.getDrawable(R.styleable.ChipView_containerDisSelected)

            displayRemoveIcon()
        } finally {
            attributes.recycle()
        }
        chip_container.setOnClickListener {
            selectedState = if (selectedState == "false") {
                "true"
            }
            else{
                "false"
            }
            displayRemoveIcon()
        }
    }


    private var containerSelected: Drawable? = null
    private var containerDisSelected: Drawable? = null
    private var selectedState: String? = null
    private var imageResourceSelected: Drawable? = null

    private var text : String? = null
        set(value) {
            field = value
            displayText()
        }

    private var imageResource : Drawable? = null

    private fun displayText() {
        if (text != null) {
            chip_text.text = text
        } else {
            chip_text.text = ""
        }
        chip_text.invalidate()
        chip_text.requestLayout()
    }


    private fun displayRemoveIcon() {
        chip_close.visibility = View.VISIBLE
        when(selectedState){
            "true" -> {
                chip_container.background = containerSelected
                chip_close.setImageDrawable(imageResourceSelected)
            }
            "false" -> {
                chip_container.background = containerDisSelected
                chip_close.setImageDrawable(imageResource)}
        }
    }
}