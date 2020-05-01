package io.github.lz233.walkmanupdater.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import io.github.lz233.walkmanupdater.R;
import io.github.lz233.walkmanupdater.utils.GetUtil;
import io.github.lz233.walkmanupdater.utils.SystemPropertyUtil;

public class UpdateLogActivity extends AppCompatActivity {
    private TextView updateInfoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_log);
        //
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        Toolbar toolbar = findViewById(R.id.toolbar);
        updateInfoTextView = findViewById(R.id.updateInfoTextView);
        //
        setSupportActionBar(toolbar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new GetUtil().sendGet("https://info.update.sony.net/PA001/" + SystemPropertyUtil.getSystemProperty("vendor.product_id").trim() + "_" + SystemPropertyUtil.getSystemProperty("vendor.service_id").substring(0, 4) + "/info/info.xml", new GetUtil.GetCallback() {
                    @Override
                    public void onGetDone(final String result) {
                        try {
                            final HashMap hashMap = pull2xml(new ByteArrayInputStream(result.substring(result.indexOf("<")).getBytes(StandardCharsets.UTF_8)));
                            updateInfoTextView.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateInfoTextView.setText((String) hashMap.get("Chinese(Simplified)"));
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*updateInfoTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                updateInfoTextView.setText(result.substring(result.indexOf("<")));
                            }
                        });*/
                    }
                });
            }
        }).start();
    }
    public HashMap pull2xml(InputStream is) throws Exception {
        HashMap hashMap = new HashMap();
        //创建xmlPull解析器
        XmlPullParser parser = Xml.newPullParser();
        ///初始化xmlPull解析器
        parser.setInput(is, "utf-8");
        //读取文件的类型
        int type = parser.getEventType();
        //无限判断文件类型进行读取
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                //开始标签
                case XmlPullParser.START_TAG:
                    if ("Description".equals(parser.getName())) {
                        hashMap.put(parser.getAttributeValue(null,"Lang"),parser.nextText());
                    }
                    break;
                //结束标签
                case XmlPullParser.END_TAG:

            }
            //继续往下读取标签类型
            type = parser.next();
        }
        return hashMap;
    }
}
