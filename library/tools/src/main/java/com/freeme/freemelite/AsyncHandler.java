/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.freeme.freemelite;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Helper class for managing the background thread used to perform io operations
 */
public final class AsyncHandler {
    private static final HandlerThread sHandlerThread = new HandlerThread("Common-AsyncHandler");
    private static final Handler sHandler;

    private static final Thread sUiThread;
    private static final Handler sUiHandler;

    static {
        sHandlerThread.start();
        sHandler = new Handler(sHandlerThread.getLooper());

        sUiThread = Thread.currentThread();
        sUiHandler = new Handler();
    }

    public static void post(Runnable r) {
        sHandler.post(r);
    }

    public static void postDelayed(Runnable runnable,long delayMillis){
        sHandler.postDelayed(runnable,delayMillis);
    }

    public static void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != sUiThread) {
            sUiHandler.post(action);
        } else {
            action.run();
        }
    }

    public static void removeRunnable(Runnable runnable){
        sHandler.removeCallbacks(runnable);
    }

    private AsyncHandler() {
    }
}
