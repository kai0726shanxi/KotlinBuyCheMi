package com.chmichat.chat.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import com.chmichat.chat.Constants

/**
 * @类名     动画工具类
 *
 * @java类  类描述
 *
 * @作者    gao
 *
 * @创建时间 2019/7/26
 */


class AnimationUtils {


    private fun animatorStyleOne(view: View) {
        //X方向平移
        val translateXAnimaotr = ObjectAnimator.ofFloat(view, "translationX", 0f, 200f)
        //Y方向平移
        val translateYAnimaotr = ObjectAnimator.ofFloat(view, "translationY", 0f, 300f)
        //实现旋转动画，也可以单独使用rotationX或rotationY
        //rotationX 表示围绕 X 轴旋转
        //rotationY:表示围绕 Y 轴旋转
        //rotation:表示围绕 Z 旋转
        val rotationAnimaotr = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f, 0f)
        //缩放动画，也有X及Y两个方向上设置
        val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 3f)
        val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 3f)
        //透明度动画
        val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1f)
        //然后通过AnimatorSet把几种动画组合起来
        val animatorSet = AnimatorSet()
        /**
         * AnimatorSet正是通过以下方法来控制动画播放顺序：
         * after（Animator anim）：将现有动画插入到传入的动画之后执行。
         * before（Animator anim）：将现有动画插入到传入的动画之前执行。
         * with（Animator anim）：将现有动画和传入的动画同时执行。
         */
        animatorSet.play(translateXAnimaotr).with(translateYAnimaotr).before(rotationAnimaotr).after(scaleXAnimator).with(scaleYAnimator).after(alphaAnimator)
        //设置动画时间
        animatorSet.duration = 2000
        //开始动画
        animatorSet.start()
    }


    /**
     * PropertyValuesHolder实现组合动画
     * PropertyValuesHolder类只能是多个动画一起执行
     */
    private fun animatorStyleTwo(view: View) {
        //X方向平移
        val translateXAnimaotr = PropertyValuesHolder.ofFloat("translationX", 0f, 200f)
        //Y方向平移
        val translateYAnimaotr = PropertyValuesHolder.ofFloat("translationY", 0f, 300f)
        //实现旋转动画，也可以单独使用rotationX或rotationY
        //rotationX 表示围绕 X 轴旋转
        //rotationY:表示围绕 Y 轴旋转
        //rotation:表示围绕 Z 旋转
        val rotationAnimaotr = PropertyValuesHolder.ofFloat("rotation", 0f, 360f, 0f)
        //缩放动画，也有X及Y两个方向上设置
        val scaleXAnimator = PropertyValuesHolder.ofFloat("scaleX", 1f, 3f)
        val scaleYAnimator = PropertyValuesHolder.ofFloat("scaleY", 1f, 3f)
        //透明度动画
        val alphaAnimator = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, translateXAnimaotr, translateYAnimaotr, rotationAnimaotr, scaleXAnimator, scaleYAnimator)
        objectAnimator.duration = 2000
        objectAnimator.start()
    }

    companion object {


        fun scaleAnimator(view: View, a: Float, b: Float, `is`: Boolean) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", a, b)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", a, b)
            var alphaAnimator: ObjectAnimator? = null
            if (`is`) {
                alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1f)

            } else {
                alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f)

            }

            val animatorSet = AnimatorSet()
            animatorSet.play(alphaAnimator)
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }


        //入场场动画(缩放，透明)
        fun scaleAnimatorShow(view: View) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f)

            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f)

            val animatorSet = AnimatorSet()
            animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }

        //入场场动画(缩放，透明)
        fun scaleAnimatorShow(view: View, a: Float, b: Float) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", a, b)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", a, b)

            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f)

            val animatorSet = AnimatorSet()
            animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }

        //入场场动画(缩放，透明，位移)
        fun scaleAnimatorShow1(view: View, n: Boolean, b: Float) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f)

            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f)

            val translateYAnimaotr = ObjectAnimator.ofFloat(view, "translationY", 0f, b)

            val animatorSet = AnimatorSet()
            if (n) {
                animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator).with(translateYAnimaotr)

            } else {
                animatorSet.play(translateYAnimaotr)

            }
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }

        //出场动画（缩放，透明）
        fun scaleAnimatorDismiss(view: View) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f)
            var alphaAnimator: ObjectAnimator? = null

            alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f)

            val animatorSet = AnimatorSet()
            animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }


        //出场动画（缩放，透明）
        fun scaleAnimatorDismiss(view: View, a: Float, b: Float) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", a, b)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", a, b)
            var alphaAnimator: ObjectAnimator? = null

            alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f)

            val animatorSet = AnimatorSet()
            animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }

        //出场动画（缩放，透明，位移）
        fun scaleAnimatorDismiss1(view: View, n: Boolean, b: Float) {
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f)
            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f)
            val translateYAnimaotr = ObjectAnimator.ofFloat(view, "translationY", b, 0f)

            val animatorSet = AnimatorSet()

            if (n) {
                animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator).with(translateYAnimaotr)

            } else {
                animatorSet.play(translateYAnimaotr)

            }        //设置动画时间
            animatorSet.duration = 350L
            //开始动画
            animatorSet.start()
        }

        /**
         * 位移动画
         */
        fun TranslateAnimation(view: View, a: Float, b: Float, type: String) {
            var translateXAnimaotr: ObjectAnimator? = null
            if (type == "x") {
                translateXAnimaotr = ObjectAnimator.ofFloat(view, "translationX", a, b)

            } else {
                translateXAnimaotr = ObjectAnimator.ofFloat(view, "translationY", a, b)
            }
            translateXAnimaotr!!.duration = Constants.ANMATIONSHOWTIME
            translateXAnimaotr.start()
        }


        fun SetAlphaAnmation(view: View,a: Float,b: Float){
            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", a, b)
            alphaAnimator.duration=Constants.ANMATIONSHOWTIME
            alphaAnimator.start()

        }

        /***
         *
         * @param view
         * @param b
         */
        fun SetAnimation(view: View, b: Boolean) {
            //b为true的时候显示，false隐藏
            if (b) {
                val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f)
                val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f)
                val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f)

                val animatorSet = AnimatorSet()
                animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
                //设置动画时间
                animatorSet.duration = Constants.ANMATIONSHOWTIME
                //开始动画
                animatorSet.start()

            } else {
                val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f)
                val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f)
                val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f)

                val animatorSet = AnimatorSet()
                animatorSet.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
                //设置动画时间
                animatorSet.duration = Constants.ANMATIONSHOWTIME
                //开始动画
                animatorSet.start()

            }


        }
    }

}
