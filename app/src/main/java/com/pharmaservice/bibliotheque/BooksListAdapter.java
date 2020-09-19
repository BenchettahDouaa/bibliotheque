package com.pharmaservice.bibliotheque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class BooksListAdapter extends ArrayAdapter<Books> {
    DBHelper mydb;


    Context mContext;
    private int mResource;
    private int lastPosition = -1;


    private static class ViewHolder {
        TextView Id;
        TextView title;
        TextView author;
        Button btn;
    }


    public BooksListAdapter(Context context, int resource, ArrayList<Books> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Books b=getItem(position);
        int Id = getItem(position).getId();
        String title = getItem(position).getTitre();
        String author = getItem(position).getAuteur();
        String keyWord = getItem(position).getMotCles();


       Books book = new Books(Id,title,author,keyWord);
        mydb = new DBHelper(getContext());
        final View result;
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.Id = (TextView) convertView.findViewById(R.id.tvId);
            holder.Id.setText(Id+"");
            holder.title = (TextView) convertView.findViewById(R.id.tvTitre);
            holder.title.setText(title+"");
            holder.author = (TextView) convertView.findViewById(R.id.tvAuteur);
            holder.author.setText(author+"");


            holder.btn=(Button)convertView.findViewById(R.id.delete);
            holder.btn.setTag(b);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mydb.deleteBook(((Books)v.getTag()).getId());
                    remove((Books)v.getTag());



        }
    });
            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        return result;
    }



}
