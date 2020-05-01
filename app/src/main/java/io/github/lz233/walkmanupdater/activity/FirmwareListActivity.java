package io.github.lz233.walkmanupdater.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

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

public class FirmwareListActivity extends AppCompatActivity {
    private FirmwareFileAdapter firmwareFileAdapter;
    private List<FirmwareFile> firmwareFileList = new ArrayList<>();;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        firmwareListRecyclerView.setLayoutManager(linearLayoutManager);
        firmwareFileAdapter = new FirmwareFileAdapter(firmwareFileList);
        firmwareListRecyclerView.setAdapter(firmwareFileAdapter);
        try {
            JSONArray jsonArray = new JSONArray(FileUtil.readStringFromAssets(this, SystemPropertyUtil.getSystemProperty("vendor.product_id").trim()+".json"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                firmwareFileList.add(new FirmwareFile(this,jsonObject.getString("versionName"),jsonObject.getString("versionArea"),jsonObject.getString("fileURL")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
