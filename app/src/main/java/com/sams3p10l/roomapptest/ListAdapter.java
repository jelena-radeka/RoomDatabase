package com.sams3p10l.roomapptest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ItemData> dataList;
    private Activity context;
    private RoomDB database;

    public ListAdapter(Activity context,List<ItemData> dataList) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    public void setData(List<ItemData> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {

        ItemData data=dataList.get(position);
        database=RoomDB.getInstance(context);
        holder.textView.setText(data.getText());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.title);
        }
    }
}
