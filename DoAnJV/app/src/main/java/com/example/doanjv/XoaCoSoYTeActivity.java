package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_XoaCoSoYTe;
import Model.SuaCoSoYTe;
import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import my_interface.ItemTouchHelperListener_CSYT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XoaCoSoYTeActivity extends AppCompatActivity implements ItemTouchHelperListener_CSYT {

    private RecyclerView recyclerView;
    private Adapter_XoaCoSoYTe adapterXoaCoSoYTe;
    private List<CoSoYTe> list = new ArrayList<>();
    private EditText hoten;
    private EditText cmnd;
    private LinearLayout linearLayout;
    private int maCSYT;
    private ImageButton btnBack;
    private Button btnDatLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_co_so_yte);
        getSupportActionBar().hide();

        linearLayout = findViewById(R.id.root_view);
        recyclerView = findViewById(R.id.dsbenhnhan);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterXoaCoSoYTe = new Adapter_XoaCoSoYTe(list);
        recyclerView.setAdapter(adapterXoaCoSoYTe);
        getAllData();
        //dòng kẻ phân biệt
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper_CSYT(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        //tim kiem theo ten
        hoten = findViewById(R.id.tenCSYT);
        hoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterTEN(editable.toString());
            }
        });

        //tim kiem theo di chi
        cmnd = findViewById(R.id.diachi);
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterDC(editable.toString());
            }
        });
        btnBack = findViewById(R.id.back_XoaCoSoYTe);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDatLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoten.setText("");
                cmnd.setText("");
            }
        });
    }

    private void getAllData() {
        CoSoYTeService.CSYTService.getAllCoSoYTe().enqueue(new Callback<List<CoSoYTe>>() {
            @Override
            public void onResponse(Call<List<CoSoYTe>> call, Response<List<CoSoYTe>> response) {
                if(response.body() != null)
                {
                    list.addAll(response.body());
                    adapterXoaCoSoYTe.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<CoSoYTe>> call, Throwable t) {
                Toast.makeText(XoaCoSoYTeActivity.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterDC(String text)
    {
        List<CoSoYTe> filterList = new ArrayList<>();
        for(CoSoYTe item : list)
        {
            if(item.getDiaChi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterXoaCoSoYTe.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<CoSoYTe> filterList = new ArrayList<>();
        for(CoSoYTe item : list)
        {
            if(item.getTenCSYT().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterXoaCoSoYTe.filterList(filterList);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getTenCSYT();

            //tạo đối tượng user để thực hiện xóa
            final CoSoYTe coSoYTeDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            maCSYT = list.get(viewHolder.getAdapterPosition()).getMaCSYT();
            adapterXoaCoSoYTe.RemoveItem(index, maCSYT);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + " remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterXoaCoSoYTe.UndoItem(coSoYTeDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}