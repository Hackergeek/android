package skyward.com.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_1 = 1;
    public static final int REQUEST_CODE_2 = 2;
    private static final String TAG = "MainActivity";
    //文件路径
    private String mFilePath;

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        //获取SD卡路径
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath + "/temp.png";
    }

    /**
     * 调用系统相机（返回缩略图）
     * @param view
     */
    public void startCamera1(View view) {
        //通过隐式intent启动相机应用
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_1);
    }

    /**
     * 调用系统相机（返回原图）
     * @param view
     */
    public void startCamera2(View view) {
        //通过隐式intent启动相机应用
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        //修改系统保存图片的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, REQUEST_CODE_2);
    }

    /**
     * 调用自定义相机
     * @param view
     */
    public void customCamera(View view) {
        Intent intent = new Intent(MainActivity.this, CustomCameraActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_1) {
                /**
                 * 系统相机默认返回的bitmap为缩略图
                 */
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                iv.setImageBitmap(bitmap);
            } else if(requestCode == REQUEST_CODE_2) {
                /**
                 * 要想使系统相机返回原图
                 * 1.修改系统默认保存照片路径
                 * 2.自定义照片文件保存路径，在调用系统相机之前，将路径作为参数传递给系统相机
                 * 3.创建文件输入流，读取照片文件并转换为Bitmap，显示在ImageView中
                 */
                try {
                    FileInputStream fis = new FileInputStream(mFilePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    Log.d(TAG, "onActivityResult: 读取图片");
                    iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }
    }
}
