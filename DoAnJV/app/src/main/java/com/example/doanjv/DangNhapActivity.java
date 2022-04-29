package com.example.doanjv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Model.entity.NV_Yte;
import api.NhanVienYTeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    private TextInputLayout txtEmail;
    private TextInputLayout txtMatKhau;
    private String TAG = DangNhapActivity.class.getName();
    private AppCompatButton btnDangNhap;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        getSupportActionBar().hide();

        anhXa();

    }

    private void anhXa() {
        txtEmail = (TextInputLayout) findViewById(R.id.txtlEmail);
        txtMatKhau = (TextInputLayout) findViewById(R.id.txtlMatKhau);
        btnDangNhap = (AppCompatButton) findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail =(txtEmail.getEditText().getText().toString().trim());
                String mMatKhau =(txtMatKhau.getEditText().getText().toString().trim());
                onClickDangNhap(mEmail,mMatKhau);
            }
        });
        progressDialog = new ProgressDialog(this);
    }

    private void onClickDangNhap(String mEmail, String mMatKhau) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(mEmail, mMatKhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithEmail:success "+ user.getUid() );
                            goToMainActivity(user.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(DangNhapActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToMainActivity(String uid) {
        NhanVienYTeService.nhanVienYTeService.getOneNVYTeByUID(uid).enqueue(new Callback<NV_Yte>() {
            @Override
            public void onResponse(Call<NV_Yte> call, Response<NV_Yte> response) {
                if(response.body() != null)
                {
                    Intent intent = new Intent(DangNhapActivity.this,HomeActivity.class);
                    intent.putExtra("uid",uid);
                    startActivity(intent);
                    finishAffinity();
                }
                else
                {
                    Toast.makeText(DangNhapActivity.this,"Tài khoản của bạn không phải là tài khoản của nhân viên y tế!"
                            ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NV_Yte> call, Throwable t) {
                Log.e("DangNhapActivity",t.toString());
            }
        });


    }
}