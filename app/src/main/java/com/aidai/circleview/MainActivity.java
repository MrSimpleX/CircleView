package com.aidai.circleview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

		private SeekBar mSeekBar;
		private CircleProgressBar mCircleProgressBar;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				mSeekBar = findViewById(R.id.sb_contet);
				mCircleProgressBar = findViewById(R.id.cpb_progress);
				ObjectAnimator animator = ObjectAnimator.ofInt(mCircleProgressBar, "argle", 0, 360);
				animator.setDuration(3000);
				animator.setRepeatCount(-1);
				animator.start();
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
