package com.ari.cryp;

import org.json.JSONException;
import org.json.JSONObject;

class FTHelpers {

    static String response(String code, String msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", code);
            obj.put("message", msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

}
