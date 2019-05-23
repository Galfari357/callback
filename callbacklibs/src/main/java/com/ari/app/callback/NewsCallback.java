package com.ari.app.callback;

import com.ari.app.model.artikel.ListData;

public interface NewsCallback {

    void onNewsSuccess(ListData listData);

    void onNewsFailure(String message);


}
