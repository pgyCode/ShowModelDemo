package com.danale.shixisheng2018.shower.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.danale.shixisheng2018.shower.R;
import com.danale.shixisheng2018.shower.model.AllModel;
import com.danale.shixisheng2018.shower.parent.OnModelChangeListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AllFragment extends Fragment {


    private View root;

    private ListView lst;
    private BaseAdapter adapter;

    private FlushOnModelChangeListener flushOnModelChangeListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.all, null, false);
        initLayout();

        flushOnModelChangeListener = new FlushOnModelChangeListener();
        AllModel.getInstance().listeners.add(flushOnModelChangeListener);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AllModel.getInstance().listeners.remove(flushOnModelChangeListener);
    }
    private class FlushOnModelChangeListener implements OnModelChangeListener{

        @Override
        public void onChange() {
            adapter.notifyDataSetChanged();
        }
    }

    private void initLayout(){
        lst = root.findViewById(R.id.lst);
        lst.setAdapter(adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (AllModel.getInstance().recordBeans == null){
                    return 0;
                } else {
                    return AllModel.getInstance().recordBeans.size();
                }
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(AllFragment.this.getContext()).inflate(R.layout.item, lst, false);
                TextView txtName = view.findViewById(R.id.txt_name);
                txtName.setText(AllModel.getInstance().recordBeans.get(i).name);
                TextView txtStart = view.findViewById(R.id.txt_start);
                txtStart.setText(AllModel.getInstance().recordBeans.get(i).startTime);
                TextView txtEnd = view.findViewById(R.id.txt_end);
                txtEnd.setText(AllModel.getInstance().recordBeans.get(i).endTime);
                return view;
            }
        });
    }
}
