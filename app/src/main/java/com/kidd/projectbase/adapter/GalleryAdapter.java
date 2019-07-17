package com.kidd.projectbase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kidd.projectbase.R;
import com.kidd.projectbase.entity.ImageObject;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    public static final String TAG = GalleryAdapter.class.getSimpleName();

    private ArrayList<ImageObject> lsImages;
    private Context context;
    private ListenerClickImageChat listenerClickImageChat;
    private int realWidth;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgImageChat;
        private TextView txtBackground;
        private CheckBox cbChoose;

        public ViewHolder(View v) {
            super(v);
            imgImageChat = v.findViewById(R.id.imgImageItemGallery);
            txtBackground = v.findViewById(R.id.txtBackgroundItemGallery);
            cbChoose = v.findViewById(R.id.cbItemGallery);
        }
    }

    public GalleryAdapter(ArrayList<ImageObject> ls, int realWidth, Context context) {
        this.lsImages = ls;
        this.context = context;
        this.realWidth = realWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_library, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ImageObject imageObject = lsImages.get(position);
        try {
            Glide.with(context)
                    .load(imageObject.getPath())
                    .apply(new RequestOptions()
                            .override(realWidth, realWidth)
                            .optionalCenterCrop()
                    )
                    .into(holder.imgImageChat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imageObject.isChoose()) {
            holder.txtBackground.setVisibility(View.VISIBLE);
            holder.cbChoose.setChecked(imageObject.isChoose());
        } else {
            holder.txtBackground.setVisibility(View.GONE);
            holder.cbChoose.setChecked(imageObject.isChoose());
        }

        holder.cbChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageObject.isChoose()) {
                    imageObject.setChoose(false);
                    holder.txtBackground.setVisibility(View.GONE);
                } else {
                    imageObject.setChoose(true);
                    holder.txtBackground.setVisibility(View.VISIBLE);
                }
                listenerClickImageChat.onClickImageChat(imageObject);
            }
        });

        holder.imgImageChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageObject.isChoose()) {
                    imageObject.setChoose(false);
                    holder.txtBackground.setVisibility(View.GONE);
                } else {
                    imageObject.setChoose(true);
                    holder.txtBackground.setVisibility(View.VISIBLE);
                }
                boolean isCheck = holder.cbChoose.isChecked();
                holder.cbChoose.setChecked(!isCheck);
                listenerClickImageChat.onClickImageChat(imageObject);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lsImages.size();
    }

    public interface ListenerClickImageChat {
        void onClickImageChat(ImageObject imageObject);
    }

    public void setListenerClickImageChat(ListenerClickImageChat listenerClickImageChat) {
        this.listenerClickImageChat = listenerClickImageChat;
    }

    public ArrayList<ImageObject> getLsImages() {
        return lsImages;
    }

    public void setLsImages(ArrayList<ImageObject> lsImages) {
        this.lsImages = lsImages;
    }
}
