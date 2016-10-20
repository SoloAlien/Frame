package com.example.appframe.common.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

import java.util.List;

/**
 * @FileName: com.example.appframe.common.utils.ActionUtils.java
 * @author: Alien
 * @date: 2016/10/20
 * @Function: Call Phone、Send Message、Take Photo、Clipping Photo
 */
public class ActionUtils {
    private Context context;
    public ActionUtils(Context context){
        this.context=context;
    }
    /**
     * Make a phone call
     * @param phonenumber
     */
    public void callPhone(String phonenumber){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phonenumber));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SendMessage
     * @param phonenumber
     * @param content
     */
    public void sendMessage(String phonenumber,String content){
        SmsManager manager=SmsManager.getDefault();
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        if (content.length()>=70){
            //if message.length>=70,auto to divide message
            List<String> ms = manager.divideMessage(content);
            for(String msg : ms) {
                manager.sendTextMessage(phonenumber,null,msg,sentIntent,null);
            }
        }else {
            manager.sendTextMessage(phonenumber,null,content,sentIntent,null);
        }
    }
    public void takePhoto(){

    }
    public void clipPhoto(){

    }
}
