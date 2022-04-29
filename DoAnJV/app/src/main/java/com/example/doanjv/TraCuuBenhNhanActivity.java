package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Adapter.Adapter_TraCuuBenhNhan;
import Model.TraCuuBenhNhan;
import Model.entity.BenhNhanCustom;
import api.BenhNhanService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraCuuBenhNhanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_TraCuuBenhNhan adapterTraCuuBenhNhan;
    List<BenhNhanCustom> list = new ArrayList<>();

    private EditText editText1;
    private EditText hoten;
    private EditText namsinh;
    private EditText cmnd;
    private EditText diachi;
    private ImageButton btnBack;
    private Button btnDatLai;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_benh_nhan);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.dsbenhnhan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTraCuuBenhNhan = new Adapter_TraCuuBenhNhan(list);
        recyclerView.setAdapter(adapterTraCuuBenhNhan);
        getAllData();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //
        //xuất ra lịch ngày sinh
        //
        editText1 = findViewById(R.id.namsinh);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TraCuuBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                editText1.setText(date);
            }
        };

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
                filter(editable.toString());
            }
        });

        //tim kiem nam sinh
        namsinh = findViewById(R.id.namsinh);
        namsinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter2(editable.toString());
            }
        });

        //tim kiem cmnd
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
                filter3(editable.toString());
            }
        });

        //tim kiem dia chi
        diachi = findViewById(R.id.diachi);
        diachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter4(editable.toString());
            }
        });

        //back
        btnBack = findViewById(R.id.back_TraCuuBenhNhan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDatLai = findViewById(R.id.btnDatLaiTraCuuBN);
        btnDatLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoten.setText("");
                namsinh.setText("");
                cmnd.setText("");
                diachi.setText("");
            }
        });
    }

    private void filter(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getHoTen().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter2(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getNgaySinh().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter3(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter4(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getDiaChi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }

    private void getAllData()
    {
        BenhNhanService.benhNhanService.getAllBenhNhan().enqueue(new Callback<List<BenhNhanCustom>>() {
            @Override
            public void onResponse(Call<List<BenhNhanCustom>> call, Response<List<BenhNhanCustom>> response) {
                if(response.body() != null)
                {
                    list.addAll(response.body());
                    adapterTraCuuBenhNhan.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<BenhNhanCustom>> call, Throwable t) {
                Toast.makeText(TraCuuBenhNhanActivity.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}