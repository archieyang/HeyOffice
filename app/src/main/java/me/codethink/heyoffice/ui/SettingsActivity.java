package me.codethink.heyoffice.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.codethink.heyoffice.R;

/**
 * Created by archie on 15/9/26.
 */
public class SettingsActivity extends AppCompatActivity{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.settings_list)
    RecyclerView mSettingsList;

    String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> SettingsActivity.this.finish());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.settings);


        // TODO: 15/9/27 Refactor to SettingManager
        Resources res = getResources();
        titles = res.getStringArray(R.array.setting_titles);

        mSettingsList.setLayoutManager(new LinearLayoutManager(this));

        mSettingsList.setAdapter(new SettingsAdapter());
    }


    private class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

        @Override
        public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SettingsViewHolder(getLayoutInflater().inflate(R.layout.view_settings_item, parent, false));
        }

        @Override
        public void onBindViewHolder(SettingsViewHolder holder, int position) {
            holder.nameText.setText(titles[position]);
            holder.valueText.setText("未知");
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

    }

    class SettingsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.settings_name)
        TextView nameText;

        @Bind(R.id.settings_value)
        TextView valueText;

        public SettingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
