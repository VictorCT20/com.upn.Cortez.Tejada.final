package com.example.comupncorteztejada.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupncorteztejada.Entities.Duelista;
import com.example.comupncorteztejada.R;

import java.util.List;

public class DuelistaAdapter extends RecyclerView.Adapter {

    private List<Duelista> duelistas;
    Context context;
    public DuelistaAdapter(List<Duelista> items, Context context) {
        this.duelistas = items;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NameViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View view = inflater.inflate(R.layout.item_string, parent, false);
            viewHolder = new NameViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_progressbar, parent, false);
            viewHolder = new NameViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return duelistas.size();
    }

    @Override
    public int getItemViewType(int position) {
        Duelista duelista = duelistas.get(position);
        return duelista == null ? 0 : 1;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setDuelistas(List<Duelista> duelistas) {
        this.duelistas = duelistas;
    }

}
