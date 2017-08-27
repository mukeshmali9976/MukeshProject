package com.mukeshproject.domain.services;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mukeshproject.utils.CryptoManager;


import java.util.List;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    /**
     * The constant EXTRA_PUSH_DATA.
     */
    public static final String EXTRA_PUSH_DATA = "push_data";

    private SharedPreferences prefManager = null;

    /**
     * The M push data.
     */
    //PushNotificationData mPushData;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional

        prefManager = CryptoManager.getInstance(this).getPrefs();

        // TODO : Check for your required conditions.
//        if (remoteMessage.getData().size() > 0) {
//            mPushData = new PushNotificationData(remoteMessage.getData().get(PARAMS.TAG_TITLE),
//                    remoteMessage.getData().get(PARAMS.TAG_MESSAGE),
//                    remoteMessage.getData().get(PARAMS.TAG_CREATED), remoteMessage.getData().get(PARAMS.TAG_REF_ID), remoteMessage.getData().get(PARAMS.TAG_REF_TABLE));
//
//            sendNotification();
//        } else if (!Utils.isEmptyString(remoteMessage.getNotification().getBody())) {
//
//            mPushData = new PushNotificationData(remoteMessage.getNotification().getTitle(),
//                    remoteMessage.getNotification().getBody(),
//                    "", "", "");
//
//            sendNotification();
//        }
   }

    //This method is only generating push notification
    //It is same as we did in earlier posts
//    private void sendNotification() {
//
//        String title = getApplicationContext().getString(R.string.app_name);
//
//        PendingIntent contentIntent;
//
//        Intent intent = new Intent(this, HomeTabActivity.class);
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.putExtra(Constants.EXTRA_PUSH_DATA, mPushData);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // Set Notification builder.
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
//                .setContentTitle(title)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(mPushData.getMessage())).setContentText(mPushData.getMessage());
//
//        // TODO : get transparent app icon for notification for higher thanlollipop
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mBuilder.setSmallIcon(R.drawable.ic_small_notification); // transperent
//        } else {
//            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        }
//
//        /*try {
//            if (isAppRunning(this, this.getPackageName())) {
//                mBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), NotificationActivity.class), 0));
//            } else
//                mBuilder.setContentIntent(contentIntent);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//        mBuilder.setContentIntent(contentIntent);
//        mBuilder.setAutoCancel(true);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        mBuilder.setSound(alarmSound);
//        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//        mBuilder.setVibrate(new long[]{1000, 1000});
//
//        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(new Random().nextInt(1000), mBuilder.build());
//
//        // TODO : Increase or decrease badge count.
//        int mBadge = Integer.valueOf(prefManager.getString(Constants.BADGE_COUNT, "0"));
//        mBadge++;
//        if (mBadge > 0) {
//            prefManager.edit().putString(Constants.BADGE_COUNT, mBadge + "").apply();
//            ShortcutBadger.applyCount(getApplicationContext(), mBadge); //for 1.1.4
//        } else {
//            prefManager.edit().putString(Constants.BADGE_COUNT, mBadge + "").apply();
//            ShortcutBadger.removeCount(getApplicationContext()); //for 1.1.4
//        }
  //  }

    /**
     * Is app running boolean.
     *
     * @param context     the context
     * @param packageName the package name
     * @return the boolean
     */
    public static boolean isAppRunning(Context context, String packageName) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();

        List<ActivityManager.RunningTaskInfo> runningTaskInfo = activityManager.getRunningTasks(1);

        //ComponentName componentInfo = runningTaskInfo.get(0).topActivity.getPackageName();

        for (int i = 0; i < runningTaskInfo.size(); i++) {
            if (runningTaskInfo.get(i).topActivity.getPackageName().equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

}
