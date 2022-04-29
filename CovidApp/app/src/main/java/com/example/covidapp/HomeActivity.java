package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covidapp.api.ConNguoiService;
import com.example.covidapp.model.entity.ConNguoi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private TextView tvTenNguoiDung;
    private RelativeLayout rlKhaiBaoYTe;
    private RelativeLayout rlTrungTamYTe;
    private RelativeLayout rlDiaDiemF0;
    private RelativeLayout rlThongTinCaNhiem;
    private RelativeLayout rlThongTinCaNhan;
    private String uID;
    private String cmnd;
    private LinearLayout linearLayoutXinChao;
    private CircleImageView imgAvatarUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        anhXa();
    }
    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        if(user != null)
            Glide.with(this).load(user.getPhotoUrl()).centerCrop()
                    .error(R.drawable.ic_user_avatar).into(imgAvatarUser);

        ConNguoiService.conNguoiService.getOneConNguoiByUID(uID).enqueue(new Callback<ConNguoi>() {
            @Override
            public void onResponse(Call<ConNguoi> call, Response<ConNguoi> response) {
                if(response.body() != null)
                {
                    tvTenNguoiDung.setText(String.format("Xin chào, %s", response.body().getHoTen()));
                    cmnd = response.body().getCmnd();
                }
                else
                    tvTenNguoiDung.setText(String.format("Xin chào, %s", "Lỗi"));
            }

            @Override
            public void onFailure(Call<ConNguoi> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void anhXa(){

        tvTenNguoiDung = findViewById(R.id.tv_user_name);
        rlKhaiBaoYTe = findViewById((R.id.rectangle_3));
        rlTrungTamYTe = findViewById((R.id.rectangle_2));
        rlDiaDiemF0 = findViewById((R.id.rectangle_1));
        rlThongTinCaNhiem = findViewById(R.id.btn_thongtincanhiem);
        rlThongTinCaNhan =  findViewById(R.id.btn_thongtincanhan);
        linearLayoutXinChao = findViewById(R.id.linearXinChao);
        imgAvatarUser = findViewById(R.id.imgAvataruser);

        Intent intent = getIntent();
        uID = intent.getStringExtra("uid");


        linearLayoutXinChao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhanActivity.class);
                intent.putExtra("isDangKy",false);
                intent.putExtra("uid",uID);
                startActivity(intent);
            }
        });


        rlKhaiBaoYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KhaiBaoYTe.class);
                intent.putExtra("cmnd",cmnd);
                startActivity(intent);
            }
        });

        rlTrungTamYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TrungTamYTeGanBan.class);
                startActivity(intent);
            }
        });

        rlDiaDiemF0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DiaDiemCoF0Activity.class);
                startActivity(intent);
            }
        });

        // Chuyển sang màn hình thông tin ca nhiễm cộng đồng
        rlThongTinCaNhiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhiemActivity.class);
                startActivity(intent);
            }
        });

        // Chuyển sang màn hình thông tin cá nhân
        rlThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhanActivity.class);
                intent.putExtra("isDangKy",false);
                intent.putExtra("uid",uID);
                startActivity(intent);
            }
        });
    }
}