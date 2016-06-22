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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.call4it.kbsl.prasanga.call4it.R;

import java.util.Calendar;

/**
 * Created by root on 6/22/16.
 */
public class MovieFragment extends Fragment {

    AutoCompleteTextView _film;
    AutoCompleteTextView _theater;
    static EditText _date;
    ImageButton _dateButton;
    Button _searchButton;

    String date;
    String from;
    String to;

    String[] films = {"Sikuru hathe", "paththini", "Ho gana pokuna"};
    String[] theater = {"Savoy-Wellawatta", "Liberty-Kollupitiya", "Anush-Nugegoda"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  page = getArguments().getInt("some int", page);
        //  title = getArguments().getString("some title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        _film = (AutoCompleteTextView) view.findViewById(R.id.film);
        _theater = (AutoCompleteTextView) view.findViewById(R.id.theater);

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,films);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,theater);
        //Getting the instance of AutoCompleteTextView
        _film.setThreshold(1);//will start working from first character
        _film.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
        //_from.setTextColor(Color.RED);

        _theater.setThreshold(1);
        _theater.setAdapter(adapter2);

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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    // Search........
    public void search(){

        from = _film.getText().toString();
        to = _theater.getText().toString();
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
