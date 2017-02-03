package com.expressdist.dispatch.vaisakhi5k;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    ImageView imagen;
    Button btn_login;
    EditText email_txt;
    EditText pass_txt;

    View statusView;
    int uiOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        statusView= getWindow().getDecorView();
        uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        statusView.setSystemUiVisibility(uiOptions);
        getWindow().setStatusBarColor(Color.rgb(48,63,159));
        getWindow().setNavigationBarColor(Color.rgb(48,63,159));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);



        imagen=(ImageView)findViewById(R.id.img_logo);
        imagen.setBackgroundColor(Color.rgb(48,63,159));
        imagen.setImageResource(R.drawable.noors);

        email_txt = (EditText) findViewById(R.id.email);
        pass_txt = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login_btn);

        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login.setEnabled(false);
                userLogin(view);

            }
        });

        public void userLogin(View view) {

        String email = email_txt.getText().toString();
        String password = pass_txt.getText().toString();
        BackgroundTask background = new BackgroundTask(this);
        background.execute(email, password);
    }


    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}



