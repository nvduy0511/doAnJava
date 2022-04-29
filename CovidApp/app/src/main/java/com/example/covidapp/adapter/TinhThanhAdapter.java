package com.example.covidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.model.thongtincanhiem.ThongTinCaNhiem;
import com.example.covidapp.model.thongtincanhiem.TinhThanh;

import java.text.DecimalFormat;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.TinhThanhViewHolder> {

    private Context mContext;
    private ThongTinCaNhiem mThongTinCaNhiem;
    public TinhThanhAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ThongTinCaNhiem thongTinCaNhiem)
    {
        this.mThongTinCaNhiem = thongTinCaNhiem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TinhThanhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_canhiemtinhthanh,parent,false);
        return new TinhThanhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhThanhViewHolder holder, int position) {
        TinhThanh tinhThanh = mThongTinCaNhiem.getLocations().get(position);
        if(tinhThanh == null)
        {
            return;
        }
        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        holder.tvTinhThanh.setText(tinhThanh.getName());
        holder.tvCaHomNay.setText("+"+decimalFormat.format(tinhThanh.getCasesToday()));
        holder.tvTongCanhNhiem.setText(decimalFormat.format(tinhThanh.getCases()));
        holder.tvCaTuVong.setText(decimalFormat.format(tinhThanh.getDeath()));
    }

    @Override
    public int getItemCount() {
        if(mThongTinCaNhiem!=null)
            if(mThongTinCaNhiem.getLocations() != null)
                return mThongTinCaNhiem.getLocations().size();
        return 0;
    }

    public class TinhThanhViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTinhThanh;
        private TextView tvTongCanhNhiem;
        private TextView tvCaHomNay;
        private TextView tvCaTuVong;
        public TinhThanhViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTinhThanh = (TextView) itemView.findViewById(R.id.tvTinhThanh);
            tvTongCanhNhiem = (TextView) itemView.findViewById(R.id.tvTongCaNhiem);
            tvCaHomNay = (TextView) itemView.findViewById(R.id.tvCaNhiemHomNay);
            tvCaTuVong = (TextView) itemView.findViewById(R.id.tvTongCaTuVong);

        }
    }
}
