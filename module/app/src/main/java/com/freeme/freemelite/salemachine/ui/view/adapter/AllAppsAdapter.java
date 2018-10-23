package com.freeme.freemelite.salemachine.ui.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.ComponentName;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ImageUtils;
import com.freeme.freemelite.salemachine.ActivityRouter;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.ItemAllAppsBinding;
import com.freeme.freemelite.salemachine.models.entity.AppEntity;
import com.freeme.freemelite.salemachine.viewmodels.FeatureViewModel;

import java.util.List;

public class AllAppsAdapter extends RecyclerView.Adapter<AllAppsAdapter.ViewHolder> {
    private static final String TAG = "AllAppsAdapter";

    private FeatureViewModel mFeatureViewModel;
    private List<AppEntity> mModel;
    private Context mContext;

    public AllAppsAdapter(Context context, LifecycleOwner lifecycleOwner, FeatureViewModel featureViewModel) {
        mFeatureViewModel = featureViewModel;
        mContext = context;
        mFeatureViewModel.mAppsWrapper.observe(lifecycleOwner, new Observer<List<AppEntity>>() {
            @Override
            public void onChanged(@Nullable List<AppEntity> appEntities) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>mAppsWrapper observe:" + appEntities.size());
                mModel = appEntities;
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_all_apps, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItemAllAppsBinding.appNameTv.setText(mModel.get(position).getAppName());
        holder.mItemAllAppsBinding.appIconIv.setImageDrawable(ImageUtils.bytes2Drawable(mModel.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return mModel != null ? mModel.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ItemAllAppsBinding mItemAllAppsBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemAllAppsBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mModel != null) {
                        AppEntity appEntity = mModel.get(getAdapterPosition());
                        ComponentName componentName = new ComponentName(appEntity.getPackageName(), appEntity.getClassName());
                        ActivityRouter.startActivityByComponentName(mContext, componentName);
                    }
                }
            });
        }
    }
}
