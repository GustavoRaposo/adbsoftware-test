package dev.gustavo.admsoftwaretest.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.gustavo.admsoftwaretest.R;

public class EditPostActivity extends AppCompatActivity {

    private EditText etitle;
    private EditText ebody;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int userId = getIntent().getIntExtra("userId", 1);
        int id = getIntent().getIntExtra("id", 1);
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");

        etitle = findViewById(R.id.editTitle);
        ebody = findViewById(R.id.editBody);
        edit = findViewById(R.id.edit);

        etitle.setText(title);
        ebody.setText(body);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}