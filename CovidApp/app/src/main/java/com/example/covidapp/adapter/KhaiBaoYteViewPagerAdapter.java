package com.example.covidapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.covidapp.KhaiBaoYTeFragment;
import com.example.covidapp.ToKhaiYTeFragment;

public class KhaiBaoYteViewPagerAdapter extends FragmentStateAdapter {


    public KhaiBaoYteViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new KhaiBaoYTeFragment();
            case 1:
                return new ToKhaiYTeFragment();
            default:
                return new KhaiBaoYTeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
