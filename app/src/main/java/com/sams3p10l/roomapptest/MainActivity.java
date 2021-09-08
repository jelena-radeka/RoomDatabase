package com.sams3p10l.roomapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sams3p10l.roomapptest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_NEW = 1;
    private RecyclerView rvList;
    private ActivityMainBinding binding;

    List<ItemData> list=new ArrayList<>();
    LinearLayoutManager layoutManager;
    ListAdapter adapter;
    DatabaseViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        rvList=findViewById(R.id.rvList);
        viewModel=new ViewModelProvider(this).get(DatabaseViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        layoutManager=new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        adapter=new ListAdapter(MainActivity.this,list);
        rvList.setAdapter(adapter);

        binding.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
            startActivityForResult(intent, ADD_NEW);
        });
        viewModel.getAll().observe(this, itemData -> adapter.setData(itemData));

        binding.btnReset.setOnClickListener(v -> {
            viewModel.deleteAllItems();
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNewActivity.TITLE);
            ItemData itemData = new ItemData(title);
            viewModel.insert(itemData);
            Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
        }
    }
}