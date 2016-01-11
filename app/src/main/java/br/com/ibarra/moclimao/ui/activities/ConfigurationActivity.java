package br.com.ibarra.moclimao.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ibarra.moclimao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class ConfigurationActivity extends AppCompatActivity implements BaseActivity{
    @Bind(R.id.progressbar) LinearLayout progressbarLayout;
    @Bind(R.id.error) RelativeLayout errorLayout;
    @Bind(R.id.content) NestedScrollView contentLayout;
    @Bind(R.id.city) EditText editTextCity;
    @Bind(R.id.save) FloatingActionButton fabSave;
    @Bind(R.id.unit) RadioGroup radioGroupUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupUnit.getCheckedRadioButtonId();
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.city_key), editTextCity.getText().toString());
                editor.putInt(getString(R.string.unit_key), selectedId);
                editor.commit();
                Toast.makeText(ConfigurationActivity.this, getString(R.string.sucess_message), Toast.LENGTH_LONG);
            }
        });

        setLayoutValues();
    }

    private void setLayoutValues(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultCity = getResources().getString(R.string.default_city);

        editTextCity.setText(sharedPref.getString(getString(R.string.city_key), defaultCity));
        radioGroupUnit.check(sharedPref.getInt(getString(R.string.unit_key), R.id.metric_unit));
    }

    @Override
    public void onLoadProgress() {
        hideError();
        hideContent();
        progressbarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishProgress() {
        progressbarLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFinishError() {
        hideContent();
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideContent() {
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        contentLayout.setVisibility(View.VISIBLE);
    }
}
