package com.dnkilic.waveform;

/*
Copyright [2016] [Doğan Kılıç]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WaveView extends WebView {
    private static final String TAG = "WaveView";

    private GestureDetector gestureDetector;
    private OnClickListener onClickListener;
    public int waveState = -1;

    WaveGestureListener waveGestureListener = new WaveGestureListener();

    public WaveView(Context context) {
        this(context, null, 0);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(context, waveGestureListener);

        initializeWebSettings();

        setWebChromeClient(new WebChromeClient());
        setBackgroundColor(Color.TRANSPARENT);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        loadUrl("file:///android_asset/voicewave.html");
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeWebSettings() {
        WebSettings webSetting = getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setBuiltInZoomControls(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void initialize(DisplayMetrics dm) {
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>initialize start");
        loadUrl("javascript:SW9.setWidth(\"" + dm.widthPixels * 92 / 100 + "\")");
        loadUrl("javascript:SW9.start(\"" + "\")");
        waveState = WaveState.WAVE_INITIALIZE;
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>initialize end");
    }

    public void stop() {
        loadUrl("javascript:SW9.stop(\"" + "\")");

        removeAllViews();

        clearHistory();
        clearCache(true);

        if (Build.VERSION.SDK_INT < 18) {
            clearView();
        } else {
            loadUrl("about:blank");
        }

        freeMemory();
        pauseTimers();

        loadUrl("file:///android_asset/voicewave.html");
        waveState = WaveState.WAVE_REMOVED;
    }

    public void speechStarted() {
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>speechStarted start:");
        loadUrl("javascript:SW9.setAmplitude(\"" + 1 + "\")");
        waveState = WaveState.WAVE_STARTED;
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>speechStarted end:");

    }

    public void speechEnded() {
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>speechEnded");
        loadUrl("javascript:SW9.setAmplitude(\"" + 0.1 + "\")");
        waveState = WaveState.WAVE_ENDED;
    }

    public void speechPaused() {
        loadUrl("javascript:SW9.setAmplitude(\"" + 0.0 + "\")");
        waveState = WaveState.WAVE_PAUSED;
    }

    private class WaveGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onContextClick(MotionEvent e) {
            if (onClickListener != null) {
                onClickListener.onClick(WaveView.this);
            }
            return super.onContextClick(e);
        }
    }

    public class WaveState {
        public static final int WAVE_INITIALIZE = 0;
        public static final int WAVE_STARTED = 1;
        public static final int WAVE_PAUSED = 2;
        public static final int WAVE_ENDED = 3;
        public static final int WAVE_REMOVED = 4;
    }
}
