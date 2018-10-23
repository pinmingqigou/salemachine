package com.freeme.freemelite.salemachine.ui.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.ItemSessionPromptBinding;
import com.freeme.freemelite.salemachine.ui.view.adapter.SessionPromptAdapter.SessionPromptHolder;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;

import java.util.List;

public class SessionPromptAdapter extends RecyclerView.Adapter<SessionPromptHolder> {
    private ConversationViewModel mViewModel;
    private final List<String> mSessionPrompts;

    public SessionPromptAdapter(ConversationViewModel viewModel) {
        this.mViewModel = viewModel;
        mSessionPrompts = mViewModel.getSessionPrompts();
    }

    @Override
    public SessionPromptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session_prompt, parent,false);
        return new SessionPromptHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SessionPromptHolder holder, int position) {
        holder.mBinding.sessionPromptText.setText(mSessionPrompts.get(position));
    }

    @Override
    public int getItemCount() {
        return mSessionPrompts.size();
    }

    class SessionPromptHolder extends ViewHolder {

        private final ItemSessionPromptBinding mBinding;

        public SessionPromptHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
