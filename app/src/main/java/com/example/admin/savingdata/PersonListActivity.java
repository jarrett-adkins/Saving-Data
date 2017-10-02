package com.example.admin.savingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PersonListActivity extends AppCompatActivity {

    private static final String TAG = "PERSON LIST ACTIVITY";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        listView = (ListView) findViewById( R.id.lvPersons );

        /* dummy data
        String[] values = new String[] {
                "v1",
                "v2",
                "v3",
                "v4"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter( adapter );
        //*/

        /* standard adapter
        DatabaseHelper dbh = new DatabaseHelper( this );
        List<Person> personList = dbh.getPersonList();
        ArrayAdapter<Person> adapter =
                new ArrayAdapter<Person>( this, android.R.layout.simple_list_item_1, personList);
        listView.setAdapter( adapter );
        //*/

        //* custom adapter
        DatabaseHelper dbh = new DatabaseHelper( this );
        List<Person> personList = dbh.getPersonList();
        PersonListAdapter psa = new PersonListAdapter( this, R.layout.custom_list_item, personList);
        listView.setAdapter( psa );
        //*/
    }
}
