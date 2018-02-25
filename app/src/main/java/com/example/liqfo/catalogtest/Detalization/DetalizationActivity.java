package com.example.liqfo.catalogtest.Detalization;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.IdleActivity;
import com.example.liqfo.catalogtest.R;

public class DetalizationActivity extends IdleActivity implements DetalizationView {

    private EditText address;
    private EditText area;
    private EditText price;
    private EditText rooms;
    private EditText money;
    private EditText floor;
    private TextInputLayout addressTIL;
    private TextInputLayout areaTIL;
    private TextInputLayout priceTIL;
    private TextInputLayout roomsTIL;
    private TextInputLayout moneyTIL;
    private TextInputLayout floorTIL;
    private Button confirm;

    private DetalizationPresenter detalizationPresenter;

    public static final String PAR_ID = "pad_id";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalization);

        id = getIntent().getStringExtra(PAR_ID);

        initViews();
        setTitle(getString(R.string.additing));

        detalizationPresenter = new DetalizationPresenter(this, getApplicationContext());
        detalizationPresenter.onCreate();
        detalizationPresenter.getCurrentObject(id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_edit:
                detalizationPresenter.onEditClick();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showCurrentObject(DataObjects objects) {
        address.setText(objects.getAddress());
        area.setText(objects.getArea());
        price.setText(objects.getPrice());
        rooms.setText(objects.getRooms());
        money.setText(String.valueOf(Integer.parseInt(objects.getPrice()) / Integer.parseInt(objects.getArea())));
        floor.setText(objects.getFloor());
    }

    @Override
    public void activateEdit() {
        if (address.isEnabled()) {
            address.setEnabled(false);
            area.setEnabled(false);
            price.setEnabled(false);
            rooms.setEnabled(false);
            floor.setEnabled(false);
            confirm.setVisibility(View.GONE);
        } else {
            confirm.setVisibility(View.VISIBLE);
            address.setEnabled(true);
            area.setEnabled(true);
            price.setEnabled(true);
            rooms.setEnabled(true);
            floor.setEnabled(true);
        }
    }

    @Override
    public void updateObject() {
        activateEdit();
        onBackPressed();
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
        moneyTIL = findViewById(R.id.money_input_layout);
        money = moneyTIL.findViewById(R.id.money);
        floorTIL = findViewById(R.id.floor_input_layout);
        floor = findViewById(R.id.floor);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalizationPresenter.updateObjects(id,
                                                    address.getText().toString(),
                                                    area.getText().toString(),
                                                    price.getText().toString(),
                                                    rooms.getText().toString(),
                                                    floor.getText().toString());
            }
        });

        addressTIL.setHint(getString(R.string.address));
        areaTIL.setHint(getString(R.string.area));
        priceTIL.setHint(getString(R.string.price));
        roomsTIL.setHint(getString(R.string.count_rooms));
        moneyTIL.setHint(getString(R.string.money));
        floorTIL.setHint(getString(R.string.floor));
    }
}
