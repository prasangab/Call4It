package com.call4it.kbsl.prasanga.call4it.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.call4it.kbsl.prasanga.call4it.R;

import java.util.Calendar;

/**
 * Created by root on 6/16/16.
 */
public class SLFragment extends Fragment{

    EditText _from;
    EditText _to;
    static EditText _date;
    ImageButton _dateButton;
    Button _searchButton;

    String date;
    String from;
    String to;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  page = getArguments().getInt("some int", page);
      //  title = getArguments().getString("some title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ntc, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _dateButton = (ImageButton) view.findViewById(R.id.btn_date);
        _dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");
                //_date.setText(formattedDate);

            }
        });
        _date = (EditText) view.findViewById(R.id.date);
        _date.setKeyListener(null);

        _searchButton = (Button) view.findViewById(R.id.btn_search);
        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search();
            }
        });
    }

    // Search........
    public void search(){

        from = _from.getText().toString();
        to = _to.getText().toString();
        date = _date.getText().toString();

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // use the current date as the default date in the picker
            final Calendar c= Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth ;
            Log.d("date :", date);
            _date.setText(date);
            //Calendar c = Calendar.getInstance();
            //c.set(year, monthOfYear, dayOfMonth);

            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //String formattedDate = sdf.format(c.getTime());

            //Log.d("dsads", formattedDate);
            //EditText _date = (EditText) view.findViewById(R.id.date);
            //_date.setText(formattedDate);


        }


    }


}
