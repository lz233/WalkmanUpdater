package io.github.lz233.walkmanupdater.utils;

public class AppUtil {
    public static String getSystemVersion(){
        return android.os.Build.ID;
    }
    public static String getSystemSDKVersion(){
        return android.os.Build.VERSION.RELEASE;
    }
}
