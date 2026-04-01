package com.example.securestoragelab14;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // 403f2e has retrieved the application context for testing
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // 403f2e has verified the package name
        assertEquals("com.example.securestoragelab14", appContext.getPackageName());
    }
}
