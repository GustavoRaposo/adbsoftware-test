package dev.gustavo.admsoftwaretest.view;

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

        int id = getIntent().getIntExtra("id", 1);
        Log.i("POST", "Post ID: " + id);

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        userId = findViewById(R.id.postActivityUserId);
        title = findViewById(R.id.postActivityTitle);
        body = findViewById(R.id.postActivityBody);
        delete = findViewById(R.id.postActivityDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deletePost(post);
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

        viewModel.getPost(id);
    }
}