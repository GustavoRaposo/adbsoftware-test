package dev.gustavo.admsoftwaretest.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dev.gustavo.admsoftwaretest.R;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.view.adapter.PostAdapter;
import dev.gustavo.admsoftwaretest.view.adapter.PostRecyclerViewClickListener;
import dev.gustavo.admsoftwaretest.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements PostRecyclerViewClickListener {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PostAdapter adapter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.posts.observe(this, posts -> {
            postList = posts;
            adapter = new PostAdapter(getApplicationContext(), posts, this);
            recyclerView.setAdapter(adapter);
        });

        recyclerView = findViewById(R.id.recyclerViewPosts);
        adapter = new PostAdapter(getApplicationContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.mainActivityNewPost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getPostList();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("id", postList.get(position).getId());
        startActivity(intent);
    }
}