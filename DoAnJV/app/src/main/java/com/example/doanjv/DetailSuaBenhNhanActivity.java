package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import Model.entity.BenhNhanCustom;
import Model.entity.ConNguoi;
import api.BenhNhanService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSuaBenhNhanActivity extends AppCompatActivity {

    private ImageButton back;
    private EditText name;
    private EditText edtNgaySinh;
    private RadioGroup rdbGioiTinh;
    private RadioButton rdbNam;
    private RadioButton rdbNu;
    private EditText edtTinhTP;
    private EditText edtQuanH;
    private EditText edtPhuongX;
    private EditText edtSonha;
    private EditText edtSdt;
    private EditText edtNgayKetQua;
    private EditText edtKetqua;
    private EditText edtSoLanDT;
    private EditText edtLichSu;
    private Button btnLuuThongTin;
    private int maBN;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sua_benh_nhan);
        getSupportActionBar().hide();
        anhxa();
        setData();
        click();
    }

    private void setData()
    {
        String username = "not set";
        String ngaysinh = "not set";
        String tinhTP = "";
        String QH = "";
        String PX = "";
        String TX = "";
        String sdt = "";
        String ngayphathien = "";
        String ketqua = "";
        String solanmac = "";
        String solantiem = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            username = extras.getString("username");
            ngaysinh = extras.getString("ngaysinh");
            tinhTP = extras.getString("tinhTP");
            QH = extras.getString("QH");
            PX = extras.getString("PX");
            TX = extras.getString("TX");
            sdt = extras.getString("sdt");
            ngayphathien = extras.getString("ngayphathien");
            ketqua = extras.getString("ketqua");
            solanmac = extras.getString("solanmac");
            solantiem = extras.getString("solantiem");
            maBN = extras.getInt("maBN");
        }
        name.setText(username);
        edtNgaySinh.setText(ngaysinh);
        edtTinhTP.setText(tinhTP);
        edtQuanH.setText(QH);
        edtPhuongX.setText(PX);
        edtSonha.setText(TX);
        edtSdt.setText(sdt);
        edtNgayKetQua.setText(ngayphathien);
        edtKetqua.setText(ketqua);
        edtSoLanDT.setText(solanmac);
        edtLichSu.setText(solantiem);
    }

    private void anhxa()
    {
        back = (ImageButton) findViewById(R.id.back_SuaBenhNhanDetail);
        name = (EditText) findViewById(R.id.hoten);
        edtNgaySinh = (EditText) findViewById(R.id.editTextDate);
        rdbGioiTinh = findViewById(R.id.rdbGioitinh);
        rdbNam = (RadioButton) findViewById(R.id.nam);
        rdbNu = (RadioButton) findViewById(R.id.nu);
        edtTinhTP = (EditText) findViewById(R.id.TinhTP);
        edtQuanH = (EditText) findViewById(R.id.Quan_huyen);
        edtPhuongX = (EditText) findViewById(R.id.Phuongxa);
        edtSonha = (EditText) findViewById(R.id.Sonha);
        edtSdt = (EditText) findViewById(R.id.sdt);
        edtNgayKetQua = (EditText) findViewById(R.id.editTextDate_NgayKetQuaXetNghiem);
        edtKetqua = (EditText) findViewById(R.id.v_spinner1);
        edtSoLanDT = (EditText) findViewById(R.id.editTextDate_SoLanDuongTinh);
        edtLichSu = (EditText) findViewById(R.id.v_spinner2);
        btnLuuThongTin = (Button) findViewById(R.id.btn_luu);
        rdbNam.setChecked(true);
    }

    private void click()
    {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailSuaBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                edtNgaySinh.setText(date);
            }
        };

        edtNgayKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(DetailSuaBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener1,year,month,day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog1.show();
            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date1 = day+"/"+month+"/"+year;
                edtNgayKetQua.setText(date1);
            }
        };

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
        BenhNhanCustom benhNhanCustom = new BenhNhanCustom();
        String SoLanMac = edtSoLanDT.getText() + "";
        String SoMuiVaccine = edtLichSu.getText() + "";
        int soLanMac = Integer.parseInt(SoLanMac);
        int soMuiVaccine = Integer.parseInt(SoMuiVaccine);
        benhNhanCustom.setNgayPhatHien(edtNgayKetQua.getText().toString());//
        benhNhanCustom.setTrangThai(edtKetqua.getText().toString().trim());
        benhNhanCustom.setSoLanMac(soLanMac);
        benhNhanCustom.setSoMuiVacin(soMuiVaccine);

        ConNguoi conNguoi = new ConNguoi();
        conNguoi.setHoTen(name.getText().toString().trim());
        conNguoi.setNgaySinh(edtNgaySinh.getText().toString());
        conNguoi.setGioiTinh(rdbNam.isChecked() == true ? "Nam":"Nữ");
        String dc = edtSonha.getText().toString().trim()+", "+edtPhuongX.getText().toString().trim()+", "
                +edtQuanH.getText().toString().trim()+", "+edtTinhTP.getText().toString().trim();
        conNguoi.setDiaChi(dc);
        conNguoi.setSdt(edtSdt.getText().toString().trim());
        benhNhanCustom.setCmnd_BenhNhan(conNguoi);

        BenhNhanService.benhNhanService.updateBenhNhan(maBN,benhNhanCustom).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(DetailSuaBenhNhanActivity.this,"Cập nhât thông tin thành công",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(DetailSuaBenhNhanActivity.this,"Call API thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}