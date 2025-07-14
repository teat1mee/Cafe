package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textViewGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private TextView textViewAdditeves;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonMakeOrder;

    private String userName;
    private String drink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setupUserName();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {

                if (id == radioButtonTea.getId()){
                    userChoseTea();
                } else if (id == radioButtonCoffee.getId()) {
                    userChoseCoffee();
                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMakeOrder();
            }
        });
    }

    private void onUserMakeOrder(){
        ArrayList<String> additives = new ArrayList<>();
        if (checkBoxSugar.isChecked()){
            additives.add(checkBoxSugar.getText().toString());
        }if (checkBoxMilk.isChecked()){
            additives.add(checkBoxMilk.getText().toString());
        }if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()){
            additives.add(checkBoxLemon.getText().toString());
        }

        String drinkType ="";

        if (radioButtonTea.isChecked()){
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailActivity.newIntent(this,
                userName,
                additives.toString(),
                drinkType,
                drink);
        startActivity(intent);
    }

    private void userChoseTea(){
        drink = getString(R.string.tea);
        String additivesLabel = getString(R.string.additives,drink);
        textViewAdditeves.setText(additivesLabel);
        checkBoxSugar.setVisibility(View.VISIBLE);
        checkBoxMilk.setVisibility(View.VISIBLE);
        checkBoxLemon.setVisibility(View.VISIBLE);

        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }
    private void userChoseCoffee(){
        drink = getString(R.string.coffee);
        String additivesLabel = getString(R.string.additives,drink);
        textViewAdditeves.setText(additivesLabel);
        checkBoxSugar.setVisibility(View.VISIBLE);
        checkBoxMilk.setVisibility(View.VISIBLE);
        checkBoxLemon.setVisibility(View.INVISIBLE);

        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings);

        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);

        textViewAdditeves = findViewById(R.id.textViewAdditeves);

        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);

        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        spinnerTea = findViewById(R.id.spinnerTea);

        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
    }

    private void setupUserName(){
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings,userName);
        textViewGreetings.setText(greetings);
    }


    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME , userName);
        return intent;
    }
}