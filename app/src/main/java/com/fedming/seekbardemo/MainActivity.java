package com.fedming.seekbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * SeekBar实战，自定义一个滑动验证
 */

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private boolean isSliderRight = false; //设置一个全部变量保存验证状态
    private TextView hintTextView; //滑动验证提示
    private TextView slideSuccessTextView; //验证成功提示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hintTextView = (TextView) findViewById(R.id.slider_hint);
        slideSuccessTextView = (TextView) findViewById(R.id.slider_success);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //这里是个大坑，SeekBar在不同安卓版本OR机型上有着不同表现，默认padding不同
        //在代码中手动设置padding，避免发生无法"matchparent"的情况
        seekBar.setPadding(0, 0, 0, 0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                hintTextView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() != 100) {
                    seekBar.setProgress(0);
                    hintTextView.setVisibility(View.VISIBLE);
                    Log.i("slide state", "please slide again...");
                } else {
                    hintTextView.setVisibility(View.GONE);
                    slideSuccessTextView.setVisibility(View.VISIBLE);
                    seekBar.setEnabled(false);
                    isSliderRight = true;
                    String state = Boolean.valueOf(isSliderRight).toString();
                    Log.i("slide state", state);
                }
            }
        });

        String state = Boolean.valueOf(isSliderRight).toString();
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }
}
