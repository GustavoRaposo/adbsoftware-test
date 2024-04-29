package dev.gustavo.admsoftwaretest.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.gustavo.admsoftwaretest.R;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private PostRecyclerViewClickListener onClickListener;

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts, PostRecyclerViewClickListener onClickListener) {
        this.context = context;
        this.posts = posts;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.userId.setText("User: " + post.getUserId());
        holder.title.setText(post.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
