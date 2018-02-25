package com.example.liqfo.catalogtest.Fragments.ObjectList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.R;

import java.util.ArrayList;
import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private List<DataObjects> dataObjects = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView address;
        public TextView area;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            area = itemView.findViewById(R.id.area);
            price = itemView.findViewById(R.id.price);
        }
    }

    public ObjectAdapter() {

    }

    public void setDataObjects(List<DataObjects> dataObjects){
        this.dataObjects = dataObjects;
    }


    @Override
    public ObjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.address.setText(dataObjects.get(position).getAddress());
        holder.area.setText(dataObjects.get(position).getArea() + " м*м");
        holder.price.setText(dataObjects.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return dataObjects.size();
    }
}
