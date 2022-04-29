package com.example.covidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.model.ToKhaiYTeTemp;
import com.example.covidapp.model.entity.PhieuKhaiBaoYTe;

import java.util.List;

public class ToKhaiYTeAdapter extends RecyclerView.Adapter<ToKhaiYTeAdapter.ToKhaiYTeViewHolder> {

    private Context mContext;
    private List<PhieuKhaiBaoYTe> lsPhieuKhaiBaoYTe;

    public ToKhaiYTeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<PhieuKhaiBaoYTe> ls)
    {
        this.lsPhieuKhaiBaoYTe = ls;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToKhaiYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieukhaibaoyte,parent,false);
        return new ToKhaiYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToKhaiYTeViewHolder holder, int position) {
        PhieuKhaiBaoYTe phieuKhaiBaoYTe = lsPhieuKhaiBaoYTe.get(position);
        if(phieuKhaiBaoYTe == null)
        {
            return;
        }
        String res[] = phieuKhaiBaoYTe.getDateTime().split(" ");
        String date[] = res[0].split("-");
        holder.tvDayMonth.setText(date[0]+"/"+date[1]);
        holder.tvYear.setText(date[2]);
        holder.tvName.setText(phieuKhaiBaoYTe.getCmnd_ConNguoi().getHoTen());
        holder.tvTime.setText(res[1]);
    }

    @Override
    public int getItemCount() {
        if(lsPhieuKhaiBaoYTe != null)
            return lsPhieuKhaiBaoYTe.size();

        return 0;
    }

    public class ToKhaiYTeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDayMonth;
        private TextView tvYear;
        private TextView tvName;
        private TextView tvTime;
        public ToKhaiYTeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDayMonth = (TextView) itemView.findViewById(R.id.tv_daymonth);
            tvYear = (TextView) itemView.findViewById(R.id.tv_year);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);

        }
    }
}
