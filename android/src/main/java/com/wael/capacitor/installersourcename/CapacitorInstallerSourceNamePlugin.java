package com.wael.capacitor.installersourcename;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorInstallerSourceName")
public class CapacitorInstallerSourceNamePlugin extends Plugin {

    private CapacitorInstallerSourceName implementation = new CapacitorInstallerSourceName();

    @PluginMethod
    public void getAppInstaller(PluginCall call) {
        try {
            String installerPackageName = getInstallerPackageName();

            // Format installer name if necessary
            String installerName = getFormattedInstallerName(installerPackageName);

            // Send result back to Capacitor
            JSObject result = new JSObject();
            result.put("installer", installerName);
            call.resolve(result);

        } catch (Exception e) {
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

        return switch (installerPackageName) {
            case "com.android.vending" -> "Google Play Store";
            case "com.amazon.venezia" -> "Amazon App Store";
            case "com.android.chrome" -> "Google Chrome";
            case "com.google.android.packageinstaller" -> "Package Installer";
            case "com.whatsapp" -> "WhatsApp";
            case "org.mozilla.firefox" -> "Firefox";
            case "com.facebook.katana" -> "Facebook";
            case "com.instagram.android" -> "Instagram";
            case "com.sec.android.app.samsungapps" -> "Samsung Galaxy Store";
            case "com.huawei.appmarket" -> "Huawei AppGallery";
            case "com.miui.supermarket" -> "Xiaomi GetApps";
            case "com.oppo.market" -> "Oppo App Market";
            case "com.vivo.sdkplugin" -> "Vivo App Store";
            case "com.samsung.android.knox.attestation" -> "Samsung Knox";
            case "com.samsung.android.oneconnect" -> "Samsung SmartThings";
            case "com.samsung.android.oneui.home" -> "Samsung One UI Home";
            case "com.heytap.market" -> "Realme Store";
            case "com.lenovo.leos.appstore" -> "Lenovo App Store";
            case "com.zte.appstore" -> "ZTE App Store";
            case "com.asus.appmarket" -> "Asus App Store";
            case "com.sony.mobile.market" -> "Sony App Store";
            case "com.htc.appmarket" -> "HTC App Store";
            case "com.lge.appbox.client" -> "LG App Store";
            case "com.nokia.nstore" -> "Nokia Store";
            case "com.tcl.appmarket" -> "TCL App Store";
            case "com.letv.app.appstore" -> "LeEco App Store";
            case "com.yandex.store" -> "Yandex Store";
            case "com.apkpure.aegon" -> "APKPure";
            case "com.uptodown" -> "Uptodown";
            case "com.aptoide.partners" -> "Aptoide";
            case "com.mobogenie.marketplace" -> "Mobogenie";
            case "com.slideme.sam.manager" -> "SlideME";
            case "com.getjar.rewards" -> "GetJar";
            case "com.opera.app.discovery" -> "Opera Mobile Store";
            case "org.fdroid.fdroid" -> "F-Droid";
            default -> installerPackageName;
        };
    }
}
