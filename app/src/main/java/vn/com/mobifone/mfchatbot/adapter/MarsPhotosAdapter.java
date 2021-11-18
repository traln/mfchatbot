package vn.com.mobifone.mfchatbot.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.mobifone.mfchatbot.R;
import vn.com.mobifone.mfchatbot.model.MarsPhotos;
import vn.com.mobifone.mfchatbot.view.RecyclerItemClickListener;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class MarsPhotosAdapter extends RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotosViewHolder>{

    private ArrayList<MarsPhotos> marsPhotosArrayList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public MarsPhotosAdapter(ArrayList<MarsPhotos> marsPhotosArrayList, RecyclerItemClickListener recyclerItemClickListener ){
        this.marsPhotosArrayList = marsPhotosArrayList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public MarsPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_mars_photo, parent, false);
        return new MarsPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MarsPhotosAdapter.MarsPhotosViewHolder holder, int position) {

        if(position % 2 == 1){
            holder.itemView.setBackgroundColor(Color.parseColor("#FFEDD3"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.textViewEarthDate.setText(marsPhotosArrayList.get(position).getEarthDate());
        holder.textViewCameraFullName.setText(marsPhotosArrayList.get(position).getCamera());
        final String marsPhotoURL = marsPhotosArrayList.get(position).getImgSrc();
        Picasso.get()
                .load(marsPhotoURL)
                .into(holder.imageViewMarsPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemClickListener.onItemClick(marsPhotoURL);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(marsPhotosArrayList != null)
            return marsPhotosArrayList.size();
        else
            return 0;
    }

    class MarsPhotosViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewEarthDate, textViewCameraFullName;
        ImageView imageViewMarsPhoto;

        MarsPhotosViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.cardView);
            textViewEarthDate = view.findViewById(R.id.textViewEarthDate);
            textViewCameraFullName = view.findViewById(R.id.textViewCameraFullName);
            imageViewMarsPhoto = view.findViewById(R.id.imageViewMarsPhoto);
        }
    }

}
