package com.androidopshanka.mynote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,AddNoteActivity.class);
                startActivity(intent);
            }
        });

        listView=(ListView) findViewById(R.id.note_list);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(null);

        ArrayList<Note> notes=Utilities.getAllSavedNotes(this);
        if(notes==null||notes.size()==0){
            Toast.makeText(this, "You have no notes", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            NoteAdapter na=new NoteAdapter(this,R.layout.item_note,notes);
            listView.setAdapter(na);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String filename=((Note)listView.getItemAtPosition(position)).getmDatTime()
                            +Utilities.FILE_EXTENTION;

                    Intent viewNoteIntent=new Intent(getApplicationContext(),AddNoteActivity.class);
                    viewNoteIntent.putExtra("NoteFile",filename);
                    startActivity(viewNoteIntent);

                }
            });

        }
    }
}
