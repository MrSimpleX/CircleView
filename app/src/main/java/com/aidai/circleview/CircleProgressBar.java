package com.aidai.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: CircleView
 * @Package com.aidai.circleview
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/11/28 18:23
 */
public class CircleProgressBar extends View {

		/**
		 * 默认进度颜色为白色
		 */
		private final int DEFAULT_RECHARED_COLOR = Color.parseColor("#FFFFFF");
		/**
		 * 默认背景进度为半透明白色
		 */
		private final int DEFAULT_UNRECHARED_COLOR = Color.parseColor("#5CFFFFFF");
		/**
		 * 默认半径为250dp
		 */
		private static final int DEFAULT_RADIUS = 250;
		/**
		 * 默认进度宽度为25dp
		 */
		private static final int DEFAULT_REACHARED_STOKE = 25;
		/**
		 * 默认背景宽度为10dp
		 */
		private static final int DEFAULT_UNREACHARED_STOKE = 10;
		/**
		 * 默认绘制角度为0
		 */
		private static final int DEFAULT_REACHARED_ARGLE = 0;

		private Paint mUnCheckPaint;
		private Paint mCheckedPaint;
		private Paint mTextPaint;
		private Paint mPointPaint;

		private int mUnCheckColor;
		private int mCheckedColor;
		private int mTextColor;

		private float mRadius;
		private int mScreenWidth;
		private int mScreenHeight;
		private float mUnCheckStoke;
		private float mCheckedStoke;
		private int mArgle;
		private Bitmap mPointBitMap;

		public CircleProgressBar(Context context) {
				super(context);
				init();
		}

		public CircleProgressBar(Context context, AttributeSet attrs) {
				super(context, attrs);
				initAttr(attrs);
				init();
		}

		public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
				super(context, attrs, defStyleAttr);
				initAttr(attrs);
				init();
		}

		private void initAttr(AttributeSet attrs) {
				TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
				mCheckedColor = typedArray.getColor(R.styleable.CircleProgressBar_reached_color, DEFAULT_RECHARED_COLOR);
				mUnCheckColor = typedArray.getColor(R.styleable.CircleProgressBar_unreached_color, DEFAULT_UNRECHARED_COLOR);
				mCheckedStoke = typedArray.getDimension(R.styleable.CircleProgressBar_reached_stoke, DEFAULT_REACHARED_STOKE);
				mUnCheckStoke = typedArray.getDimension(R.styleable.CircleProgressBar_unreached_stoke, DEFAULT_UNREACHARED_STOKE);
				mRadius = typedArray.getDimension(R.styleable.CircleProgressBar_circle_radius, DEFAULT_RADIUS);
				mTextColor = typedArray.getColor(R.styleable.CircleProgressBar_text_color, DEFAULT_RECHARED_COLOR);
				mArgle = typedArray.getInt(R.styleable.CircleProgressBar_circle_args, DEFAULT_REACHARED_ARGLE);
				typedArray.recycle();
		}

		private void init() {
				mUnCheckPaint = new Paint();
				mUnCheckPaint.setStyle(Paint.Style.STROKE);
				mUnCheckPaint.setStrokeWidth(dip2px(mUnCheckStoke));
				mUnCheckPaint.setAntiAlias(true);

				mCheckedPaint = new Paint();
				mCheckedPaint.setStyle(Paint.Style.STROKE);
				mCheckedPaint.setStrokeWidth(dip2px(mCheckedStoke));
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
				mScreenWidth = dip2px(mRadius) * 2 + 80;
				mScreenHeight = dip2px(mRadius) * 2 + 80;

				//移动画布至中心
				mUnCheckPaint.setColor(mUnCheckColor);
				canvas.drawCircle(mScreenWidth / 2, mScreenHeight / 2, dip2px(mRadius), mUnCheckPaint);

				mCheckedPaint.setColor(mCheckedColor);

				RectF rectF = new RectF(mScreenWidth / 2 - dip2px(mRadius),
					mScreenHeight / 2 - dip2px(mRadius),
					mScreenWidth / 2 + dip2px(mRadius),
					mScreenHeight / 2 + dip2px(mRadius));
				canvas.drawArc(rectF, -90, mArgle, false, mCheckedPaint);

				canvas.drawBitmap(mPointBitMap, getArcX(mArgle) - mPointBitMap.getWidth() / 2, getArcY(mArgle) - mPointBitMap.getWidth() / 2, mPointPaint);
		}

		public float getArcX(int angle) {
				float showX;
				float x;
				if (angle < 90) {
						x = (float) Math.cos(Math.PI * (90 - angle) / 180) * dip2px(mRadius);
						showX = mScreenWidth / 2 + x;
				} else if (angle > 90) {
						x = (float) Math.cos(Math.PI * (angle - 90) / 180) * dip2px(mRadius);
						showX = mScreenWidth / 2 + x;
				} else if (angle > 180) {
						x = (float) Math.cos(Math.PI * (angle - 180) / 180) * dip2px(mRadius);
						showX = mScreenWidth / 2 - x;
				} else {
						x = (float) Math.cos(Math.PI * (angle - 270) / 180) * dip2px(mRadius);
						showX = mScreenWidth / 2 - x;
				}

				return showX;
		}

		public float getArcY(int angle) {
				float showY;
				float y;
				if (angle < 90) {
						y = (float) Math.sin(Math.PI * (90 - angle) / 180) * dip2px(mRadius);
						showY = mScreenHeight / 2 - y;
				} else if (angle > 90) {
						y = (float) Math.sin(Math.PI * (angle - 90) / 180) * dip2px(mRadius);
						showY = mScreenHeight / 2 + y;
				} else if (angle > 180) {
						y = (float) Math.sin(Math.PI * (angle - 180) / 180) * dip2px(mRadius);
						showY = mScreenHeight / 2 + y;
				} else {
						y = (float) Math.sin(Math.PI * (angle - 270) / 180) * dip2px(mRadius);
						showY = mScreenHeight / 2 - y;
				}

				return showY;
		}

		public void setArgle(int arg) {
				mArgle = arg;
				invalidate();
		}

		public int getArgle() {
				return mArgle;
		}

		public int dip2px(float dipValue) {
				final float scale = getContext().getResources().getDisplayMetrics().density;
				return (int) (dipValue * scale + 0.5f);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);

				// 获取宽-测量规则的模式和大小
				int widthMode = MeasureSpec.getMode(widthMeasureSpec);
				int widthSize = MeasureSpec.getSize(widthMeasureSpec);

				// 获取高-测量规则的模式和大小
				int heightMode = MeasureSpec.getMode(heightMeasureSpec);
				int heightSize = MeasureSpec.getSize(heightMeasureSpec);

				int mWidth = dip2px(mRadius) * 2 + 90;
				int mHeight = dip2px(mRadius) * 2 + 90;

				if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
						setMeasuredDimension(mWidth, mHeight);
						// 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
				} else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
						setMeasuredDimension(mWidth, heightSize);
				} else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
						setMeasuredDimension(widthSize, mHeight);
				}
		}
}
