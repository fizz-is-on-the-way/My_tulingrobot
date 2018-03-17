package com.example.administrator.my_tulingrobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> datas)
    {
        mInflater=LayoutInflater.from(context);
        mDatas=datas;
    }

   public int getCount()
   {
       return mDatas.size();
   }

   public Object getItem(int position)
   {
       return mDatas.get(position);
   }

   public long getItemId(int position)
   {
       return position;
   }

   public int getItemViewType(int postion)
   {
       ChatMessage msg=mDatas.get(postion);
       return msg.getType()== ChatMessage.Type.INPUT ? 1 : 0;
   }

   public int getViewTypeCount()
   {
       return 2;
   }

   public View getView(int position, View convertView, ViewGroup parent)
   {
       ChatMessage chatMessage=(ChatMessage) mDatas.get(position);
       ChatMessageAdapter.ViewHolder viewHolder=null;

       if(convertView ==null)
       {
           viewHolder=new ChatMessageAdapter.ViewHolder();
           if(chatMessage.getType()== ChatMessage.Type.INPUT)
           {
               convertView=this.mInflater.inflate(R.layout.main_chat_from_msg,parent,false);
               viewHolder.createDate=(TextView)convertView.findViewById(R.id.chat_from_createDate);
               viewHolder.content=(TextView)convertView.findViewById(R.id.chat_from_content);
               convertView.setTag(viewHolder);
           }
           else
           {
               convertView=this.mInflater.inflate(R.layout.main_chat_send_msg,(ViewGroup) null );
               viewHolder.createDate=(TextView)convertView.findViewById(R.id.chat_send_createDate);
               viewHolder.content=(TextView)convertView.findViewById(R.id.chat_send_content);
               convertView.setTag(viewHolder);
           }
       }
       else
       {
           viewHolder=(ViewHolder)convertView.getTag();
       }

       viewHolder.content.setText(chatMessage.getMsg());
       viewHolder.createDate.setText(chatMessage.getDatestr());
       return convertView;
   }

   private class ViewHolder
   {
       public TextView createDate;
       public TextView name;
       private TextView content;

       private ViewHolder() {
       }
   }

}
