package com.malik.swipedelrecyclerview;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout regularLayout;
    public LinearLayout swipeLayout;
    public TextView listItem;
    public TextView undo;

    public ItemViewHolder(View view) {
        super(view);

        regularLayout = view.findViewById(R.id.regularLayout);
        listItem =  view.findViewById(R.id.list_item);
        swipeLayout = view.findViewById(R.id.swipeLayout);
        undo =  view.findViewById(R.id.undo);
    }
}
