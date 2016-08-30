package com.sky.musiclearn.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Interpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sky000 on 2016/8/15.
 */
public class FocusFrameView extends FrameLayout {
    private Context context = null;

    // 焦点的目的 --宽高
    private int desLeft, desTop, desWidth, desHeight;

    // 焦点位置
    private int[] location = new int[2];

    // 动画规定执行时间 (毫秒)
    private long animDuration = 100;
    // 是否开启动画
    private boolean isAnimOn = true;

    private int shaderSize = 0; // 阴影距离

    private Interpolator mInterpolator; // 好多书上翻译为插值器

    private LayoutParams frame_p;

    private int bgId = 0;
    private List<View> viewCache = new ArrayList<View>();
    private View lastFocus = null;

    public FocusFrameView(Context context, int shaderSize)
    {
        super(context);
        this.shaderSize = shaderSize;
        this.context = context;
    }

    // 获取图片资源
    public void setImgResourse(int img_id)
    {
        bgId = img_id;
        for (View view : viewCache){
            if (view.equals(lastFocus))
                view.setBackgroundResource(img_id);
        }
    }

    /**
     * 设置动画经历的时间长度
     *
     * @param time
     *            动画总时长，默认时间为120ms
     */
    public void setAnimDuration(long time)
    {
        this.animDuration = time;
    }

    public long getAnimDuration()
    {
        return animDuration;
    }

    /**
     * 设置焦点移动是否需要动画，动画开关
     *
     * @param need
     *            boolean值，默认为开状态
     */
    public void needAnimtion(boolean need)
    {
        isAnimOn = need;
    }

    // 初始化View位置及大小
    public void initStarPosition(View v)
    {
        v.getLocationInWindow(location);
        // 焦点目前的坐标
        desLeft = location[0] - shaderSize;
        desTop = location[1] - shaderSize;
        // 焦点目前的大小
        desWidth = v.getWidth() + shaderSize * 2;
        desHeight = v.getHeight() + shaderSize * 2;
        beginAnimation();
    }

    // 初始化View位置及大小
    public void initStarPosition(int posX, int posY, int destWidth, int destHeight)
    {
        // 焦点目前的坐标
        desLeft = posX;
        desTop = posY;
        // 焦点目前的大小
        desWidth = destWidth;
        desHeight = destHeight;
        beginAnimation();
    }

    // 设置是叫插值器,这里和动画的插值器是同一个东西
    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }


    /**
     * 设置焦点应该处在哪个控件上. 焦点将会在设置的时间后到达这个控件.
     *
     * @param v
     *            要显示焦点的控件
     */
    public void changeFocusPos(View v)
    {
        if (v != null)
        {
            v.getLocationInWindow(location);
            desLeft = location[0] - shaderSize;
            desTop = location[1] - shaderSize;
            desWidth = v.getWidth() + shaderSize * 2;
            desHeight = v.getHeight() + shaderSize * 2;
            beginAnimation();
        }
    }

    // 移动到指定位置
    public void changeFocusPos(int posX, int posY, int destWidth, int destHeight) {
        this.desLeft = posX;
        this.desTop = posY;
        this.desWidth = destWidth;
        this.desHeight = destHeight;
        Log.i("focuspos","x " + posX + " y " + posY + " w " + destWidth + " h " + destHeight);
        beginAnimation();
    }



    private void startAlphaAnimation(View v,float from,float to,long during,Animator.AnimatorListener listener){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"alpha",from,to);
        if (mInterpolator != null)
            animator.setInterpolator((TimeInterpolator) mInterpolator);
        animator.setDuration(during);
        if (listener != null)
            animator.addListener(listener);
        animator.start();
    }


    private void beginAnimation() {
        if (lastFocus != null) {
            if (isAnimOn) {
                startAlphaAnimation(lastFocus, 1.0f, 0.0f, animDuration, null);
            }else{
                lastFocus.clearAnimation();
                lastFocus.setVisibility(View.GONE);
            }
        }
        View focus = null;
        if (viewCache.size() > 0)
            focus = viewCache.remove(0);
        else{
            focus = new ImageView(context);
            focus.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            addView(focus);
        }
        focus.setVisibility(View.VISIBLE);
        focus.setBackgroundResource(bgId);
        setFocusPos(focus,desLeft, desTop, desWidth, desHeight);
        lastFocus = focus;
    }

    public void setFocusPos(int posX, int posY, int destWidth, int destHeight) {
        isAnimOn = false;
        changeFocusPos(posX,posY,destWidth,destHeight);
    }

    // 直接设置位置到指定位
    private void setFocusPos(final View view, int posX, int posY, int destWidth, int destHeight) {
        LayoutParams frameParams = (LayoutParams) view.getLayoutParams();
        frameParams.leftMargin = posX;
        frameParams.topMargin = posY;
        frameParams.width = destWidth;
        frameParams.height = destHeight;
        view.setLayoutParams(frameParams);
        if (isAnimOn){
            startAlphaAnimation(view, 0.0f, 1.0f, animDuration, new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    viewCache.add(view);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }else{
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
            if (view.getAlpha() != 1.0f)
                view.setAlpha(1.0f);

        }
    }

}
