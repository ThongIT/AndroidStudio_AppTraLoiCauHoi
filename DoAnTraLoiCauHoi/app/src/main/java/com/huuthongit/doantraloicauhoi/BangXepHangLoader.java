package com.huuthongit.doantraloicauhoi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

public class BangXepHangLoader extends AsyncTaskLoader<String> {
    private final int page;
    private final int limit;
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public BangXepHangLoader(@NonNull Context context, int page, int limit) {
        super(context);
        this.page = page;
        this.limit = limit;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        HashMap<String, String> queryparams = new HashMap<>();
        queryparams.put("page",Integer.toString(page));
        queryparams.put("limit",Integer.toString(limit));
        String json = "";
        try {
            json = CallAPI.getJSONData("nguoichoi","GET",queryparams);
        }
        catch (Exception ex)
        {
            return null;
        }
        return json;
    }
}
