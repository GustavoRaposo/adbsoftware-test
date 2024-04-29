package dev.gustavo.admsoftwaretest.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.gustavo.admsoftwaretest.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView userId;
    public TextView title;

    public PostViewHolder(@NonNull View itemView, PostRecyclerViewClickListener onClickListener) {
        super(itemView);
        userId = itemView.findViewById(R.id.postActivityUserId);
        title = itemView.findViewById(R.id.postTitle);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(pos);
                    }
                }
            }
        });
    }
}
