package skyward.com.testandroidstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //1.输入“logt”，设置静态常量TAG
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * android中有5种级别的log，分别为：
         * 1.v(verbose)：任何信息都会输出
         * 2.i(info)：输出提示信息
         * 3.e(error)：输出错误信息
         * 4.d(debug)：输出调试信息
         * 5.w(warning)：输出警告信息
         */
        //2.输入对应的logx语句生成对应的log语句
        //输入“logw”: （TAG,String,Exception）
        //warning 打印警告信息
        Log.w(TAG, "onCreate: ", );
        //输入“loge”：（TAG,String,Exception)
        //error 打印错误信息
        Log.e(TAG, "onCreate: ", );
        //输入“logd”: (TAG,String)
        //debug 打印调试信息
        Log.d(TAG, "onCreate: ");
        //输入“logi”：(TAG,String)
        //info 打印一般提示信息
        Log.i(TAG, "onCreate: ");
        //输入“logm”: (TAG,String)
        //log method name and its arguments 打印方法名和参数
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        //输入“logr”: (TAG,String)
        //log result of this method 打印方法的返回值
        Log.d(TAG, "onCreate() returned: " +);
    }
}
