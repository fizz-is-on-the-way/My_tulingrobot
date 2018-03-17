package Test;

import android.test.AndroidTestCase;
import android.util.Log;

import Utils.HttpUtils;

public class Tuling_test extends AndroidTestCase{

     public void testSendInfo()
     {
         String res= HttpUtils.doGet("给我讲个笑话");
         Log.e("TAG",res);
         res=HttpUtils.doGet("给我讲个鬼故事");
         Log.e("TAG",res);
         res=HttpUtils.doGet("你好");
         Log.e("TAG",res);
         res=HttpUtils.doGet("今天天气如何");
         Log.e("TAG",res);
     }
}
