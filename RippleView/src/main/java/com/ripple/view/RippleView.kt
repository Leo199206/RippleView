package com.ripple.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.lifecycle.LifecycleOwner
import com.ripple.view.lifecycle.RippleLifecycle
import com.ripple.view.lifecycle.RippleLifecycleAdapter
import java.util.*
import kotlin.math.min

/**
 * <pre>
 *   @author : leo
 *   @time   : 2021/03/21
 *   @desc   : 水波纹View
 * </pre>
 */
class RippleView : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initPaint()
    }

    companion object {
        private const val MAX_ALPHA = 255
    }

    private val rippleLifecycle: RippleLifecycle by lazy {
        RippleLifecycle(this)
    }
    private var paint = Paint()
    private var circleMaxRadius: Int = 0
    private var circleList: MutableList<RippleCircle> = LinkedList()
    private var isStart: Boolean = false
    private var isPause: Boolean = false

    private var speed: Int = 1
    private var circleStrokeWidth: Float = 3f

    @ColorInt
    private var circleColor: Int = Color.RED
    private var circleMinRadius: Float = 20f
    private var circleCount: Int = 10
    private var circleCenterX: Float = 0f
    private var circleCenterY: Float = 0f


    /**
     * 圆形半径、透明度参数初始化
     */
    private fun initCircle() {
        circleMaxRadius = (width / 2 - circleStrokeWidth).toInt()
        circleCenterX = width / 2f
        circleCenterY = height / 2f
        circleList.add(RippleCircle(circleMinRadius, MAX_ALPHA))
    }

    /**
     * 画笔初始化
     */
    private fun initPaint() {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = circleStrokeWidth
        paint.isDither = true
        paint.isAntiAlias = true
        paint.color = circleColor
    }


    /**
     * 测量控件尺寸
     * @param widthMeasureSpec Int
     * @param heightMeasureSpec Int
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultWidth = 200
        var defaultHeight = 200
        val width = measureSize(widthMeasureSpec, defaultWidth)
        val height = measureSize(heightMeasureSpec, defaultHeight)
        val size = min(width, height)
        setMeasuredDimension(size, size)
    }

    /**
     * 测量尺寸
     *
     * @param measureSpec
     * @param defaultSize
     * @return
     */
    private fun measureSize(measureSpec: Int, defaultSize: Int): Int {
        var result: Int
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        if (mode == MeasureSpec.EXACTLY) {
            result = size
        } else {
            result = defaultSize
            if (mode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(size)
            }
        }
        return result
    }


    /**
     * 尺寸变动回调
     * @param w Int
     * @param h Int
     * @param oldw Int
     * @param oldh Int
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initCircle()
        onStart()
    }

    /**
     * 视图绘制
     * @param canvas Canvas
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            onDrawRippleCircle(canvas)
        }
    }


    /**
     * 绘制水波纹
     * @param canvas Canvas
     */
    private fun onDrawRippleCircle(canvas: Canvas) {
        if (!isStart) {
            return
        }
        with(circleList.iterator()) {
            while (hasNext()) {
                next().also {
                    paint.alpha = it.alpha
                    canvas.drawCircle(
                        circleCenterX,
                        circleCenterY,
                        it.radius.toFloat(),
                        paint
                    )
                    it.radius += speed
                    if (it.radius > circleMaxRadius) {
                        remove()
                    } else {
                        it.alpha =
                            (MAX_ALPHA - (it.radius / circleMaxRadius.toFloat()) * MAX_ALPHA).toInt()
                    }
                }
            }
            addNewRippleCircle()
        }
        postInvalidate()
    }


    /**
     * 添加新水波纹
     */
    private fun addNewRippleCircle() {
        if (circleList.size <= 0) {
            return
        }
        val minMeet = (circleMaxRadius - circleMinRadius) / circleCount
        val add = circleList.last().radius > minMeet
        if (add) {
            circleList.add(RippleCircle(circleMinRadius, MAX_ALPHA))
        }
    }


    /**
     * 绑定页面生命周期，自动进行资源释放
     * @param lifecycleOwner LifecycleOwner?
     */
    private fun bindLifecycle(lifecycleOwner: LifecycleOwner?) {
        lifecycleOwner
            ?.lifecycle
            ?.addObserver(RippleLifecycleAdapter(rippleLifecycle))
    }

    /**
     * 开始播放动画
     * @param lifecycleOwner LifecycleOwner?
     */
    fun onStart(lifecycleOwner: LifecycleOwner? = null) {
        bindLifecycle(lifecycleOwner)
        isStart = true
        circleList.add(RippleCircle(circleMinRadius, MAX_ALPHA))
        postInvalidate()
    }

    /**
     * 动画暂停后，恢复动画播放
     */
    fun onResume() {
        if (isPause) {
            isStart = true
            isPause = false
            postInvalidate()
        }
    }

    /**
     * 停止播放水波纹动画
     */
    fun onStop() {
        isPause = false
        isStart = false
        circleList.clear()
    }

    /**
     * 暂停水波纹动画播放
     */
    fun onPause() {
        if (isStart) {
            isPause = true
            isStart = false
        }
    }


}