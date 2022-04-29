package com.example.doanjv;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import Adapter.Adapter_XoaBenhNhan;
import my_interface.ItemTouchHelperListener;

public class RcvItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ItemTouchHelperListener listener;
    public RcvItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(listener != null)
        {
            listener.onSwiped(viewHolder);
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null)
        {
            View view = ((Adapter_XoaBenhNhan.XoaBenhNhanViewHolder) viewHolder).linearLayout;
            getDefaultUIUtil().onSelected(view);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View view = ((Adapter_XoaBenhNhan.XoaBenhNhanViewHolder) viewHolder).linearLayout;
        getDefaultUIUtil().onDrawOver(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View view = ((Adapter_XoaBenhNhan.XoaBenhNhanViewHolder) viewHolder).linearLayout;
        getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View view = ((Adapter_XoaBenhNhan.XoaBenhNhanViewHolder) viewHolder).linearLayout;
        getDefaultUIUtil().clearView(view);
    }
}
