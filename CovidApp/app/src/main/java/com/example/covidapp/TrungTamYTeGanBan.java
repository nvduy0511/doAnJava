package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeoNamesPOIProvider;
import org.osmdroid.bonuspack.location.OverpassAPIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.ManifestUtil;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class TrungTamYTeGanBan extends AppCompatActivity {

    MapView map = null;
    IMapController mapController;
    private MyLocationNewOverlay mLocationOverlay;

    static String geonamesAccount;
    public static ArrayList<POI> mPOIs;

    private ImageButton btnMyLocation;
    private ImageButton btnTimBV;
    private ImageButton btnBack;
    @SuppressLint("MissingPermission")
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_trung_tam_ye_te_gan_ban);
        getSupportActionBar().hide();

        geonamesAccount = ManifestUtil.retrieveKey(this, "GEONAMES_ACCOUNT");
        // Check GPS
        AccessPermission();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS đã được bật", Toast.LENGTH_SHORT).show();
        }else{
            showGPSDisabledAlertToUser();
        }

        map = (MapView) findViewById(R.id.map);
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

        btnTimBV = (ImageButton) findViewById(R.id.btnTimBenhVien);
        btnMyLocation = (ImageButton) findViewById(R.id.btnMyLocation);
        btnBack = (ImageButton) findViewById(R.id.ibtn_backTrungTamYte);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnTimBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feature = "amenity=hospital";
                if (!feature.equals(""))
                    Toast.makeText(TrungTamYTeGanBan.this, "Đang tìm kiếm bệnh viện !", Toast.LENGTH_SHORT).show();
                getPOIAsync(feature);
            }
        });
        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapController.animateTo(mLocationOverlay.getMyLocation());
            }
        });

    }

    void getPOIAsync(String tag){
        new POILoadingTask().execute(tag);
    }

    private class POILoadingTask extends AsyncTask<String, Void, ArrayList<POI>> {
        String mFeatureTag;
        String message;
        protected ArrayList<POI> doInBackground(String... params) {
            mFeatureTag = params[0];
            BoundingBox bb = map.getBoundingBox();
            if (mFeatureTag == null || mFeatureTag.equals("")){
                return null;
            } else if (mFeatureTag.equals("wikipedia")){
                GeoNamesPOIProvider poiProvider = new GeoNamesPOIProvider(geonamesAccount);
                //Get POI inside the bounding box of the current map view:
                ArrayList<POI> pois = poiProvider.getPOIInside(bb, 30);
                return pois;
            } else {
                OverpassAPIProvider overpassProvider = new OverpassAPIProvider();
                String osmTag = mFeatureTag;
                if (osmTag == null){
                    message = mFeatureTag + " không phải là loại danh mục.";
                    return null;
                }
                String oUrl = overpassProvider.urlForPOISearch(osmTag, bb, 100, 10);
                ArrayList<POI> pois = overpassProvider.getPOIsFromUrl(oUrl);
                return pois;
            }
        }
        protected void onPostExecute(ArrayList<POI> pois) {
            mPOIs = pois;
            if (mFeatureTag == null || mFeatureTag.equals(""))
            {
                //no search, no message
            }
            else if (mPOIs == null)
            {
                if (message != null)
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Sự cố khi tìm kiếm "+mFeatureTag+ " POI.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Tìm thấy "+mPOIs.size() +"bệnh viện gần bạn.", Toast.LENGTH_SHORT).show();
            }
            updateUIWithPOI(mPOIs, mFeatureTag);
        }
    }


    void updateUIWithPOI(ArrayList<POI> pois, String featureTag){
        map.getOverlays().clear();
        map.getOverlays().add(mLocationOverlay);
        if (pois != null){
            for (POI poi:pois){
                Marker poiMarker = new Marker(map);
                poiMarker.setTitle(poi.mType);
//                poiMarker.setSnippet(poi.mDescription);
                poiMarker.setPosition(poi.mLocation);
                Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.maker_hospital, null);
                poiMarker.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                poiMarker.setIcon(icon);
                map.getOverlays().add(poiMarker);
            }
        }
        map.invalidate();
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

    public void onResume(){
        super.onResume();

        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();

        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }
}
