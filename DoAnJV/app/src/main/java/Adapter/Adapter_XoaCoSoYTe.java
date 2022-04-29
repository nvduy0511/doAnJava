package Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;
import com.example.doanjv.ThemBenhNhanActivity;
import com.example.doanjv.XoaCoSoYTeActivity;

import java.util.List;

import Model.SuaCoSoYTe;
import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_XoaCoSoYTe extends RecyclerView.Adapter<Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder>{
    private View view;
    private List<CoSoYTe> coSoYTeList;

    public Adapter_XoaCoSoYTe(List<CoSoYTe> coSoYTeList) {
        this.coSoYTeList = coSoYTeList;
    }

    @NonNull
    @Override
    public XoaCoSoYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xoacosoyte, parent, false);
        return new Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XoaCoSoYTeViewHolder holder, int position) {
        CoSoYTe coSoYTe = coSoYTeList.get(position);
        if(coSoYTe == null)
        {
            return;
        }
        holder.ten.setText(coSoYTe.getTenCSYT());
        holder.cmnd.setText(coSoYTe.getDiaChi());
    }

    @Override
    public int getItemCount() {
        if(coSoYTeList != null)
        {
            return coSoYTeList.size();
        }
        return 0;
    }

    public class XoaCoSoYTeViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ten;
        private TextView cmnd;
        public LinearLayout linearLayout;

        public XoaCoSoYTeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenCSYT);
            cmnd = itemView.findViewById(R.id.diachiCSYT);
            linearLayout = itemView.findViewById(R.id.layout_thongtinxoa);
        }
    }
    public void filterList(List<CoSoYTe> filterList)
    {
        coSoYTeList = filterList;
        notifyDataSetChanged();
    }

    //hàm để xóa item
    public void RemoveItem(int index, int maCSYT)
    {
        CoSoYTeService.CSYTService.deleteCoSoYTe(maCSYT).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                coSoYTeList.remove(index);
                notifyItemRemoved(index);
                Log.e("Call API", "Xóa thành công");
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }

    //hàm để khôi phục item
    public void UndoItem(CoSoYTe coSoYTe, int index)
    {
        CoSoYTeService.CSYTService.UndoCoSoYTe(coSoYTe).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                coSoYTeList.add(index, coSoYTe);
                notifyItemInserted(index);
                Log.e("Call API", "Hủy xóa thành công");
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }
}
