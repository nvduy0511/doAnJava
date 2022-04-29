package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import Model.SuaCoSoYTe;
import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSuaCoSoYTe extends AppCompatActivity {

    private ImageButton back;
    private EditText name;
    private EditText edtTinhTP;
    private EditText edtQuanH;
    private EditText edtPhuongX;
    private EditText edtSonha;
    private EditText edtSdt;
    private Button btnLuuThongTin;
    private int maCSYT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sua_co_so_yte);
        getSupportActionBar().hide();
        anhxa();
        setData();
        click();
    }

    private void setData()
    {
        String username = "not set";
        String tinhTP = "";
        String QH = "";
        String PX = "";
        String TX = "";
        String sdt = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            username = extras.getString("username");
            tinhTP = extras.getString("tinhTP");
            QH = extras.getString("QH");
            PX = extras.getString("PX");
            TX = extras.getString("TX");
            sdt = extras.getString("sdt");
            maCSYT = extras.getInt("maCSYT");
        }
        name.setText(username);
        edtTinhTP.setText(tinhTP);
        edtQuanH.setText(QH);
        edtPhuongX.setText(PX);
        edtSonha.setText(TX);
        edtSdt.setText(sdt);
    }

    private void anhxa()
    {
        back = (ImageButton) findViewById(R.id.backSuaCoSoYTe2);
        name = (EditText) findViewById(R.id.tenCSYT);
        edtTinhTP = (EditText) findViewById(R.id.TinhTP);
        edtQuanH = (EditText) findViewById(R.id.Quan_huyen);
        edtPhuongX = (EditText) findViewById(R.id.Phuongxa);
        edtSonha = (EditText) findViewById(R.id.Sonha);
        edtSdt = (EditText) findViewById(R.id.sdt);
        btnLuuThongTin = (Button) findViewById(R.id.btn_luu);
    }

    private void click()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });
    }

    private void Update()
    {
        CoSoYTe coSoYTe = new CoSoYTe();
        coSoYTe.setTenCSYT(name.getText().toString().trim());
        coSoYTe.setSdt(edtSdt.getText().toString().trim());
        String dc = edtSonha.getText().toString().trim()+", "+edtPhuongX.getText().toString().trim()+", "
                +edtQuanH.getText().toString().trim()+", "+edtTinhTP.getText().toString().trim();
        coSoYTe.setDiaChi(dc);

        CoSoYTeService.CSYTService.updateCoSoYTe(maCSYT, coSoYTe).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(DetailSuaCoSoYTe.this,"Cập nhât thông tin thành công",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(DetailSuaCoSoYTe.this,"Call API thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}