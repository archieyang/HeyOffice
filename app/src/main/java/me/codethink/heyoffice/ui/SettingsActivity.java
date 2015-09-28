package me.codethink.heyoffice.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.codethink.heyoffice.R;
import me.codethink.heyoffice.SettingItem;
import me.codethink.heyoffice.SettingStore;

/**
 * Created by archie on 15/9/26.
 */
public class SettingsActivity extends AppCompatActivity{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.settings_list)
    RecyclerView mSettingsList;

    private List<SettingItem> mSettings = new ArrayList<SettingItem>();
    private RecyclerView.Adapter<SettingsViewHolder> mAdapter = new SettingsAdapter();

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

        mSettingsList.setLayoutManager(new LinearLayoutManager(this));

        mSettingsList.setAdapter(mAdapter);

        SettingStore.get().getSettingListObservable().subscribe(settings -> {
            mSettings.clear();
            mSettings.addAll(settings);
            mAdapter.notifyDataSetChanged();
        });
    }


    private class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

        @Override
        public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SettingsViewHolder(getLayoutInflater().inflate(R.layout.view_settings_item, parent, false));
        }

        @Override
        public void onBindViewHolder(SettingsViewHolder holder, int position) {
            holder.bindData(mSettings.get(position));

        }

        @Override
        public int getItemCount() {
            return mSettings.size();
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

        public void bindData(SettingItem settingItem)  {
            nameText.setText(settingItem.getName());
            valueText.setText(settingItem.getValue());
        }
    }


}
