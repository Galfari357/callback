package com.ari.app.cryp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FTCrypts {

    public String encrypt(String data, String skParam) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.ddHHmmss", Locale.getDefault());
        String skAuto = sdf.format(new Timestamp(System.currentTimeMillis()));
        SimpleDateFormat sdfExp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        long exMilis = 1800000L;
        String expTime = sdfExp.format(new Timestamp(System.currentTimeMillis() + exMilis));
        String dataEncy = FTAes.encrypt(data, skAuto);
        String skAutoEncy = FTAes.encrypt(skAuto, skParam);
        String expTimeEncy = FTAes.encrypt(expTime, skParam);
        return dataEncy + "." + skAutoEncy + "." + expTimeEncy;
    }

    public String decrypt(String encryptedData, String skParam) {
        try {
            String[] e = encryptedData.split("\\.");
            String dataEncy = e[0];
            String skAutoEncy = e[1];
            String expTimeEncy = e[2];
            String expTime = FTAes.decrypt(expTimeEncy, skParam);
            SimpleDateFormat sdfExp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
            Timestamp toDate = new Timestamp(System.currentTimeMillis());
            Date expParsed = sdfExp.parse(expTime);
            return toDate.after(expParsed) ? FTHelpers.response("111", "Encryption Duration Expired") : decrypt(dataEncy, skAutoEncy, skParam);
        } catch (ArrayIndexOutOfBoundsException var11) {
            return FTHelpers.response("101", "Invalid Count of Arguments Provided");
        } catch (ParseException var12) {
            return FTHelpers.response("110", "Invalid Given Encrypted Data");
        }
    }

    private String decrypt(String dataEncy, String skAutoEncy, String skParam) {
        String skAuto = FTAes.decrypt(skAutoEncy, skParam);
        return FTAes.decrypt(dataEncy, skAuto);
    }

}
