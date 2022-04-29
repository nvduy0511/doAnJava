package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;

import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemCoSoYTeActivity extends AppCompatActivity {

    private EditText edtTenCSYT;
    private EditText edtTinhTP;
    private EditText edtQuanH;
    private EditText edtPhuongX;
    private EditText edtSonha;
    private EditText edtSdt;
    private Button btnLuuThongTin;
    private ImageButton btnBack;
    private ProgressDialog progressDialog;
    FirebaseStorage storage;
    // Create a storage reference from our app
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_co_so_yte);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(ThemCoSoYTeActivity.this);
        anhxa();
        click();
    }

    private void anhxa()
    {
        edtTenCSYT = (EditText) findViewById(R.id.tencsyt);
        edtTinhTP = (EditText) findViewById(R.id.TinhTP);
        edtQuanH = (EditText) findViewById(R.id.Quan_huyen);
        edtPhuongX = (EditText) findViewById(R.id.Phuongxa);
        edtSonha = (EditText) findViewById(R.id.Sonha);
        edtSdt = (EditText) findViewById(R.id.sdt);
        btnLuuThongTin = (Button) findViewById(R.id.btn_luu);
        btnBack = findViewById(R.id.back_ThemCoSoYTe);
    }

    private void click()
    {
        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKyCSYT();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void dangKyCSYT()
    {
        CoSoYTe coSoYTe = new CoSoYTe();
        String dc = edtSonha.getText().toString().trim()+", "+edtPhuongX.getText().toString().trim()+", "
                +edtQuanH.getText().toString().trim()+", "+edtTinhTP.getText().toString().trim();
        coSoYTe.setDiaChi(dc);
        coSoYTe.setSdt(edtSdt.getText().toString().trim());
        coSoYTe.setTenCSYT(edtTenCSYT.getText().toString().trim());
        CoSoYTeService.CSYTService.addCoSoYTe(coSoYTe).enqueue(new Callback<CoSoYTe>() {
            @Override
            public void onResponse(Call<CoSoYTe> call, Response<CoSoYTe> response) {
                if(response.body() != null)
                {
                    Toast.makeText(ThemCoSoYTeActivity.this,"Thêm mới thông tin thành công!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ThemCoSoYTeActivity.this,"Thêm mới CSYT thất bại!",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CoSoYTe> call, Throwable t) {
                Toast.makeText(ThemCoSoYTeActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}