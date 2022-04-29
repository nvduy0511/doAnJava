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

import Adapter.Adapter_XoaBenhNhan;
import Model.entity.BenhNhanCustom;
import api.BenhNhanService;
import my_interface.ItemTouchHelperListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XoaBenhNhanActivity extends AppCompatActivity implements ItemTouchHelperListener {

    private RecyclerView recyclerView;
    private Adapter_XoaBenhNhan adapterXoaBenhNhan;
    private List<BenhNhanCustom> list = new ArrayList<>();
    private EditText hoten;
    private EditText cmnd;
    private LinearLayout linearLayout;
    private int maBN;
    private ImageButton btnBack;
    private Button btnDatLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_benh_nhan);
        getSupportActionBar().hide();

        linearLayout = findViewById(R.id.root_view);
        recyclerView = findViewById(R.id.dsbenhnhan);
        btnDatLai = findViewById(R.id.btnDatLaiXoaBN);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterXoaBenhNhan = new Adapter_XoaBenhNhan(list);
        recyclerView.setAdapter(adapterXoaBenhNhan);
        getAllData();
        //dòng kẻ phân biệt
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        //tim kiem theo ten
        hoten = findViewById(R.id.hoten);
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

        //tim kiem theo cmnd
        cmnd = findViewById(R.id.cmnd);
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterCMND(editable.toString());
            }
        });
        btnBack = findViewById(R.id.back_XoaBenhNhan);
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
        BenhNhanService.benhNhanService.getAllBenhNhan().enqueue(new Callback<List<BenhNhanCustom>>() {
            @Override
            public void onResponse(Call<List<BenhNhanCustom>> call, Response<List<BenhNhanCustom>> response) {
                if(response.body() != null)
                {
                    list.addAll(response.body());
                    adapterXoaBenhNhan.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<BenhNhanCustom>> call, Throwable t) {
                Toast.makeText(XoaBenhNhanActivity.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterCMND(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterXoaBenhNhan.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getHoTen().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterXoaBenhNhan.filterList(filterList);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_XoaBenhNhan.XoaBenhNhanViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getCmnd_BenhNhan().getHoTen();

            //tạo đối tượng user để thực hiện xóa
            final BenhNhanCustom BenhNhanDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            maBN = list.get(viewHolder.getAdapterPosition()).getMaBN();
            adapterXoaBenhNhan.RemoveItem(index, maBN);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + " remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterXoaBenhNhan.UndoItem(BenhNhanDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}