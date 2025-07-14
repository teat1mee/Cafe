package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView textViewName ;
    private TextView textViewDrink ;
    private TextView textViewDrinkType ;
    private TextView textViewAdditeves ;


    private static final String Extra_USER_NAME = "userName";
    private static final String Extra_ADDITIVES = "additives";
    private static final String Extra_DRINK_TYPE = "drinkType";
    private static final String Extra_DRINK = "drink";
    private String userName;
    private String additivies;
    private String drinkType;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        initIntent();

    }

    private  void initViews(){
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditeves = findViewById(R.id.textViewAdditeves);
    }

    private void initIntent(){
        userName = getIntent().getStringExtra(Extra_USER_NAME);
        String greetings = getString(R.string.viewName,userName);
        textViewName.setText(greetings);

        drink = getIntent().getStringExtra(Extra_DRINK);
        String yourDrinks = getString(R.string.viewDrinks,drink);
        textViewDrink.setText(yourDrinks);

        drinkType = getIntent().getStringExtra(Extra_DRINK_TYPE);
        String yourDrinkType = getString(R.string.viewDrinkType,drinkType);
        textViewDrinkType.setText(yourDrinkType);

        additivies = getIntent().getStringExtra(Extra_ADDITIVES);
        String yourAdditives = getString(R.string.viewAdditives,additivies);
        textViewAdditeves.setText(yourAdditives);
    }

    public static Intent newIntent(
            Context context,
            String userName,
            String additives,
            String drinkType,
            String drink) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("additives", additives);
        intent.putExtra("drinkType", drinkType);
        intent.putExtra("drink", drink);
        return intent;
    }



}