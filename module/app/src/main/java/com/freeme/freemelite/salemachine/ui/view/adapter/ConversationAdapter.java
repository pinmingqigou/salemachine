package com.freeme.freemelite.salemachine.ui.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freeme.freemelite.dueros.DcsWebView;
import com.freeme.freemelite.router.RouterConfig.CoversationType;
import com.freeme.freemelite.router.payload.BaseModel;
import com.freeme.freemelite.router.payload.ForgeryCardModel;
import com.freeme.freemelite.router.payload.RenderCardModel;
import com.freeme.freemelite.router.payload.RenderWeatherModel;
import com.freeme.freemelite.salemachine.PayloadParser;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.databinding.ItemConversationHtmlpayloadBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationListCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationStandardCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationTextCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationUserBinding;
import com.freeme.freemelite.salemachine.models.HtmlPayLoadModel;
import com.freeme.freemelite.salemachine.models.RenderVoiceInputModel;
import com.freeme.freemelite.salemachine.models.TextCardContentModel;
import com.freeme.freemelite.salemachine.subject.PayMoneySubject;
import com.freeme.freemelite.salemachine.subject.TextCardContentSubject;
import com.freeme.freemelite.salemachine.ui.view.adapter.ConversationAdapter.ViewHolder;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;
import com.freeme.freemelite.tools.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "ConversationAdapter";
    private ConversationViewModel mConversationViewModel;
    public List<BaseModel> mModels = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private List<Integer> mHasHandle = new ArrayList<>();

    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public ConversationAdapter(final RecyclerView recyclerView, LifecycleOwner lifecycleOwner, ConversationViewModel conversationViewModel) {
        mRecyclerView = recyclerView;
        this.mConversationViewModel = conversationViewModel;
        conversationViewModel.mRenderVoiceInputModelWrapper.observe(lifecycleOwner, new Observer<RenderVoiceInputModel>() {
            @Override
            public void onChanged(@Nullable RenderVoiceInputModel renderVoiceInputModel) {
                if (renderVoiceInputModel == null) {
                    return;
                }
                if (renderVoiceInputModel.type != SaleMachineCofig.RenderVoiceInputTextPayloadType.INTERMEDIATE && !TextUtils.isEmpty(renderVoiceInputModel.inputText)) {
                    mModels.add(renderVoiceInputModel);
                }
                notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(getItemCount());
            }
        });

        conversationViewModel.mHtmlPayLoadModelWrapper.observe(lifecycleOwner, new Observer<HtmlPayLoadModel>() {
            @Override
            public void onChanged(@Nullable HtmlPayLoadModel htmlPayLoadModel) {
                mModels.add(htmlPayLoadModel);
                notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(getItemCount());
            }
        });

        conversationViewModel.mRenderCardModelWrapper.observe(lifecycleOwner, new Observer<RenderCardModel>() {
            @Override
            public void onChanged(@Nullable RenderCardModel renderCardModel) {
                mModels.add(renderCardModel);
                notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(getItemCount());
            }
        });

        conversationViewModel.mRenderWeatherModelWrapper.observe(lifecycleOwner, new Observer<RenderWeatherModel>() {
            @Override
            public void onChanged(@Nullable RenderWeatherModel renderWeatherModel) {
                mModels.add(renderWeatherModel);
                notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(getItemCount());
            }
        });

        conversationViewModel.mForgeryCardWrapper.observe(lifecycleOwner, new Observer<ForgeryCardModel>() {
            @Override
            public void onChanged(@Nullable ForgeryCardModel forgeryCardModel) {
                mModels.add(forgeryCardModel);
                notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(getItemCount());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mModels.get(position).getConversationType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == CoversationType.VOICE_INPUT) {
            ItemConversationUserBinding userBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_user, parent, false);
            ViewHolder viewHolder = new ViewHolder(userBinding.getRoot());
            return viewHolder;
        } else if (viewType == CoversationType.HTML) {
            ItemConversationHtmlpayloadBinding htmlpayloadBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_htmlpayload, parent, false);
            ViewHolder viewHolder = new ViewHolder(htmlpayloadBinding.getRoot());
            return viewHolder;
        } else if (viewType == CoversationType.TEXT_CARD || viewType == CoversationType.FORGERY_CARD) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_text_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(textCardBinding.getRoot());
            return viewHolder;
        } else if (viewType == CoversationType.LIST_CARD) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>viewType == CoversationType.LIST_CARD");
            ItemConversationListCardBinding listCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_list_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(listCardBinding.getRoot());
            return viewHolder;
        } else if (viewType == CoversationType.STANDARD_CARD) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>viewType == CoversationType.STANDARD_CARD");
            ItemConversationStandardCardBinding standardCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_standard_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(standardCardBinding.getRoot());
            return viewHolder;
        } else if (viewType == CoversationType.IMAGE_LIST_CARD) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>viewType == CoversationType.IMAGE_LIST_CARD");
        } else if (viewType == CoversationType.EXTEND_WEATHER) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_text_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(textCardBinding.getRoot());
            return viewHolder;
        }
        return new ViewHolder(new TextView(parent.getContext()));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == CoversationType.VOICE_INPUT) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> VoiceInput");
            ItemConversationUserBinding userBinding = DataBindingUtil.getBinding(holder.itemView);
            RenderVoiceInputModel renderVoiceInputModel = (RenderVoiceInputModel) mModels.get(position);
            userBinding.userVoiceInputTv.setText(renderVoiceInputModel.inputText);
        } else if (itemViewType == CoversationType.HTML) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> HTML");
            ItemConversationHtmlpayloadBinding htmlpayloadBinding = DataBindingUtil.getBinding(holder.itemView);
            HtmlPayLoadModel htmlPayLoadModel = (HtmlPayLoadModel) mModels.get(position);
            DcsWebView dcsWebView = new DcsWebView(htmlpayloadBinding.htmlContainer.getContext());
            dcsWebView.loadUrl(htmlPayLoadModel.url);
            htmlpayloadBinding.htmlContainer.addView(dcsWebView);
        } else if (itemViewType == CoversationType.TEXT_CARD) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.getBinding(holder.itemView);
            RenderCardModel renderCardModel = (RenderCardModel) mModels.get(position);
            String content = renderCardModel.getPayload().getContent();
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> TEXT_CARD.isBadJson:" + JsonUtil.isBadJson(content) + "/" + content);
            if (JsonUtil.isBadJson(content)) {
                String[] split = content.split(":");
                textCardBinding.renderCardTv.setText(split.length > 1 ? split[1] : content);
                if (content.startsWith("卓易支付") && !mHasHandle.contains(position)) {
                    mHasHandle.add(position);
                    new PayMoneySubject().handlePayMoneyEvent(content);
                }
            } else {
                TextCardContentModel textCardContentModel = PayloadParser.parseTextCardContent(content);
                textCardBinding.renderCardTv.setText(textCardContentModel.getContent());
                if (!mHasHandle.contains(position)) {
                    mHasHandle.add(position);
                    new TextCardContentSubject().handleTextCardContent(textCardContentModel);
                }
            }

        } else if (itemViewType == CoversationType.EXTEND_WEATHER) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.getBinding(holder.itemView);
            RenderWeatherModel renderWeatherModel = (RenderWeatherModel) mModels.get(position);
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> ExtendWeather:" + renderWeatherModel.toString());
            textCardBinding.renderCardTv.setText(renderWeatherModel.toString());
        } else if (itemViewType == CoversationType.LIST_CARD) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> ListCard");
            try {
                ItemConversationListCardBinding listCardBinding = DataBindingUtil.getBinding(holder.itemView);
                RenderCardModel renderCardModel = (RenderCardModel) mModels.get(position);
                List<RenderCardModel.PayloadBean.ListBean> listBeans = renderCardModel.getPayload().getList();
                HorizontalListCardAdapter horizontalListCardAdapter = new HorizontalListCardAdapter(listBeans);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                listCardBinding.iconRv.setLayoutManager(linearLayoutManager);
                listCardBinding.iconRv.setAdapter(horizontalListCardAdapter);
            } catch (Exception e) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>handle list card error:" + e);
            }

        } else if (itemViewType == CoversationType.FORGERY_CARD) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.getBinding(holder.itemView);
            ForgeryCardModel forgeryCardModel = (ForgeryCardModel) mModels.get(position);
            textCardBinding.renderCardTv.setText(forgeryCardModel.content);
        } else if (itemViewType == CoversationType.STANDARD_CARD) {
            ItemConversationStandardCardBinding standardCardBinding = DataBindingUtil.getBinding(holder.itemView);
            RenderCardModel renderCardModel = (RenderCardModel) mModels.get(position);
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder ->STANDARD_CARD:" + renderCardModel.toString());
            standardCardBinding.titleTv.setText(renderCardModel.getPayload().getTitle());
            standardCardBinding.contentTv.setText(renderCardModel.getPayload().getContent());
            Glide.with(holder.itemView.getContext()).load(renderCardModel.getPayload().getImage().getSrc()).centerCrop().into(standardCardBinding.bigImageView);
        } else {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder -> Unknow");
        }

    }


    @Override
    public int getItemCount() {
        return mModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
        }
    }
}
