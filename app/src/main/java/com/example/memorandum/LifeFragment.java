package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memorandum.Dao.NoteDao;
import com.example.memorandum.Dao.NoteDaoLmp;

import java.util.ArrayList;

public class LifeFragment extends Fragment {

    public ArrayList<Note> notesLife = new ArrayList<Note>();
    public LifeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NoteDao noteDao3 = new NoteDaoLmp(getContext());
        notesLife=noteDao3.FindByNoteType("生活");
        RecyclerView notesRecycle = (RecyclerView) inflater.inflate(R.layout.fragment_life, container, false);
        String[] notesName = new String[notesLife.size()];
        for (int i = 0; i < notesName.length; i++)
            notesName[i] = notesLife.get(i).getTitle();
        cardL adapter = new cardL(notesName);
        notesRecycle.setAdapter(adapter);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        notesRecycle.setLayoutManager(layoutManager);
        adapter.setListener(new cardJ.Listener() {
            @Override
            public void onClick(int postion) {
                Intent intent = new Intent(getActivity(), read.class);
                intent.putExtra(read.EXTRA_NOTE_ID, postion);
                intent.putExtra("type", 2);
                getActivity().startActivity(intent);
            }
        });
        return notesRecycle;
    }

}
