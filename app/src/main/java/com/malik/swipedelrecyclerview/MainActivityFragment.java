package com.malik.swipedelrecyclerview;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    String[] listValue = {"C Tutorial","C++ Tutorial","Data Structure","Java Tutorial","Android Example","Kotlin Programing","Python language","Ruby Tutorial",".Net Tutorial","MySQL Database"};
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getData());
        mRecyclerView.setAdapter(myAdapter);

        setSwipeForRecyclerView();
    }

    private List<String> getData() {
        List<String> modelList = new ArrayList<>();
        for (int i = 0; i < listValue.length; i++) {
            modelList.add(listValue[i]);
        }
        return modelList;
    }

    private void setSwipeForRecyclerView() {

        SwipeUtil swipeHelper = new SwipeUtil(0, ItemTouchHelper.LEFT, getActivity()) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                myAdapter = (MyAdapter)mRecyclerView.getAdapter();
                myAdapter.pendingRemoval(swipedPosition);
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                myAdapter = (MyAdapter) mRecyclerView.getAdapter();
                if (myAdapter.isPendingRemoval(position)) {
                    return 0;
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(swipeHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        //set swipe label
        swipeHelper.setLeftSwipeLable("Archive");
        //set swipe background-Color
        swipeHelper.setLeftcolorCode(ContextCompat.getColor(getActivity(), R.color.swipebackground));
    }
}
