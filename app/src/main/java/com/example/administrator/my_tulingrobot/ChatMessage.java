package com.example.administrator.my_tulingrobot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/15.
 */

public class ChatMessage {
    private ChatMessage.Type type;
    private String msg;
    private Date date;
    private String datestr;
    private String name;

    public enum Type
    {
        INPUT,OUTPUT
    }

    public ChatMessage() {
    }


    public ChatMessage(Type type,String msg)
    {
        super();
        this.type=type;
        this.msg=msg;
    }

    public String getDatestr()
    {
        return datestr;
    }

    public void setDate(Date date)
    {
        this.date=date;
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.datestr=df.format(date);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type=type;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg=msg;
    }
}
