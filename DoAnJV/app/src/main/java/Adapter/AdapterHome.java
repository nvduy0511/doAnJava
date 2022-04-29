package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanjv.R;

import java.util.List;

import Model.ChucNangOHome;

public class AdapterHome extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ChucNangOHome> chucNangOHomeList;

    public AdapterHome(Context context, int layout, List<ChucNangOHome> chucNangOHomeList) {
        this.context = context;
        this.layout = layout;
        this.chucNangOHomeList = chucNangOHomeList;

    }

    @Override
    public int getCount() {
        return chucNangOHomeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTenHinh;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgHinh = (ImageView) view.findViewById(R.id.android_gridview_image);
            viewHolder.tvTenHinh = (TextView) view.findViewById(R.id.android_gridview_text);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ChucNangOHome chucNangOHome = chucNangOHomeList.get(i);

        viewHolder.imgHinh.setImageResource(chucNangOHome.getHinh());
        viewHolder.tvTenHinh.setText(chucNangOHome.getTen());

        return view;
    }
}
