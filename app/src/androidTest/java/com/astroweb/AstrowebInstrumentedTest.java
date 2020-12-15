package com.astroweb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * ./gradlew connectedAndroidTest
 * file:///home/stijanic/Projects/Astroweb-Android/app/build/reports/androidTests/connected/flavors/debugAndroidTest/index.html
 */
@RunWith(AndroidJUnit4.class)
public class AstrowebInstrumentedTest {
    private static final String BASIC_SAMPLE_PACKAGE  = "com.astroweb";
    private static final int LAUNCH_TIMEOUT = 5000;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(30);

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.astroweb", appContext.getPackageName());
    }

    @Test
    public void ok() {
        Log.i("com.astroweb.MainActivity", "############################################## Instrumented #############################################");
        System.out.println("############################################## Instrumented #############################################");
    }
}