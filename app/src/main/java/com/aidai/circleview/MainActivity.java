package com.aidai.circleview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * @author MrSimpleZ
 */
public class MainActivity extends AppCompatActivity {

		private SeekBar mSeekBar;
		private CircleProgressBar mCircleProgressBar;
		private Button mBtnAnimStart;
		private Button mBtnAnimStop;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				mSeekBar = findViewById(R.id.sb_contet);
				mCircleProgressBar = findViewById(R.id.cpb_progress);
				mBtnAnimStart = findViewById(R.id.btn_start);
				mBtnAnimStop = findViewById(R.id.btn_end);
				ObjectAnimator animator = ObjectAnimator.ofInt(mCircleProgressBar, "argle", 0, 360);
				animator.setDuration(3000);
				animator.setRepeatCount(-1);
				mBtnAnimStart.setOnClickListener(v -> animator.start());
				mBtnAnimStop.setOnClickListener(v -> {
						animator.end();
						mCircleProgressBar.setArgle(0);
				});

				mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
								mCircleProgressBar.setArgle(i);
						}

						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {

						}

						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {

						}
				});
		}
}
