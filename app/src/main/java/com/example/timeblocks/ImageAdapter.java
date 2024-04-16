package com.example.timeblocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0,0 , 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public	int[] mThumbIds = { R.drawable.nopging,
            R.drawable.m15,R.drawable.m30,R.drawable.m45,R.drawable.m60,
            R.drawable.n6, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.n7,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.n8,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.n9,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.n10, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.n11,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.n12,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.n13,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.n14, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.n15,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.n16,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.n17,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.n18, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.n19,R.drawable.pust,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.n20,R.drawable.pust,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.n21,
            R.drawable.pust, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.n22, R.drawable.pust,R.drawable.pust,R.drawable.pust,
            R.drawable.pust

    };

}