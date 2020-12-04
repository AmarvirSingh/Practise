package com.example.thirdexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    // creating array list of coffee types
    ArrayList<coffee> list = new ArrayList<>();
    ArrayList<String> coffeeName =  new ArrayList<>();

    public static double originalPrice = 0;

    // creating needed instance of components
    TextView total,quantity;
    Spinner spinner;
    RadioButton rbSmall, rbMedium, rbLarge;
    CheckBox cbSugar,cbMilk,cbCream;
    SeekBar seekBar;
    Button btnOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling fill data function so that array will be filled

        fillData();

        // creating connection with design
        total = findViewById(R.id.total);
        spinner = findViewById(R.id.spinner);
        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);
        cbSugar = findViewById(R.id.cbSugar);
        cbCream = findViewById(R.id.cbCream);
        cbMilk = findViewById(R.id.cbMilk);
        quantity = findViewById(R.id.quantity);
        seekBar = findViewById(R.id.seekBar);
        btnOrder = findViewById(R.id.btnOrder);



        // creating event for each raioButton
        rbSmall.setOnClickListener(this);
        rbMedium.setOnClickListener(this);
        rbLarge.setOnClickListener(this);

        rbSmall.isChecked();

        // creatnig event for button
        btnOrder.setOnClickListener(this);


        // creating evernt for checkBoxes
        cbCream.setOnCheckedChangeListener(new checkboxEvent());
        cbMilk.setOnCheckedChangeListener(new checkboxEvent());

        // setting event for Seekbar

        seekBar.setOnSeekBarChangeListener(this);


        // filling the spinner
        ArrayAdapter i = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,coffeeName);
        spinner.setAdapter(i);
        spinner.setOnItemSelectedListener(this);


        // seting total to first value of the dropDown Menu
        total.setText(Double.toString(list.get(0).getPrice()) ); // changing double to String type
    }

    public void fillData(){
        list.add(new coffee("Tea", 1.5));
        list.add(new coffee("Simple Coffee", 1.7));
        list.add(new coffee("Hot Chocolate",1.3));
        list.add(new coffee("cappacinno", 2.2));
        list.add(new coffee("Espresso",0.9));
        for (int i = 0;i < list.size(); i++){
            coffeeName.add(list.get(i).getName());  // filling the arraylist with the name of coffee types
        }

    }

    public void reset(){
        rbSmall.setChecked(true);
        cbMilk.setChecked(false);
        cbCream.setChecked(false);
        cbSugar.setChecked(false);
        quantity.setText("");
        originalPrice = 0;

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        reset();
        rbSmall.isChecked();
        total.setText(Double.toString(list.get(position).getPrice())); // changing double to String type
        originalPrice = list.get(position).getPrice();  // whenever something is selected price will be set to original price variable 
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        double tot = originalPrice; // using original price variable

        switch (v.getId()){
            case R.id.rbSmall:
                tot = 1 * originalPrice ;
                break;
            case R.id.rbMedium:
                tot = 1.5 * originalPrice ;
                break;
            case R.id.rbLarge:
                tot = 2.0 * originalPrice ;
                break;
            case R.id.btnOrder:
                double amount = Double.parseDouble(total.getText().toString());
                double quan = Double.parseDouble(quantity.getText().toString());
                amount *= quan;
                tot = amount * 1.13;
                reset();
        }
        total.setText(String.format("%.2f",tot));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        quantity.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class checkboxEvent implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            double tot = Double.parseDouble(total.getText().toString());

            if (buttonView.getId() == R.id.cbCream){
                if (cbCream.isChecked()){
                    tot += 0.5;
                }else {
                    tot -= 0.5;
                }
            }
            if (buttonView.getId() == R.id.cbMilk){
                if (cbMilk.isChecked()){
                    tot += 0.25;
                }else{
                    tot -= 0.25;
                }
            }
            total.setText(String.format("%.2f", tot));
        }
    }


}