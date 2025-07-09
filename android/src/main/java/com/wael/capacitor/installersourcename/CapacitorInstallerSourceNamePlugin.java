package com.wael.capacitor.installersourcename;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorInstallerSourceName")
public class CapacitorInstallerSourceNamePlugin extends Plugin {

    private static final String TAG = "InstallerSourcePlugin";
    private CapacitorInstallerSourceName implementation = new CapacitorInstallerSourceName();

    @PluginMethod
    public void getAppInstaller(PluginCall call) {
        try {
            String installerPackageName = getInstallerPackageName();
            Log.d(TAG, "Raw installer package: " + installerPackageName);

            // Format installer name if necessary
            String installerName = getFormattedInstallerName(installerPackageName);
            Log.d(TAG, "Formatted installer name: " + installerName);

            // Send result back to Capacitor
            JSObject result = new JSObject();
            result.put("installer", installerName);
            call.resolve(result);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package not found: " + e.getMessage());
            call.reject("Package not found", e);
        } catch (SecurityException e) {
            Log.e(TAG, "Security exception: " + e.getMessage());
            call.reject("Permission denied", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error: " + e.getMessage());
            call.reject("Error fetching installer info", e);
        }
    }

    @Nullable
    private String getInstallerPackageName() throws PackageManager.NameNotFoundException {
        Context context = getContext();
        PackageManager packageManager = context.getPackageManager();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // API 30+
            return packageManager.getInstallSourceInfo(context.getPackageName())
                    .getInstallingPackageName();
        } else {
            // API 29 and below
            return getInstallerPackageNameLegacy(packageManager, context.getPackageName());
        }
    }

    @Nullable
    private String getInstallerPackageNameLegacy(PackageManager packageManager, String packageName) {
        // Deprecated method isolated
        return packageManager.getInstallerPackageName(packageName);
    }

    // Helper method to format installer name
    private String getFormattedInstallerName(String installerPackageName) {
        if (installerPackageName == null) {
            return "Installed from an unknown source";
        }

        // Using traditional switch statement for better compatibility
        switch (installerPackageName) {
            case "com.android.vending":
                return "Google Play Store";
            case "com.amazon.venezia":
                return "Amazon App Store";
            case "com.android.chrome":
                return "Google Chrome";
            case "com.android.packageinstaller":
                return "Package Installer";
            case "com.google.android.packageinstaller":
                return "Google Package Installer";
            case "com.android.apm":
                return "Android Package Manager";
            case "com.whatsapp":
                return "WhatsApp";
            case "org.mozilla.firefox":
                return "Firefox";
            case "com.facebook.katana":
                return "Facebook";
            case "com.instagram.android":
                return "Instagram";
            case "com.sec.android.app.samsungapps":
                return "Samsung Galaxy Store";
            case "com.huawei.appmarket":
                return "Huawei AppGallery";
            case "com.miui.supermarket":
                return "Xiaomi GetApps";
            case "com.oppo.market":
                return "Oppo App Market";
            case "com.vivo.sdkplugin":
                return "Vivo App Store";
            case "com.samsung.android.knox.attestation":
                return "Samsung Knox";
            case "com.samsung.android.oneconnect":
                return "Samsung SmartThings";
            case "com.samsung.android.oneui.home":
                return "Samsung One UI Home";
            case "com.heytap.market":
                return "Realme Store";
            case "com.lenovo.leos.appstore":
                return "Lenovo App Store";
            case "com.zte.appstore":
                return "ZTE App Store";
            case "com.asus.appmarket":
                return "Asus App Store";
            case "com.sony.mobile.market":
                return "Sony App Store";
            case "com.htc.appmarket":
                return "HTC App Store";
            case "com.lge.appbox.client":
                return "LG App Store";
            case "com.nokia.nstore":
                return "Nokia Store";
            case "com.tcl.appmarket":
                return "TCL App Store";
            case "com.letv.app.appstore":
                return "LeEco App Store";
            case "com.yandex.store":
                return "Yandex Store";
            case "com.apkpure.aegon":
                return "APKPure";
            case "com.uptodown":
                return "Uptodown";
            case "com.aptoide.partners":
                return "Aptoide";
            case "com.mobogenie.marketplace":
                return "Mobogenie";
            case "com.slideme.sam.manager":
                return "SlideME";
            case "com.getjar.rewards":
                return "GetJar";
            case "com.opera.app.discovery":
                return "Opera Mobile Store";
            case "org.fdroid.fdroid":
                return "F-Droid";
            case "com.android.adb":
                return "ADB Install";
            case "com.google.android.gms":
                return "Google Play Services";
            case "com.mi.global.shop":
                return "Mi Store Global";
            case "com.xiaomi.market":
                return "Xiaomi Market";
            case "com.bbk.appstore":
                return "Vivo App Store";
            case "com.coolapk.market":
                return "CoolAPK";
            case "com.tencent.android.qqdownloader":
                return "Tencent MyApp";
            case "com.wandoujia.phoenix2":
                return "Wandoujia";
            case "com.baidu.appsearch":
                return "Baidu Mobile Assistant";
            case "com.qihoo.appstore":
                return "360 Mobile Assistant";
            case "com.pp.assistant":
                return "PP Assistant";
            case "com.dragon.android.pandaspace":
                return "PandaSpace";
            case "com.hiapk.marketpho":
                return "HiAPK";
            case "com.mappn.gfan":
                return "GFan Market";
            case "com.nduoa.nmarket":
                return "N Market";
            case "com.taptap":
                return "TapTap";
            case "com.sonymobile.entrance":
                return "Sony Select";
            default:
                return installerPackageName;
        }
    }
}
