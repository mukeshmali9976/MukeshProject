package com.mukeshproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mukeshproject.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    // Alert dialog to show message
    public static void showMessageDialog(Context context, String message) {

        if (message != null && message.trim().length() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setMessage(message);
            builder.setPositiveButton(R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = builder.create();
//            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            // show it
            alertDialog.show();
        }

    }
    public static int dp2px(Context mContext, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }



    // String validation method
    public static boolean isEmptyString(String object) {
        return !(object != null && !object.isEmpty() && !object.equalsIgnoreCase("null") && object.trim().length() > 0 && !object.equalsIgnoreCase("(null)"));
    }


    public static PackageInfo getSoftwareVersion(Context mContext) {
        PackageInfo pi;
        try {
            pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pi;
        } catch (final PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    // to hide keyboard

    /**
     * It will hide keyboard
     *
     * @param context activity context
     * @param view    edit view in which we have focus
     */
    public static void hideKeyboard(Context context, View view) {
        try {
            if (context != null && view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hides the already popped up keyboard from the screen.
     *
     * @param context activity context
     */
    public static void hideKeyboard(Context context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e(TAG, "Sigh, cant even hide keyboard " + e.getMessage());
        }
    }



    /**
     * For making phone call from app
     *
     * @param number you want to call
     */
    public static void phoneCall(Context context, String number) {

        if (!TextUtils.isEmpty(number)) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + number));
            context.startActivity(callIntent);
        }
    }

    /**
     * For sending SNS from app using default SMS services
     *
     * @param message of the SMS
     */
    public static void sendSMS(Context context, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // At least KitKat

            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            sendIntent.putExtra("exit_on_sent", true);
            context.startActivity(sendIntent);

        } else {
            // For early versions, do what worked for you before.

            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra(Intent.EXTRA_TEXT, message);
            smsIntent.putExtra("exit_on_sent", true);
            context.startActivity(smsIntent);
        }
    }

    /**
     * For sending mail from app using mail clients
     *
     * @param mailId       you want to mail to person
     * @param subject      of the mail
     * @param body         of the mail
     * @param chooserTitle mail client selector title
     */
    public static void composeEmail(Context context, String mailId, String subject, String body, String chooserTitle) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.fromParts("mailto", mailId, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(emailIntent, chooserTitle));
    }


    // email validation
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * @param context   The context.
     * @return  returns whether connection is available and active or not.
     */
    //Check internet connection
    public static boolean isInternetAvailable(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // test for connection
            if (cm.getActiveNetworkInfo() != null
                    && cm.getActiveNetworkInfo().isAvailable()
                    && cm.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                Log.v(TAG, "Internet Connection Not Available");
//                Toast.makeText(context, context.getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String md5Encryption(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String saveBitmapToInternalStorage(@NonNull final Context context, @NonNull final Bitmap bitmapImage, @NonNull final String fileName) {
        try {

            File dir = null;

            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    dir = new File(Environment.getExternalStorageDirectory() + "/PrismXKB");
                } else {
                    dir = new File(context.getFilesDir() + "/PrismXKB");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dir == null) {
                dir = new File(Environment.getDataDirectory() + "/PrismXKB");
            }

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // File location to save image
            File mediaFile = new File(dir, fileName);

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mediaFile);
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();

                // Media scanner need to scan for the image saved
                Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri fileContentUri = Uri.fromFile(mediaFile);
                mediaScannerIntent.setData(fileContentUri);
                context.sendBroadcast(mediaScannerIntent);

                return mediaFile.getAbsolutePath();

            } catch (FileNotFoundException e) {
                Log.e(TAG, "Exception while trying to save file to internal storage: " + mediaFile + " " + e);
            } catch (IOException e) {
                Log.e(TAG, "Exception while trying to flush the output stream" + e);
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception wile trying to close file output stream." + e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
