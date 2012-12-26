package com.socmodder.android.mysign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.socmodder.android.database.Sign;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: socmodder
 * Date: 12/26/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class SignArrayAdapter extends ArrayAdapter<Sign> {
    private final Context context;
    private final List<Sign> signs;

    public SignArrayAdapter(Context context, List<Sign> signs) {
        super(context, R.layout.sign_list_item, signs);
        this.context = context;
        this.signs = signs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.sign_list_item, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.item_name);
        TextView city = (TextView) rowView.findViewById(R.id.item_city);
        ImageView image = (ImageView) rowView.findViewById(R.id.item_image);

        if(signs.get(position) != null){
            name.setText(signs.get(position).getStreetName());
            city.setText(signs.get(position).getCity());
        }
        return rowView;
    }
}