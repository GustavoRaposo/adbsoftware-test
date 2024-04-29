package dev.gustavo.admsoftwaretest;

import android.app.Application;

import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PostDatabase.getInstance(this);
    }
}
