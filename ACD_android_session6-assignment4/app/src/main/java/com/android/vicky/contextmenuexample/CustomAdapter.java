package com.android.vicky.contextmenuexample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Vicky on 3/1/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final String[] phNO;

    public CustomAdapter(Activity context, String[] itemname, String[] phNO) {
        super(context, R.layout.custom_list_resource, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.phNO=phNO;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_list_resource, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.label);
        TextView imageView = (TextView) rowView.findViewById(R.id.phnoContainer);


        txtTitle.setText(itemname[position]);
        imageView.setText(phNO[position]);

        return rowView;

    }
}
