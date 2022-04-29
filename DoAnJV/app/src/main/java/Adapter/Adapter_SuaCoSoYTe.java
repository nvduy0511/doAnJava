package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.List;

import Model.SuaCoSoYTe;
import Model.entity.CoSoYTe;
import my_interface.ClickItemListener_CSYT;

public class Adapter_SuaCoSoYTe extends RecyclerView.Adapter<Adapter_SuaCoSoYTe.SuaCoSoYTeViewHolder>{
    private View view;
    private List<CoSoYTe> coSoYTeList;
    private ClickItemListener_CSYT clickItemUserListener;

    public Adapter_SuaCoSoYTe(List<CoSoYTe> coSoYTeList, ClickItemListener_CSYT clickItemUserListener) {
        this.coSoYTeList = coSoYTeList;
        this.clickItemUserListener = clickItemUserListener;
    }

    @NonNull
    @Override
    public SuaCoSoYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suacosoyte, parent, false);
        return new Adapter_SuaCoSoYTe.SuaCoSoYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuaCoSoYTeViewHolder holder, int position) {
        CoSoYTe coSoYTe = coSoYTeList.get(position);
        if(coSoYTe == null)
        {
            return;
        }
        holder.ten.setText(coSoYTe.getTenCSYT());
        holder.diaChi.setText(coSoYTe.getDiaChi());
    }

    @Override
    public int getItemCount() {
        if(coSoYTeList != null)
        {
            return coSoYTeList.size();
        }
        return 0;
    }

    public class SuaCoSoYTeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView ten;
        private TextView diaChi;

        public SuaCoSoYTeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenCSYT);
            diaChi = itemView.findViewById(R.id.diachi_SuaCSYT);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickItemUserListener.onClick(view, getAdapterPosition());
        }
    }
    public void filterList(List<CoSoYTe> filterList)
    {
        coSoYTeList = filterList;
        notifyDataSetChanged();
    }
}
