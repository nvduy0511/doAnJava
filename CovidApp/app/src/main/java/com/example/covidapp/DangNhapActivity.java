package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class DangNhapActivity extends AppCompatActivity {

    private TextInputLayout txtlSDT;
    private String TAG = DangNhapActivity.class.getName();
    private AppCompatButton btnVerify;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(DangNhapActivity.this);
        anhXa();
    }

    private void anhXa() {
        btnVerify = (AppCompatButton) findViewById(R.id.btnTiepTuc);
        txtlSDT = (TextInputLayout) findViewById(R.id.txtlSDT_DangNhap);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = (txtlSDT.getPrefixText().toString().trim()) + (txtlSDT.getEditText().getText().toString().trim());
                onClickVerifyPhoneNumber(phoneNumber);
            }
        });
    }

    private void onClickVerifyPhoneNumber(String phoneNumber) {
        progressDialog.setTitle("Đang tiến hành xác minh!");
        progressDialog.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressDialog.dismiss();
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressDialog.dismiss();
                                Toast.makeText(DangNhapActivity.this,"onVerificationFailed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifyID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifyID, forceResendingToken);
                                progressDialog.dismiss();
                                goToEnterOTPActivity(phoneNumber,verifyID);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            goToMainActivity(user.getUid());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(DangNhapActivity.this,"signInWithPhoneAuthCredential Fail",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToMainActivity(String uid) {
        Intent intent = new Intent(this, KhaiBaoYTe.class);
        intent.putExtra("uid",uid);
        startActivity(intent);
        finishAffinity();
    }

    private void goToEnterOTPActivity(String phoneNumber, String verifyID) {
        Intent intent = new Intent(this, EnterOTPActivity.class);
        intent.putExtra("phone_number",phoneNumber);
        intent.putExtra("verifyID",verifyID);
        startActivity(intent);
    }

}