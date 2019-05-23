package com.ari.app.helper;


import android.content.Context;
import android.provider.Settings;

import java.io.File;

public class DeviceHelper {
    public static String getDeviceID(Context ctx)
    {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public Boolean isDeviceRooted(){
        boolean isRooted = checkSuperUser() || findBinary("su") || checkBuildInfo();
        return isRooted;
    }

    private boolean checkSuperUser() {
        File file = new File("/system/app/Superuser.apk");
        return file.exists();
    }

//    private boolean isAbleToExecCommand() {
//        return canExecuteCommand("/system/xbin/which su")
//                || canExecuteCommand("/system/bin/which su")
//                || canExecuteCommand("which su");
//    }
//
//    private boolean canExecuteCommand(String command) {
//        boolean executedSuccesfully;
//        try {
//            Runtime.getRuntime().exec(command);
//            executedSuccesfully = true;
//        } catch (Exception e) {
//            executedSuccesfully = false;
//        }
//
//        return executedSuccesfully;
//    }

    private boolean findBinary(String binaryName) {
        boolean found = false;
        if (!found) {
            String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                    "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
            for (String where : places) {
                if ( new File( where + binaryName ).exists() ) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    private boolean checkBuildInfo(){
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }
}
