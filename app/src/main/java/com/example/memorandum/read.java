package com.example.memorandum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memorandum.Dao.NoteDao;
import com.example.memorandum.Dao.NoteDaoLmp;

import java.io.File;
import java.util.ArrayList;

public class read extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "noteId";
    private ShareActionProvider shareActionProvider;
    public String NoteText;
    public String NoteTitle;
    public String NoteImage;
    int type;
    public ArrayList<Note> notes = new ArrayList<Note>();
    public ArrayList<Note> notesStu = new ArrayList<Note>();
    public ArrayList<Note> notesLife = new ArrayList<Note>();
    public ArrayList<Note> notesOther = new ArrayList<Note>();
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        NoteDao noteDao1 = new NoteDaoLmp(getApplicationContext());
        notes=noteDao1.FindByNote();

        NoteDao noteDao2 = new NoteDaoLmp(getApplicationContext());
        notesStu=noteDao2.FindByNoteType("学习");

        NoteDao noteDao3 = new NoteDaoLmp(getApplicationContext());
        notesLife=noteDao3.FindByNoteType("生活");

        NoteDao noteDao4 = new NoteDaoLmp(getApplicationContext());
        notesOther=noteDao4.FindByNoteType("其他");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        type= (Integer)getIntent().getExtras().get("type");
        noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        switch(type){
            case 0://全部
                NoteTitle = notes.get(noteId).getTitle();
                NoteText = notes.get(noteId).getText();
                NoteImage = notes.get(noteId).getUri();
                break;
            case 1://学习
                NoteTitle = notesStu.get(noteId).getTitle();
                NoteText = notesStu.get(noteId).getText();
                NoteImage = notesStu.get(noteId).getUri();
                break;
            case 2://生活
                NoteTitle = notesLife.get(noteId).getTitle();
                NoteText = notesLife.get(noteId).getText();
                NoteImage = notesLife.get(noteId).getUri();
                break;
            case 3://其他
                NoteTitle = notesOther.get(noteId).getTitle();
                NoteText = notesOther.get(noteId).getText();
                NoteImage =notesOther.get(noteId).getUri();
                break;
        }

        TextView textView = (TextView)findViewById(R.id.read_title);
        textView.setText(NoteTitle);
        TextView textView2 = (TextView)findViewById(R.id.read_text);
        textView2.setText(NoteText);
        ImageView imageView = findViewById(R.id.images);
        if(NoteImage!=null){
            File file = new File(NoteImage);
            if(file.exists()){
                Bitmap bm = BitmapFactory.decodeFile(NoteImage);
                imageView.setImageBitmap(bm);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        int noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        getMenuInflater().inflate(R.menu.menu_read, menu);
        MenuItem menuItem = menu.findItem(R.id.read_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent(NoteText);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("ShowToast")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        switch (item.getItemId()){//得到动作的ID
            case R.id.dele:
                switch(type){
                    case 0:
                        String title01 = null,text01=null,type01=null,uri01=null;
                        if(notes.get(noteId).getType().equals("学习")){
                            for(int i = 0; i < notesStu.size(); i++){
                                if(notesStu.get(i).getTitle().equals(notes.get(noteId).getTitle())
                                        && notesStu.get(i).getText().equals(notes.get(noteId).getText())
                                        && notesStu.get(i).getType().equals(notes.get(noteId).getType())){
                                    title01 = notes.get(noteId).getTitle();
                                    text01 = notes.get(noteId).getText();
                                    type01 = notes.get(noteId).getType();
                                    uri01 = notes.get(noteId).getUri();
                                    Log.e("ssssss",title01+"  "+text01+"  "+type01+"uri:   "+uri01);
                                    //noteDao2.remove(notesLife.get(i).getTitle(),notesLife.get(i).getType());
                                    break;
                                }
                            }
                            NoteDao noteDao01 = new NoteDaoLmp(getApplicationContext());
                            if(uri01==null){
                                noteDao01.remove(title01,text01,type01);
                            }else{
                                noteDao01.remove(title01,text01,type01,uri01);
                            }
                            break;
                        }
                        else if(notes.get(noteId).getType().equals("生活")){
                            for(int i = 0; i < notesLife.size(); i++){
                                if(notesLife.get(i).getTitle().equals(notes.get(noteId).getTitle())
                                        && notesLife.get(i).getText().equals(notes.get(noteId).getText())
                                        && notesLife.get(i).getType().equals(notes.get(noteId).getType())){
                                    title01 = notes.get(noteId).getTitle();
                                    text01 = notes.get(noteId).getText();
                                    type01 = notes.get(noteId).getType();
                                    uri01 = notes.get(noteId).getUri();
                                    Log.e("ssssss",title01+"  "+text01+"  "+type01+"uri:   "+uri01);
                                    //noteDao2.remove(notesLife.get(i).getTitle(),notesLife.get(i).getType());
                                    break;
                                }
                            }
                            NoteDao noteDao02 = new NoteDaoLmp(getApplicationContext());
                            if(uri01==null){
                                noteDao02.remove(title01,text01,type01);
                            }else{
                                noteDao02.remove(title01,text01,type01,uri01);
                            }
                        }
                        else if(notes.get(noteId).getType().equals("其他")){//主界面的其他
                            for(int i = 0; i < notesOther.size(); i++){
                                if(notesOther.get(i).getTitle().equals(notes.get(noteId).getTitle())
                                        && notesOther.get(i).getText().equals(notes.get(noteId).getText())
                                        && notesOther.get(i).getType().equals(notes.get(noteId).getType())){
                                    title01 = notes.get(noteId).getTitle();
                                    text01 = notes.get(noteId).getText();
                                    type01 = notes.get(noteId).getType();
                                    uri01 = notes.get(noteId).getUri();
                                    Log.e("ssssss",title01+"  "+text01+"  "+type01+"uri:   "+uri01);
                                    NoteDao noteDao3 = new NoteDaoLmp(getApplicationContext());
                                   // noteDao3.remove(notesOther.get(i).getTitle(),notesOther.get(i).getText(),notesOther.get(i).getType(),notesOther.get(i).getUri());
                                    break;
                                }
                            }
                            NoteDao noteDao03 = new NoteDaoLmp(getApplicationContext());
                            if(uri01==null){
                                noteDao03.remove(title01,text01,type01);
                            }else{
                                noteDao03.remove(title01,text01,type01,uri01);
                            }
                        }
                        break;
                    case 1:
                        String title1 = null,text1=null,type1=null,uri1=null;
                       for(int i = 0; i < notes.size(); i++){
                            if(notes.get(i).getTitle().equals(notesStu.get(noteId).getTitle())
                                    && notes.get(i).getText().equals(notesStu.get(noteId).getText())
                                    && notes.get(i).getType().equals(notesStu.get(noteId).getType())){
                                title1 = notesStu.get(noteId).getTitle();
                                text1 = notesStu.get(noteId).getText();
                                type1 = notesStu.get(noteId).getType();
                                uri1 = notesStu.get(noteId).getUri();
                                break;
                            }
                       }
                        NoteDao noteDao1 = new NoteDaoLmp(getApplicationContext());
                        if(uri1==null){
                            noteDao1.remove(title1,text1,type1);
                       }else{
                            noteDao1.remove(title1,text1,type1,uri1);
                       }
                        break;
                    case 2:
                        String title2 = null,text2=null,type2=null,uri2=null;
                        for(int i = 0; i < notes.size(); i++){
                        if(notes.get(i).getTitle().equals(notesLife.get(noteId).getTitle())
                                && notes.get(i).getText().equals(notesLife.get(noteId).getText())
                                && notes.get(i).getType().equals(notesLife.get(noteId).getType())){
                            title2 = notesLife.get(noteId).getTitle();
                            text2 = notesLife.get(noteId).getText();
                            type2 = notesLife.get(noteId).getType();
                            uri2 = notesLife.get(noteId).getUri();
                            break;
                        }
                    }
                        NoteDao noteDao2 = new NoteDaoLmp(getApplicationContext());
                        if(uri2==null){
                            noteDao2.remove(title2,text2,type2);
                        }else{
                            noteDao2.remove(title2,text2,type2,uri2);
                        }
                      break;
                    case 3:
                        String title3 = null,text3=null,type3=null,uri3=null;
                        for(int i = 0; i < notes.size(); i++){
                            if(notes.get(i).getTitle().equals(notesOther.get(noteId).getTitle())
                                    && notes.get(i).getText().equals(notesOther.get(noteId).getText())
                                    && notes.get(i).getType().equals(notesOther.get(noteId).getType())){
                                title3 = notesOther.get(noteId).getTitle();
                                text3 = notesOther.get(noteId).getText();
                                type3 = notesOther.get(noteId).getType();
                                uri3 = notesOther.get(noteId).getUri();
                                Log.e("ssssss",title3+"  "+text3+"  "+type3+"uri:   "+uri3);
                                break;
                            }
                        }
                        NoteDao noteDao3 = new NoteDaoLmp(getApplicationContext());
                        if(uri3==null) {
                            Log.e("ssssss", title3 + "  " + text3 + "  " + type3);
                            noteDao3.remove(title3, text3, type3);
                        }else{
                            noteDao3.remove(title3, text3, type3, uri3);
                        }
                        break;
                }
                return true;//返回true表示已经处理了所单击的动作源
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NoteDao noteDao1 = new NoteDaoLmp(getApplicationContext());
        notes=noteDao1.FindByNote();

        NoteDao noteDao2 = new NoteDaoLmp(getApplicationContext());
        notesStu=noteDao2.FindByNoteType("学习");

        NoteDao noteDao3 = new NoteDaoLmp(getApplicationContext());
        notesLife=noteDao3.FindByNoteType("生活");

        NoteDao noteDao4 = new NoteDaoLmp(getApplicationContext());
        notesOther=noteDao4.FindByNoteType("其他");
    }
}
