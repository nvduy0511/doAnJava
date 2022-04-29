package com.example.covidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.covidapp.adapter.ToKhaiYTeAdapter;
import com.example.covidapp.api.PhieuKhaiBaoYTeService;
import com.example.covidapp.model.ToKhaiYTeTemp;
import com.example.covidapp.model.entity.PhieuKhaiBaoYTe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToKhaiYTeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToKhaiYTeFragment extends Fragment {

    private View view_ToKhaiYTe;

    private RecyclerView rcv_ToKhaiYTe;
    private ToKhaiYTeAdapter toKhaiYTeAdapter;
    private KhaiBaoYTe mKhaiBaoYTe;

    public static ToKhaiYTeFragment newInstance(String param1, String param2) {
        ToKhaiYTeFragment fragment = new ToKhaiYTeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_ToKhaiYTe =  inflater.inflate(R.layout.fragment_to_khai_y_te, container, false);
        mKhaiBaoYTe = (KhaiBaoYTe) getActivity();
        return view_ToKhaiYTe;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(menuVisible)
        {
            // RecyclerView
            rcv_ToKhaiYTe = view_ToKhaiYTe.findViewById(R.id.rcv_ToKhai);
            toKhaiYTeAdapter = new ToKhaiYTeAdapter(this.getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext() , RecyclerView.VERTICAL, false );
            rcv_ToKhaiYTe.setLayoutManager(linearLayoutManager);
            getLsToKhai();
            rcv_ToKhaiYTe.setAdapter(toKhaiYTeAdapter);
        }
    }

    private void getLsToKhai() {
        List<PhieuKhaiBaoYTe> ls = new ArrayList<>();
        PhieuKhaiBaoYTeService.phieuKhaiBaoYTeService.getListPhieuKhaiBaoYTe(mKhaiBaoYTe.getCmnd()).enqueue(new Callback<List<PhieuKhaiBaoYTe>>() {
            @Override
            public void onResponse(Call<List<PhieuKhaiBaoYTe>> call, Response<List<PhieuKhaiBaoYTe>> response) {
                toKhaiYTeAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<PhieuKhaiBaoYTe>> call, Throwable t) {
                Toast.makeText(getContext(),"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}