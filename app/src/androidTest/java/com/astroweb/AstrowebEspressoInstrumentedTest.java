package com.astroweb;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * ./gradlew connectedAndroidTest
 * file:///home/stijanic/Projects/Astroweb-Android/app/build/reports/androidTests/connected/flavors/debugAndroidTest/index.html
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AstrowebEspressoInstrumentedTest {

    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp()throws Exception {};

    @Test
    public void getNatalChart() throws InterruptedException {
        // Type text and then press the button.
        onView(withId(R.id.fab)).perform(click());
        TimeUnit.SECONDS.sleep(10);

        Log.i("com.astroweb.MainActivity", "############################################## EspressoInstrumented #############################################");
        System.out.println("############################################## EspressoInstrumented #############################################");
    };

    @After
    public void tearDown() {};
}
