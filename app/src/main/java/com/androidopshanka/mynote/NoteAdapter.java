package com.androidopshanka.mynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_note,null);
        }
        Note note=getItem(position);
        if (note!=null){
            TextView vTitle=(TextView) convertView.findViewById(R.id.list_note_title);
            TextView vDate=(TextView) convertView.findViewById(R.id.list_note_item_created_time);
            TextView vContent=(TextView) convertView.findViewById(R.id.list_note_item_content);

            vTitle.setText(note.getmTitile());
            vDate.setText(note.dateFormated(getContext()));
            vContent.setText(note.getmContent());

            if (note.getmContent().length()>50){
                vContent.setText(note.getmContent().substring(0,50));
            }
            else {
                vContent.setText(note.getmContent());
            }
        }
        return convertView;
    }
}
