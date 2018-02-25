package com.example.liqfo.catalogtest.AddNew;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.liqfo.catalogtest.IdleActivity;
import com.example.liqfo.catalogtest.R;

public class AddNewActivity extends IdleActivity implements AddView {

    private EditText address;
    private EditText area;
    private EditText price;
    private EditText rooms;
    private EditText floor;
    private TextInputLayout addressTIL;
    private TextInputLayout areaTIL;
    private TextInputLayout priceTIL;
    private TextInputLayout roomsTIL;
    private TextInputLayout floorTIL;

    private AddNewPresenter addNewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_objects);

        initViews();
        setTitle(getString(R.string.new_object));

        addNewPresenter = new AddNewPresenter(this, getApplicationContext());
        addNewPresenter.onCreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_in_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_confirm:
                addNewPresenter.addNewItem(address.getText().toString(),
                                            area.getText().toString(),
                                            price.getText().toString(),
                                            rooms.getText().toString(),
                                            floor.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        addressTIL = findViewById(R.id.address_input_layout);
        address = addressTIL.findViewById(R.id.address);
        areaTIL = findViewById(R.id.area_input_layout);
        area = areaTIL.findViewById(R.id.area);
        priceTIL = findViewById(R.id.price_input_layout);
        price = priceTIL.findViewById(R.id.price);
        roomsTIL = findViewById(R.id.rooms_input_layout);
        rooms = roomsTIL.findViewById(R.id.rooms);
        floorTIL = findViewById(R.id.floor_input_layout);
        floor = findViewById(R.id.floor);


        addressTIL.setHint(getString(R.string.address));
        areaTIL.setHint(getString(R.string.area));
        priceTIL.setHint(getString(R.string.price));
        roomsTIL.setHint(getString(R.string.count_rooms));
        floorTIL.setHint(getString(R.string.floor));
    }

    @Override
    public void successAdding() {
        onBackPressed();
    }
}
