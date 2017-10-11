package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity =0;
    private int price =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckBox.isChecked();
        Log.v("MainActivity.java","Has Whipped Cream " + hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean isChocolate= chocolateCheckBox.isChecked();
        Log.v("MainActivity.java", "Is choco" +isChocolate);

        EditText editName = (EditText) findViewById(R.id.name_view);
        String inputName = editName.getText().toString();
        Log.v("MainActivity.java", inputName);

        //int price=calculatePrice();

        if(hasWhippedCream == true && isChocolate ==true) {
            price = quantity * (5 + 1 +2);

        } else if (hasWhippedCream ==true){
            price = quantity * (5 +1);
        } else if(isChocolate ==true){
            price = quantity * (5 +2);
        }
        String StringMessage=createOrderSummary(inputName,price, hasWhippedCream, isChocolate);
        //displayMessage(StringMessage);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "TEST Just Java order for " +inputName);
        intent.putExtra(Intent.EXTRA_TEXT, StringMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void increment(View view) {

        if(quantity > 100) {
            Toast.makeText(getApplicationContext(), " No greater than 100 cups.!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);


    }

    public void decrement(View view) {

        if(quantity < 1) {
            Toast.makeText(getApplicationContext(), " No less than 1 cup.!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity - 1;
        display(quantity);
    }

    private int calculatePrice() {
        price = quantity * 5;
        return price;
    }

    private String createOrderSummary(String inputName,int price, boolean hasWhippedCream,boolean isChocolate){
        String StringMessage =   "Name: " + inputName +
                                "\nHas Whipped cream?" +hasWhippedCream +
                                "\nAdd chocolate?" +isChocolate +
                                "\nQuantity: " + quantity +
                                "\nTotal bill: $" + price +
                                "\nThank you";
        return StringMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
