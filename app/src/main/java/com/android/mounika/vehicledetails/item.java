package com.android.mounika.vehicledetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }

        public class data {
            public String DatePicker;
            public int Amount;
            public String Message;
            //public String Save;

            data(String datePicker, int amount, String message) {
                this.DatePicker = datePicker;
                this.Amount = amount;
                this.Message = message;

            }


        }
}
