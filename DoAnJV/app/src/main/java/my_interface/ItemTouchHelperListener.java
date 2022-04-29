package my_interface;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperListener {
    //bắt sự kiện khi kéo xóa item
    void onSwiped(RecyclerView.ViewHolder viewHolder);
}
