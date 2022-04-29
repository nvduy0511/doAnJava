package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.covidapp.api.BenhNhanService;
import com.example.covidapp.model.entity.BenhNhanCustom;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaDiemCoF0Activity extends AppCompatActivity {

    MapView map = null;
    IMapController mapController;
    private MyLocationNewOverlay mLocationOverlay;


    private ImageButton btnMyLocation;
    private ImageButton btnBack;
    private ImageButton btnTimKiemBenhNhanF0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_dia_diem_co_f0);
        getSupportActionBar().hide();
        // Check GPS
        AccessPermission();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS đã được bật", Toast.LENGTH_SHORT).show();
        }else{
            showGPSDisabledAlertToUser();
        }
        map = (MapView) findViewById(R.id.mapDiaDiemCoF0);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(10.776530, 106.700981);
        mapController.setCenter(startPoint);

        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this),map);
        this.mLocationOverlay.enableMyLocation();
        map.getOverlays().add(this.mLocationOverlay);
        mLocationOverlay.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
                if(mLocationOverlay.getMyLocation() != null)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mapController.animateTo(mLocationOverlay.getMyLocation());
                        }
                    });
                }

            }
        });
        btnBack = findViewById(R.id.ibtn_backDiaDiemCoF0);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnMyLocation = (ImageButton) findViewById(R.id.btnMyLocationDDF0);
        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapController.animateTo(mLocationOverlay.getMyLocation());
            }
        });

        btnTimKiemBenhNhanF0 = (ImageButton) findViewById(R.id.btnTimKiemTheoNgay);
        btnTimKiemBenhNhanF0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lựa chọn ngày để hiển thị lên map
                chonNgay();
            }
        });
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
                String date = simpleDateFormat.format(calendar.getTime());
                if(date != null)
                {
                    // call API lấy danh sách bệnh nhân theo ngày
                    BenhNhanService.benhNhanService.getBenhNhanCustomTheoNgay(date).enqueue(new Callback<List<BenhNhanCustom>>() {
                        @Override
                        public void onResponse(Call<List<BenhNhanCustom>> call, Response<List<BenhNhanCustom>> response) {
                            List<BenhNhanCustom> lsBenhNhanCustom = response.body();
                            if(lsBenhNhanCustom != null && lsBenhNhanCustom.size() >0)
                            {
                                // tiến hành tìm kiếm theo địa chỉ để lấy ra location hiển thị lên map
                                new GeocodingTask().execute(lsBenhNhanCustom);
                            }
                            else
                            {
                                Toast.makeText(DiaDiemCoF0Activity.this,"Không có bệnh nhân trong ngày "+ date,Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<BenhNhanCustom>> call, Throwable t) {
                            Toast.makeText(DiaDiemCoF0Activity.this,"Call API getBenhNhanCustomTheoNgay fail!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }


    private class GeocodingTask extends AsyncTask<Object, Void, List<Marker>> {

        protected List<Marker> doInBackground(Object... params) {
            List<BenhNhanCustom> lsDiaChiBenhNhan = (List<BenhNhanCustom>) params[0];
            Geocoder geocoder = new Geocoder(DiaDiemCoF0Activity.this, Locale.getDefault());
            List<Marker> lsMarkers = new ArrayList<>();
            // Tìm location từ danh sách địa chỉ từ đó tạo marker
            for(BenhNhanCustom i: lsDiaChiBenhNhan)
            {
                String locationAddress = i.getCmnd_BenhNhan().getDiaChi();
                try {
                    List<Address> geoResults = geocoder.getFromLocationName(locationAddress, 1);
                    while (geoResults.size()==0) {
                        geoResults = geocoder.getFromLocationName(locationAddress, 1);
                    }
                    if (geoResults.size()>0) {
                        Address addr = geoResults.get(0);
                        GeoPoint  bnPoint = new GeoPoint(addr.getLatitude(), addr.getLongitude());
                        Marker poiMarker = new Marker(map);
                        String title = "BN "+i.getMaBN()+"\nNăm sinh: "+i.getCmnd_BenhNhan().getNgaySinh()+
                                "\nSố mũi vaccin: " + i.getSoMuiVacin()+"\nSố lần mắc: "+ i.getSoLanMac();
                        poiMarker.setTitle(title);
                        poiMarker.setPosition(bnPoint);
                        Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.marker_person, null);
                        poiMarker.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        poiMarker.setIcon(icon);
                        lsMarkers.add(poiMarker);// thêm vào lsMarker
                    }
                } catch (Exception e) {
                    return null;
                }
            }
            return lsMarkers;

        }
        protected void onPostExecute(List<Marker> lsMarkers) {

            if(lsMarkers == null)
                return;
            map.getOverlays().clear();
            map.getOverlays().add(mLocationOverlay);
            for(Marker i : lsMarkers)
            {
                map.getOverlays().add(i);
            }

            map.invalidate();
            Toast.makeText(DiaDiemCoF0Activity.this,"Có "+lsMarkers.size()+" bệnh nhân Covid-19!",Toast.LENGTH_SHORT).show();

        }
    }


    void AccessPermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1001);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                checkSelfPermission(Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.INTERNET},
                    1002);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    1003);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1004);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1005);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 1001:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("permission","ACCESS_FINE_LOCATION GRANTED");
                }
                else
                {
                    Log.e("permission","Fail ACCESS_FINE_LOCATION GRANTED");
                }
                break;
            case 1002:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("permission","INTERNET GRANTED");
                }
                else
                {
                    Log.e("permission","Fail INTERNET GRANTED");
                }
                break;
            case 1003:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("permission","ACCESS_NETWORK_STATE GRANTED");
                }
                else
                {
                    Log.e("permission","Fail ACCESS_NETWORK_STATE GRANTED");
                }
                break;
            case 1004:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("permission","READ_EXTERNAL_STORAGE GRANTED");
                }
                else
                {
                    Log.e("permission","Fail READ_EXTERNAL_STORAGE GRANTED");
                }
                break;
            case 1005:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("permission","WRITE_EXTERNAL_STORAGE GRANTED");
                }
                else
                {
                    Log.e("permission","Fail WRITE_EXTERNAL_STORAGE GRANTED");
                }
                break;
        }
    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Vị trí đã tắt trên thiết bị của bạn? Cần phải bật nó lên")
                .setCancelable(false)
                .setPositiveButton("Đi đến cài đặt để bật Vị trí",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Bỏ qua",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void onResume(){
        super.onResume();
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }
}