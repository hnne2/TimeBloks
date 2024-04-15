package com.example.timeblocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageAdapter imageAdapter=new ImageAdapter(this);
    GridView gridview;
    private DaysAppDataBase daysAppDataBase;
    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDateTime = findViewById(R.id.selectedDate);

        gridview = findViewById(R.id.gridView1);
        setInitialDate();
        daysAppDataBase = Room.databaseBuilder(getApplicationContext(),DaysAppDataBase.class,"DaysDB").allowMainThreadQueries().build();

        if ( daysAppDataBase.getDayDao().findByDate(currentDateTime.getText().toString())!=null){
            imageAdapter.mThumbIds=stringToIntArray(daysAppDataBase.getDayDao().findByDate(currentDateTime.getText().toString()).dayInfo);
        }

        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(gridviewOnItemClickListener);
    }
    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {
        if (position!=0){
            if (imageAdapter.mThumbIds[position] == R.drawable.pust) {
                imageAdapter.mThumbIds[position] = R.drawable.gal;
            } else if (imageAdapter.mThumbIds[position] == R.drawable.gal) {
                imageAdapter.mThumbIds[position] = R.drawable.krestik;
            } else if (imageAdapter.mThumbIds[position] == R.drawable.krestik) {
                imageAdapter.mThumbIds[position] = R.drawable.pust;
            } 
            gridview.setAdapter(imageAdapter);
        }}
    };


    // установка обработчика выбора даты
    public void setDate(View v) {

        if ( daysAppDataBase.getDayDao().findByDate(currentDateTime.getText().toString())==null){
         daysAppDataBase.getDayDao().insertAll(new Day(Arrays.toString(imageAdapter.mThumbIds),currentDateTime.getText().toString()));}
        else {
                   daysAppDataBase.getDayDao().updateDayInfoByDayData(Arrays.toString(imageAdapter.mThumbIds),currentDateTime.getText().toString());
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), currentDateTime.getText().toString(), Toast.LENGTH_LONG).show();
                if ( daysAppDataBase.getDayDao().findByDate(currentDateTime.getText().toString())==null){
                    imageAdapter.mThumbIds=new mThumIds().mThumbIds;
                }else {
               imageAdapter.mThumbIds=stringToIntArray(daysAppDataBase.getDayDao().findByDate(currentDateTime.getText().toString()).dayInfo);}
              gridview.setAdapter(imageAdapter);
            }
        });

        datePickerDialog.show();

    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };

    // установка начальной даты и времени
    private void setInitialDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(dateAndTime.getTime());
        currentDateTime.setText(formattedDate);
    }
    public int[] stringToIntArray(String str) {
        str=removeFirstAndLastChar(str);
        String[] parts = str.split(", ");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }
    public String removeFirstAndLastChar(String str) {
        if (str == null || str.length() <= 1) {
            return "";
        }
        return str.substring(1, str.length() - 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        daysAppDataBase.getDayDao().updateDayInfoByDayData(Arrays.toString(imageAdapter.mThumbIds),currentDateTime.getText().toString());
        Log.e("log","закрылось окно");     }
    public void clearInfo(View v){
        imageAdapter.mThumbIds=new mThumIds().mThumbIds;
        gridview.setAdapter(imageAdapter);
    }
}
