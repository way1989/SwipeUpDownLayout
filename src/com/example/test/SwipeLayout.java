package com.example.test;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SwipeLayout extends FrameLayout implements RecentsSwipeHelper.Callback {
	private RecentsSwipeHelper mSwipeHelper;
	private Context mContext;
	private Display mDisplay;
	private DisplayMetrics mDisplayMetrics = new DisplayMetrics();

	public SwipeLayout(Context context) {
		this(context, null);
	}

	public SwipeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		float densityScale = getResources().getDisplayMetrics().density;
		float pagingTouchSlop = ViewConfiguration.get(mContext).getScaledPagingTouchSlop();
		mSwipeHelper = new RecentsSwipeHelper(RecentsSwipeHelper.Y, this, densityScale, pagingTouchSlop);
		mDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		mDisplay.getMetrics(mDisplayMetrics);
		mSwipeHelper.setScreenHeight(mDisplayMetrics.heightPixels);
	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		float densityScale = getResources().getDisplayMetrics().density;
		mSwipeHelper.setDensityScale(densityScale);
		float pagingTouchSlop = ViewConfiguration.get(mContext).getScaledPagingTouchSlop();
		mSwipeHelper.setPagingTouchSlop(pagingTouchSlop);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mSwipeHelper.onInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mSwipeHelper.getCurrentTouchRecentItem() == null && mSwipeHelper.getActionDownOutListScope()) {
			return false;
		} else {
			return mSwipeHelper.onTouchEvent(event) || super.onTouchEvent(event);
		}
	}

	@Override
	public View getChildAtPosition(MotionEvent ev) {
		return this;
	}

	@Override
	public View getChildContentView(View v) {
		// TODO Auto-generated method stub
		return v.findViewById(R.id.icon);
	}

	@Override
	public boolean canChildBeDismissed(View v) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onBeginDrag(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildDismissed(View v) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDragCancelled(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLockState(View v, boolean isAnimatinEnd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateItemLabel(View v, boolean visible) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDeleteAppLable(boolean opacity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllViewsOnAnimationEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsOutListScope(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}

}
