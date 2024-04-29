package dev.gustavo.admsoftwaretest.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import dev.gustavo.admsoftwaretest.R;
import dev.gustavo.admsoftwaretest.viewmodel.NewPostViewModel;

public class NewPostActivity extends AppCompatActivity {

    private NewPostViewModel viewModel;

    private EditText userId;
    private EditText title;
    private EditText body;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(NewPostViewModel.class);

        viewModel.save.observe(this, it -> {
            finish();
        });

        userId = findViewById(R.id.newPostUserId);
        title = findViewById(R.id.newPostTitle);
        body = findViewById(R.id.newPostBody);
        save = findViewById(R.id.newPostSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId.getText().toString().isEmpty()){
                    finish();
                    return;
                }

                viewModel.save(
                        Integer.parseInt(userId.getText().toString().trim()),
                        title.getText().toString().trim(),
                        body.getText().toString().trim()
                );
            }
        });

    }
}