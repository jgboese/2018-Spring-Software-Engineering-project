package edu.colostate.jaredboese.treetour;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class mapActivity extends FragmentActivity
implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener{
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;
    private GoogleMap parkMap;
    private GoogleApiClient googleClient;
    private Location lastLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Marker currentLocationMarker;
    private String[] TreeNames = new String[] {"CanadaRed","WeepingMulberry","Ivory Silk","Hackberry","Giant Sequoia",
    "AmericanElm","AmericanLinden","Apricot","Aristocrat Pear","AtlasCedar","AustrianPine","AutumnBlaze",
    "AutumnPurple","Betchel Crabapple","BirchbarkCherry","Bluespruce","Bristlecone","Buckthorn","Bur Oak",
    "CrimsonCloud","CrimsonKing","Dawn Redwood","EasternCottonwood","EasternRedbud","EnglishOak",
    "Flamingo Boxelder","FrontierElm","Ginkgo","GoldRain","GreenAsh","GreenVase",
    "IncenseCedar","KentuckyCoffee","LacebarkElm","LindenHybrid","LittleleafLinden","LondonPlane",
    "MountainAsh","Mulberry","NewPortPlum","NorwayMaple","OrnaPear","OrnaCrab","PinyonPine","PonderosaPine",
    "PurpleLocust","Red HorseChesnut","RedspirePear","RockyMountainJ","RussianHawthorn","ScotchPine",
    "Sensation","SiberianElm","ShumardOak","SpringSnow","SugarMaple","Summit Ash","Swamp WhiteOak","Thornless Cockspur",
    "ThornlessHoney","TriColorBeech","Tuliptree","TurkishFilbert","UtahJunpier","WesternCatalpa",
    "WhiteSpruce","WinterKing","Yellowhorn"};
 //   private LocationCallback mLocationCallback;
    String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        checkLocationPermission();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        com.google.android.gms.maps.SupportMapFragment mapFragment =
                (com.google.android.gms.maps.SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.ParkMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission();
    }

    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.i("MainActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());

            }
        };

    };

 /*   public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(mapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Premission is granted
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(googleClient == null){
                            buildGoogleApiClient();
                        }
                        parkMap.setMyLocationEnabled(true);
                    }
                }
                else{ // Permission is not granted
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
                return;
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {
        parkMap = map;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mapActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Tree Tour requires location to run", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
      //  Marker[] markerarray = new Marker[5];


       Marker marker = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0742663, -108.5493843))
                .title(TreeNames[0]));
        marker.setTag(0);


        Marker m2 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741504, -108.5492618))
                .title(TreeNames[1]));
        m2.setTag(1);

        Marker m3 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741242, -108.5486979))
                .title(TreeNames[2]));
        marker.setTag(2);

        Marker m4 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0742909, -108.5490879))
                .title(TreeNames[3]));
        marker.setTag(3);

        Marker m5 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740934, -108.5489564))
                .title(TreeNames[4]));
        marker.setTag(4);

        Marker m6 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0736086, -108.549962))
                .title(TreeNames[5]));
        marker.setTag(5);

        Marker m7 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739769, -108.5510632))
                .title(TreeNames[6]));
        marker.setTag(6);

        /*Marker m8 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0736086, -108.549962))
                .title(TreeNames[7]));
        marker.setTag(7);*/

        Marker m9 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745023, -108.5491173))
                .title(TreeNames[8]));
        marker.setTag(8);

        /*Marker m10 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0736086, -108.549962))
                .title(TreeNames[9]));
        marker.setTag(9);*/

        /*Marker m11 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0736086, -108.549962))
                .title(TreeNames[10]));
        marker.setTag(10);*/

        Marker m12 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.074387, -108.548378))
                .title(TreeNames[11]));
        marker.setTag(11);

        Marker m13 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739733, -108.5514381))
                .title(TreeNames[12]));
        marker.setTag(12);

        Marker m14 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0742073, -108.5487163))
                .title(TreeNames[13]));
        marker.setTag(13);

        Marker m15 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073705, -108.551438))
                .title(TreeNames[14]));
        marker.setTag(14);

        Marker m16 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.074768, -108.5509806))
                .title(TreeNames[15]));
        marker.setTag(15);

        /*Marker m17 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739733, -108.5514381))
                .title(TreeNames[16]));
        marker.setTag(16);*/

        Marker m18 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0746731, -108.5508771))
                .title(TreeNames[17]));
        marker.setTag(17);
        //bur oak
        Marker m19 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745776, -108.548776))
                .title(TreeNames[18]));
        marker.setTag(18);
        //crimson cloud
        Marker m20 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0749561, -108.5518873))
                .title(TreeNames[19]));
        marker.setTag(19);
        //crimson king
        Marker m21 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739348, -108.5515128))
                .title(TreeNames[20]));
        marker.setTag(20);
        //Dawn redwood
        Marker m22 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739064, -108.549803))
                .title(TreeNames[21]));
        marker.setTag(21);
        //Eastern Cottonwood
        Marker m23 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0746931, -108.5509023))
                .title(TreeNames[22]));
        marker.setTag(22);
        //Eastern Redbud
        Marker m24 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740323, -108.5506498))
                .title(TreeNames[23]));
        marker.setTag(23);
        //Estern Oak
        Marker m25 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741996, -108.5516606))
                .title(TreeNames[24]));
        marker.setTag(24);
        //Flamingo Boxelder
        Marker m26 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0738982, -108.5485951))
                .title(TreeNames[25]));
        marker.setTag(25);
        //FrontierElm
        Marker m27 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0734252, -108.5516718))
                .title(TreeNames[26]));
        marker.setTag(26);
        //Ginkgo
        Marker m28 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745973, -108.5515721))
                .title(TreeNames[27]));
        marker.setTag(27);
        //Gold Rain
        Marker m29 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740746, -108.5516371))
                .title(TreeNames[28]));
        marker.setTag(28);
        //Green Ash
        Marker m30 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[29]));
        marker.setTag(29);
        //Green Vase
        Marker m31 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0749684, -108.551932))
                .title(TreeNames[30]));
        marker.setTag(30);
        //Incense Cedar
       /* Marker m32 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[31]));
        marker.setTag(31);*/
       //Kentucky Coffee
        Marker m33 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741759, -108.5511806))
                .title(TreeNames[32]));
        marker.setTag(32);
        //Lacebark Elm
        Marker m34 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739131, -108.5500567))
                .title(TreeNames[33]));
        marker.setTag(33);
        //LindenHybrid
        Marker m35 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740292, -108.5513712))
                .title(TreeNames[34]));
        marker.setTag(34);
        //Littleleaf Linden
        Marker m36 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739338, -108.5510996))
                .title(TreeNames[35]));
        marker.setTag(35);
        //London Plane
        Marker m37 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0734497, -108.551929))
                .title(TreeNames[36]));
        marker.setTag(36);
        //Mountain Ash
        Marker m38 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0739, -108.551719))
                .title(TreeNames[37]));
        marker.setTag(37);
        //Mulberry
        Marker m39 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0737932, -108.5496831))
                .title(TreeNames[38]));
        marker.setTag(38);
        //NewPort Plum
        /*
        Marker m40 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[39]));
        marker.setTag(39);
        */
        //Norway Maple
        Marker m41 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740743, -108.5513482))
                .title(TreeNames[40]));
        marker.setTag(40);
        //Ornamental Pear
        Marker m42 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0746053, -108.551159))
                .title(TreeNames[41]));
        marker.setTag(41);
        //Ornamental Crab
        Marker m43 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0737677, -108.5511678))
                .title(TreeNames[42]));
        marker.setTag(42);
        //Pinyon Pine
        /*
        Marker m44 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[43]));
        marker.setTag(43);
        */
        //Ponderosa Pine
        Marker m45 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741961, -108.5508158))
                .title(TreeNames[44]));
        marker.setTag(44);
        //Purple Locust
        Marker m46 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0735008, -108.550292))
                .title(TreeNames[45]));
        marker.setTag(45);
        //Red Horsechesnut
        Marker m47 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0735653, -108.549126))
                .title(TreeNames[46]));
        marker.setTag(46);
        //Redspire Pear
        Marker m48 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0747578, -108.5509623))
                .title(TreeNames[47]));
        marker.setTag(47);
        //Rocky Mountain Juniper
        Marker m49 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0750126, -108.5521945))
                .title(TreeNames[48]));
        marker.setTag(48);
        //Russian Hawthorne
        /*
        Marker m50 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[49]));
        marker.setTag(49);
        */
        //Scotch Pine
        Marker m51 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0752279, -108.5508726))
                .title(TreeNames[50]));
        marker.setTag(50);
        //Sensation
        Marker m52 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.074871, -108.5509511))
                .title(TreeNames[51]));
        marker.setTag(51);
        //Siberian Elm
        Marker m53 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0746731, -108.5508771))
                .title(TreeNames[52]));
        marker.setTag(52);
        //Shumard Oak
        /*
        Marker m54 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[53]));
        marker.setTag(53);
        */
        // Spring snow crabapple
        Marker m55 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073705, -108.5499708))
                .title(TreeNames[54]));
        marker.setTag(54);
        //Sugar Maple
        Marker m56 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0742554, -108.5515343))
                .title(TreeNames[55]));
        marker.setTag(55);
        //Summit Ash
        Marker m57 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0734463, -108.5497535))
                .title(TreeNames[56]));
        marker.setTag(56);
        //Swamp White Oak
        Marker m58 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745845, -108.5487139))
                .title(TreeNames[57]));
        marker.setTag(57);
        //Thornless Cockspur
        Marker m59 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0741064, -108.5495885))
                .title(TreeNames[58]));
        marker.setTag(58);
        //Thronless Honey
        Marker m60 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745121, -108.5515095))
                .title(TreeNames[59]));
        marker.setTag(59);
        //Tricolor beech
        Marker m61 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073822, -108.5519241))
                .title(TreeNames[60]));
        marker.setTag(60);
        //TulipTree
        /*Marker m62 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[61]));
        marker.setTag(61);*/
        //Turkish Filbert
        Marker m63 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0740271, -108.5518247))
                .title(TreeNames[62]));
        marker.setTag(62);
        //Utah Juniper
        /*Marker m64 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[63]));
        marker.setTag(63);*/
        //Western Cata
        Marker m65 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0737584, -108.5518237))
                .title(TreeNames[64]));
        marker.setTag(64);
        //White Spruce
        /*Marker m66 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.073509, -108.5514231))
                .title(TreeNames[65]));
        marker.setTag(65);*/
        //Winter King
        Marker m67 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0745675, -108.5513996))
                .title(TreeNames[66]));
        marker.setTag(66);
        //YellowHorn
        Marker m68 = parkMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.0738283, -108.5486639))
                .title(TreeNames[67]));
        marker.setTag(67);








        parkMap.setOnMarkerClickListener(this);
        parkMap.setMyLocationEnabled(true);
        parkMap.setOnMyLocationButtonClickListener(this);
        parkMap.setOnMyLocationClickListener(this);


    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent i = new Intent(mapActivity.this,ViewData.class);
        String title = marker.getTitle();
   //     int tag = (int) marker.getTag();
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

    protected synchronized void buildGoogleApiClient(){
        googleClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleClient.connect();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
           // LocationServices.FusedLocationApi.requestLocationUpdates(googleClient, locationRequest, this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations, this can be null.
                            if (location != null) {
                                // Logic to handle location object
                            }
                        }
                    });
        }

    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return  false;
        }
        return  true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }

        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("your current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        currentLocationMarker = parkMap.addMarker(markerOptions);

        parkMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        parkMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(googleClient != null){
         //   LocationServices.FusedLocationApi.removeLocationUpdates(googleClient,this);
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}