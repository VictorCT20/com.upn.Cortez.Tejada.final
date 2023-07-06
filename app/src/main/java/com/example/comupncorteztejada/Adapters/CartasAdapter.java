package com.example.comupncorteztejada.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupncorteztejada.Entities.Cartas;
import com.example.comupncorteztejada.R;

import java.util.List;

public class CartasAdapter extends RecyclerView.Adapter {

    private List<Cartas> cartas;
    Context context;

    public CartasAdapter(List<Cartas> items, Context context) {
        this.cartas = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartasAdapter.NameViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View view = inflater.inflate(R.layout.item_string_coment, parent, false);
            viewHolder = new CartasAdapter.NameViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_progressbar, parent, false);
            viewHolder = new CartasAdapter.NameViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    @Override
    public int getItemViewType(int position) {
        Cartas item = cartas.get(position);
        return item == null ? 0 : 1;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setMovimiento(List<Movimientos> movimientos) {
        this.items = movimientos;
    }
}
