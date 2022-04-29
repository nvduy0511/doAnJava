package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidapp.adapter.TinhThanhAdapter;
import com.example.covidapp.api.ApiService;
import com.example.covidapp.model.thongtincanhiem.ThongTinCaNhiem;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinCaNhiemActivity extends AppCompatActivity {

    private RecyclerView rcv_thongTinTinhThanh;
    private TinhThanhAdapter tinhThanhAdapter;
    private TextView tvSoCaNhiem;
    private TextView tvSoCaKhoi;
    private TextView tvSoCaTuVong;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhiem);
        getSupportActionBar().hide();
        tinhThanhAdapter = new TinhThanhAdapter(this);
        anhXa();
        getThongTinCaNhiem();
    }

    private void anhXa()
    {
        tvSoCaNhiem = (TextView) findViewById(R.id.tvSoCaNhiem);
        tvSoCaKhoi = (TextView) findViewById(R.id.tvSoCaKhoi);
        tvSoCaTuVong = (TextView) findViewById(R.id.tvSoCaTuVong);
        rcv_thongTinTinhThanh = (RecyclerView) findViewById(R.id.rcv_ThongTinCaNhiemTinhThanh);
        btnBack = (ImageButton) findViewById(R.id.ibtn_backThongTinCaNhiem);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getThongTinCaNhiem() {
        // call API
        ApiService.apiService.getListThongTinCaNhiem().enqueue(new Callback<ThongTinCaNhiem>() {
            @Override
            public void onResponse(Call<ThongTinCaNhiem> call, Response<ThongTinCaNhiem> response) {
                Log.e("thatbai","call API thanh cong!");
                ThongTinCaNhiem tt = response.body();
                setDataToRecycleView(tt);
            }

            @Override
            public void onFailure(Call<ThongTinCaNhiem> call, Throwable t) {
                Toast.makeText(ThongTinCaNhiemActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
                Log.e("thatbai",t.toString());
            }
        });

    }

    private void setDataToRecycleView(ThongTinCaNhiem thongTinCaNhiem)
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL, false );
        rcv_thongTinTinhThanh.setLayoutManager(linearLayoutManager);
        tinhThanhAdapter.setData(thongTinCaNhiem);
        rcv_thongTinTinhThanh.setAdapter(tinhThanhAdapter);
        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        tvSoCaNhiem.setText(decimalFormat.format(thongTinCaNhiem.getTotal().getInternal().getCases()));
        tvSoCaKhoi.setText(decimalFormat.format(thongTinCaNhiem.getTotal().getInternal().getRecovered()));
        tvSoCaTuVong.setText(decimalFormat.format(thongTinCaNhiem.getTotal().getInternal().getDeath()));

    }

}