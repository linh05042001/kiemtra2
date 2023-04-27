package com.example.kiemtra2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra2.R;
import com.example.kiemtra2.UpdateDeleteActivity;
import com.example.kiemtra2.adapter.RecycleViewAdapter;
import com.example.kiemtra2.dal.SQLiteHelper;
import com.example.kiemtra2.model.Item;

import java.util.List;

public class FragmentHistory extends Fragment implements RecycleViewAdapter.IteamListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        adapter=new RecycleViewAdapter();
        db=new SQLiteHelper(getContext());
//        Item i=new Item(1,"Mua quan bo","Mua sam","500","28/3/2022");
//        db.addItem(i);
        List<Item> list=db.getAll();

        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setIteamListener(this);
    }

    @Override
    public void onIteamClick(View view, int position) {
        Item item =adapter.getIteam(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list=db.getAll();
        adapter.setList(list);
    }
}
