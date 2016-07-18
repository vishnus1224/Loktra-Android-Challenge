package com.vishnus1224.flickflipper.ui.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vishnus1224.flickflipper.R;
import com.vishnus1224.flickflipper.model.PhotoInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamGridAdapter extends BaseAdapter {

    private List<PhotoInfo> photoInfoList = new ArrayList<>();

    private LayoutInflater layoutInflater;

    private Activity activity;

    private AnimatorSet flipOutAnimation;

    private AnimatorSet flipInAnimation;

    private AnimatorSet resetAnimation;

    @Inject
    public PhotoStreamGridAdapter(Activity activity, LayoutInflater layoutInflater) {

        this.activity = activity;

        this.layoutInflater = layoutInflater;

        flipOutAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.flip_out_animator);

        flipInAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.flip_in_animator);

        resetAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.reset_animator);
    }

    public void updateDataSet(List<PhotoInfo> photoInfoList){

        this.photoInfoList.addAll(photoInfoList);

        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return photoInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return photoInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final PhotoStreamViewHolder photoStreamViewHolder;

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_photo_stream, viewGroup, false);

            photoStreamViewHolder = new PhotoStreamViewHolder();

            photoStreamViewHolder.frontView = (ImageView) view.findViewById(R.id.adapterPhotoStreamFrontView);

            photoStreamViewHolder.backView = (RelativeLayout) view.findViewById(R.id.adapterPhotoStreamBackView);

            photoStreamViewHolder.backViewPhotoTitle = (TextView) view.findViewById(R.id.adapterBackViewPhotoTitle);

            photoStreamViewHolder.backViewPhotoDimens = (TextView) view.findViewById(R.id.adapterBackViewPhotoDimens);

            view.setTag(photoStreamViewHolder);

        }else{

            photoStreamViewHolder = (PhotoStreamViewHolder) view.getTag();

        }

        final PhotoInfo photoInfo = (PhotoInfo) getItem(i);

        //load the image with picasso.
        Picasso.with(activity).load(photoInfo.getMedia().getMediaUrl()).placeholder(android.R.color.darker_gray).fit().into(photoStreamViewHolder.frontView);

        photoStreamViewHolder.backViewPhotoTitle.setText(photoInfo.getTitle());

        photoStreamViewHolder.backViewPhotoDimens.setText(photoInfo.getLink());

        //animate the layout when clicked.
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateViews(photoInfo, photoStreamViewHolder.frontView, photoStreamViewHolder.backView);

            }
        });

        return view;
    }

    private void animateViews(PhotoInfo photoInfo, ImageView frontView, View backView) {

        if(photoInfo.getVisibleSide() == PhotoInfo.Side.FRONT || photoInfo.getVisibleSide() == null){

            flipOutAnimation.setTarget(frontView);
            flipInAnimation.setTarget(backView);

            photoInfo.setVisibleSide(PhotoInfo.Side.BACK);

        }else{

            flipOutAnimation.setTarget(backView);
            flipInAnimation.setTarget(frontView);

            photoInfo.setVisibleSide(PhotoInfo.Side.FRONT);

        }

        flipOutAnimation.start();

        flipInAnimation.start();
    }

    private static class PhotoStreamViewHolder{

        ImageView frontView;
        RelativeLayout backView;
        TextView backViewPhotoTitle;
        TextView backViewPhotoDimens;

    }
}
