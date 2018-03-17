package com.example.administrator.my_tulingrobot;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Utils.HttpUtils;


public class MainActivity extends Activity {

    private ListView mChatView;
    private EditText mMsg;
    private List<ChatMessage> mDatas = new ArrayList<ChatMessage>();
    private ChatMessageAdapter mAapter;

    private Handler mHandler =new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            ChatMessage from=(ChatMessage)msg.obj;
            mDatas.add(from);
            mAapter.notifyDataSetChanged();
            mChatView.setSelection(mDatas.size() - 1);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        mAapter=new ChatMessageAdapter(this,mDatas);
        mChatView.setAdapter(mAapter);
    }

    private void initView()
    {
        mChatView=(ListView)findViewById(R.id.id_chat_listView);
        mMsg=(EditText)findViewById(R.id.id_chat_msg);
        mDatas.add(new ChatMessage(ChatMessage.Type.INPUT,"Hi,我是fizz的小宠物，很高兴见到你!"));
    }

    public void sendMessage(View view)
    {
        final String msg=mMsg.getText().toString();
        if(TextUtils.isEmpty(msg))
        {
            Toast.makeText(this,"您还没填写信息呢!",Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage to =new ChatMessage(ChatMessage.Type.OUTPUT,msg);
        to.setDate(new Date());
        mDatas.add(to);

        mAapter.notifyDataSetChanged();
        mChatView.setSelection(mDatas.size()-1);
        mMsg.setText("");

        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if(imm.isActive())
        {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,InputMethodManager.HIDE_NOT_ALWAYS);
        }

        new Thread()
        {
            public void run()
            {
                ChatMessage from =null;
                try
                {
                    from= HttpUtils.sendMsg(msg);
                }
                catch (Exception e)
                {
                    from =new ChatMessage(ChatMessage.Type.INPUT,"信号被劫持了...");
                }

                Message message=Message.obtain();
                message.obj=from;
                mHandler.sendMessage(message);
            };
        }.start();
    }

}
