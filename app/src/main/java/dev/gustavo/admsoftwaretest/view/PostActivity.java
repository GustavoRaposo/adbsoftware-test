package dev.gustavo.admsoftwaretest.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import dev.gustavo.admsoftwaretest.R;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.viewmodel.PostViewModel;

public class PostActivity extends AppCompatActivity {

    private PostViewModel viewModel;
    private TextView userId;
    private TextView title;
    private TextView body;
    private Button edit;
    private Button delete;
    private Post post;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = getIntent().getIntExtra("id", 1);
        Log.i("POST", "Post ID: " + id);

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        userId = findViewById(R.id.postActivityUserId);
        title = findViewById(R.id.postActivityTitle);
        body = findViewById(R.id.postActivityBody);
        delete = findViewById(R.id.postActivityDelete);
        edit = findViewById(R.id.postActivityEdit);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deletePost(post);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                intent.putExtra("userId", post.getUserId());
                intent.putExtra("id", post.getId());
                intent.putExtra("title", post.getTitle());
                intent.putExtra("body", post.getBody());
                startActivity(intent);
            }
        });

        viewModel.post.observe(this, post ->{
            this.post = post;
            userId.setText("User: " + post.getUserId());
            title.setText(post.getTitle());
            body.setText(post.getBody());
        });

        viewModel.delete.observe(this, delete ->{
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getPost(id);
    }
}