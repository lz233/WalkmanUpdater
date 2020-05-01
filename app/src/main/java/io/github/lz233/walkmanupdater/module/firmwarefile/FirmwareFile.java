package io.github.lz233.walkmanupdater.module.firmwarefile;

import android.app.Activity;

public class FirmwareFile {
    private Activity activity;
    private String firmwareVersion;
    private String firmwareFileSummary;
    private String firmwareFileURL;

    public FirmwareFile(Activity activity, String firmwareVersion, String firmwareFileSummary, String firmwareFileURL) {
        this.activity = activity;
        this.firmwareVersion = firmwareVersion;
        this.firmwareFileSummary = firmwareFileSummary;
        this.firmwareFileURL = firmwareFileURL;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getFirmwareFileSize() {
        return firmwareFileSummary;
    }

    public String getFirmwareFileURL() {
        return firmwareFileURL;
    }
}
