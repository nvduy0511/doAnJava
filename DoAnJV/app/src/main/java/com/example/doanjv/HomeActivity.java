package com.example.doanjv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import Adapter.AdapterHome;
import Model.ChucNangOHome;
import Model.entity.ConNguoi;
import api.ConNguoiService;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private GridView gvChucNang;
    private RelativeLayout btnThongTinCaNhiem;
    private RelativeLayout btnThongTinCaNhan;
    private ArrayList<ChucNangOHome> chucNangOHomeArrayList;
    private AdapterHome adapter;
    private String uID;
    private CircleImageView imgAvatarUser;
    private TextView tvTenNguoiDung;
    private LinearLayout linearLayoutXinChao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        uID = extras.getString("uid");
        AnhXa();
        adapter = new AdapterHome(this, R.layout.dong_chuc_nang_home, chucNangOHomeArrayList);
        gvChucNang.setAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        if(user != null)
            Glide.with(this).load(user.getPhotoUrl()).centerCrop()
                    .error(R.drawable.ic_user_avatar).into(imgAvatarUser);

        ConNguoiService.conNguoiService.getConNguoiByUID_NVYTe(uID).enqueue(new Callback<ConNguoi>() {
            @Override
            public void onResponse(Call<ConNguoi> call, Response<ConNguoi> response) {
                if(response.body() != null)
                {
                    tvTenNguoiDung.setText(String.format("Xin chào, %s", response.body().getHoTen()));
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

    private void AnhXa() {
        tvTenNguoiDung = findViewById(R.id.tv_user_name);
        imgAvatarUser = findViewById(R.id.imgAvataruser);
        btnThongTinCaNhiem = (RelativeLayout) findViewById(R.id.btn_thongtincanhiem);
        btnThongTinCaNhan = (RelativeLayout) findViewById(R.id.btn_thongtincanhan);
        gvChucNang = (GridView) findViewById(R.id.gv_services);
        linearLayoutXinChao = findViewById(R.id.linearXinChao);
        chucNangOHomeArrayList = new ArrayList<>();
        chucNangOHomeArrayList.add(new ChucNangOHome("Thêm bệnh nhân", R.drawable.icons_person_add));
        chucNangOHomeArrayList.add(new ChucNangOHome("Sửa bệnh nhân", R.drawable.icons_edit));
        chucNangOHomeArrayList.add(new ChucNangOHome("Xóa bệnh nhân", R.drawable.icons_person_delete));
        chucNangOHomeArrayList.add(new ChucNangOHome("Tìm kiếm bệnh nhân", R.drawable.icons_find));
        chucNangOHomeArrayList.add(new ChucNangOHome("Thêm cơ sở y tế", R.drawable.icons_hospital));
        chucNangOHomeArrayList.add(new ChucNangOHome("Sửa cơ sở y tể", R.drawable.icons_edit_1));
        chucNangOHomeArrayList.add(new ChucNangOHome("Xóa cơ sở y tế", R.drawable.icons_delete));

        gvChucNang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(HomeActivity.this, ThemBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(HomeActivity.this, SuaBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(HomeActivity.this, XoaBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(HomeActivity.this, TraCuuBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(HomeActivity.this, ThemCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(HomeActivity.this, SuaCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(HomeActivity.this, XoaCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        btnThongTinCaNhiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhiemActivity.class);
                startActivity(intent);
            }
        });

        btnThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhanActivity.class);
                intent.putExtra("isDangKy",false);
                intent.putExtra("uid",uID);
                startActivity(intent);
            }
        });
        linearLayoutXinChao.setOnClickListener(new View.OnClickListener() {
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