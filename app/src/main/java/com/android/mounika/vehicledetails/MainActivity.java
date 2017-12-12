package com.android.mounika.vehicledetails;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase database = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(MainActivity.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        VehicleDB db = new VehicleDB(this);
        database = db.getWritableDatabase();
    }

    public void saveDetails(View view) {
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        EditText Amount = (EditText) findViewById(R.id.editText1);
        EditText Message = (EditText) findViewById(R.id.editText2);
        Button Button = (android.widget.Button) findViewById(R.id.button1);

        String d = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
        /*Date D = new Date();
        try {
            D = new SimpleDateFormat("dd/MM/YYYY").parse(d);
        }
        catch(ParseException e){
        }*/

        //int D = DatePicker.getDayOfMonth();

        int a = Integer.parseInt(Amount.getText().toString());
        String m = Message.getText().toString();

        ContentValues map = new ContentValues();
        map.put("Date", d);
        map.put("Amount", a);
        map.put("Message", m);

        database.insert("VehicleDB", null, map);

        Toast.makeText(this, "Details Saved...", Toast.LENGTH_LONG).show();
    }

    public void getDetails(View view) {
        ArrayList list = new ArrayList();
        String col[] = {"Date", "Amount", "Message"};
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, item.class);
                startActivity(i);
            }
        });
        Cursor cursor = database.query("Vehicledetails", col, null, null, null, null, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                String date = cursor.getString(0);
                int amount = cursor.getInt(1);
                String message = cursor.getString(2);
                list.add("Date=" + date + "Amount=" + amount + "Message=" + message);
            }
            while (cursor.moveToNext());
            RecyclerView reView = (RecyclerView) findViewById(R.id.recycler);
        }
    }
  // public void addItem(View view){
   //    Button button1 = (Button) findViewById(R.id.button);
      // button1.setOnClickListener(new View.OnClickListener()

      /* {
           @Override
           public void onClick (View v){
               Intent i = new Intent(MainActivity.this, item.class);
               startActivity(i);
           }
       });*/


  // }

    @Override
    public void onClick(View v) {
        Log.i("inonclick","hjty");
        Intent i = new Intent(MainActivity.this, item.class);
        startActivity(i);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main,null);
            //View reView=LayoutInflater.from(item.this).inflate(R.layout.activity_main,null);

            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}