package com.sams3p10l.roomapptest.acivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sams3p10l.roomapptest.R;
import com.sams3p10l.roomapptest.adapter.ListAdapter;
import com.sams3p10l.roomapptest.databinding.ActivityMainBinding;
import com.sams3p10l.roomapptest.model.ItemData;
import com.sams3p10l.roomapptest.viewmodel.DatabaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_NEW = 1;
    private RecyclerView rvList;
    private ActivityMainBinding binding;
    private List<ItemData> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private ListAdapter adapter;
    private DatabaseViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rvList = findViewById(R.id.rvList);
        viewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.getAll().observe(this, itemData -> {
            layoutManager = new LinearLayoutManager(MainActivity.this);
            rvList.setLayoutManager(layoutManager);
            adapter = new ListAdapter(MainActivity.this, itemData);
            rvList.setHasFixedSize(true);
            rvList.setAdapter(adapter);
            list.addAll(itemData);
            adapter.notifyDataSetChanged();
        });
        binding.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
            startActivityForResult(intent, ADD_NEW);
        });

        binding.btnReset.setOnClickListener(v -> {
            viewModel.deleteAllItems();
            list.clear();
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