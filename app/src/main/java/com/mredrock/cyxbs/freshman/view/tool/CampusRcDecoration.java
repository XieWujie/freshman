package com.mredrock.cyxbs.freshman.view.tool;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CampusRcDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 35;
        outRect.left = 15;
        outRect.right = 5;

    }
}
