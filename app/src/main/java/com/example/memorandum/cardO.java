package com.example.memorandum;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class cardO extends RecyclerView.Adapter<cardO.ViewHolder> {

    private String[] title;private cardJ.Listener listener;

    interface Listener{
        void onClick(int postion);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public cardO(String[] title) {
        this.title = title;
    }

    public int getItemCount() {
        return title.length;
    }

    public void setListener(cardJ.Listener listener) {
        this.listener = listener;
    }

    public cardO.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_other, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        CardView cardView = holder.cardView;
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(title[position]);
        cardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }
}
