package com.example.covidapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.covidapp.api.ConNguoiService;
import com.example.covidapp.api.NguoiDungService;
import com.example.covidapp.model.entity.ConNguoi;
import com.example.covidapp.model.entity.NguoiDung;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongTinCaNhanActivity extends AppCompatActivity {

    public final int REQUEST_CODE = 10;
    private boolean isUpdateImage;
    private ImageButton ibtn_back;
    private CircleImageView ibtn_ImageUser;

    private EditText edtHoTen;
    private EditText edtNgaySinh;
    private EditText edtSDT;
    private RadioGroup rdbGioiTinh;
    private RadioButton rdbNam;
    private RadioButton rdbNu;
    private EditText edtCCCD;
    private EditText edtThanhPho;
    private EditText edtQuanHuyen;
    private EditText edtPhuongXa;
    private EditText edtThonXomSoNha;
    private AppCompatButton btnLuuThongTin;
    private TextView tvDangXuat;
    private String uID;
    private ProgressDialog progressDialog;
    FirebaseStorage storage ;
    // Create a storage reference from our app
    StorageReference storageRef ;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK)
            {
                Intent intent = result.getData();
                if(intent == null)
                    return;
                Uri uri = intent.getData();
                Glide.with(ThongTinCaNhanActivity.this).load(uri)
                        .error(rdbNu.isChecked() ?  R.drawable.defaultuserwomen:R.drawable.defaultuserman).into(ibtn_ImageUser);

                isUpdateImage = true;
            }
        }
    });

    private boolean isDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        Objects.requireNonNull(getSupportActionBar()).hide();
        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://senotp-462ac.appspot.com");
        isUpdateImage  = false;
        progressDialog = new ProgressDialog(ThongTinCaNhanActivity.this);
        anhXa();

    }


    private void anhXa(){
        Intent intent = getIntent();
        isDangKy = intent.getBooleanExtra("isDangKy",false);
        uID = intent.getStringExtra("uid");
        ibtn_back = (ImageButton)findViewById(R.id.ibtn_back);
        ibtn_ImageUser = (CircleImageView) findViewById(R.id.img_userimage);
        edtSDT = (EditText) findViewById(R.id.edt_sodienthoai);
        edtCCCD = (EditText) findViewById(R.id.edt_cancuoc);
        edtThanhPho = (EditText) findViewById(R.id.edt_thanhpho);
        edtQuanHuyen = (EditText) findViewById(R.id.edt_quan_huyen);
        edtPhuongXa = (EditText) findViewById(R.id.edt_phuong_xa);
        edtHoTen = (EditText) findViewById(R.id.edt_hoten);
        rdbGioiTinh = findViewById(R.id.rdg_gioitinh);
        edtNgaySinh = (EditText)  findViewById(R.id.edt_ngaysinh);
        edtThonXomSoNha = (EditText) findViewById(R.id.edt_thon_xom_sonha);
        btnLuuThongTin = (AppCompatButton) findViewById(R.id.btnLuuThongTin);
        rdbNu = (RadioButton) findViewById(R.id.rbtn_nu);
        rdbNam = (RadioButton) findViewById(R.id.rbtn_nam);
        tvDangXuat = (TextView) findViewById(R.id.tvDangXuat);
        rdbNam.setChecked(true);

        ibtn_ImageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermision();
            }
        });

        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });

        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("Vui lòng chờ!");
                progressDialog.show();
                if(isDangKy)
                    dangKyConNguoi();
                else
                    suaThongIinConNguoi();

                if(isUpdateImage == false)
                     progressDialog.dismiss();
                else
                    themAnh();
            }
        });

        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                // chuyển đến màn hình đăng nhập
                Intent intent1  = new Intent(ThongTinCaNhanActivity.this,DangNhapActivity.class);
                startActivity(intent1);
                finishAffinity();
            }
        });

        if(isDangKy == false)
        {
            setThongTin();
            FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null)
            {
                Glide.with(this).load(user.getPhotoUrl())
                        .error(rdbNu.isChecked() ? R.drawable.defaultuserwomen:R.drawable.defaultuserman ).into(ibtn_ImageUser);
            }
            tvDangXuat.setVisibility(View.VISIBLE);
        }
        else
        {
            isUpdateImage = true;
            rdbGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.rbtn_nam:
                            Glide.with(ThongTinCaNhanActivity.this).load(R.drawable.defaultuserman).into(ibtn_ImageUser);
                            break;
                        case R.id.rbtn_nu:
                            Glide.with(ThongTinCaNhanActivity.this).load(R.drawable.defaultuserwomen).into(ibtn_ImageUser);
                            break;
                    }
                }
            });
        }

    }

    private void onClickRequestPermision() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            openGallery();
            return;
        }

        if(this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            openGallery();
        }
        else
        {
            String [] permision = {Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permision,REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE)
        {
            if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openGallery();
            }
            else
            {
                Toast.makeText(ThongTinCaNhanActivity.this,"Cần cấp quyền truy cập hình ảnh",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Chọn hình ảnh"));
    }

    private void suaThongIinConNguoi() {
        ConNguoi conNguoi = new ConNguoi();
        conNguoi.setCmnd(edtCCCD.getText().toString().trim());
        conNguoi.setGioiTinh(rdbNam.isChecked() == true ? "Nam":"Nữ");
        conNguoi.setSdt(edtSDT.getText().toString().trim());
        conNguoi.setNgaySinh(edtNgaySinh.getText().toString());
        conNguoi.setHoTen(edtHoTen.getText().toString().trim());
        String dc = edtThonXomSoNha.getText().toString().trim()+", "+edtPhuongXa.getText().toString().trim()+", "
                +edtQuanHuyen.getText().toString().trim()+", "+edtThanhPho.getText().toString().trim();
        conNguoi.setDiaChi(dc);
        ConNguoiService.conNguoiService.updateConNguoi(conNguoi.getCmnd(),conNguoi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == true)
                    Toast.makeText(ThongTinCaNhanActivity.this,"Cập nhật thông tin thành công!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ThongTinCaNhanActivity.this,"Cập nhật thông tin thất bại!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ThongTinCaNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void themAnh()
    {
        Calendar calendar = Calendar.getInstance();
        StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");


        ibtn_ImageUser.setDrawingCacheEnabled(true);
        ibtn_ImageUser.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) ibtn_ImageUser.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("thongtincanhan","Lỗi update hình ảnh!" + exception.toString());
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("thongtincanhan","Update hình ảnh thành công! ");
            }
        });

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return mountainsRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // Lấy ra URI để lưu vào thông tin cá nhân của user
                    Uri downloadUri = task.getResult();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null)
                    {
                        // Xóa hình ảnh trên firebase nếu user đã có ảnh trước đó
                        if(user.getPhotoUrl()!= null)
                        {
                            StorageReference desertRef = storageRef.getStorage().getReferenceFromUrl(user.
                                    getPhotoUrl().toString());
                            desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e("avatar","Xóa file thành công");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.e("avatar","Xóa file thất bại "+exception.toString());
                                }
                            });
                        }
                        // Cập nhật lại URI hình ảnh avatar
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(edtCCCD.getText().toString().trim())
                                .setPhotoUri(downloadUri)
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Log.e("thongtincanhan","Update hình ảnh user thành công! ");
                                        }
                                    }
                                });
                    }
                } else {
                    // Handle failures
                    // ...
                }
            }
        });

    }

    private void dangKyConNguoi() {
        ConNguoi conNguoi = new ConNguoi();
        conNguoi.setCmnd(edtCCCD.getText().toString().trim());
        conNguoi.setGioiTinh(rdbNam.isChecked() == true ? "Nam":"Nữ");
        conNguoi.setSdt(edtSDT.getText().toString().trim());
        conNguoi.setNgaySinh(edtNgaySinh.getText().toString());
        conNguoi.setHoTen(edtHoTen.getText().toString().trim());
        String dc = edtThonXomSoNha.getText().toString().trim()+", "+edtPhuongXa.getText().toString().trim()+", "
                +edtQuanHuyen.getText().toString().trim()+", "+edtThanhPho.getText().toString().trim();
        conNguoi.setDiaChi(dc);
        ConNguoiService.conNguoiService.addConNguoi(conNguoi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == false)
                    Log.e("ThemConNguoi","Đã tồn tại con người");
                themMoiNguoiDung(conNguoi);
                if(isUpdateImage == false)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("ThemConNguoi","Lỗi call API");
                if(isUpdateImage == false)
                    progressDialog.dismiss();
            }
        });
    }

    private void themMoiNguoiDung(ConNguoi conNguoi) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setuID(uID);
        nguoiDung.setCmnd_ConNguoi(conNguoi);
        NguoiDungService.nguoiDungService.addNguoiDung(nguoiDung).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == true)
                {
                    Toast.makeText(ThongTinCaNhanActivity.this,"Thêm mới thông tin thành công!",Toast.LENGTH_SHORT).show();
                    goToHomeActivity(nguoiDung);
                }
                else
                    Toast.makeText(ThongTinCaNhanActivity.this,"Thêm mới thông tin thất bại!",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ThongTinCaNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToHomeActivity(NguoiDung nguoiDung) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("uid",nguoiDung.getuID());
        intent.putExtra("cmnd",nguoiDung.getCmnd_ConNguoi().getCmnd());
        startActivity(intent);
        finishAffinity();
    }

    private void setThongTin() {
        ConNguoi conNguoiGetData;
        ConNguoiService.conNguoiService.getConNguoiByUID(uID).enqueue(new Callback<ConNguoi>() {
            @Override
            public void onResponse(Call<ConNguoi> call, Response<ConNguoi> response) {
                if(response.body()!=null)
                    setDataToView(response.body());
                else
                    Toast.makeText(ThongTinCaNhanActivity.this,"Không tồn tại tài khoản trong database!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ConNguoi> call, Throwable t) {
                Toast.makeText(ThongTinCaNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setDataToView(ConNguoi body) {
        if(rdbGioiTinh != null){
            switch (body.getGioiTinh()){
                case "Nam":
                    rdbNam.setChecked(true);
                    break;
                case "Nữ":
                    rdbNu.setChecked(true);
                    break;
            }
        }
        edtHoTen.setText(body.getHoTen());
        edtNgaySinh.setText(body.getNgaySinh());
        edtSDT.setText(body.getSdt());
        edtCCCD.setText(body.getCmnd());
        String diaChi[] = body.getDiaChi().split(", ");
        if(diaChi.length == 4)
        {
            edtThanhPho.setText(diaChi[3]);
            edtQuanHuyen.setText(diaChi[2]);
            edtPhuongXa.setText(diaChi[1]);
            edtThonXomSoNha.setText(diaChi[0]);
        }
    }

    private void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
                calendar.set(nam, thang, ngay);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}