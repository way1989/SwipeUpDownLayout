package com.example.test;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

public class CalendarDrawable extends FancyDrawable {
	private static final Paint sPaint = new Paint();
	private Context mContext;
	private final Paint mPaint;
	private boolean mChanged;
	private String mTimeZoneId;
	private String mDateTimeDay;

	public CalendarDrawable(final Context context) {
		mContext = context;
		mPaint = new Paint();
		mPaint.setFilterBitmap(true);
		mPaint.setDither(true);

		registerListener();
		onTimeChanged();
	}

	@Override
	public void registerListener() {
		IntentFilter filter = new IntentFilter();

		filter.addAction(Intent.ACTION_DATE_CHANGED);
		filter.addAction(Intent.ACTION_TIME_CHANGED);
		filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);

		mContext.registerReceiver(mIntentReceiver, filter, null, mHandler);

	}

	@Override
	public void unRegisterListener() {
		try {
			mContext.unregisterReceiver(mIntentReceiver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void draw(Canvas canvas) {
		Log.i("way", "ClockDrawable draw...");
		final Rect bounds = getBounds();
		if (!isVisible() || bounds.isEmpty()) {
			return;
		}

		boolean changed = mChanged;
		if (changed) {
			mChanged = false;
		}
		// Draw background color.
		sPaint.setColor(Color.parseColor("#ffffffff"));
		sPaint.setAlpha(mPaint.getAlpha());
		RectF rectF = new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
		canvas.drawRoundRect(rectF, 10.0f, 10.0f, sPaint);

		// enable width and height
		int availableWidth = bounds.right - bounds.left;
		int availableHeight = bounds.bottom - bounds.top;
		if (mIntrinsicWidth > 0 && mIntrinsicHeight > 0) {
			availableWidth = mIntrinsicWidth;
			availableHeight = mIntrinsicHeight;
		}

		int x = availableWidth / 2;
		int y = availableHeight / 2;
		// Draw divider
		sPaint.setColor(Color.parseColor("#e0e0e0"));
		Rect rectLineF = new Rect(bounds.left, y - 1, bounds.right, y + 1);
		canvas.drawRect(rectLineF, sPaint);

		// Draw left little point
		sPaint.setColor(Color.parseColor("#ff5900"));
		RectF rectLeftF = new RectF(bounds.left + 2, y - 6, bounds.left + 5, y + 6);
		canvas.drawRoundRect(rectLeftF, 2.0f, 2.0f, sPaint);

		// Draw right little point
		// sPaint.setColor(Color.parseColor("#ff5900"));
		RectF rectRightF = new RectF(bounds.right - 5, y - 6, bounds.right - 2, y + 6);
		canvas.drawRoundRect(rectRightF, 2.0f, 2.0f, sPaint);

		// Draw date
		sPaint.setTypeface(Typeface.create("sans-serif-thin", Typeface.BOLD));
		sPaint.setTextAlign(Align.CENTER);
		sPaint.setAntiAlias(true);
		sPaint.setTextSize(x);
		Rect textRect = new Rect(bounds.left, bounds.top, bounds.right, bounds.bottom);
		sPaint.getTextBounds(mDateTimeDay, 0, 2, textRect);
		canvas.drawText(mDateTimeDay, 0, 2, bounds.centerX(), bounds.centerY() + textRect.height() / 2, sPaint);
	}

	@Override
	public void setAlpha(int alpha) {
		mPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		mPaint.setColorFilter(cf);
	}

	@Override
	public int getOpacity() {
		return android.graphics.PixelFormat.OPAQUE;
	}

	public void setTimeZone(String id) {
		mTimeZoneId = id;
		onTimeChanged();
	}

	private void onTimeChanged() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());

		if (mTimeZoneId != null) {
			calendar.setTimeZone(TimeZone.getTimeZone(mTimeZoneId));
		}

		int day = calendar.get(Calendar.DAY_OF_MONTH);

		mDateTimeDay = String.valueOf(day);
		mChanged = true;
		invalidateSelf();
	}

	private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
				String tz = intent.getStringExtra("time-zone");
				mTimeZoneId = TimeZone.getTimeZone(tz).getID();
			}
			onTimeChanged();
		}
	};

}
