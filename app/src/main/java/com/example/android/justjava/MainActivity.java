package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        //Find the user number
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);
        //Figure out if the user want whipped cream topping
        CheckBox whipped_cream_checkbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whipped_cream_checkbox.isChecked();
        //Figure out if the user want whipped cream topping
        CheckBox add_chocolate_checkbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = add_chocolate_checkbox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }



    /**
     * This method displays the given quantity value on the screen.
     * @param
     * @return  total price
     */
    private  int calculatePrice(boolean addWhippedCream,boolean addChocolate){
        int basePrice = 5;
        if (addWhippedCream){
            basePrice = basePrice +1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
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
        if (quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        displayQuantity(quantity);
    }

    }

