package com.example.cc.knowldegedemo.ipc;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.knowldegedemo.R;
import com.example.cc.utils.ChineseCharUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestActivity extends Activity {
    private int count = 0;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn).setOnClickListener(v -> {
            test();
        });
        textView = findViewById(R.id.tv_token);
        editText = findViewById(R.id.edit);
        final int maxLength = 5;
        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                int destLength = ChineseCharUtil.getLength(dest.toString());
                int sourceLength = ChineseCharUtil.getLength(source.toString());
                Log.e("dream", "dest:" + dest.toString() + ", destLength:" + destLength + ", source:" + source + ", sourceLength:" + sourceLength);
                if (destLength + sourceLength >= maxLength) {
                    if (destLength >= maxLength) {
                        return "";
                    } else {
                        int t = 0;
                        int canUseLength = maxLength - destLength;
                        for (int count = 0; t < source.length(); ++t) {
                            char c = source.charAt(t);
                            if (ChineseCharUtil.isChinese(c) || ChineseCharUtil.isChinesePunctuation(c) || ChineseCharUtil.isEmojiCharacter(c)) {
                                if (count + 2 > canUseLength) {
                                    break;
                                } else {
                                    count += 2;
                                }
                            } else {
                                if (count + 1 > canUseLength) {
                                    break;
                                } else {
                                    count += 1;
                                }
                            }
                        }

                        Log.e("dream", "result:" + source.subSequence(0, t));
                        return t > 0 ? source.subSequence(0, t) : "";
                    }
                }
                return source;
            }
        }});
    }


    private void test() {
        Uri uri = Uri.parse("content://com.netease.gl.core.ipc.glprovider/logToken");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.getColumnCount() > 0) {
            while (cursor.moveToNext()) {
                String token = cursor.getString(cursor.getColumnIndex("logToken"));
                textView.setText(token);
                Toast.makeText(this, "logToken:" + token, Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "没访问到大神GLProvider", Toast.LENGTH_SHORT).show();
        }
    }

    private static final String PROVIDER_DIR = "provider";

    public static void writeLogToken(Context context, String logToken) {
        if (TextUtils.isEmpty(logToken)) {
            return;
        }
        File file = new File(context.getFilesDir() + File.separator + PROVIDER_DIR);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(file, "logToken"));
            outputStream.write(logToken.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readLogToken(Context context) {
        String result = "";
        File file = new File(context.getFilesDir() + File.separator + PROVIDER_DIR);
        if (file != null && !file.exists()) {
            return result;
        }
        try {
            FileInputStream inputStream = new FileInputStream(new File(file, "logToken"));
            int lenght = inputStream.available();
            byte[] buffer = new byte[lenght];
            inputStream.read(buffer);
            inputStream.close();
            result = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
