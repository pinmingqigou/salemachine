package com.freeme.freemelite.salemachine.ui.view.factory;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.freeme.freemelite.router.RouterConfig.CoversationType;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.ItemConversationHtmlpayloadBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationListCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationStandardCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationTextCardBinding;
import com.freeme.freemelite.salemachine.databinding.ItemConversationUserBinding;

public class DataBindingFactory {
    private static final String TAG = "DataBindingFactory";

    public ViewDataBinding getViewDataBinding(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == CoversationType.VOICE_INPUT) {
            ItemConversationUserBinding userBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_user, parent, false);
            return userBinding;
        } else if (viewType == CoversationType.HTML) {
            ItemConversationHtmlpayloadBinding htmlpayloadBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_htmlpayload, parent, false);
            return htmlpayloadBinding;
        } else if (viewType == CoversationType.TEXT_CARD || viewType == CoversationType.FORGERY_CARD) {
            ItemConversationTextCardBinding textCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_text_card, parent, false);
            return textCardBinding;
        } else if (viewType == CoversationType.LIST_CARD) {
            ItemConversationListCardBinding listCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_list_card, parent, false);
            return listCardBinding;
        } else if (viewType == CoversationType.STANDARD_CARD) {
            ItemConversationStandardCardBinding standardCardBinding = DataBindingUtil.inflate(inflater, R.layout.item_conversation_standard_card, parent, false);
            return standardCardBinding;
        } else if (viewType == CoversationType.IMAGE_LIST_CARD) {
            Log.e(TAG,">>>>>>>>>>>>>>>>getViewDataBinding:CoversationType.IMAGE_LIST_CARD");
        }
        return null;
    }
}
