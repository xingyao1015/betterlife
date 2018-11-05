package com.yc.betterlife.customview

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup

/**
 * Created by Oubowu on 2016/7/26 14:14.
 *
 * 绘制分割线工具类
 */
object DividerHelper {

    // 将分割线画在view的顶部，并且左右会多出分割线的宽度
    fun drawTop(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val left = child.left - params.leftMargin - drawable.intrinsicWidth
        val right = child.right + params.rightMargin + drawable.intrinsicWidth
        val top = child.top - params.topMargin - drawable.intrinsicHeight
        val bottom = top + drawable.intrinsicHeight

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }

    // 将分割线画在view的底部，并且左右会多出分割线的宽度
    fun drawBottom(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val left = child.left - params.leftMargin - drawable.intrinsicWidth
        val right = child.right + params.rightMargin + drawable.intrinsicWidth
        val top = child.bottom + params.bottomMargin
        val bottom = top + drawable.intrinsicHeight

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }

    // 将分割线画在view的左边，并且上下会多出分割线的高度
    fun drawLeft(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val top = child.top - params.topMargin - drawable.intrinsicHeight
        val bottom = child.bottom + params.bottomMargin + drawable.intrinsicHeight
        val left = child.left - params.leftMargin - drawable.intrinsicWidth
        val right = left + drawable.intrinsicWidth

        drawable.setBounds(left, top, right, bottom)

        drawable.draw(canvas)
    }

    // 将分割线画在view的右边，并且上下会多出分割线的高度
    fun drawRight(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val top = child.top - params.topMargin - drawable.intrinsicHeight
        val bottom = child.bottom + params.bottomMargin + drawable.intrinsicHeight
        val left = child.right + params.rightMargin
        val right = left + drawable.intrinsicWidth

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }


    // 将分割线画在view的顶部，与view左右对齐，考虑margin值
    fun drawTopAlignItem(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val left = child.left - params.leftMargin
        val right = child.right + params.rightMargin
        val top = child.top - params.topMargin - drawable.intrinsicHeight
        val bottom = top + drawable.intrinsicHeight

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }

    // 将分割线画在view的底部，与view左右对齐，考虑margin值
    fun drawBottomAlignItem(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val left = child.left - params.leftMargin
        val right = child.right + params.rightMargin
        val top = child.bottom + params.bottomMargin
        val bottom = top + drawable.intrinsicHeight

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }

    // 将分割线画在view的左边，与Item上下对齐，考虑margin值
    fun drawLeftAlignItem(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val top = child.top - params.topMargin
        val bottom = child.bottom + params.bottomMargin
        val left = child.left - params.leftMargin - drawable.intrinsicWidth
        val right = left + drawable.intrinsicWidth

        drawable.setBounds(left, top, right, bottom)

        drawable.draw(canvas)
    }

    // 将分割线画在view的右边，与Item上下对齐，考虑margin值
    fun drawRightAlignItem(canvas: Canvas, drawable: Drawable, child: View, params: ViewGroup.MarginLayoutParams) {

        val top = child.top - params.topMargin
        val bottom = child.bottom + params.bottomMargin
        val left = child.right + params.rightMargin
        val right = left + drawable.intrinsicWidth

        drawable.setBounds(left, top, right, bottom)
        drawable.draw(canvas)
    }


}
