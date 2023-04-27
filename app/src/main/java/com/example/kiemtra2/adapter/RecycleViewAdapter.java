package com.example.kiemtra2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra2.R;
import com.example.kiemtra2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Item> list;
    private IteamListener iteamListener;


    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setIteamListener(IteamListener iteamListener) {
        this.iteamListener = iteamListener;
    }

    public void setList(List<Item> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public Item getIteam(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
            Item item =list.get(position);
            holder.title.setText((item.getTitle()));
            holder.category.setText(item.getCategory());
            holder.price.setText(item.getPrice());
            holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,category,price,date;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tvTitle);
            category=itemView.findViewById(R.id.tvCategory);
            price=itemView.findViewById(R.id.tvPrice);
            date=itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(iteamListener!=null){
                iteamListener.onIteamClick(v,getAdapterPosition());
            }
        }
    }
    public interface  IteamListener{
        void onIteamClick(View view,int position);
    }
}
