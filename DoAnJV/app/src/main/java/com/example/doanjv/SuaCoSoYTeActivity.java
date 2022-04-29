package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_SuaBenhNhan;
import Adapter.Adapter_SuaCoSoYTe;
import Model.entity.BenhNhanCustom;
import Model.entity.CoSoYTe;
import api.BenhNhanService;
import api.CoSoYTeService;
import my_interface.ClickItemListener_CSYT;
import my_interface.ClickItemUserListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaCoSoYTeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_SuaCoSoYTe adapterSuaCoSoYTe;
    private List<CoSoYTe> list = new ArrayList<>();
    private EditText hoten;
    private EditText cmnd;
    private ClickItemListener_CSYT clickItemListenerCsyt;
    private ImageButton btnBack;
    private Button btnDatLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_co_so_yte);
        getSupportActionBar().hide();
        anhxa();
        setData();
        click();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }

    private void anhxa()
    {
        recyclerView = findViewById(R.id.dsbenhnhan);
        hoten = findViewById(R.id.tenCSYT);
        cmnd = findViewById(R.id.diachi);
        btnBack = findViewById(R.id.back_SuaThongTinCoSoYTe1);
        btnDatLai = findViewById(R.id.btnDatLaiSuaCSYT);
    }

    private void setData()
    {
        setOnClickListner();
        LinearLayoutManager linearLayoutManage = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManage);
        //listener để sử lý việc click gọi hàm onClickGotoDetail để đẩy dữ liệu sang Detail
        adapterSuaCoSoYTe = new Adapter_SuaCoSoYTe(list, clickItemListenerCsyt);
        recyclerView.setAdapter(adapterSuaCoSoYTe);
        getAllData();
    }

    private void click()
    {
        //tim kiem theo ten
        hoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                filterTEN(editable.toString());
            }
        });

        //tim kiem theo dia chi
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                filterCMND(editable.toString());
            }
        });
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

    private void filterCMND(String text)
    {
        List<CoSoYTe> filterList = new ArrayList<>();
        for(CoSoYTe item : list)
        {
            if(item.getDiaChi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaCoSoYTe.filterList(filterList);
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
        adapterSuaCoSoYTe.filterList(filterList);
    }

    private void getAllData()
    {
        CoSoYTeService.CSYTService.getAllCoSoYTe().enqueue(new Callback<List<CoSoYTe>>() {
            @Override
            public void onResponse(Call<List<CoSoYTe>> call, Response<List<CoSoYTe>> response) {
                list.clear();
                list.addAll(response.body());
                adapterSuaCoSoYTe.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CoSoYTe>> call, Throwable t) {
                Toast.makeText(SuaCoSoYTeActivity.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnClickListner() {
        clickItemListenerCsyt = new ClickItemListener_CSYT() {
            @Override
            public void onClick(View v, int position) {
                String res[] = list.get(position).getDiaChi().split(", ");

                Intent intent = new Intent(getApplicationContext(), DetailSuaCoSoYTe.class);
                intent.putExtra("username", list.get(position).getTenCSYT());
                intent.putExtra("tinhTP", res[3]);
                intent.putExtra("QH", res[2]);
                intent.putExtra("PX", res[1]);
                intent.putExtra("TX", res[0]);
                intent.putExtra("sdt", list.get(position).getSdt());
                intent.putExtra("maCSYT",list.get(position).getMaCSYT());
                startActivity(intent);
            }
        };
    }
}