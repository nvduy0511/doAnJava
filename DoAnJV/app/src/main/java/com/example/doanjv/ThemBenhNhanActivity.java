package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import Model.entity.BenhNhanCustom;
import Model.entity.CoSoYTe;
import Model.entity.ConNguoi;
import api.BenhNhanService;
import api.CoSoYTeService;
import api.ConNguoiService;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemBenhNhanActivity extends AppCompatActivity {

    private EditText edtHoten;
    private EditText edtNgaySinh;
    private RadioGroup rdbGioiTinh;
    private RadioButton rdbNam;
    private RadioButton rdbNu;
    private EditText edtTinhTP;
    private EditText edtQuanH;
    private EditText edtPhuongX;
    private EditText edtSonha;
    private EditText edtSdt;
    private EditText edtCCCD;
    private EditText edtNgayKetQua;
    private Spinner edtKetqua;
    private EditText edtSoLanDT;
    private Spinner edtLichSu;
    private Spinner spCSYT;
    private Button btnLuuThongTin;
    private ProgressDialog progressDialog;
    private ImageButton btnBack;
    FirebaseStorage storage;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    // Create a storage reference from our app
    StorageReference storageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_benh_nhan);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(ThemBenhNhanActivity.this);
        anhxa();
        click();
    }

    private void anhxa()
    {
        edtHoten = (EditText) findViewById(R.id.hoten);
        edtNgaySinh = (EditText) findViewById(R.id.ngaysinh);
        rdbGioiTinh = findViewById(R.id.rdbGioitinh);
        rdbNam = (RadioButton) findViewById(R.id.nam);
        rdbNu = (RadioButton) findViewById(R.id.nu);
        edtTinhTP = (EditText) findViewById(R.id.TinhTP);
        edtQuanH = (EditText) findViewById(R.id.Quan_huyen);
        edtPhuongX = (EditText) findViewById(R.id.Phuongxa);
        edtSonha = (EditText) findViewById(R.id.Sonha);
        edtSdt = (EditText) findViewById(R.id.sdt);
        edtCCCD = (EditText) findViewById(R.id.cmnd);
        edtNgayKetQua = (EditText) findViewById(R.id.editTextDate_NgayKetQuaXetNghiem);
        edtKetqua = (Spinner) findViewById(R.id.v_spinner1);
        edtSoLanDT = (EditText) findViewById(R.id.editTextDate_SoLanDuongTinh);
        edtLichSu = (Spinner) findViewById(R.id.v_spinner2);
        spCSYT = (Spinner) findViewById(R.id.spCSYT);
        btnLuuThongTin = (Button) findViewById(R.id.btn_luu);
        rdbNam.setChecked(true);
        btnBack = findViewById(R.id.back_ThemBenhNhan);

        CoSoYTeService.CSYTService.getAllCoSoYTe().enqueue(new Callback<List<CoSoYTe>>() {
            @Override
            public void onResponse(Call<List<CoSoYTe>> call, Response<List<CoSoYTe>> response) {
                setDataToSpinerCSYT(response.body());
                Log.e("ThemBenhNhan","Call API thanh cong");
            }

            @Override
            public void onFailure(Call<List<CoSoYTe>> call, Throwable t) {
                Log.e("ThemBenhNhan","Call API that bai");
            }
        });
    }

    private void setDataToSpinerCSYT(List<CoSoYTe> ls) {
        ArrayList<String> dataCSYT = new ArrayList<String>();
        for (CoSoYTe coSoYTe : ls)
        {
            dataCSYT.add(coSoYTe.getMaCSYT()+"-"+coSoYTe.getTenCSYT());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,dataCSYT);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCSYT.setAdapter(arrayAdapter);

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
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemBenhNhanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
                        calendar.set(nam, thang, ngay);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date = simpleDateFormat.format(calendar.getTime());
                        edtNgaySinh.setText(date);
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        edtNgayKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemBenhNhanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
                        calendar.set(nam, thang, ngay);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date = simpleDateFormat.format(calendar.getTime());
                        edtNgayKetQua.setText(date);
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKyConNguoi();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void DangKyConNguoi()
    {
        ConNguoi conNguoi = new ConNguoi();
        conNguoi.setCmnd(edtCCCD.getText().toString().trim());
        conNguoi.setGioiTinh(rdbNam.isChecked() == true ? "Nam":"Nữ");
        conNguoi.setSdt(edtSdt.getText().toString().trim());
        conNguoi.setNgaySinh(edtNgaySinh.getText().toString());
        conNguoi.setHoTen(edtHoten.getText().toString().trim());
        String dc = edtSonha.getText().toString().trim()+", "+edtPhuongX.getText().toString().trim()+", "
                +edtQuanH.getText().toString().trim()+", "+edtTinhTP.getText().toString().trim();
        conNguoi.setDiaChi(dc);

        ConNguoiService.conNguoiService.addConNguoi(conNguoi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == false)
                    Toast.makeText(ThemBenhNhanActivity.this,"Đã tồn tại con người!",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(ThemBenhNhanActivity.this,"Đã thêm người mới!",Toast.LENGTH_SHORT).show();
                    themMoiBenhNhan(conNguoi);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ThemBenhNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void themMoiBenhNhan(ConNguoi conNguoi)
    {
        String Macsyt = spCSYT.getSelectedItem().toString().split("-")[0];
        String SoLanMac = edtSoLanDT.getText() + "";
        String SoMuiVaccine = edtLichSu.getSelectedItem().toString();
        int soLanMac = Integer.parseInt(SoLanMac);
        int soMuiVaccine = Integer.parseInt(SoMuiVaccine);
        int macsyt = Integer.parseInt(Macsyt);

        BenhNhanCustom benhNhanCustom = new BenhNhanCustom();
        benhNhanCustom.setNgayPhatHien(edtNgayKetQua.getText().toString());
        benhNhanCustom.setSoLanMac(soLanMac);
        benhNhanCustom.setSoMuiVacin(soMuiVaccine);
        benhNhanCustom.setCmnd_BenhNhan(conNguoi);
        benhNhanCustom.setTrangThai(edtKetqua.getSelectedItem().toString());

        BenhNhanService.benhNhanService.addBenhNhan(benhNhanCustom, macsyt).enqueue(new Callback<BenhNhanCustom>() {
            @Override
            public void onResponse(Call<BenhNhanCustom> call, Response<BenhNhanCustom> response) {
                if(response.body() != null)
                {
                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thành công!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thất bại!",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<BenhNhanCustom> call, Throwable t) {
                Toast.makeText(ThemBenhNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}