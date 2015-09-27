package me.codethink.heyoffice.ui;


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
import me.codethink.heyoffice.Alarm;
import me.codethink.heyoffice.AlarmStore;
import me.codethink.heyoffice.R;
import me.codethink.heyoffice.utils.ui.ItemClickSupport;
import rx.Subscription;

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
                AlarmStore.get().addAlarm(hour, minute);

            }
        }, 3, 5, false);
        timePickerDialog.show(getFragmentManager(), "Show TimePickerDialog");
    }

    RecyclerView.Adapter mAdapter;
    List<Alarm> mAlarms = new ArrayList<Alarm>();

    Subscription mSubscription = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ButterKnife.bind(this, view);

        mAlarmList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AlarmAdapter();
        mAlarmList.setAdapter(mAdapter);

        ItemClickSupport.addTo(mAlarmList).setOnItemClickListener((recyclerView, position, v) -> {
            if (position >= 0) {
                mAlarms.get(position).delete();
                mAlarms.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        mSubscription = AlarmStore.get().getListObservable().subscribe(alarms -> {
                mAlarms.clear();
                mAlarms.addAll(alarms);
                mAdapter.notifyDataSetChanged();
        });

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    private class AlarmAdapter extends RecyclerView.Adapter<AlarmItemViewHolder> {

        @Override
        public AlarmItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AlarmItemViewHolder(LayoutInflater.from(DayFragment.this.getActivity()).inflate(R.layout.view_item, parent, false));
        }

        @Override
        public void onBindViewHolder(AlarmItemViewHolder holder, int position) {
            holder.bindData(mAlarms.get(position));
        }

        @Override
        public int getItemCount() {
            return mAlarms.size();
        }
    }

    class AlarmItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_text)
        TextView simpleText;

        public AlarmItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Alarm alarm) {
            simpleText.setText(alarm.getFormattedAlarmTime());
        }
    }
}
