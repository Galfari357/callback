package com.ari.callback;

import com.ari.model.artikel.ListData;

public interface NewsCallback {

    void onNewsSuccess(ListData listData);

    void onNewsFailure(String message);


}
