package com.huuthongit.doantraloicauhoi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LinhVucLoader extends AsyncTaskLoader{
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public LinhVucLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        return CallAPI.getJSONData("linhvuc","GET");
    }
}
