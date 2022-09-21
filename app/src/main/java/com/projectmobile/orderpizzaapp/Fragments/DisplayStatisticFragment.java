package com.projectmobile.orderpizzaapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.projectmobile.orderpizzaapp.Activities.DetailStatisticActivity;
import com.projectmobile.orderpizzaapp.Activities.HomeActivity;
import com.projectmobile.orderpizzaapp.CustomAdapter.AdapterDisplayStatistic;
import com.projectmobile.orderpizzaapp.DAO.DonDatDAO;
import com.projectmobile.orderpizzaapp.Model.DonDatModel;
import com.projectmobile.orderpizzaapp.R;

import java.util.List;

public class DisplayStatisticFragment extends Fragment {

    ListView lvStatistic;
    List<DonDatModel> donDatModels;
    DonDatDAO donDatDAO;
    AdapterDisplayStatistic adapterDisplayStatistic;
    FragmentManager fragmentManager;
    int madon, manv, maban;
    String ngaydat, tongtien;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.displaystatistic_layout,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Quản lý thống kê");
        setHasOptionsMenu(true);

        lvStatistic = (ListView)view.findViewById(R.id.lvStatistic);
        donDatDAO = new DonDatDAO(getActivity());

        donDatModels = donDatDAO.LayDSDonDat();
        adapterDisplayStatistic = new AdapterDisplayStatistic(getActivity(),R.layout.custom_layout_displaystatistic, donDatModels);
        lvStatistic.setAdapter(adapterDisplayStatistic);
        adapterDisplayStatistic.notifyDataSetChanged();

        lvStatistic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                madon = donDatModels.get(position).getMaDonDat();
                manv = donDatModels.get(position).getMaNV();
                maban = donDatModels.get(position).getMaBan();
                ngaydat = donDatModels.get(position).getNgayDat();
                tongtien = donDatModels.get(position).getTongTien();

                Intent intent = new Intent(getActivity(), DetailStatisticActivity.class);
                intent.putExtra("madon",madon);
                intent.putExtra("manv",manv);
                intent.putExtra("maban",maban);
                intent.putExtra("ngaydat",ngaydat);
                intent.putExtra("tongtien",tongtien);
                startActivity(intent);
            }
        });

        return view;
    }
}
