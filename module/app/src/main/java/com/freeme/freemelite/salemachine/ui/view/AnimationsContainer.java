package com.freeme.freemelite.salemachine.ui.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.widget.ImageView;

import com.freeme.freemelite.salemachine.R;

import java.lang.ref.SoftReference;

public class AnimationsContainer {
    private int FPS = 30;  // animation FPS

    // single instance procedures
    private static AnimationsContainer mInstance;

    private AnimationsContainer() {
    }

    ;

    public static AnimationsContainer getInstance() {
        if (mInstance == null)
            mInstance = new AnimationsContainer();
        return mInstance;
    }

    // animation voice frames
    private int[] mVoiceAnimFrames = {R.mipmap.voice_animation_00000, R.mipmap.voice_animation_00001, R.mipmap.voice_animation_00002, R.mipmap.voice_animation_00003, R.mipmap.voice_animation_00004
            , R.mipmap.voice_animation_00005, R.mipmap.voice_animation_00006, R.mipmap.voice_animation_00007, R.mipmap.voice_animation_00008, R.mipmap.voice_animation_00009, R.mipmap.voice_animation_00010
            , R.mipmap.voice_animation_00011, R.mipmap.voice_animation_00012, R.mipmap.voice_animation_00013, R.mipmap.voice_animation_00014, R.mipmap.voice_animation_00015, R.mipmap.voice_animation_00016
            , R.mipmap.voice_animation_00017, R.mipmap.voice_animation_00018, R.mipmap.voice_animation_00019, R.mipmap.voice_animation_00020, R.mipmap.voice_animation_00021, R.mipmap.voice_animation_00022
            , R.mipmap.voice_animation_00023, R.mipmap.voice_animation_00024, R.mipmap.voice_animation_00025, R.mipmap.voice_animation_00026, R.mipmap.voice_animation_00027, R.mipmap.voice_animation_00028
            , R.mipmap.voice_animation_00029, R.mipmap.voice_animation_00030, R.mipmap.voice_animation_00031, R.mipmap.voice_animation_00032, R.mipmap.voice_animation_00033, R.mipmap.voice_animation_00034
            , R.mipmap.voice_animation_00035, R.mipmap.voice_animation_00036, R.mipmap.voice_animation_00037, R.mipmap.voice_animation_00038, R.mipmap.voice_animation_00039, R.mipmap.voice_animation_00040
            , R.mipmap.voice_animation_00041, R.mipmap.voice_animation_00042, R.mipmap.voice_animation_00043, R.mipmap.voice_animation_00044, R.mipmap.voice_animation_00045, R.mipmap.voice_animation_00046
            , R.mipmap.voice_animation_00047, R.mipmap.voice_animation_00048, R.mipmap.voice_animation_00049, R.mipmap.voice_animation_00050, R.mipmap.voice_animation_00051, R.mipmap.voice_animation_00052
            , R.mipmap.voice_animation_00053, R.mipmap.voice_animation_00054, R.mipmap.voice_animation_00055, R.mipmap.voice_animation_00056, R.mipmap.voice_animation_00057, R.mipmap.voice_animation_00058
            , R.mipmap.voice_animation_00059, R.mipmap.voice_animation_00060, R.mipmap.voice_animation_00061, R.mipmap.voice_animation_00062, R.mipmap.voice_animation_00063, R.mipmap.voice_animation_00064
            , R.mipmap.voice_animation_00065, R.mipmap.voice_animation_00066, R.mipmap.voice_animation_00067, R.mipmap.voice_animation_00068, R.mipmap.voice_animation_00069, R.mipmap.voice_animation_00070
            , R.mipmap.voice_animation_00072, R.mipmap.voice_animation_00073, R.mipmap.voice_animation_00074, R.mipmap.voice_animation_00075, R.mipmap.voice_animation_00076, R.mipmap.voice_animation_00077
            , R.mipmap.voice_animation_00078, R.mipmap.voice_animation_00079, R.mipmap.voice_animation_00080, R.mipmap.voice_animation_00081, R.mipmap.voice_animation_00082, R.mipmap.voice_animation_00083
            , R.mipmap.voice_animation_00084, R.mipmap.voice_animation_00085, R.mipmap.voice_animation_00086, R.mipmap.voice_animation_00087, R.mipmap.voice_animation_00088, R.mipmap.voice_animation_00089
            , R.mipmap.voice_animation_00090, R.mipmap.voice_animation_00091, R.mipmap.voice_animation_00092, R.mipmap.voice_animation_00093, R.mipmap.voice_animation_00094, R.mipmap.voice_animation_00095
            , R.mipmap.voice_animation_00096, R.mipmap.voice_animation_00097, R.mipmap.voice_animation_00098, R.mipmap.voice_animation_00099, R.mipmap.voice_animation_00110, R.mipmap.voice_animation_00111
            , R.mipmap.voice_animation_00112, R.mipmap.voice_animation_00113, R.mipmap.voice_animation_00114, R.mipmap.voice_animation_00115, R.mipmap.voice_animation_00116, R.mipmap.voice_animation_00117
            , R.mipmap.voice_animation_00118, R.mipmap.voice_animation_00119, R.mipmap.voice_animation_00120, R.mipmap.voice_animation_00121, R.mipmap.voice_animation_00122, R.mipmap.voice_animation_00123
            , R.mipmap.voice_animation_00124, R.mipmap.voice_animation_00125, R.mipmap.voice_animation_00126, R.mipmap.voice_animation_00127, R.mipmap.voice_animation_00128, R.mipmap.voice_animation_00129
            , R.mipmap.voice_animation_00130, R.mipmap.voice_animation_00131, R.mipmap.voice_animation_00132, R.mipmap.voice_animation_00133, R.mipmap.voice_animation_00134, R.mipmap.voice_animation_00135
            , R.mipmap.voice_animation_00136, R.mipmap.voice_animation_00137, R.mipmap.voice_animation_00138, R.mipmap.voice_animation_00139, R.mipmap.voice_animation_00140, R.mipmap.voice_animation_00141
            , R.mipmap.voice_animation_00142, R.mipmap.voice_animation_00143, R.mipmap.voice_animation_00144, R.mipmap.voice_animation_00145, R.mipmap.voice_animation_00146, R.mipmap.voice_animation_00147
            , R.mipmap.voice_animation_00148, R.mipmap.voice_animation_00149};

    //animation voice undo frames
    private int[] mProgressAnimFrames = {R.mipmap.voice_undo_00061, R.mipmap.voice_undo_00062, R.mipmap.voice_undo_00063, R.mipmap.voice_undo_00064, R.mipmap.voice_undo_00065
            , R.mipmap.voice_undo_00066, R.mipmap.voice_undo_00067, R.mipmap.voice_undo_00068, R.mipmap.voice_undo_00069, R.mipmap.voice_undo_00070, R.mipmap.voice_undo_00071
            , R.mipmap.voice_undo_00072, R.mipmap.voice_undo_00073, R.mipmap.voice_undo_00074, R.mipmap.voice_undo_00075, R.mipmap.voice_undo_00076, R.mipmap.voice_undo_00077
            , R.mipmap.voice_undo_00078, R.mipmap.voice_undo_00079, R.mipmap.voice_undo_00080, R.mipmap.voice_undo_00081, R.mipmap.voice_undo_00082, R.mipmap.voice_undo_00083
            , R.mipmap.voice_undo_00084, R.mipmap.voice_undo_00085, R.mipmap.voice_undo_00086, R.mipmap.voice_undo_00087, R.mipmap.voice_undo_00088, R.mipmap.voice_undo_00089
            , R.mipmap.voice_undo_00090, R.mipmap.voice_undo_00091, R.mipmap.voice_undo_00092, R.mipmap.voice_undo_00093, R.mipmap.voice_undo_00094, R.mipmap.voice_undo_00095
            , R.mipmap.voice_undo_00096, R.mipmap.voice_undo_00097, R.mipmap.voice_undo_00098, R.mipmap.voice_undo_00099, R.mipmap.voice_undo_00100, R.mipmap.voice_undo_00101
            , R.mipmap.voice_undo_00102, R.mipmap.voice_undo_00103, R.mipmap.voice_undo_00104, R.mipmap.voice_undo_00105, R.mipmap.voice_undo_00106, R.mipmap.voice_undo_00107
            , R.mipmap.voice_undo_00108, R.mipmap.voice_undo_00109, R.mipmap.voice_undo_00110, R.mipmap.voice_undo_00111, R.mipmap.voice_undo_00112, R.mipmap.voice_undo_00113
            , R.mipmap.voice_undo_00114, R.mipmap.voice_undo_00115, R.mipmap.voice_undo_00116, R.mipmap.voice_undo_00117, R.mipmap.voice_undo_00118, R.mipmap.voice_undo_00119
            , R.mipmap.voice_undo_00120, R.mipmap.voice_undo_00121, R.mipmap.voice_undo_00122, R.mipmap.voice_undo_00123, R.mipmap.voice_undo_00124, R.mipmap.voice_undo_00125
            , R.mipmap.voice_undo_00126, R.mipmap.voice_undo_00127, R.mipmap.voice_undo_00128, R.mipmap.voice_undo_00129, R.mipmap.voice_undo_00130, R.mipmap.voice_undo_00131
            , R.mipmap.voice_undo_00131, R.mipmap.voice_undo_00132, R.mipmap.voice_undo_00133, R.mipmap.voice_undo_00134, R.mipmap.voice_undo_00135, R.mipmap.voice_undo_00136
            , R.mipmap.voice_undo_00137, R.mipmap.voice_undo_00138, R.mipmap.voice_undo_00139, R.mipmap.voice_undo_00140, R.mipmap.voice_undo_00141, R.mipmap.voice_undo_00142
            , R.mipmap.voice_undo_00143, R.mipmap.voice_undo_00144, R.mipmap.voice_undo_00145, R.mipmap.voice_undo_00146, R.mipmap.voice_undo_00147, R.mipmap.voice_undo_00148
            , R.mipmap.voice_undo_00149, R.mipmap.voice_undo_00150, R.mipmap.voice_undo_00151, R.mipmap.voice_undo_00152, R.mipmap.voice_undo_00153, R.mipmap.voice_undo_00154
            , R.mipmap.voice_undo_00155, R.mipmap.voice_undo_00156, R.mipmap.voice_undo_00157, R.mipmap.voice_undo_00158, R.mipmap.voice_undo_00159, R.mipmap.voice_undo_00160
            , R.mipmap.voice_undo_00161, R.mipmap.voice_undo_00162, R.mipmap.voice_undo_00163, R.mipmap.voice_undo_00164, R.mipmap.voice_undo_00165, R.mipmap.voice_undo_00166
            , R.mipmap.voice_undo_00167, R.mipmap.voice_undo_00168, R.mipmap.voice_undo_00169, R.mipmap.voice_undo_00170, R.mipmap.voice_undo_00171, R.mipmap.voice_undo_00172
            , R.mipmap.voice_undo_00173, R.mipmap.voice_undo_00174, R.mipmap.voice_undo_00175, R.mipmap.voice_undo_00176, R.mipmap.voice_undo_00177, R.mipmap.voice_undo_00178
            , R.mipmap.voice_undo_00179, R.mipmap.voice_undo_00180, R.mipmap.voice_undo_00181, R.mipmap.voice_undo_00182, R.mipmap.voice_undo_00183, R.mipmap.voice_undo_00184
            , R.mipmap.voice_undo_00185, R.mipmap.voice_undo_00186, R.mipmap.voice_undo_00187, R.mipmap.voice_undo_00188, R.mipmap.voice_undo_00189, R.mipmap.voice_undo_00190
            , R.mipmap.voice_undo_00191, R.mipmap.voice_undo_00192, R.mipmap.voice_undo_00193, R.mipmap.voice_undo_00194, R.mipmap.voice_undo_00195, R.mipmap.voice_undo_00196
            , R.mipmap.voice_undo_00197, R.mipmap.voice_undo_00198, R.mipmap.voice_undo_00199};

    /**
     * @param imageView
     * @return voice animation
     */
    public FramesSequenceAnimation createVoiceFrameAnim(ImageView imageView) {
        return new FramesSequenceAnimation(imageView, mVoiceAnimFrames, FPS);
    }

    public FramesSequenceAnimation createVoiceUndoFrameAnim(ImageView imageView) {
        return new FramesSequenceAnimation(imageView, mProgressAnimFrames, FPS);
    }

    /**
     * AnimationPlayer. Plays animation frames sequence in loop
     */
    public class FramesSequenceAnimation {
        private int[] mFrames; // animation frames
        private int mIndex; // current frame
        private boolean mShouldRun; // true if the animation should continue running. Used to stop the animation
        private boolean mIsRunning; // true if the animation currently running. prevents starting the animation twice
        private SoftReference<ImageView> mSoftReferenceImageView; // Used to prevent holding ImageView when it should be dead.
        private Handler mHandler;
        private int mDelayMillis;
        private boolean mIsBack = false;
        //private OnAnimationStoppedListener mOnAnimationStoppedListener;

        private Bitmap mBitmap = null;
        private BitmapFactory.Options mBitmapOptions;

        public FramesSequenceAnimation(ImageView imageView, int[] frames, int fps) {
            mHandler = new Handler();
            mFrames = frames;
            mIndex = -1;
            mSoftReferenceImageView = new SoftReference<ImageView>(imageView);
            mShouldRun = false;
            mIsRunning = false;
            mDelayMillis = 1000 / fps;

            imageView.setImageResource(mFrames[0]);

            // use in place bitmap to save GC work (when animation images are the same size & type)
            if (Build.VERSION.SDK_INT >= 11) {
                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                int width = bmp.getWidth();
                int height = bmp.getHeight();
                Bitmap.Config config = bmp.getConfig();
                mBitmap = Bitmap.createBitmap(width, height, config);
                mBitmapOptions = new BitmapFactory.Options();
                // setup bitmap reuse options.
                mBitmapOptions.inBitmap = mBitmap;
                mBitmapOptions.inMutable = true;
                mBitmapOptions.inSampleSize = 1;
            }
        }

        private int getNext() {
            if (!mIsBack) {
                mIndex++;
            } else {
                mIndex--;
            }
            if (mIndex >= mFrames.length) {
                mIndex--;
                mIsBack = true;
            } else if (mIndex <= 0) {
                mIsBack = false;
            }
            return mFrames[mIndex];
        }

        /**
         * Starts the animation
         */
        public synchronized void start() {
            mShouldRun = true;
            if (mIsRunning)
                return;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = mSoftReferenceImageView.get();
                    if (!mShouldRun || imageView == null) {
                        mIsRunning = false;
//                        if (mOnAnimationStoppedListener != null) {
//                            mOnAnimationStoppedListener.AnimationStopped();
//                        }
                        return;
                    }

                    mIsRunning = true;
                    mHandler.postDelayed(this, mDelayMillis);

                    if (imageView.isShown()) {
                        int imageRes = getNext();
                        if (mBitmap != null) { // so Build.VERSION.SDK_INT >= 11
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeResource(imageView.getResources(), imageRes, mBitmapOptions);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            } else {
                                imageView.setImageResource(imageRes);
                                mBitmap.recycle();
                                mBitmap = null;
                            }
                        } else {
                            imageView.setImageResource(imageRes);
                        }
                    }

                }
            };

            mHandler.post(runnable);
        }

        /**
         * Stops the animation
         */
        public synchronized void stop() {
            mShouldRun = false;
        }
    }
}
