package com.beardleysebastian.studentschedulizer13;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotiReceiver extends BroadcastReceiver {

    public static final String NOTI_LOCKER = "noti_locker";
    private final String NOTI_CHANNEL_ID = "course_start_alert";
    private static int NOTI_ID = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        courseStartNotiChannel(context,NOTI_CHANNEL_ID);
        Notification notification = new NotificationCompat.Builder(context, NOTI_CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(intent.getStringExtra(NOTI_LOCKER))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        //Get compatibility NotiMgr
        NotificationManagerCompat notiMgr = NotificationManagerCompat.from(context);
        //Post noti using ID - same ID: this replaces previous
        notiMgr.notify(NOTI_ID++, notification);
//        //NOTI_LOCKER will be for Intent extra
//
//        int notiType = intent.getIntExtra(NOTI_LOCKER,0);
//        int courseID = intent.getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
//        courseStartNotiChannel(context,NOTI_CHANNEL_ID);
//        Toast.makeText(context,intent.getIntExtra(NOTI_LOCKER,0) + " notiType int " + courseID, Toast.LENGTH_LONG).show();
//
//
//        if(notiType == 1) { //Noti: Course Start AKA NotiType 1
//            //Create noti text - get courseID
////            int courseID = intent.getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
//            String startMsg = CatalogDatabase.getInstance().getCourse(courseID).getCourseName() +
//                    " begins soon: " +
//                    CatalogDatabase.getInstance().getCourse(courseID).getTargetStart()
//                    + "ID: " + NOTI_ID;
//            //Create notification and its properties
//            Notification notification = new NotificationCompat.Builder(context, NOTI_CHANNEL_ID)
//                    .setSmallIcon(android.R.drawable.ic_dialog_info)
//                    .setContentTitle(context.getString(R.string.app_name))
//                    .setContentText(startMsg)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .build();
//            //Get compatibility NotiMgr
//            NotificationManagerCompat notiMgr = NotificationManagerCompat.from(context);
//            //Post noti using ID - same ID: this replaces previous
//            notiMgr.notify(NOTI_ID++, notification);
//        } else if(notiType == 2){ //Noti: Course End AKA NotiType 2 - called from different methods
//            //Create noti text - get courseID
////            int courseID = intent.getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
//            String endMsg = CatalogDatabase.getInstance().getCourse(courseID).getCourseName() +
//                    " ends soon: " +
//                    CatalogDatabase.getInstance().getCourse(courseID).getTargetEnd()
//                    + "ID: " + NOTI_ID;
//            //Create notification and its properties
//            Notification notification = new NotificationCompat.Builder(context, NOTI_CHANNEL_ID)
//                    .setSmallIcon(android.R.drawable.ic_dialog_info)
//                    .setContentTitle(context.getString(R.string.app_name))
//                    .setContentText(endMsg)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .build();
//            //Get compatibility NotiMgr
//            NotificationManagerCompat notiMgr = NotificationManagerCompat.from(context);
//            //Post noti using ID - same ID: this replaces previous
//            notiMgr.notify(NOTI_ID++, notification);
//        } else if(notiType == 3){ //Noti: Assess Day/Due AKA NotiType 3
//            //Create noti text - get assessID
//        }
    }

    private void courseStartNotiChannel(Context context, String CHANNEL_ID){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = context.getString(R.string.course_start_channel);
            String desc = context.getString(R.string.course_start_channel_desc);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTI_CHANNEL_ID,name,importance);
            channel.setDescription(desc);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
