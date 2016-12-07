package com.liuzheng.admin.mysavingdata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button button1;

    private String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
//                String filename = "Liuzheng.txt";
//                String string = "Hello world!";
//                FileOutputStream outputStream;
//                try {
//                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//                    outputStream.write(string.getBytes());
//                    outputStream.close();
////                    Toast.makeText(MainActivity.this, "文件位置在" + getFilesDir().getAbsolutePath(), Toast.LENGTH_LONG).show();
//                    Log.e(LOG_TAG, "文件位置在" + getFilesDir().getAbsolutePath());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "出错误了哦", Toast.LENGTH_SHORT).show();
//                }
                //12-02 18:21:08.888 17940-17940/com.liuzheng.admin.mysavingdata E/MainActivity: 文件位置在/data/data/com.liuzheng.admin.mysavingdata/files
                getAlbumStorageDir(getApplicationContext(),"Liuzheng");
                break;
        }
    }

    //检查外部存储器是否可用于读写
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //检查外部存储器是否可用以至少读取
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 在外存储上放公共文件你可以使用getExternalStoragePublicDirectory()
     *
     * @param albumName
     * @return
     */
    public File getAlbumStorageDir(String albumName) {
        // 获取用户的公共图片目录的目录。
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    /**
     * 创建应用私有文件的方法是Context.getExternalFilesDir()，如下：
     *
     * @param context
     * @param albumName
     * @return
     */
    public File getAlbumStorageDir(Context context, String albumName) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
}
