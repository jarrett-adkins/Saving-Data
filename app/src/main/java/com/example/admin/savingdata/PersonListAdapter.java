package com.example.admin.savingdata;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 10/2/2017.
 */

public class PersonListAdapter extends ArrayAdapter<Person> {

    public PersonListAdapter(@NonNull Context context, @LayoutRes int resource, List<Person> personList) {
        super(context, resource, personList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        //inflate the view with custom layout xml
        if( view == null )
            view = LayoutInflater.from( parent.getContext()).inflate( R.layout.custom_list_item, null );

        //bind views

        TextView nameTextView = view.findViewById( R.id.tvPersonName ); //not extending AppCompatActivity
        TextView ageTextView = view.findViewById( R.id.tvPersonAge );
        TextView genderTextView = view.findViewById( R.id.tvPersonGender );

        //set values to the binded views
        Person person = getItem( position );
        nameTextView.setText( person.getName() );
        ageTextView.setText( person.getAge() );
        genderTextView.setText( person.getGender() );

        return view;
    }
}
