package com.projectmobile.orderpizzaapp.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projectmobile.orderpizzaapp.DAO.BanAnDAO;
import com.projectmobile.orderpizzaapp.DAO.NhanVienDAO;
import com.projectmobile.orderpizzaapp.Model.DonDatModel;
import com.projectmobile.orderpizzaapp.Model.NhanVienModel;
import com.projectmobile.orderpizzaapp.R;

import java.util.List;

public class AdapterDisplayStatistic extends BaseAdapter {

    Context context;
    int layout;
    List<DonDatModel> donDatModels;
    ViewHolder viewHolder;
    NhanVienDAO nhanVienDAO;
    BanAnDAO banAnDAO;

    public AdapterDisplayStatistic(Context context, int layout, List<DonDatModel> donDatModels){
        this.context = context;
        this.layout = layout;
        this.donDatModels = donDatModels;
        nhanVienDAO = new NhanVienDAO(context);
        banAnDAO = new BanAnDAO(context);
    }

    @Override
    public int getCount() {
        return donDatModels.size();
    }

    @Override
    public Object getItem(int position) {
        return donDatModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return donDatModels.get(position).getMaDonDat();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.txt_customstatistic_MaDon = (TextView)view.findViewById(R.id.txt_customstatistic_MaDon);
            viewHolder.txt_customstatistic_NgayDat = (TextView)view.findViewById(R.id.txt_customstatistic_NgayDat);
            viewHolder.txt_customstatistic_TenNV = (TextView)view.findViewById(R.id.txt_customstatistic_TenNV);
            viewHolder.txt_customstatistic_TongTien = (TextView)view.findViewById(R.id.txt_customstatistic_TongTien);
            viewHolder.txt_customstatistic_TrangThai = (TextView)view.findViewById(R.id.txt_customstatistic_TrangThai);
            viewHolder.txt_customstatistic_BanDat = (TextView)view.findViewById(R.id.txt_customstatistic_BanDat);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DonDatModel donDatModel = donDatModels.get(position);

        viewHolder.txt_customstatistic_MaDon.setText("Mã đơn: "+ donDatModel.getMaDonDat());
        viewHolder.txt_customstatistic_NgayDat.setText(donDatModel.getNgayDat());
        viewHolder.txt_customstatistic_TongTien.setText(donDatModel.getTongTien()+" VNĐ");
        if (donDatModel.getTinhTrang().equals("true"))
        {
            viewHolder.txt_customstatistic_TrangThai.setText("Đã thanh toán");
        }else {
            viewHolder.txt_customstatistic_TrangThai.setText("Chưa thanh toán");
        }
        NhanVienModel nhanVienModel = nhanVienDAO.LayNVTheoMa(donDatModel.getMaNV());
        viewHolder.txt_customstatistic_TenNV.setText(nhanVienModel.getHOTENNV());
        viewHolder.txt_customstatistic_BanDat.setText(banAnDAO.LayTenBanTheoMa(donDatModel.getMaBan()));

        return view;
    }
    public class ViewHolder{
        TextView txt_customstatistic_MaDon, txt_customstatistic_NgayDat, txt_customstatistic_TenNV
                ,txt_customstatistic_TongTien,txt_customstatistic_TrangThai, txt_customstatistic_BanDat;

    }
}
