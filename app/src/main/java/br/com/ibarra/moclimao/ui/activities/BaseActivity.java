package br.com.ibarra.moclimao.ui.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by joaoibarra on 12/23/15.
 */
public interface BaseActivity {
    public void onLoadProgress();
    public void onFinishProgress();
    public void onFinishError();
    public void hideError();
    public void showContent();
    public void hideContent();
}
