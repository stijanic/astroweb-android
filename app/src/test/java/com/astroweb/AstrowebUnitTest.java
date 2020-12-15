package com.astroweb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * ./gradlew test
 * file:///home/stijanic/Projects/Astroweb-Android/app/build/reports/tests/testDebugUnitTest/index.html
 */
public class AstrowebUnitTest {

    @Before
    public void setUp() {};

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @After
    public void tearDown() {};
}