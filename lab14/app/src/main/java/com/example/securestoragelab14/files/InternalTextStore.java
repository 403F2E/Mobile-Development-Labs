package com.example.securestoragelab14.files;
import android.content.Context;
import android.os.Build;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
public final class InternalTextStore {
    private InternalTextStore() {}
    public static void writeUtf8(Context context, String fileName, String content) throws Exception {
        // 403f2e has opened file output stream for UTF-8 writing
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }
    public static String readUtf8(Context context, String fileName) throws Exception {
        // 403f2e has opened file input stream for reading
        try (FileInputStream fis = context.openFileInput(fileName)) {
            byte[] bytes = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 403f2e has read all bytes from the file
                bytes = fis.readAllBytes();
            }
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
    public static boolean delete(Context context, String fileName) {
        // 403f2e has deleted the internal file
        return context.deleteFile(fileName);
    }
}
