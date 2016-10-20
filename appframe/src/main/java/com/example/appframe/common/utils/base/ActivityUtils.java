package com.example.appframe.common.utils.base;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.appframe.R;
import com.example.appframe.common.utils.base.LogUtils;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @FileName: com.example.appframe.common.utils.base.ActivityUtils.java
 * @author: Alien
 * @date: 2016/10/20
 */
public class ActivityUtils {
    // 使用弱引用，避免不恰当地持有Activity或Fragment的引用。
    // 持有Activity的引用会阻止Activity的内存回收，增大OOM的风险。
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;

    private Toast toast;

    public ActivityUtils(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    public ActivityUtils(Fragment fragment){
        fragmentWeakReference = new WeakReference<>(fragment);
    }

    /**
     * Get the reference of the specific {@link Activity}, this method
     * may return null, you should check the result when you invoke it.
     *
     * <p/>
     * 通过弱引用获取Activity对象，此方法可能返回null，调用后需要做检查。
     * @return reference of {@link Activity}
     */
    private @Nullable
    Activity getActivity() {

        if (activityWeakReference != null) return activityWeakReference.get();
        if (fragmentWeakReference != null) {
            Fragment fragment = fragmentWeakReference.get();
            return fragment == null? null : fragment.getActivity();
        }
        return null;
    }

    public void showToast(CharSequence msg){
        Activity activity = getActivity();
        if (activity != null){
            if (toast == null) toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        }
    }

    @SuppressWarnings("SameParameterValue")
    public void showToast(@StringRes int resId){
        Activity activity = getActivity();
        if (activity != null) {
            String msg = activity.getString(resId);
            showToast(msg);
        }
    }

    public void startActivity(Class<? extends Activity> clazz,boolean b){
        startActivity(clazz,null,null,b);
    }
    public void startActivity(Class<? extends Activity> clazz,Bundle bundle,boolean b){
        startActivity(clazz,bundle,null,b);
    }

    public void startActivity(Class<? extends Activity> clazz, Bundle bundle, Uri uri,boolean b){
        Activity activity = getActivity();
        if (activity == null) return;
        Intent intent = new Intent(activity, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        activity.startActivity(intent);
        openOrCloseAnim(activity,b);
    }
    /**
     * Close Activity with Animation(true or false)
     * @param b
     */
    public void finish(boolean b){
        Activity activity = getActivity();
        if (activity == null) return;
        activity.finish();
        openOrCloseAnim(activity,b);
    }
    public void openOrCloseAnim(Activity activity,boolean b){
        if (b==true)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
    /**
     * To get StatusBar's height
     * @return The height of StatusBar.
     */
    public int getStatusBarHeight() {
        Activity activity = getActivity();
        if (activity == null) return 0;

        Resources resources = getActivity().getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        LogUtils.v("getStatusBarHeight: " + result);
        return result;
    }
    /**
     * To get Screen's width
     * @return The width of Screen.
     */
    public int getScreenWidth() {
        Activity activity = getActivity();
        if (activity == null) return 0;

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    /**
     * To get Screen's height
     * @return The height of Screen.
     */
    public int getScreenHeight() {
        Activity activity = getActivity();
        if (activity == null) return 0;

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public void hideSoftKeyboard(){
        Activity activity = getActivity();
        if (activity == null) return;

        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * To open  Browser
     * @param url (url address)
     */
    public void startBrowser(String url){
        if (getActivity() == null) return;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        getActivity().startActivity(intent);
    }
}

