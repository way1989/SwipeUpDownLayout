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
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class ClockDrawable extends FancyDrawable {
	private Context mContext;
	private final Paint mPaint;
	private static final Paint sPaint = new Paint();
	private boolean mChanged;
	private float mHour;
	private float mMinutes;
	private String mTimeZoneId;

	public ClockDrawable(final Context context) {
		mContext = context;
		mPaint = new Paint();
		mPaint.setFilterBitmap(true);
		mPaint.setDither(true);

		registerListener();

		// Make sure we update to the current time
		onTimeChanged();
	}

	@Override
	public void registerListener() {
		IntentFilter filter = new IntentFilter();

		filter.addAction(Intent.ACTION_TIME_TICK);
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
		sPaint.setColor(Color.parseColor("#ff353535"));
		sPaint.setAlpha(mPaint.getAlpha());
		sPaint.setAntiAlias(true);
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

		// Draw dial background
		sPaint.setColor(Color.parseColor("#ff282828"));
		canvas.drawCircle(bounds.centerX(), bounds.centerY(), availableWidth / 3, sPaint);

		// Draw a little white circle point
		sPaint.setColor(Color.parseColor("#ffffff"));
		canvas.drawCircle(x + (x / 3.0f), y, 3, sPaint);

		// Draw minute hand
		canvas.save();
		canvas.rotate(mMinutes / 60.0f * 360.0f, x, y);
		sPaint.setColor(Color.parseColor("#ffffff"));
		sPaint.setAlpha(80);
		RectF rectMinuteHandF = new RectF(x - 2, y - 31, x + 2, y + 2);
		canvas.drawRoundRect(rectMinuteHandF, 2.0f, 2.0f, sPaint);
		canvas.restore();

		// Draw hour hand
		canvas.save();
		canvas.rotate(mHour / 12.0f * 360.0f, x, y);
		sPaint.setColor(Color.parseColor("#ffffff"));
		sPaint.setAlpha(mPaint.getAlpha());
		RectF rectHourHandF = new RectF(x - 2, y - 21, x + 2, y + 2);
		canvas.drawRoundRect(rectHourHandF, 2.0f, 2.0f, sPaint);
		canvas.restore();

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

		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		mMinutes = minute + second / 60.0f;
		mHour = hour + mMinutes / 60.0f;
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
