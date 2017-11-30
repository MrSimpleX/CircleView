package com.aidai.circleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: CircleView
 * @Package com.aidai.circleview
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/11/28 18:23
 */
public class CircleProgressBar extends View {

		private Paint mUnCheckPaint;
		private Paint mCheckedPaint;
		private Paint mTextPaint;
		private Paint mPointPaint;

		private int mUnCheckColor;
		private int mCheckedColor;
		private int mTextColor;

		private int mRadius = 250;
		private int mScreenWidth;
		private int mScreenHeight;
		private int mUnCheckStoke = 10;
		private int mCheckedStoke = 25;
		private int mArgle = 80;
		private Bitmap mPointBitMap;

		public CircleProgressBar(Context context) {
				super(context);
				init();
		}

		public CircleProgressBar(Context context, AttributeSet attrs) {
				super(context, attrs);
				init();
				initAttr(attrs);
		}

		public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
				super(context, attrs, defStyleAttr);
				init();
				initAttr(attrs);
		}

		private void initAttr(AttributeSet attrs) {

		}

		private void init() {
				mUnCheckPaint = new Paint();
				mUnCheckPaint.setStyle(Paint.Style.STROKE);
				mUnCheckPaint.setStrokeWidth(mUnCheckStoke);
				mUnCheckPaint.setAntiAlias(true);

				mCheckedPaint = new Paint();
				mCheckedPaint.setStyle(Paint.Style.STROKE);
				mCheckedPaint.setStrokeWidth(mCheckedStoke);
				mCheckedPaint.setStrokeCap(Paint.Cap.ROUND);
				mCheckedPaint.setAntiAlias(true);

				mTextPaint = new Paint();
				mTextPaint.setColor(Color.parseColor("#FFFFFF"));

				mPointPaint = new Paint();
				mPointBitMap = BitmapFactory.decodeResource(getResources(), R.mipmap.point);
		}

		@Override
		protected synchronized void onDraw(Canvas canvas) {
				super.onDraw(canvas);
				mScreenWidth = canvas.getWidth();
				mScreenHeight = mRadius * 2 + 80;

				//移动画布至中心
				mUnCheckPaint.setColor(Color.parseColor("#5cFFFFFF"));
				canvas.drawCircle(mScreenWidth / 2, mScreenHeight / 2, mRadius, mUnCheckPaint);

				mCheckedPaint.setColor(Color.parseColor("#FFFFFF"));

				RectF rectF = new RectF(mScreenWidth / 2 - mRadius,
					mScreenHeight / 2 - mRadius,
					mScreenWidth / 2 + mRadius,
					mScreenHeight / 2 + mRadius);
				canvas.drawArc(rectF, -90, mArgle,false, mCheckedPaint);

//				canvas.drawText("");

//				canvas.drawPoint(getArcX(mArgle), getArcY(mArgle), mPointPaint);
				canvas.drawBitmap(mPointBitMap, getArcX(mArgle) - mPointBitMap.getWidth() /2, getArcY(mArgle)- mPointBitMap.getWidth() /2, mPointPaint);
		}

		public float getArcX(int angle){
				float showX ;
				float x ;
				if(angle < 90){
						x = (float) Math.cos(Math.PI * (90 -angle) / 180) * mRadius;
						showX = mScreenWidth / 2 + x;
						Log.i("showX", showX + "radius" + mRadius);
				}else if(angle > 90){
						x = (float)Math.cos(Math.PI * (angle -90) / 180) * mRadius;
						showX = mScreenWidth / 2 + x;
						Log.i("showX", showX + "radius" + mRadius);
				}else if(angle > 180){
						x = (float)Math.cos(Math.PI * (angle -180) / 180) * mRadius;
						showX = mScreenWidth / 2 - x;
						Log.i("showX", showX + "radius" + mRadius);
				}else{
						x = (float)Math.cos(Math.PI * (angle -270) / 180) * mRadius;
						showX = mScreenWidth / 2 - x;
						Log.i("showX", showX + "radius" + mRadius);
				}

				return showX;
		}

		public float getArcY(int angle){
				float showY ;
				float y ;
				if(angle < 90){
						y = (float)Math.sin(Math.PI * (90-angle) / 180) * mRadius;
						showY = mScreenHeight / 2 - y;
						Log.i("showY", showY + "radius" + mRadius);
				}else if(angle > 90){
						y = (float)Math.sin(Math.PI * (angle -90) / 180) * mRadius;
						showY = mScreenHeight / 2 + y;
						Log.i("showY", showY + "radius" + mRadius);
				}else if(angle > 180){
						y = (float)Math.sin(Math.PI * (angle -180) / 180) * mRadius;
						showY = mScreenHeight / 2 + y;
						Log.i("showY", showY + "radius" + mRadius);
				}else{
						y = (float)Math.sin(Math.PI * (angle -270) / 180) * mRadius;
						showY = mScreenHeight / 2 - y;
						Log.i("showY", showY + "radius" + mRadius);
				}

				return showY;
		}

		public void setArgle(int arg){
				mArgle = arg;
				invalidate();
		}

		public int getArgle(){
				return mArgle;
		}
}
