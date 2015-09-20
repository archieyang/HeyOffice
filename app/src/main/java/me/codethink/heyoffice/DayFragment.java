package me.codethink.heyoffice;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by archie on 15/8/31.
 */
public class DayFragment extends Fragment {
    @Bind(R.id.alarm_list)
    RecyclerView mAlarmList;

    @OnClick(R.id.set_time)
    public void setTimeClicked() {
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
                AlarmManager.get().addAlarm(hour, minute);
                alarms.clear();
                alarms.addAll(AlarmManager.get().getAlarmTime());
                mAdapter.notifyDataSetChanged();
            }
        }, 3, 5, false);
        timePickerDialog.show(getFragmentManager(), "Show TimePickerDialog");
    }

    RecyclerView.Adapter mAdapter;
    List<Alarm> alarms = new ArrayList<Alarm>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ButterKnife.bind(this, view);

        mAlarmList.setLayoutManager(new LinearLayoutManager(getActivity()));
        alarms.addAll(AlarmManager.get().getAlarmTime());

        mAdapter = new RecyclerView.Adapter(){
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new AlarmItemViewHolder(LayoutInflater.from(DayFragment.this.getActivity()).inflate(R.layout.view_item, viewGroup, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                ((AlarmItemViewHolder) viewHolder).simpleText.setText(alarms.get(i).getFormattedAlarmTime());
            }

            @Override
            public int getItemCount() {
                return alarms.size();
            }

            final class AlarmItemViewHolder extends RecyclerView.ViewHolder {
                TextView simpleText;

                public AlarmItemViewHolder(View itemView) {
                    super(itemView);
                    simpleText = (TextView) itemView.findViewById(R.id.item_text);
                }
            }

        };
        mAlarmList.setAdapter(mAdapter);

        return view;

    }


}
