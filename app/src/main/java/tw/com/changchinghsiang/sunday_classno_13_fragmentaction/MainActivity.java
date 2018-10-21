package tw.com.changchinghsiang.sunday_classno_13_fragmentaction;

import android.app.Fragment;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.content.res.Resources;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //processFragNormal();

        processMobileStatus();
    }

    //正常動態載入二個 Fragment
    private void processFragNormal(){
        //1
        Fragment1 fragment1 = new Fragment1();
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction fragTransaction = mgr.beginTransaction();
        fragTransaction.add(R.id.linear, fragment1);
        fragTransaction.commit();

        //2
        Fragment2 fragment2 = new Fragment2();
        getFragmentManager().beginTransaction().add(R.id.linear, fragment2).commit();
    }

    //翻轉手機互換 Fragment 位置
    private void processMobileStatus() {
        //取得畫面長寬 Pixel
        Resources resource = getResources();
        DisplayMetrics metrics = resource.getDisplayMetrics();
        int widthPixel = metrics.widthPixels;
        int heightPixel = metrics.heightPixels;

        //當長 > 寬（手機：橫放）
        if (widthPixel > heightPixel){
            //取得目前 Fragment1 物件
            FragmentManager mgr1 = getFragmentManager();
            FragmentTransaction trans1 = mgr1.beginTransaction();
            Fragment frag1 = mgr1.findFragmentByTag("frag1");

            //存在則移除
            if (frag1 != null){
                trans1.remove(frag1);
                trans1.commit();
            }

            //取得目前 Fragment2 物件
            FragmentManager mgr2 = getFragmentManager();
            FragmentTransaction trans2 = mgr2.beginTransaction();
            Fragment frag2 = mgr2.findFragmentByTag("frag2");

            //存在則移除
            if (frag2 != null){
                trans2.remove(frag1);
                trans2.commit();
            }

            //再重新建立 Fragment
            Fragment1 fragment1 = new Fragment1();
            getFragmentManager().beginTransaction().add(R.id.linear, fragment1, "frag1").commit();

            Fragment2 fragment2 = new Fragment2();
            getFragmentManager().beginTransaction().add(R.id.linear, fragment2, "frag2").commit();
        }
    }
}
