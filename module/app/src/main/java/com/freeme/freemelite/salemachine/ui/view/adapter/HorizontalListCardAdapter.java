package com.freeme.freemelite.salemachine.ui.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.freeme.freemelite.router.payload.RenderCardModel.PayloadBean.ListBean;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.ListCardHorizontalLayoutBinding;

import java.util.List;

public class HorizontalListCardAdapter extends RecyclerView.Adapter<HorizontalListCardAdapter.ViewHolder> {
    private static final String TAG = "HorizontalListCard";
    private List<ListBean> mDatas;

    public HorizontalListCardAdapter(List<ListBean> datas) {
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_horizontal_layout, parent, false);
        ListCardHorizontalLayoutBinding listCardHorizontalLayoutBinding = DataBindingUtil.bind(view);
        return new ViewHolder(view, listCardHorizontalLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            ListBean listBean = mDatas.get(position);
            Glide.with(holder.itemView.getContext()).load(listBean.getImage().getSrc()).into(holder.mListCardHorizontalLayoutBinding.srcIv);
            holder.mListCardHorizontalLayoutBinding.contentTv.setText(listBean.getContent());
        } catch (Exception e) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>onBindViewHolder error:" + e);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ListCardHorizontalLayoutBinding mListCardHorizontalLayoutBinding;

        public ViewHolder(View itemView, ListCardHorizontalLayoutBinding listCardHorizontalLayoutBinding) {
            super(itemView);
            mListCardHorizontalLayoutBinding = listCardHorizontalLayoutBinding;
        }
    }
}
