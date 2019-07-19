package com.kidd.projectbase.adapter.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kidd.projectbase.R;

public abstract class EndlessLoadingRecyclerViewAdapter extends RecyclerViewAdapter {
    public static final int VIEW_TYPE_LOADING = -1;

    private OnLoadingMoreListener loadingMoreListener;
    private boolean disableLoadMore = false;
    protected boolean isLoading = false;

    public EndlessLoadingRecyclerViewAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    public void setLoadingMoreListener(OnLoadingMoreListener loadingMoreListener) {
        this.loadingMoreListener = loadingMoreListener;
        enableLoadingMore(loadingMoreListener != null);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: {
                        if (disableLoadMore || isLoading) {
                            return;
                        }
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        int lastVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        if (firstVisibleItemPosition > 0 && lastVisibleItemPosition == getItemCount() - 1) {
                            isLoading = true;
                            if (loadingMoreListener != null) {
                                loadingMoreListener.onLoadMore();
                            }
                        }
                    }
                    break;

                    default: {
                        break;
                    }
                }
            }
        });
    }

    public void enableLoadingMore(boolean enable) {
        this.disableLoadMore = !enable;
    }

    public void showLoadingItem(boolean isScroll) {
        addModel(null, VIEW_TYPE_LOADING, isScroll);
    }

    public void hideLoadingItem() {
        if (isLoading) {
            removeModel(getItemCount() - 1);
            isLoading = false;
        }
    }

    @Override
    protected RecyclerView.ViewHolder solvedOnCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder result;
        switch (viewType) {
            case VIEW_TYPE_LOADING: {
                result = new LoadingViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_load_more, parent, false));
            }
            break;

            default: {
                result = initNormalViewHolder(parent);
            }
            break;
        }
        return result;
    }

    @Override
    protected void solvedOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        switch (viewType) {
            case VIEW_TYPE_LOADING: {
//                bindLoadingViewHolder((LoadingViewHolder) viewHolder, position);
            }
            break;

            default: {
                bindNormalViewHolder((NormalViewHolder) viewHolder, position);
            }
            break;
        }
    }

    public interface OnLoadingMoreListener {
        void onLoadMore();
    }

//    protected abstract RecyclerView.ViewHolder initLoadingViewHolder(ViewGroup parent);

//    protected abstract void bindLoadingViewHolder(LoadingViewHolder loadingViewHolder, int position);

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.ivLoading)
//        ImageView ivLoading;

        public LoadingViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
//            Glide.with(itemView.getContext())
//                    .asGif()
//                    .load(R.drawable.ic_load_more)
//                    .apply(new RequestOptions().circleCrop())
//                    .into(ivLoading);
        }
    }
}
