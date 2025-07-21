package com.example.samshoeshop.view.users;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samshoeshop.view.adapters.ShoeAdapter;
import com.example.samshoeshop.model.Shoe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.samshoeshop.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerShoes;
    private ShoeAdapter adapter;
    private ArrayList<Shoe> shoeList;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerShoes = findViewById(R.id.recycler_shoes);
        edtSearch = findViewById(R.id.edt_search);

        shoeList = new ArrayList<>();
        adapter = new ShoeAdapter(this, shoeList, shoe -> {
            // Mô phỏng mua giày
            shoe.setSold(true);
            FirebaseDatabase.getInstance().getReference("shoes")
                    .child(shoe.getId())
                    .setValue(shoe)
                    .addOnSuccessListener(unused -> Toast.makeText(MainActivity.this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show());
        });

        recyclerShoes.setLayoutManager(new LinearLayoutManager(this));
        recyclerShoes.setAdapter(adapter);

        loadShoesFromFirebase();
    }

    private void loadShoesFromFirebase() {
        FirebaseDatabase.getInstance().getReference("shoes")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shoeList.clear();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Shoe shoe = data.getValue(Shoe.class);
                            if (shoe != null) {
                                shoeList.add(shoe);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
