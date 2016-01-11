package br.com.ibarra.moclimao.ui.holders;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.ibarra.moclimao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 09/01/16.
 */

public class WeatherHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
    @Nullable
    @Bind(R.id.day_temperature) TextView textViewDayTemperature;
    @Nullable
    @Bind(R.id.max_temperature) TextView textViewMaxTemperature;
    @Nullable
    @Bind(R.id.min_temperature) TextView textViewMinTemperature;
    @Nullable
    @Bind(R.id.temperature) TextView textViewTemperature;
    @Nullable
    @Bind(R.id.unit) TextView textViewUnit;
    @Nullable
    @Bind(R.id.humidity) TextView textViewHumidity;
    @Nullable
    @Bind(R.id.description) TextView textViewDescription;

    public WeatherHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
       /* Intent intent = new Intent(view.getContext(), WeatherDetailActivity.class);
        intent.putExtra("URL", view.getTag(R.string.product_url).toString());
        view.getContext().startActivity(intent);*/
       /* EventBus.getDefault().post(new BookSelectedEvent(
                (Long)view.getTag(R.string.tag_book_id),
                (String)view.getTag(R.string.tag_book_title)));*/
    }

    public TextView getTextViewDayTemperature() {
        return textViewDayTemperature;
    }

    public TextView getTextViewMaxTemperature() {
        return textViewMaxTemperature;
    }

    public TextView getTextViewMinTemperature() {
        return textViewMinTemperature;
    }

    public TextView getTextViewTemperature() {
        return textViewTemperature;
    }

    public TextView getTextViewUnit() {
        return textViewUnit;
    }

    public TextView getTextViewHumidity() {
        return textViewHumidity;
    }

    public TextView getTextViewDescription() {
        return textViewDescription;
    }
}
