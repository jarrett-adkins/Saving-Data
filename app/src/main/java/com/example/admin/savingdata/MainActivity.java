package com.example.admin.savingdata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.admin.savingdata.R.id.btnGetPersonTable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN ACTIVITY";
    private EditText etSavedDataToSharedPred;
    private TextView tvGetDataFromSharedPref;
    private EditText etPersonName;
    private EditText etPersonGender;
    private EditText etPersonAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSavedDataToSharedPred = (EditText) findViewById( R.id.etSaveDataToSharedPref );
        tvGetDataFromSharedPref = (TextView) findViewById( R.id.tvDataFromSharedPref );

        //for SQL
        etPersonName = (EditText) findViewById( R.id.etPersonName );
        etPersonGender = (EditText) findViewById( R.id.etPersonGender );
        etPersonAge = (EditText) findViewById( R.id.etPersonAge );
     }

    public void usingSharedPref(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences( "mySharedPref", Context.MODE_PRIVATE );
        //making it private prevents other apps from appending to it.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //using the editor to save the value in the shared pref xml file.

        switch( view.getId() ) {
            case R.id.btnSaveDataToSharedPref:
                editor.putString( "data", etSavedDataToSharedPred.getText().toString() );

                if( editor.commit() )
                    Toast.makeText( this, "Saved", Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( this, "Not Saved", Toast.LENGTH_SHORT ).show();
                break;
            case R.id.btnGetDataToSharedPref:
                String data = sharedPreferences.getString( "data", "defaultValue" );
                tvGetDataFromSharedPref.setText( data );
                break;
            case R.id.btnClearSharedPref:
                if( editor.clear().commit() )
                    Toast.makeText( this, "Cleared", Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( this, "Not Cleared", Toast.LENGTH_SHORT ).show();
                break;
        }
    }

    public void usingSQLLite(View view) {
        DatabaseHelper dbh = new DatabaseHelper( this );

        switch ( view.getId() ) {
            case R.id.btnSavePerson:
                String personName = etPersonName.getText().toString();
                String personAge = etPersonAge.getText().toString();
                String personGender = etPersonGender.getText().toString();

                Person p = new Person( personName, personAge, personGender );

                long rowID = dbh.savePerson( p );
                Toast.makeText( this, "Row id: " + rowID, Toast.LENGTH_SHORT ).show();
                break;

            case btnGetPersonTable:
                /*
                List<Person> personList = dbh.getPersonList();
                Log.d(TAG, "usingSQLLite: dumping table.");

                for( Person per: personList)
                    Log.d(TAG, "usingSQLLite: " + per.toString() );
                */
                Intent intent = new Intent( this, PersonListActivity.class );
                startActivity( intent );
                break;
        }
    }
}

// TODO: 10/2/2017


