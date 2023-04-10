package com.michael.appbalum.mainModule.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.michael.appbalum.R;
import com.michael.appbalum.common.pojo.Vocabulary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private List<Vocabulary> vocabularys;
    private OnItemClickListener listener;
    private Context context;

    public VocabularyAdapter(List<Vocabulary> vocabularys, OnItemClickListener listener) {
        this.vocabularys = vocabularys;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palabras, parent, false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vocabulary vocabulary = vocabularys.get(position);

        holder.setOnClickListener(vocabulary, listener);

        holder.tvData.setText(context.getString(R.string.item_palabras_data, vocabulary.getSpanish(),
                vocabulary.getTranslate()));

                /*
                *
                *
                *  revisar codigo (context.getString(R.string.item_palabras_data, vocabulary.getSpanish(),
                vocabulary.getTranslate()));
                *
                *
                *
                * */

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(vocabulary.getPhotoUrl())
                .apply(options)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return vocabularys.size();
    }

    public void add(Vocabulary vocabulary){
        if(!vocabularys.contains(vocabulary)){
            vocabularys.add(vocabulary);
            notifyItemInserted(vocabularys.size()-1);
        } else {
            update(vocabulary);
        }
    }

    public void update(Vocabulary vocabulary) {
        if(vocabularys.contains(vocabulary)){
            final int index = vocabularys.indexOf(vocabulary);
            vocabularys.set(index, vocabulary);
            notifyItemChanged(index);
        }
    }

    public void remove(Vocabulary vocabulary){
        if(vocabularys.contains(vocabulary)){
            final int index = vocabularys.indexOf(vocabulary);
            vocabularys.remove(index);
            notifyItemRemoved(index);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgPhoto)
        AppCompatImageView imgPhoto;
        @BindView(R.id.tvData)
        AppCompatTextView tvData;

        private View view;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        void setOnClickListener(Vocabulary vocabulary, final OnItemClickListener listener){
            view.setOnClickListener((view) -> {
                listener.onItemClick(vocabulary);
        });

            view.setOnLongClickListener((view) -> {
                listener.onLongItemClick(vocabulary);
                return true;
            });
        }
    }

}
