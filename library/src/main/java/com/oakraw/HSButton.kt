package com.oakraw

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.ImageViewCompat
import com.oakraw.library.R


class HSButton : CardView {
    private var mTintColor: ColorStateList? = null
    private var mTextAppearance: Int? = null
    private var mTextColor: Int? = null
    private var mTextSize: Float? = null
    private var mImage: ImageView? = null
    private var mTextView: TextView? = null
    private var mRadius: Float? = null
    private var mImageDrawable: Drawable? = null
    private var mText: String? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributesArray(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttributesArray(attrs)
        initView()
    }


    private fun initAttributesArray(attrs: AttributeSet?) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.HSButtonAttrs, 0, 0)
        mImageDrawable = attrsArray.getDrawable(R.styleable.HSButtonAttrs_android_src)
        mRadius = attrsArray.getDimension(R.styleable.HSButtonAttrs_android_radius, 0f)
        mText = attrsArray.getString(R.styleable.HSButtonAttrs_android_text)
        mTextSize = attrsArray.getDimension(R.styleable.HSButtonAttrs_android_textSize, 0f)
        mTextAppearance = attrsArray.getResourceId(
            R.styleable.HSButtonAttrs_android_textAppearance,
            R.style.TextAppearance_AppCompat_Body1
        )
        mTintColor = attrsArray.getColorStateList(R.styleable.HSButtonAttrs_android_tint)
        mTextColor = attrsArray.getColor(R.styleable.HSButtonAttrs_android_textColor, Color.BLACK)

        attrsArray.recycle()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.button_hs, this, true)
        mImage = findViewById(R.id.image)
        mTextView = findViewById(R.id.textView)

        cardElevation = 4f.px
        mImageDrawable?.let { setImageDrawable(it) }
        mRadius?.let { radius = it }
        mText?.let { setText(it) }
        mTextAppearance?.let { mTextView?.setTextAppearance(context, it) }
        mTextColor?.let { setTextColor(it) }
        mTextSize?.let { if (it > 0) setTextSize(it) }
        mTintColor?.let { setImageTintColor(it) }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        findViewById<View>(R.id.layoutClickable).apply {
            setOnClickListener(l)
            val outValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            setBackgroundResource(outValue.resourceId)
        }
    }

    fun setText(text: String) {
        mTextView?.text = text
    }

    fun setTextSize(textSize: Float) {
        mTextView?.textSize = textSize
    }

    fun setTextColor(textColor: Int) {
        mTextView?.setTextColor(textColor)
    }

    fun setImageTintColor(tintColor: ColorStateList) {
        mImage?.let {
            ImageViewCompat.setImageTintList(it, tintColor)
        }
    }

    fun setImageDrawable(drawable: Drawable) {
        mImage?.setImageDrawable(drawable)
    }

    fun setImageBitmap(bitmap: Bitmap) {
        mImage?.setImageBitmap(bitmap)
    }

    fun setImageResource(redId: Int) {
        mImage?.setImageResource(redId)
    }
}