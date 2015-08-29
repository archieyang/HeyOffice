package me.codethink.heyoffice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.alarm_list)
    RecyclerView mAlarmList;

    RecyclerView.Adapter mAdapter;


    @OnClick(R.id.set_time)
    public void setTimeClicked() {
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {

            }
        }, 3, 5, false);
        timePickerDialog.show(getFragmentManager(), "Time Picker Dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAlarmList.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new AlarmItemViewHolder(LayoutInflater.from(getBaseContext()).inflate(R.layout.view_item, viewGroup, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                ((AlarmItemViewHolder) viewHolder).simpleText.setText("Dummy Text " + i);
            }

            @Override
            public int getItemCount() {
                return 10;
            }


        };

        mAlarmList.setAdapter(mAdapter);

        AlarmCenter.startUp(this);
        AlarmCenter.get().setAlarm(System.currentTimeMillis(), System.currentTimeMillis() + 30000, 6000);


    }

    private final class AlarmItemViewHolder extends RecyclerView.ViewHolder {
        private TextView simpleText;

        public AlarmItemViewHolder(View itemView) {
            super(itemView);
            simpleText = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
