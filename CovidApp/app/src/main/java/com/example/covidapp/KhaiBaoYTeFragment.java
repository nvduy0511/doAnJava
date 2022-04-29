package com.example.covidapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidapp.api.ConNguoiService;
import com.example.covidapp.api.PhieuKhaiBaoYTeService;
import com.example.covidapp.model.entity.ConNguoi;
import com.example.covidapp.model.entity.PhieuKhaiBaoYTe;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ramotion.foldingcell.FoldingCell;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhaiBaoYTeFragment extends Fragment {

    private static final String TAG = KhaiBaoYTe.class.getName().toString();
    private View mView;
    private AppCompatButton btn;
    private FoldingCell fc;
    private Button btnExtend;
    private Button btnColab;
    private TextView tvHoTenColab;
    private TextView tvHotenExtend;

    private CheckBox cbKhaiBaoHoColab;
    private CheckBox cbKhaiBaoHoExtend;
    private RadioButton rdQuestion1;
    private RadioButton rdQuestion2;
    private RadioButton rdQuestion3_1;
    private RadioButton rdQuestion3_2;
    private RadioButton rdQuestion3_3;
    private RadioButton rdGioiTinh;
    private RadioButton rdGioiTinhNu;


    private TextInputEditText tilHoTen;
    private TextInputEditText tiNamSinh;
    private TextInputLayout tlNgayThangNamSinh;
    private TextInputLayout tlCCCD;
    private TextInputLayout tlSDT;

    private TextInputLayout tlTinhThanPho;
    private TextInputLayout tlQuanHuyen;
    private TextInputLayout tlPhuongXa;
    private TextInputLayout tlThonXom;


    private AppCompatButton btnSubmit;
    private KhaiBaoYTe mKhaiBaoYTe;
    private ConNguoi mConNguoiKhaiBao;


    public KhaiBaoYTeFragment() {
    }

    public static KhaiBaoYTeFragment newInstance(String param1, String param2) {
        KhaiBaoYTeFragment fragment = new KhaiBaoYTeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_khai_bao_y_te, container, false);
        mKhaiBaoYTe = (KhaiBaoYTe) getActivity();
        anhXa();
        return mView;
    }

    private void anhXa()
    {
        fc= (FoldingCell) mView.findViewById(R.id.folding_cell);
        btnExtend = (Button) mView.findViewById(R.id.btnExtend);
        btnColab = (Button) mView.findViewById(R.id.btnColab);
        setDataOnFragment();

        cbKhaiBaoHoExtend = (CheckBox) mView.findViewById(R.id.cbExtend);
        cbKhaiBaoHoColab = (CheckBox) mView.findViewById(R.id.cbColab);

        rdQuestion1 = (RadioButton) mView.findViewById(R.id.rd_quest1yes);
        rdQuestion2 = (RadioButton) mView.findViewById(R.id.rd_quest2yes);
        rdQuestion3_1 = (RadioButton) mView.findViewById(R.id.rd_quest3_1yes);
        rdQuestion3_2 = (RadioButton) mView.findViewById(R.id.rd_quest3_2yes);
        rdQuestion3_3 = (RadioButton) mView.findViewById(R.id.rd_quest3_3yes);
        rdGioiTinh = (RadioButton) mView.findViewById(R.id.radioButtonNam);
        rdGioiTinhNu = (RadioButton) mView.findViewById(R.id.radioButtonNu);

        tilHoTen = (TextInputEditText) mView.findViewById(R.id.txti_hoTen);
        tlNgayThangNamSinh = (TextInputLayout) mView.findViewById(R.id.l_NamSinh);
        tlCCCD = (TextInputLayout) mView.findViewById(R.id.l_CCCD);

        tlTinhThanPho = (TextInputLayout) mView.findViewById(R.id.lTinhThanhPho);
        tlQuanHuyen = (TextInputLayout) mView.findViewById(R.id.lQuanHuyen);
        tlPhuongXa = (TextInputLayout) mView.findViewById(R.id.lPhuongXa);
        tlThonXom = (TextInputLayout) mView.findViewById(R.id.lThonXomSoNha);


        tlSDT = (TextInputLayout) mView.findViewById(R.id.l_SDT);

        tvHoTenColab = (TextView) mView.findViewById(R.id.tv_hoTenColab);
        tvHotenExtend = (TextView) mView.findViewById(R.id.tv_hoTenExtend);

        btnSubmit = (AppCompatButton) mView.findViewById(R.id.btn_GuiPhieuKhaiBao);
        tiNamSinh = (TextInputEditText) mView.findViewById(R.id.txti_NamSinh);
        setStatusEditText(false);

        tiNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });

        tilHoTen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvHoTenColab.setText(tilHoTen.getText().toString());
                tvHotenExtend.setText(tilHoTen.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        cbKhaiBaoHoColab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbKhaiBaoHoExtend.setChecked(cbKhaiBaoHoColab.isChecked());
                setStatusEditText(cbKhaiBaoHoColab.isChecked());
            }
        });


        cbKhaiBaoHoExtend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbKhaiBaoHoColab.setChecked(cbKhaiBaoHoExtend.isChecked());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDataToSever();
            }
        });

        btnExtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });
        btnColab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });
    }

    private void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int nam, int thang, int ngay) {
                calendar.set(nam, thang, ngay);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tiNamSinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void setStatusEditText(boolean checked) {
        tilHoTen.setEnabled(checked);
        tlNgayThangNamSinh.setEnabled(checked);
        tlCCCD.setEnabled(checked);
        tlTinhThanPho.setEnabled(checked);
        tlQuanHuyen.setEnabled(checked);
        tlPhuongXa.setEnabled(checked);
        tlThonXom.setEnabled(checked);
        tlSDT.setEnabled(checked);
        rdGioiTinhNu.setEnabled(checked);
        rdGioiTinh.setEnabled(checked);
    }

    private void setDataOnFragment() {
        ConNguoiService.conNguoiService.getOneConNguoi(mKhaiBaoYTe.getCmnd()).enqueue(new Callback<ConNguoi>() {
            @Override
            public void onResponse(Call<ConNguoi> call, Response<ConNguoi> response) {
                mConNguoiKhaiBao = response.body();
                if(mConNguoiKhaiBao != null)
                    setData(mConNguoiKhaiBao);
            }

            @Override
            public void onFailure(Call<ConNguoi> call, Throwable t) {
                Toast.makeText(getContext(),"Call API thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(ConNguoi conNguoi) {
        tilHoTen.setText(conNguoi.getHoTen());
        tlNgayThangNamSinh.getEditText().setText(conNguoi.getNgaySinh());
        tlCCCD.getEditText().setText(conNguoi.getCmnd());
        String diaChi[] = conNguoi.getDiaChi().split(", ");
        if(diaChi.length == 4)
        {
            tlTinhThanPho.getEditText().setText(diaChi[3]);
            tlQuanHuyen.getEditText().setText(diaChi[2]);
            tlPhuongXa.getEditText().setText(diaChi[1]);
            tlThonXom.getEditText().setText(diaChi[0]);
        }
        tlSDT.getEditText().setText(conNguoi.getSdt());
        switch (conNguoi.getGioiTinh())
        {
            case "Nam":
                rdGioiTinh.setChecked(true);
                break;
            case "Nữ":
                rdGioiTinhNu.setChecked(true);
                break;
        }
    }

    private void submitDataToSever() {
        if(cbKhaiBaoHoExtend.isChecked() == true)
        {
            ConNguoi cn = new ConNguoi();
            cn.setHoTen(tilHoTen.getText().toString().trim());
            cn.setCmnd(tlCCCD.getEditText().getText().toString().trim());
            String dc = tlThonXom.getEditText().getText().toString().trim()+", "+tlPhuongXa.getEditText().getText().toString().trim()+", "
                    +tlQuanHuyen.getEditText().getText().toString().trim()+", "+tlTinhThanPho.getEditText().getText().toString().trim();
            cn.setDiaChi(dc);
            cn.setNgaySinh(tlNgayThangNamSinh.getEditText().getText().toString().trim());
            cn.setSdt(tlSDT.getEditText().getText().toString().trim());
            cn.setGioiTinh(rdGioiTinh.isChecked() == true ? "Nam":"Nữ");

            ConNguoiService.conNguoiService.addConNguoi(cn).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response.body() == true)
                    {
                        Log.e(TAG,"Them thanh cong!");
                    }
                    else
                        Log.e(TAG,"Da ton tai con nguoi khong can them !");
                    themPhieuKhaiBaoYTe(cn);
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.e(TAG,t.toString());
                }
            });
            return;
        }
        // thêm phiếu khai báo y tế
       themPhieuKhaiBaoYTe(mConNguoiKhaiBao);
    }

    private void themPhieuKhaiBaoYTe(ConNguoi conNguoi) {
        PhieuKhaiBaoYTe phieuKhaiBaoYTe = new PhieuKhaiBaoYTe();
        phieuKhaiBaoYTe.setCauHoi1(rdQuestion1.isChecked());
        phieuKhaiBaoYTe.setCauHoi2(rdQuestion2.isChecked());
        phieuKhaiBaoYTe.setCauHoi3_1(rdQuestion3_1.isChecked());
        phieuKhaiBaoYTe.setCauHoi3_2(rdQuestion3_2.isChecked());
        phieuKhaiBaoYTe.setCauHoi3_3(rdQuestion3_3.isChecked());
        phieuKhaiBaoYTe.setCmnd_ConNguoi(conNguoi);

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString  = df.format(date);

        phieuKhaiBaoYTe.setDateTime(dateString);
        phieuKhaiBaoYTe.setCmnd_NguoiKhaiBao(mKhaiBaoYTe.getCmnd());

        PhieuKhaiBaoYTeService.phieuKhaiBaoYTeService.addPhieuKhaiBaoYTe(phieuKhaiBaoYTe).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == true)
                    Toast.makeText(getContext(),"Gửi thành công phiếu khai báo",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Gửi thất bại",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getContext(),"Lỗi khi gọi API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}