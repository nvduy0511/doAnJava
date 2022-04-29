package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_SuaBenhNhan;
import Model.entity.BenhNhanCustom;
import api.BenhNhanService;
import my_interface.ClickItemUserListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaBenhNhanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_SuaBenhNhan adapterSuaBenhNhan;
    private List<BenhNhanCustom> list = new ArrayList<>();
    private EditText hoten;
    private EditText cmnd;
    private ClickItemUserListener listener;
    private ImageView btnBack;
    private Button btnDatLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_benh_nhan);
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
        hoten = findViewById(R.id.hoten);
        cmnd = findViewById(R.id.cmnd);
        btnBack = findViewById(R.id.backSuaThongTinBenhNhan1);
        btnDatLai = findViewById(R.id.btnDatLaiSuaBN);
    }

    private void setData()
    {
        setOnClickListner();
        LinearLayoutManager linearLayoutManage = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManage);
        //listener để sử lý việc click gọi hàm onClickGotoDetail để đẩy dữ liệu sang Detail
        adapterSuaBenhNhan = new Adapter_SuaBenhNhan(list, listener);
        recyclerView.setAdapter(adapterSuaBenhNhan);
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

        //tim kiem theo cmnd
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
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaBenhNhan.filterList(filterList);
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
        adapterSuaBenhNhan.filterList(filterList);
    }

    private void getAllData()
    {
        BenhNhanService.benhNhanService.getAllBenhNhan().enqueue(new Callback<List<BenhNhanCustom>>() {
            @Override
            public void onResponse(Call<List<BenhNhanCustom>> call, Response<List<BenhNhanCustom>> response) {
                if(response.body() != null)
                {
                    list.clear();
                    list.addAll(response.body());
                    Log.e("SuaThonTinBenhNhan",list.size()+"");
                    adapterSuaBenhNhan.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<BenhNhanCustom>> call, Throwable t) {
                Log.e("SuaThonTinBenhNhan","Fail");
            }
        });
    }

    private void setOnClickListner() {
        listener = new ClickItemUserListener() {
            @Override
            public void onClick(View v, int position) {
                int solanmac = list.get(position).getSoLanMac();
                int solantiem = list.get(position).getSoMuiVacin();
                int macsyt = list.get(position).getMaCSYT_BenhNhan().getMaCSYT();
                String solanmacc = String.valueOf(solanmac);
                String solantiemm = String.valueOf(solantiem);
                String macsytt = String.valueOf(macsyt);
                String res[] = list.get(position).getCmnd_BenhNhan().getDiaChi().split(", ");

                Intent intent = new Intent(getApplicationContext(), DetailSuaBenhNhanActivity.class);
                intent.putExtra("username", list.get(position).getCmnd_BenhNhan().getHoTen());
                intent.putExtra("ngaysinh", list.get(position).getCmnd_BenhNhan().getNgaySinh());
                intent.putExtra("gioitinh", list.get(position).getCmnd_BenhNhan().getGioiTinh());
                intent.putExtra("tinhTP", res[3]);
                intent.putExtra("QH", res[1]);
                intent.putExtra("PX", res[1]);
                intent.putExtra("TX", res[0]);
                intent.putExtra("sdt", list.get(position).getCmnd_BenhNhan().getSdt());
                intent.putExtra("cmnd", list.get(position).getCmnd_BenhNhan().getCmnd());
                intent.putExtra("ngayphathien", list.get(position).getNgayPhatHien());
                intent.putExtra("ketqua", list.get(position).getTrangThai());
                intent.putExtra("solanmac",  solanmacc);
                intent.putExtra("solantiem", solantiemm);
                intent.putExtra("macsyt", macsytt);
                intent.putExtra("maBN",list.get(position).getMaBN());
                startActivity(intent);
            }
        };
    }
}