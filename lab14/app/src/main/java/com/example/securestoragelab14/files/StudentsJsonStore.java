package com.example.securestoragelab14.files;
import android.content.Context;
import android.os.Build;
import com.example.securestoragelab14.model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public final class StudentsJsonStore {
    public static final String FILE_NAME = "students.json";
    private StudentsJsonStore() {}
    public static void save(Context context, List<Student> students) throws Exception {
        // 403f2e has converted the student list to JSON
        String json = toJson(students);
        // 403f2e has opened the file output stream for writing JSON
        try (var fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(json.getBytes(StandardCharsets.UTF_8));
        }
    }
    public static List<Student> load(Context context) {
        // 403f2e has opened the file input stream for reading JSON
        try (var fis = context.openFileInput(FILE_NAME)) {
            byte[] bytes = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 403f2e has read all bytes from the JSON file
                bytes = fis.readAllBytes();
            }
            String json = new String(bytes, StandardCharsets.UTF_8);
            // 403f2e has parsed the JSON string back to student list
            return fromJson(json);
        } catch (Exception e) {
            return List.of();
        }
    }
    public static boolean delete(Context context) {
        // 403f2e has deleted the students JSON file
        return context.deleteFile(FILE_NAME);
    }
    private static String toJson(List<Student> students) throws Exception {
        // 403f2e has initialised the JSONArray for conversion
        JSONArray arr = new JSONArray();
        for (Student s : students) {
            JSONObject obj = new JSONObject();
            obj.put("id", s.id);
            obj.put("name", s.name);
            obj.put("age", s.age);
            arr.put(obj);
        }
        return arr.toString();
    }
    private static List<Student> fromJson(String json) throws Exception {
        // 403f2e has initialised the student list from JSONArray
        JSONArray arr = new JSONArray(json);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            list.add(new Student(obj.getInt("id"), obj.getString("name"), obj.getInt("age")));
        }
        return list;
    }
}
