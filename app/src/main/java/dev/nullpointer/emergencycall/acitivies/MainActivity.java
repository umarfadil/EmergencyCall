package dev.nullpointer.emergencycall.acitivies;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.nullpointer.emergencycall.R;
import dev.nullpointer.emergencycall.adapter.ItemsAdapter;
import dev.nullpointer.emergencycall.model.Items;
import dev.nullpointer.emergencycall.utils.RVClickListener;
import dev.nullpointer.emergencycall.utils.RVTouchListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private List<Items> itemsList;
    String phone = "+6282332901990";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        adapter = new ItemsAdapter(getApplicationContext(), itemsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RVTouchListener(getApplicationContext(), recyclerView, new RVClickListener() {
            @Override
            public void onClick(View view, int position) {
                Items items = itemsList.get(position);
                if (items.getTitle().equals("Polisi")) {
                    Toast.makeText(MainActivity.this, "Panggil Pak Polisi", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
                } else if (items.getTitle().equals("Rumah Sakit")) {
                    Toast.makeText(MainActivity.this, "Panggil Rumah Sakit", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Panggil Saya saja", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Di Klik Lamaaaa...", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
            }
        }));

        prepareData();
    }

    private void prepareData() {
        int[] thumbnail = new int[] {
                R.drawable.alarm,           //0
                R.drawable.ambulance,       //1
                R.drawable.damkar,          //2
                R.drawable.police,          //3
                R.drawable.fire_truck,      //4
                R.drawable.pizzeria,        //5
                R.drawable.police_station   //6
        };

        Items a = new Items("Polisi", thumbnail[3]);
        itemsList.add(a);

        a = new Items("Rumah Sakit", thumbnail[1]);
        itemsList.add(a);

        a = new Items("Damkar", thumbnail[4]);
        itemsList.add(a);

        a = new Items("Tim SAR", thumbnail[2]);
        itemsList.add(a);

        a = new Items("PLN", thumbnail[5]);
        itemsList.add(a);

        a = new Items("BNN", thumbnail[6]);
        itemsList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this,
                            "Permission denied to make a call", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
