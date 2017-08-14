package com.reactdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.microsoft.azure.mobile.react.analytics.RNAnalyticsPackage;
import com.microsoft.azure.mobile.react.crashes.RNCrashesPackage;
import com.microsoft.azure.mobile.react.mobilecenter.RNMobileCenterPackage;
import com.microsoft.azure.mobile.react.push.RNPushPackage;
import com.microsoft.azure.mobile.push.Push;


import java.util.Arrays;
import java.util.List;




public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new RNPushPackage(MainApplication.this),
            new RNCrashesPackage(MainApplication.this, getResources().getString(R.string.mobileCenterCrashes_whenToSendCrashes)),
            new RNAnalyticsPackage(MainApplication.this, getResources().getString(R.string.mobileCenterAnalytics_whenToEnableAnalytics)),
            new RNMobileCenterPackage(MainApplication.this)
      );
    }
  };
  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }
  private FirebaseAnalytics mFirebaseAnalytics;
  @Override
  public void onCreate() {
    // Obtain the FirebaseAnalytics instance.
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    Push.enableFirebaseAnalytics(getApplication());
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
  }

  private Context getApplication() {
    return getApplicationContext();
  }

}
