package com.example.samshoeshop.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samshoeshop.R;
import com.example.samshoeshop.model.Shoe;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {

    private Context context;
    private List<Shoe> shoeList;
    private OnShoeClickListener listener;

    public interface OnShoeClickListener {
        void onAddToCart(Shoe shoe);
    }

    public ShoeAdapter(Context context, List<Shoe> shoeList, OnShoeClickListener listener) {
        this.context = context;
        this.shoeList = shoeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoe, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        Shoe shoe = shoeList.get(position);
        holder.txtName.setText(shoe.getName());
        holder.txtPrice.setText("$" + shoe.getPrice());

        Glide.with(context)
                .load(shoe.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgShoe);

        holder.btnAddToCart.setEnabled(!shoe.isSold());
        holder.btnAddToCart.setText(shoe.isSold() ? "Sold Out" : "Add to Cart");

        holder.btnAddToCart.setOnClickListener(v -> {
            if (listener != null && !shoe.isSold()) {
                listener.onAddToCart(shoe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoeList.size();
    }

    public static class ShoeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShoe;
        TextView txtName, txtPrice;
        Button btnAddToCart;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShoe = itemView.findViewById(R.id.img_shoe);
            txtName = itemView.findViewById(R.id.txt_shoe_name);
            txtPrice = itemView.findViewById(R.id.txt_shoe_price);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
