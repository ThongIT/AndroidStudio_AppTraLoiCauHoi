package com.huuthongit.doantraloicauhoi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    private static final String BASE_URL_CAU_HOI = "http://10.0.2.2:8000/api/cauhoi/";



    public CauHoiLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        String id = LinhVucCauHoi.linhVucDuocChon;
        return CallAPI.getJSONData(id,BASE_URL_CAU_HOI ,"GET");
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
