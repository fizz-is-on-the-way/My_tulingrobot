package Utils;

import com.example.administrator.my_tulingrobot.ChatMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;
import java.net.URL;

import com.example.administrator.my_tulingrobot.CommonException;
import com.example.administrator.my_tulingrobot.Result;
import com.google.gson.Gson;


/**
 * Created by Administrator on 2018/3/13.
 */

public class HttpUtils {
    private static final String URL="http://www.tuling123.com/openapi/api";
    private static final String API_KEY="3b31090d1e8646d082fbdedaf5239c6f";

    public static ChatMessage sendMsg(String msg)
    {
        ChatMessage message = new ChatMessage();
        String url = setParams(msg);
        String res = doGet(url);
        Gson gson = new Gson();
        Result result = gson.fromJson(res, Result.class);

        if (result.getCode() > 400000 || result.getText() == null
                || result.getText().trim().equals(""))
        {
            message.setMsg("该功能等待开发...");
        }else
        {
            message.setMsg(result.getText());
        }
        message.setType(ChatMessage.Type.INPUT);
        message.setDate(new Date());

        return message;
    }

    public static String doGet(String urlStr)
    {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200)
            {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1)
                {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else
            {
                throw new CommonException("服务器连接错误！");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            throw new CommonException("服务器连接错误！");
        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            conn.disconnect();
        }
    }

    private static String setParams(String msg)
    {
        try
        {
            msg = URLEncoder.encode(msg, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url=URL+"?key="+API_KEY+"&info="+msg;
        return url;
    }

}
