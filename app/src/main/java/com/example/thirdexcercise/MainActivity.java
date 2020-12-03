package com.example.thirdexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // creating array list of coffee types
    ArrayList<coffee> list = new ArrayList<>();
    ArrayList<String> coffeeName =  new ArrayList<>();

    // creating needed instance of components
    TextView total;
    Spinner spinner;
    RadioButton rbSmall, rbMedium, rbLarge;



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

        // creating event for each raioButton
        rbSmall.setOnClickListener(this);
        rbMedium.setOnClickListener(this);
        rbLarge.setOnClickListener(this);



        // filling the spinner
        ArrayAdapter i = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,coffeeName);
        spinner.setAdapter(i);
        spinner.setOnItemSelectedListener(this);


        // seting total to first value of the dropDown Menu
        total.setText(Double.toString(list.get(0).getPrice()) + " $ " ); // changing double to String type
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        total.setText(Double.toString(list.get(position).getPrice()) + " $ "); // changing double to String type

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        
    }
}