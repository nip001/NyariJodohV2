package com.batch9.nyarijodohapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.batch9.nyarijodohapps.R;
import com.batch9.nyarijodohapps.entity.UserEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class DeckAdapter extends BaseAdapter {

    List<UserEntity> calon;
    Context context;

    public DeckAdapter(List<UserEntity> calon, Context context) {
        this.calon = calon;
        this.context = context;
    }

    @Override
    public int getCount() {
        return calon.size();
    }

    @Override
    public Object getItem(int i) {
        return calon.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.idNameCalon)).setText(calon.get(i).getName());
        ((TextView) view.findViewById(R.id.idUmur)).setText(calon.get(i).getUmur());
        Glide.with(context).load("http://ffa5-139-193-195-43.ngrok.io/image/"+calon.get(i).getImage()).into(((ImageView) view.findViewById(R.id.idGambarCalon)));
        return view;
    }
}
