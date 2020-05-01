package io.github.lz233.walkmanupdater.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;

import io.github.lz233.walkmanupdater.R;
import io.github.lz233.walkmanupdater.utils.GetUtil;
import io.github.lz233.walkmanupdater.utils.SystemPropertyUtil;

public class UpdateLogActivity extends AppCompatActivity {
    private String serviceId;
    private CardView detailCardView;
    private TextView detailTitleTextView;
    private TextView detailSummaryTextView;
    private TextView detailURLTextView;
    private TextView detail0000TextView;
    private TextView detail0001TextView;
    private TextView detail0002TextView;
    private TextView detail0003TextView;
    private TextView detail0004TextView;
    private TextView detail0005TextView;
    private TextView detail0006TextView;
    private ProgressBar progressBar;
    private TextView updateInfoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_log);
        //
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        Toolbar toolbar = findViewById(R.id.toolbar);
        detailCardView = findViewById(R.id.detailCardView);
        detailTitleTextView = findViewById(R.id.detailTitleTextView);
        detailSummaryTextView = findViewById(R.id.detailSummaryTextView);
        detailURLTextView = findViewById(R.id.detailURLTextView);
        detail0000TextView = findViewById(R.id.detail0000TextView);
        detail0001TextView = findViewById(R.id.detail0001TextView);
        detail0002TextView = findViewById(R.id.detail0002TextView);
        detail0003TextView = findViewById(R.id.detail0003TextView);
        detail0004TextView = findViewById(R.id.detail0004TextView);
        detail0005TextView = findViewById(R.id.detail0005TextView);
        detail0006TextView = findViewById(R.id.detail0006TextView);
        progressBar = findViewById(R.id.progressBar);
        updateInfoTextView = findViewById(R.id.updateInfoTextView);
        //
        setSupportActionBar(toolbar);
        //
        serviceId = SystemPropertyUtil.getSystemProperty("vendor.service_id").substring(0, 4);
        detailSummaryTextView.setText(serviceId);
        getUpdateLog();
        detailCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailTitleTextView.setText(R.string.detailTitle2);
                detailSummaryTextView.setVisibility(View.GONE);
                detailURLTextView.setVisibility(View.GONE);
                detail0000TextView.setVisibility(View.VISIBLE);
                detail0001TextView.setVisibility(View.VISIBLE);
                detail0002TextView.setVisibility(View.VISIBLE);
                detail0003TextView.setVisibility(View.VISIBLE);
                detail0004TextView.setVisibility(View.VISIBLE);
                detail0005TextView.setVisibility(View.VISIBLE);
                detail0006TextView.setVisibility(View.VISIBLE);
            }
        });
        detailURLTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newRawUri("Label", Uri.parse(detailURLTextView.getText().toString())));
                Snackbar.make(v, getString(R.string.copied), Snackbar.LENGTH_SHORT).show();
            }
        });
        detail0000TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0000";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0001TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0001";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0002TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0002";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0003TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0003";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0004TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0004";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0005TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0005";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
        detail0006TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceId = "0006";
                detailTitleTextView.setText(R.string.detailTitle1);
                detailSummaryTextView.setVisibility(View.VISIBLE);
                detailSummaryTextView.setText(serviceId);
                detailURLTextView.setVisibility(View.VISIBLE);
                detailURLTextView.setText("");
                detail0000TextView.setVisibility(View.GONE);
                detail0001TextView.setVisibility(View.GONE);
                detail0002TextView.setVisibility(View.GONE);
                detail0003TextView.setVisibility(View.GONE);
                detail0004TextView.setVisibility(View.GONE);
                detail0005TextView.setVisibility(View.GONE);
                detail0006TextView.setVisibility(View.GONE);
                updateInfoTextView.setText("");
                getUpdateLog();
            }
        });
    }
    public void getUpdateLog(){
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new GetUtil().sendGet("https://info.update.sony.net/PA001/" + SystemPropertyUtil.getSystemProperty("vendor.product_id").trim() + "_" + serviceId + "/info/info.xml", new GetUtil.GetCallback() {
                    @Override
                    public void onGetDone(final String result) {
                        try {
                            final HashMap hashMap = pull2xml(new ByteArrayInputStream(result.substring(result.indexOf("<")).getBytes(StandardCharsets.UTF_8)));
                            final String language = Locale.getDefault().getLanguage();
                            final String country = Locale.getDefault().getCountry();
                            updateInfoTextView.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    //detailURLTextView.setText("test");
                                    detailURLTextView.setText((String)hashMap.get("URI"));
                                    if(language.equals("pt")){
                                        if (country.equals("BR")){
                                            updateInfoTextView.setText((String) hashMap.get("Brazilian"));
                                        }else {
                                            updateInfoTextView.setText((String) hashMap.get("Portuguese"));
                                        }
                                    }else if (language.equals("zh")){
                                        if (country.equals("CN")|country.equals("")){
                                            updateInfoTextView.setText((String) hashMap.get("Chinese(Simplified)"));
                                        }else {
                                            updateInfoTextView.setText((String) hashMap.get("Chinese(Traditional)"));
                                        }
                                    }else if (language.equals("fr")){
                                        updateInfoTextView.setText((String) hashMap.get("French"));
                                    }else if (language.equals("de")){
                                        updateInfoTextView.setText((String) hashMap.get("German"));
                                    }else if (language.equals("it")){
                                        updateInfoTextView.setText((String) hashMap.get("Italian"));
                                    }else if (language.equals("ko")){
                                        updateInfoTextView.setText((String) hashMap.get("Korean"));
                                    }else if (language.equals("pl")){
                                        updateInfoTextView.setText((String) hashMap.get("Polish"));
                                    }else if (language.equals("ru")){
                                        updateInfoTextView.setText((String) hashMap.get("Russian"));
                                    }else if (language.equals("es")){
                                        updateInfoTextView.setText((String) hashMap.get("Spanish"));
                                    }else if (language.equals("tr")){
                                        updateInfoTextView.setText((String) hashMap.get("Turkish"));
                                    }else {
                                        updateInfoTextView.setText((String) hashMap.get("English"));
                                    }
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
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                //开始标签
                case XmlPullParser.START_TAG:
                    if ("Description".equals(parser.getName())) {
                        hashMap.put(parser.getAttributeValue(null,"Lang"),parser.nextText());
                    }else if("Distribution".equals(parser.getName())){
                        hashMap.put("URI",parser.getAttributeValue(null,"URI"));
                    }
                    break;
                //结束标签
                case XmlPullParser.END_TAG:
                    break;
            }
            //继续往下读取标签类型
            type = parser.next();
        }
        return hashMap;
    }
}
