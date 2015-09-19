package com.example.test;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

public abstract class FancyDrawable extends Drawable {
	protected final Handler mHandler = new Handler(Looper.getMainLooper());

	protected int mIntrinsicHeight;
	protected int mIntrinsicWidth;

	public abstract void registerListener();

	public abstract void unRegisterListener();

	@Override
	public int getIntrinsicHeight() {
		return mIntrinsicHeight;
	}

	@Override
	public int getIntrinsicWidth() {
		return mIntrinsicWidth;
	}

	public void setIntrinsicSize(int width, int height) {
		mIntrinsicWidth = width;
		mIntrinsicHeight = height;
	}
}
