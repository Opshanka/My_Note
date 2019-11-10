package com.androidopshanka.mynote;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private EditText mEtTitle;
    private EditText mEtContent;
    private String mNoteFileName;
    private Note mLoadedNote;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        mEtTitle=(EditText)findViewById(R.id.note_editext_title);
        mEtContent=(EditText)findViewById(R.id.note_editext_cotent);
        mNoteFileName=getIntent().getStringExtra("NoteFile");

        if (mNoteFileName!=null && !mNoteFileName.isEmpty()){
            mLoadedNote=Utilities.getNoteByName(this,mNoteFileName);
            if (mLoadedNote!=null){
                mEtTitle.setText(mLoadedNote.getmTitile());
                mEtContent.setText(mLoadedNote.getmContent());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes_new,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notes_save) {
            saveNotes();
            return true;
        }
        if (id == R.id.action_notes_delete) {
            deleteNotes();
            return true;
        }
        return true;
    }


    private void saveNotes(){
        Note note;
        if (mEtTitle.getText().toString().trim().isEmpty() || mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter title and note", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mLoadedNote==null) {
            note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString(), mEtContent.getText().toString());
        }
        else {
            note = new Note(mLoadedNote.getmDatTime(), mEtTitle.getText().toString(), mEtContent.getText().toString());

        }


        if (Utilities.saveNote(this,note)){
            Toast.makeText(this, "Your note is saved", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Pleace make sure have enough space", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNotes() {
        if (mLoadedNote==null){
            finish();
        }
        else {

            AlertDialog.Builder dialog=new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Do you want to delete \'"+mLoadedNote.getmTitile()+"\'?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext(),mLoadedNote.getmDatTime()+Utilities.FILE_EXTENTION);
                            Toast.makeText(AddNoteActivity.this, mEtTitle.getText().toString()+"is deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("No",null)
                    .setCancelable(false);

            dialog.show();
                    




        }
    }

}
