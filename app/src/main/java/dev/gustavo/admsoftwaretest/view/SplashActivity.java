package dev.gustavo.admsoftwaretest.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import dev.gustavo.admsoftwaretest.R;
import dev.gustavo.admsoftwaretest.view.adapter.PostAdapter;
import dev.gustavo.admsoftwaretest.viewmodel.MainViewModel;
import dev.gustavo.admsoftwaretest.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        viewModel.posts.observe(this, posts -> {
            viewModel.savePostList(posts);
        });

        viewModel.saving.observe(this, saving ->{
            startActivity(new Intent(this, MainActivity.class));
        });

        viewModel.getPostList();
    }
}