package br.com.ibarra.moclimao.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import br.com.ibarra.moclimao.HomeActivity;
import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.Configuration;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class ConfigurationActivity extends AppCompatActivity implements BaseActivity {
    @BindView(R.id.progressbar)
    LinearLayout progressbarLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.content)
    NestedScrollView contentLayout;
    @BindView(R.id.city)
    EditText editTextCity;
    @BindView(R.id.save)
    FloatingActionButton fabSave;
    @BindView(R.id.unit)
    RadioGroup radioGroupUnit;

    Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);
        configuration = new Configuration(ConfigurationActivity.this);
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
                configuration.setCity(editTextCity.getText().toString());
                configuration.setUnit(selectedId);
                Toast.makeText(ConfigurationActivity.this, getString(R.string.sucess_message), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ConfigurationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        setLayoutValues();
    }

    private void setLayoutValues() {
        editTextCity.setText(configuration.getCity());
        radioGroupUnit.check(configuration.getUnit());
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
