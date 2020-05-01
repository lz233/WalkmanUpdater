package io.github.lz233.walkmanupdater.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import io.github.lz233.walkmanupdater.BuildConfig;
import io.github.lz233.walkmanupdater.R;
import io.github.lz233.walkmanupdater.utils.AppUtil;
import io.github.lz233.walkmanupdater.utils.SystemPropertyUtil;

public class MainActivity extends AppCompatActivity {
    private CardView deviceInformationCardView;
    private TextView deviceInformationSummaryTextView;
    private CardView updateInfoCardView;
    private CardView firmwareListCardView;
    private LinearLayout helpLinearLayout;
    private LinearLayout aboutLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        deviceInformationCardView = findViewById(R.id.deviceInformationCardView);
        deviceInformationSummaryTextView = findViewById(R.id.deviceInformationSummaryTextView);
        updateInfoCardView = findViewById(R.id.updateInfoCardView);
        firmwareListCardView = findViewById(R.id.firmwareListCardView);
        helpLinearLayout = findViewById(R.id.helpLinearLayout);
        aboutLinearLayout = findViewById(R.id.aboutLinearLayout);
        //
        AppCenter.start(getApplication(), "25068599-7a75-47ef-b966-46b2ecaa1c89", Analytics.class, Crashes.class);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        String productID = SystemPropertyUtil.getSystemProperty("vendor.product_id").trim();
        if(!(productID.equals("NW-A100Series")|productID.equals("NW-ZX500Series"))){
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
            builder.setTitle(R.string.unsupportDeviceTitle);
            builder.setMessage(R.string.unsupportDeviceSummary);
            builder.setPositiveButton(R.string.unsupportDeviceButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog materialDialogs = builder.create();
            //materialDialogs.setCancelable(false);
            materialDialogs.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
            materialDialogs.show();
        }else {
            deviceInformationSummaryTextView.setText(productID+" ("+AppUtil.getSystemVersion() + " " + SystemPropertyUtil.getSystemProperty("vendor.service_id").substring(0,4) + ")");
        }
        //
        deviceInformationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
            }
        });
        updateInfoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(MainActivity.this, UpdateLogActivity.class));
            }
        });
        firmwareListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(MainActivity.this, FirmwareListActivity.class));
            }
        });
        helpLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to-do
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://lz233.github.io/2020/05/01/how-to-use-walkman-updater/")));
            }
        });
        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setTitle(R.string.app_name);
                builder.setMessage(BuildConfig.VERSION_NAME+" ("+BuildConfig.VERSION_CODE+")");
                builder.setNegativeButton(R.string.aboutCoolapk, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://coolapk.com/apk/io.github.lz233.walkmanupdater")));
                    }
                });
                builder.setPositiveButton(R.string.aboutGithub, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/lz233/WalkmanUpdater")));
                    }
                });
                AlertDialog materialDialogs = builder.create();
                materialDialogs.show();
            }
        });
    }
}
