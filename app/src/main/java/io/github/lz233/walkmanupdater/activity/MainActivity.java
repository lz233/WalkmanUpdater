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

import io.github.lz233.walkmanupdater.BuildConfig;
import io.github.lz233.walkmanupdater.R;
import io.github.lz233.walkmanupdater.utils.AppUtil;
import io.github.lz233.walkmanupdater.utils.SystemPropertyUtil;

public class MainActivity extends AppCompatActivity {
    private CardView systemVersionCardView;
    private TextView systemVersionSummaryTextView;
    private CardView updateInfoCardView;
    private CardView firmwareListCardView;
    private LinearLayout aboutLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        systemVersionCardView = findViewById(R.id.systemVersionCardView);
        systemVersionSummaryTextView = findViewById(R.id.systemVersionSummaryTextView);
        updateInfoCardView = findViewById(R.id.updateInfoCardView);
        firmwareListCardView = findViewById(R.id.firmwareListCardView);
        aboutLinearLayout = findViewById(R.id.aboutLinearLayout);
        //
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        systemVersionSummaryTextView.setText(AppUtil.getSystemVersion() + " (" + SystemPropertyUtil.getSystemProperty("vendor.service_id").substring(0,4) + ")");
        //
        systemVersionCardView.setOnClickListener(new View.OnClickListener() {
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
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://coolapk.com/apk/com.lz233.onetext")));
                    }
                });
                builder.setPositiveButton(R.string.aboutGithub, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //to-do
                    }
                });
                AlertDialog materialDialogs = builder.create();
                materialDialogs.show();
            }
        });
    }
}
