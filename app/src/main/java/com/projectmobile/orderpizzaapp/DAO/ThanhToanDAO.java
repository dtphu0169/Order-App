package com.projectmobile.orderpizzaapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projectmobile.orderpizzaapp.Model.ThanhToanModel;
import com.projectmobile.orderpizzaapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanDAO {

    SQLiteDatabase database;
    public ThanhToanDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public List<ThanhToanModel> LayDSMonTheoMaDon(int madondat){
        List<ThanhToanModel> thanhToanModels = new ArrayList<ThanhToanModel>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_CHITIETDONDAT+" ctdd,"+CreateDatabase.TBL_MON+" mon WHERE "
                +"ctdd."+CreateDatabase.TBL_CHITIETDONDAT_MAMON+" = mon."+CreateDatabase.TBL_MON_MAMON+" AND "
                +CreateDatabase.TBL_CHITIETDONDAT_MADONDAT+" = '"+madondat+"'";

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ThanhToanModel thanhToanModel = new ThanhToanModel();
            thanhToanModel.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_CHITIETDONDAT_SOLUONG)));
            thanhToanModel.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_MON_GIATIEN)));
            thanhToanModel.setTenMon(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_MON_TENMON)));
            thanhToanModel.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(CreateDatabase.TBL_MON_HINHANH)));
            thanhToanModels.add(thanhToanModel);

            cursor.moveToNext();
        }

        return thanhToanModels;
    }
}
