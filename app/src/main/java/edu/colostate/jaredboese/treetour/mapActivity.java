package edu.colostate.jaredboese.treetour;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class mapActivity extends FragmentActivity
        implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        LocationListener{
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    private GoogleMap parkMap;
    private Location lastLocation;
    private Marker currentLocationMarker;
    protected LocationManager locationManager;
    protected Criteria criteria = new Criteria();
    private String[] TreeNames = new String[] {"CanadaRed","WeepingMulberry","Ivory Silk","Hackberry","Giant Sequoia",
            "AmericanElm","AmericanLinden","Apricot","Aristocrat Pear",/*"AtlasCedar","AustrianPine",*/"AutumnBlaze",
            "AutumnPurple","Betchel Crabapple","BirchbarkCherry","Bluespruce",/*"Bristlecone",*/"Buckthorn","Bur Oak",
            "CrimsonCloud","CrimsonKing","Dawn Redwood","EasternCottonwood","EasternRedbud","EnglishOak",
            "Flamingo Boxelder","FrontierElm","Ginkgo","GoldRain","GreenAsh","GreenVase",
            /*"IncenseCedar",*/"KentuckyCoffee","LacebarkElm","LindenHybrid","LittleleafLinden","LondonPlane",
            "MountainAsh","Mulberry",/*"NewPortPlum",*/"NorwayMaple","OrnaPear","OrnaCrab","PinyonPine","PonderosaPine",
            "PurpleLocust","Red HorseChesnut","RedspirePear","RockyMountainJ",/*"RussianHawthorn",*/"ScotchPine",
            "Sensation","SiberianElm",/*"ShumardOak",*/"SpringSnow","SugarMaple","Summit Ash","Swamp WhiteOak","Thornless Cockspur",
            "ThornlessHoney","TriColorBeech",/*"Tuliptree",*/"TurkishFilbert",/*"UtahJunpier",*/"WesternCatalpa",
            /*"WhiteSpruce",*/"WinterKing","Yellowhorn"};
    private LatLng[] locations = new LatLng[] {new LatLng(39.0742663, -108.5493843), new LatLng(39.0741504, -108.5492618),
            new LatLng(39.0741242, -108.5486979), new LatLng(39.0742909, -108.5490879), new LatLng(39.0740934, -108.5489564),
            new LatLng(39.0736086, -108.549962), new LatLng(39.0739769, -108.5510632), new LatLng(39.0736086, -108.549962),
            new LatLng(39.0745023, -108.5491173),/* new LatLng(39.0736086, -108.549962), new LatLng(39.0736086, -108.549962),*/
            new LatLng(39.074387, -108.548378), new LatLng(39.0739733, -108.5514381), new LatLng(39.0742073, -108.5487163),
            new LatLng(39.073705, -108.551438), new LatLng(39.074768, -108.5509806), /*new LatLng(39.0739733, -108.5514381),*/
            new LatLng(39.0745522, -108.5510641), new LatLng(39.0745776, -108.548776), new LatLng(39.0749561, -108.5518873),
            new LatLng(39.0739348, -108.5515128), new LatLng(39.0739064, -108.549803), new LatLng(39.0746612, -108.5506057),
            new LatLng(39.0740323, -108.5506498), new LatLng(39.0741996, -108.5516606), new LatLng(39.0738982, -108.5485951),
            new LatLng(39.0734252, -108.5516718), new LatLng(39.0745973, -108.5515721), new LatLng(39.0740746, -108.5516371),
            new LatLng(39.073509, -108.5514231), new LatLng(39.0749684, -108.551932), /*new LatLng(39.073509, -108.5514231),*/
            new LatLng(39.0741759, -108.5511806), new LatLng(39.0739131, -108.5500567), new LatLng(39.0740292, -108.5513712),
            new LatLng(39.0739338, -108.5510996), new LatLng(39.0734497, -108.551929), new LatLng(39.0739, -108.551719),
            new LatLng(39.0737932, -108.5496831), /*new LatLng(39.073509, -108.5514231),*/ new LatLng(39.0740743, -108.5513482),
            new LatLng(39.0746053, -108.551159), new LatLng(39.0737677, -108.5511678), new LatLng(39.073509, -108.5514231),
            new LatLng(39.0742033, -108.5508752), new LatLng(39.0735008, -108.550292), new LatLng(39.0735653, -108.549126),
            new LatLng(39.0747458, -108.5510359), new LatLng(39.0750126, -108.5521945), /*new LatLng(39.073509, -108.5514231),*/
            new LatLng(39.0752279, -108.5508726), new LatLng(39.074871, -108.5509511), new LatLng(39.0741993, -108.5507238),
            /*new LatLng(39.073509, -108.5514231),*/ new LatLng(39.073705, -108.5499708), new LatLng(39.0742554, -108.5515343),
            new LatLng(39.0734463, -108.5497535), new LatLng(39.0745845, -108.5487139), new LatLng(39.0741064, -108.5495885),
            new LatLng(39.0745121, -108.5515095), new LatLng(39.073822, -108.5519241), /*new LatLng(39.073509, -108.5514231),*/
            new LatLng(39.0740271, -108.5518247), /*new LatLng(39.073509, -108.5514231),*/ new LatLng(39.0737458, -108.5517441),
            /*new LatLng(39.073509, -108.5514231),*/ new LatLng(39.0745675, -108.5513996), new LatLng(39.0738283, -108.5486639)};
    String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        com.google.android.gms.maps.SupportMapFragment mapFragment = (com.google.android.gms.maps.SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.ParkMap);
        mapFragment.getMapAsync(this);
        checkLocationPermission();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
    }
    @Override
    public void onMapReady(GoogleMap map) {
        parkMap = map;
        parkMap.setMaxZoomPreference(21);
        parkMap.setMinZoomPreference(19);
        parkMap.getUiSettings().setScrollGesturesEnabled(false);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mapActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Tree Tour requires location to run", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
        Marker[] markers = new Marker[TreeNames.length-1];
        int i = 0;
        while (i < TreeNames.length-1)
        {
            markers[i]=parkMap.addMarker(new MarkerOptions()
                    .position(locations[i])
                    .title(TreeNames[i]));
            markers[i].setTag(i);
            i++;
        }
        LatLng location = new LatLng(locationManager.getLastKnownLocation(provider).getLatitude(),
                locationManager.getLastKnownLocation(provider).getLongitude());
        parkMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        parkMap.setMyLocationEnabled(true);
        parkMap.setOnMyLocationButtonClickListener(this);
        parkMap.setOnMyLocationClickListener(this);
        parkMap.setOnMarkerClickListener(this);
    }
    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent i = new Intent(mapActivity.this,ViewData.class);
        String title = marker.getTitle();
        //  int tag = (int) marker.getTag();
        i.putExtra("TreeName",title);
        startActivity(i);
        return false;
    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" +"Latitude: "+ location.getLatitude()+"\nLongitude: " +
                location.getLongitude(), Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkLocationPermission();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(mapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // startActivity(new Intent(this, ViewData.class));
                }
                return;
            }
        }
    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onLocationChanged(Location location) {
        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
        //MarkerOptions markerOptions = new MarkerOptions();
        //markerOptions.position(latlng);
        //markerOptions.title("your current location");
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //currentLocationMarker = parkMap.addMarker(markerOptions);
        parkMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
