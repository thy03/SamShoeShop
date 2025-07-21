package com.example.samshoeshop.repository;

import com.example.samshoeshop.model.Shoe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ShoeRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getAvailableShoes(OnShoesLoaded callback) {
        db.collection("shoes")
                .whereEqualTo("isSold", false)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Shoe> list = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Shoe shoe = doc.toObject(Shoe.class);
                        if (shoe != null) {
                            list.add(shoe);
                        }
                    }
                    callback.onShoesLoaded(list);
                });
    }

    public void addToCart(String userId, Shoe shoe, OnCompleteListener<Void> listener) {
        db.collection("cart")
                .document(userId)
                .collection("items")
                .document(shoe.getId())
                .set(shoe)
                .addOnCompleteListener(listener);
    }


    public interface OnShoesLoaded {
        void onShoesLoaded(List<Shoe> shoes);
    }
}
