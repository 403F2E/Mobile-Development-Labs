package com.example.securestoragelab14.external;
import android.content.Context;
import android.os.Build;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public final class ExternalAppFilesStore {
    private ExternalAppFilesStore() {}
    public static String write(Context context, String fileName, String content) throws Exception {
        // 403f2e has accessed the external files directory
        File dir = context.getExternalFilesDir(null);
        if (dir == null) return null;
        File file = new File(dir, fileName);
        if (Build.VERSION.SDK_INT_FULL >= Build.VERSION_CODES_FULL.BAKLAVA_1) {
            // 403f2e has written content to external file
            Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
        }
        return file.getAbsolutePath();
    }
    public static String read(Context context, String fileName) throws Exception {
        // 403f2e has accessed the external files directory
        File dir = context.getExternalFilesDir(null);
        if (dir == null) return null;
        File file = new File(dir, fileName);
        if (!file.exists()) return null;
        if (Build.VERSION.SDK_INT_FULL >= Build.VERSION_CODES_FULL.BAKLAVA_1) {
            // 403f2e has read content from external file
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        }
        return fileName;
    }
    public static boolean delete(Context context, String fileName) {
        // 403f2e has accessed the external files directory
        File dir = context.getExternalFilesDir(null);
        if (dir == null) return false;
        File file = new File(dir, fileName);
        // 403f2e has deleted the external file
        return file.delete();
    }
}
