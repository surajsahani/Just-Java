package com.example.android.justjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.EdgeEffectCompat;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);
        CheckBox whipped_cream_checkbox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox add_chocolate_checkbox =(CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean haswhippedCream = whipped_cream_checkbox.isChecked();

        boolean addChocolate = add_chocolate_checkbox.isChecked();

        int price =  calculatePrice();
        displayMessage(createOrderSummary(name,price, haswhippedCream, addChocolate));
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param
     * @return  total price
     */
    private  int calculatePrice(){
        return quantity * 5;
    }
    private  String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage ="Name: " +name;
        priceMessage +=   "\nAdd Chocolate?" + addChocolate;
        priceMessage +=   "\nAdd Whipped Cream?" + addWhippedCream;
        priceMessage +=   "\nQuantity:" + quantity;
        priceMessage +=   "\nTotal: $" + price;
        priceMessage +=   "\nThank you!";
        return priceMessage;
    }
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity -1;
        displayQuantity(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(message);
    }
    private void hasChecked(View view){
        boolean hasChecked = true;

    }

}
