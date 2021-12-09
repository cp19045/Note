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

public class StuFragment extends Fragment {

    public ArrayList<Note> notesStu = new ArrayList<Note>();
    public StuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NoteDao noteDao2 = new NoteDaoLmp(getContext());
        notesStu=noteDao2.FindByNoteType("学习");
        RecyclerView notesRecycle = (RecyclerView) inflater.inflate(R.layout.fragment_stu, container, false);
        String[] notesName = new String[notesStu.size()];
        for (int i = 0; i < notesName.length; i++)
            notesName[i] = notesStu.get(i).getTitle();
        cardS adapter = new cardS(notesName);
        notesRecycle.setAdapter(adapter);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        notesRecycle.setLayoutManager(layoutManager);
        adapter.setListener(new cardJ.Listener() {
            @Override
            public void onClick(int postion) {
                Intent intent = new Intent(getActivity(), read.class);
                intent.putExtra(read.EXTRA_NOTE_ID, postion);
                intent.putExtra("type", 1);
                getActivity().startActivity(intent);
            }
        });
        return notesRecycle;
    }
}
