package Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.List;

import Model.entity.BenhNhanCustom;
import my_interface.ClickItemUserListener;

public class Adapter_SuaBenhNhan extends RecyclerView.Adapter<Adapter_SuaBenhNhan.SuaBenhNhanViewHolder>{
    private View view;
    private List<BenhNhanCustom> benhNhanCustomList;
    private ClickItemUserListener listener;

    public Adapter_SuaBenhNhan(List<BenhNhanCustom> ls, ClickItemUserListener listener) {
        this.benhNhanCustomList = ls;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuaBenhNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suabenhnhan, parent, false);
        return new Adapter_SuaBenhNhan.SuaBenhNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuaBenhNhanViewHolder holder, int position) {
        BenhNhanCustom benhNhanCustom = benhNhanCustomList.get(position);
        if(benhNhanCustom == null)
        {
            return;
        }
        holder.ten.setText(benhNhanCustom.getCmnd_BenhNhan().getHoTen());
        holder.cmnd.setText(benhNhanCustom.getCmnd_BenhNhan().getCmnd());
    }

    @Override
    public int getItemCount() {
        if(benhNhanCustomList != null)
        {
            return benhNhanCustomList.size();
        }
        return 0;
    }

    public class SuaBenhNhanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView ten;
        private TextView cmnd;
        public SuaBenhNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvName);
            cmnd = itemView.findViewById(R.id.tvCmnd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public void filterList(List<BenhNhanCustom> filterList)
    {
        benhNhanCustomList = filterList;
        notifyDataSetChanged();
    }
}
