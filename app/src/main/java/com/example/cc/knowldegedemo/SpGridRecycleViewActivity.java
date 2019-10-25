package com.example.cc.knowldegedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpGridRecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_grid);
        mRecycleView = findViewById(R.id.recyclerview);
//        GridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
//        mRecycleView.setLayoutManager(layoutManager);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 0) {
//                    return 3;
//                } else if (position == 1 || position == 2) {
//                    return 3;
//                } else {
//                    return 2;
//                }
//            }
//        });
//        mRecycleView.setLayoutManager(layoutManager);
//        MyAdapter adapter = new SpGridRecycleViewActivity.MyAdapter();
//        List<Integer> dataList = getTestData();
//        adapter.setImgIds(dataList);
//        mRecycleView.setAdapter(adapter);

    }

    private List<Integer> getTestData() {
        List<Integer> dataList = new ArrayList<>();
        for (int t = 0; t < 9; ++t) {
            dataList.add(t);
        }
        return dataList;
    }

    public class MyAdapter extends RecyclerView.Adapter<ItemView> {
        private List<Integer> mImgIds;

        public void setImgIds(List<Integer> mImgIds) {
            this.mImgIds = mImgIds;
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_grid, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
//            ViewGroup.LayoutParams layoutParams = holder.root.getLayoutParams();
//            if (position == 0) {
//                layoutParams.height = 400;
//            } else if (position == 1 || position == 2) {
//                layoutParams.height = 200;
//            } else {
//                layoutParams.height = 200;
//            }
//            holder.root.setLayoutParams(layoutParams);
            holder.textView.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mImgIds != null ? mImgIds.size() : 0;
        }
    }

    public static class ItemView extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView textView;

        public ItemView(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            textView = (TextView) itemView.findViewById(R.id.tv_text);
        }

    }
}
