package com.example.cc.knowldegedemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredRecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        mRecycleView = findViewById(R.id.recyclerview);
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int space = 20;
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space;
                }
            }
        });

        MyAdapter adapter = new MyAdapter();
        List<Integer> dataList = getTestData();
        adapter.setImgIds(dataList);
        mRecycleView.setAdapter(adapter);
    }

    private List<Integer> getTestData() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(R.drawable.icon1);
        dataList.add(R.drawable.icon2);
        dataList.add(R.drawable.icon3);
        dataList.add(R.drawable.icon4);
        dataList.add(R.drawable.icon5);
        dataList.add(R.drawable.icon1);
        dataList.add(R.drawable.icon2);
        dataList.add(R.drawable.icon3);
        dataList.add(R.drawable.icon4);
        dataList.add(R.drawable.icon5);
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
            return new ItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_stagger, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            holder.imageView.setImageResource(mImgIds.get(position));
        }

        @Override
        public int getItemCount() {
            return mImgIds != null ? mImgIds.size() : 0;
        }
    }

    public class ItemView extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ItemView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item);
        }

    }
}
