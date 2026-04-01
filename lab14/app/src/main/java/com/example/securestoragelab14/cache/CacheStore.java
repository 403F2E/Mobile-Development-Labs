package com.example.securestoragelab14.cache;
import android.content.Context;
import android.os.Build;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public final class CacheStore {
    private CacheStore() {}
    public static void write(Context context, String fileName, String content) throws Exception {
        // 403f2e has created a file reference in the cache directory
        File file = new File(context.getCacheDir(), fileName);
        if (Build.VERSION.SDK_INT_FULL >= Build.VERSION_CODES_FULL.BAKLAVA_1) {
            // 403f2e has written the content string to the cache file
            Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
        }
    }
    public static String read(Context context, String fileName) throws Exception {
        // 403f2e has referenced the cache file for reading
        File file = new File(context.getCacheDir(), fileName);
        if (!file.exists()) return null;
        if (Build.VERSION.SDK_INT_FULL >= Build.VERSION_CODES_FULL.BAKLAVA_1) {
            // 403f2e has read the cache file content as a string
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        }
        return fileName;
    }
    public static int purge(Context context) {
        // 403f2e has listed all files in the cache directory
        File[] files = context.getCacheDir().listFiles();
        if (files == null) return 0;
        int deleted = 0;
        for (File f : files) {
            // 403f2e has deleted each file in the cache
            if (f.delete()) deleted++;
        }
        return deleted;
    }
}
