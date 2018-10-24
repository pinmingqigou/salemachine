package com.freeme.freemelite.salemachine.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeme.freemelite.router.base.BaseFragment;
import com.freeme.freemelite.router.payload.ForgeryCardModel;
import com.freeme.freemelite.salemachine.ActivityRouter;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.databinding.FragmentConversationBinding;
import com.freeme.freemelite.router.payload.TextCardContentModel;
import com.freeme.freemelite.salemachine.subject.PaymentSubject;
import com.freeme.freemelite.salemachine.ui.view.AnimationsContainer;
import com.freeme.freemelite.salemachine.ui.view.AnimationsContainer.FramesSequenceAnimation;
import com.freeme.freemelite.salemachine.ui.view.adapter.ConversationAdapter;
import com.freeme.freemelite.salemachine.ui.view.adapter.SessionPromptAdapter;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class ConversationFragment extends BaseFragment {
    private static final String TAG = "ConversationFragment";

    private FragmentConversationBinding mBinding;
    private ConversationAdapter mConversationAdapter;
    private ConversationViewModel mConversationViewModel;
    private boolean mIsFromActivityResult = false;
    private MainViewModel mMainViewModel;
    private FramesSequenceAnimation mVoiceFrameAnim;
    private FramesSequenceAnimation mVoiceUndoFrameAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreate");
        mConversationViewModel = ViewModelProviders.of(this).get(ConversationViewModel.class);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mConversationViewModel.bindLifecycle(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        mBinding = DataBindingUtil.bind(view);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onActivityCreated");

        mBinding.getRoot().findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>HomeButton.onClick");
                ActivityRouter.startFeatureActivity(view.getContext());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onAttach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>setUserVisibleHint：" + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onHiddenChanged：" + hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onStart");
        mVoiceFrameAnim = AnimationsContainer.getInstance().createVoiceFrameAnim(mBinding.voiceAnimationIv);
        mVoiceUndoFrameAnim = AnimationsContainer.getInstance().createVoiceUndoFrameAnim(mBinding.microphoneDefaultIv);

        mConversationViewModel.mSessionPromptContainerVisibility.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer visibility) {
                if (visibility != null && visibility == View.VISIBLE) {
                    mBinding.sessionPromptContainer.setVisibility(View.VISIBLE);
                    mBinding.conversationRecyclerView.setVisibility(View.GONE);
                } else {
                    mBinding.sessionPromptContainer.setVisibility(View.GONE);
                    mBinding.conversationRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        mConversationViewModel.mShowScanCode.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    Log.e(TAG, ">>>>>>>>>>>>>>>>>startScanCodeActivity");
                    ActivityRouter.startScanCodeActivity(ConversationFragment.this);
                    mConversationViewModel.mShowScanCode.setValue(false);
                }
            }
        });

        mMainViewModel.mDialogStateWrapper.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>mDialogStateWrapper.observe:" + integer);
                if (integer == SaleMachineCofig.DialogState.LISTENING) {
                    voiceFrameStart();
                } else {
                    voiceFramenStop();
                }
            }
        });
    }

    private void voiceFrameStart() {
        mBinding.voiceAnimationIv.setVisibility(View.VISIBLE);
        mBinding.microphoneDefaultIv.setVisibility(View.GONE);
        mVoiceFrameAnim.start();
    }

    private void voiceFramenStop() {
        mVoiceFrameAnim.stop();
        mBinding.voiceAnimationIv.setVisibility(View.GONE);
        mBinding.microphoneDefaultIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onResume");
        mVoiceUndoFrameAnim.start();
        if (!mIsFromActivityResult) {
            setupSessionPromptRecyclerView();
            setupConversationRecycleView();
            setupVoiceLineView();
        } else {
            mIsFromActivityResult = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>onDestroyView");
        mConversationViewModel.mSessionPromptContainerVisibility.setValue(View.VISIBLE);
        mConversationAdapter.mModels.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onDetach");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onDestroy");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>onActivityResult:" + requestCode + "/" + resultCode);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == ActivityRouter.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            mIsFromActivityResult = true;
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(ActivityRouter.RESULT_TYPE) == ActivityRouter.RESULT_SUCCESS) {
                    String result = bundle.getString(ActivityRouter.RESULT_STRING);
                    Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>解析结果:" + result);
                    ForgeryCardModel forgeryCardModel = new ForgeryCardModel();
                    TextCardContentModel textCardContentModel = mConversationViewModel.mTextCardContentModelWrapper.getValue();
                    String payMoney = mConversationViewModel.mPayMoneyWrapper.getValue();
                    if (textCardContentModel != null && !TextUtils.isEmpty(textCardContentModel.getPrice())) {
                        forgeryCardModel.content = "您已支付" + textCardContentModel.getPrice() + "元，支付码为：" + result;
                        mConversationViewModel.mTextCardContentModelWrapper.setValue(null);
                    } else if (!TextUtils.isEmpty(payMoney)) {
                        String[] split = payMoney.split("支付");
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>支付：" + split[0] + "/" + split[1] + "/" + split[2]);
                        String[] money = split[2].split("元");
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>元：" + money[0]);
                        forgeryCardModel.content = "您已支付" + money[0] + "元，支付码为：" + result;
                        mConversationViewModel.mPayMoneyWrapper.setValue("");
                    }

                    mConversationViewModel.mModelWrapper.setValue(forgeryCardModel);
                    new PaymentSubject().handlePaymentSuccessful(forgeryCardModel);
                } else if (bundle.getInt(ActivityRouter.RESULT_TYPE) == ActivityRouter.RESULT_FAILED) {
                    Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>解析二维码失败");
                }
            }
        }
    }

    private void setupVoiceLineView() {

    }

    private void setupConversationRecycleView() {
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>setupConversationRecycleView:" + mBinding.conversationRecyclerView.hashCode());
        if (mConversationAdapter == null) {
            mConversationAdapter = new ConversationAdapter(mBinding.conversationRecyclerView, this, mConversationViewModel);
        }
        mConversationAdapter.setRecyclerView(mBinding.conversationRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mBinding.conversationRecyclerView.setLayoutManager(linearLayoutManager);
        mBinding.conversationRecyclerView.setAdapter(mConversationAdapter);
    }

    private void setupSessionPromptRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mBinding.sessionPromptRecyclerView.setLayoutManager(linearLayoutManager);
        mBinding.sessionPromptRecyclerView.setAdapter(new SessionPromptAdapter(mConversationViewModel));
    }
}
