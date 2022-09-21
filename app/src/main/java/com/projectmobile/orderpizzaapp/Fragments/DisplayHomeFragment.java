package com.projectmobile.orderpizzaapp.Fragments;

import static com.projectmobile.orderpizzaapp.R.id.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.projectmobile.orderpizzaapp.Activities.AddCategoryActivity;
import com.projectmobile.orderpizzaapp.Activities.HomeActivity;
import com.projectmobile.orderpizzaapp.DAO.DonDatDAO;
import com.projectmobile.orderpizzaapp.DAO.LoaiMonDAO;
import com.projectmobile.orderpizzaapp.R;

public class DisplayHomeFragment extends Fragment implements View.OnClickListener {

    RelativeLayout layout_displayhome_XemMenu;
    LoaiMonDAO loaiMonDAO;
    DonDatDAO donDatDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.displayhome_layout,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Trang chủ");
        setHasOptionsMenu(true);

        //region Lấy dối tượng view
        layout_displayhome_XemMenu = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemMenu);
        //endregion

        //khởi tạo kết nối
        loaiMonDAO = new LoaiMonDAO(getActivity());
        donDatDAO = new DonDatDAO(getActivity());

        layout_displayhome_XemMenu.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        NavigationView navigationView = (NavigationView)getActivity().findViewById(navigation_view_trangchu);
        switch (id){
            case R.id.layout_displayhome_XemMenu:
                Intent iAddCategory = new Intent(getActivity(), AddCategoryActivity.class);
                startActivity(iAddCategory);
//                navigationView.setCheckedItem(nav_category);
                break;
        }
    }
}

