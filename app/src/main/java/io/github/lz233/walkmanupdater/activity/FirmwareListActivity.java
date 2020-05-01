package io.github.lz233.walkmanupdater.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.lz233.walkmanupdater.R;
import io.github.lz233.walkmanupdater.module.firmwarefile.FirmwareFile;
import io.github.lz233.walkmanupdater.module.firmwarefile.FirmwareFileAdapter;
import io.github.lz233.walkmanupdater.utils.FileUtil;
import io.github.lz233.walkmanupdater.utils.SystemPropertyUtil;
import pub.devrel.easypermissions.EasyPermissions;

public class FirmwareListActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private FirmwareFileAdapter firmwareFileAdapter;
    private List<FirmwareFile> firmwareFileList = new ArrayList<>();
    ;
    private RecyclerView firmwareListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmware_list);
        //
        Toolbar toolbar = findViewById(R.id.toolbar);
        firmwareListRecyclerView = findViewById(R.id.firmwareListRecyclerView);
        //
        setSupportActionBar(toolbar);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        //申请权限
        //final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //requestPermissions(permissions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        firmwareListRecyclerView.setLayoutManager(linearLayoutManager);
        firmwareFileAdapter = new FirmwareFileAdapter(firmwareFileList);
        firmwareListRecyclerView.setAdapter(firmwareFileAdapter);
        try {
            JSONArray jsonArray = new JSONArray(FileUtil.readStringFromAssets(this, SystemPropertyUtil.getSystemProperty("vendor.product_id").trim() + ".json"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                firmwareFileList.add(new FirmwareFile(this, jsonObject.getString("versionName"), jsonObject.getString("versionArea"), jsonObject.getString("fileURL")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void requestPermissions(final String[] permissions) {
        if (EasyPermissions.hasPermissions(this, permissions)) {
        } else {
            EasyPermissions.requestPermissions(FirmwareListActivity.this, null, 1, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
    }
}
