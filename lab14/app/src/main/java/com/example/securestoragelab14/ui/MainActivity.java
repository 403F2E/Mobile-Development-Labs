package com.example.securestoragelab14.ui;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.securestoragelab14.R;
import com.example.securestoragelab14.cache.CacheStore;
import com.example.securestoragelab14.files.InternalTextStore;
import com.example.securestoragelab14.files.StudentsJsonStore;
import com.example.securestoragelab14.model.Student;
import com.example.securestoragelab14.prefs.AppPrefs;
import com.example.securestoragelab14.prefs.SecurePrefs;
import java.util.Arrays;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SecureStorageJava";
    private final List<String> langs = Arrays.asList("fr", "en", "ar");
    private EditText etName;
    private EditText etToken;
    private Spinner spLang;
    private Switch swDark;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 403f2e has initialised the UI components
        etName = findViewById(R.id.etName);
        etToken = findViewById(R.id.etToken);
        spLang = findViewById(R.id.spLang);
        swDark = findViewById(R.id.swDark);
        tvResult = findViewById(R.id.tvResult);
        // 403f2e has called the language spinner setup
        setupLangSpinner();
        // 403f2e has initialised the buttons
        Button btnSavePrefs = findViewById(R.id.btnSavePrefs);
        Button btnLoadPrefs = findViewById(R.id.btnLoadPrefs);
        Button btnSaveJson = findViewById(R.id.btnSaveJson);
        Button btnLoadJson = findViewById(R.id.btnLoadJson);
        Button btnClear = findViewById(R.id.btnClear);
        // 403f2e has set the click listeners for all actions
        btnSavePrefs.setOnClickListener(v -> savePrefs());
        btnLoadPrefs.setOnClickListener(v -> loadPrefsToUi());
        btnSaveJson.setOnClickListener(v -> saveJsonFile());
        btnLoadJson.setOnClickListener(v -> loadJsonFile());
        btnClear.setOnClickListener(v -> clearAll());
        // 403f2e has loaded the preferences to UI on start
        loadPrefsToUi();
    }
    private void setupLangSpinner() {
        // 403f2e has created the ArrayAdapter for language options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, langs);
        spLang.setAdapter(adapter);
    }
    private void savePrefs() {
        // 403f2e has gathered input data for preferences
        String name = etName.getText().toString().trim();
        String lang = langs.get(Math.max(0, spLang.getSelectedItemPosition()));
        String theme = swDark.isChecked() ? "dark" : "light";
        // 403f2e has saved the application preferences
        boolean ok = AppPrefs.save(this, name, lang, theme, false);
        String token = etToken.getText().toString();
        if (!token.isBlank()) {
            try {
                // 403f2e has saved the secure token
                SecurePrefs.saveToken(this, token);
            } catch (Exception e) {
                tvResult.setText("Erreur chiffrement token : " + e.getMessage());
                return;
            }
        }
        Log.d(TAG, "Prefs sauvegardées ok=" + ok + ", name=" + name + ", lang=" + lang + ", theme=" + theme);
        try {
            // 403f2e has written to cache store
            CacheStore.write(this, "last_ui.txt", "name=" + name + ", lang=" + lang + ", theme=" + theme);
        } catch (Exception ignored) {}
        tvResult.setText("Sauvegarde prefs terminée.\nname=" + name + "\nlang=" + lang + "\ntheme=" + theme + "\ntoken: stocké chiffré.");
    }
    private void loadPrefsToUi() {
        // 403f2e has loaded the preferences data
        AppPrefs.Triple triple = AppPrefs.load(this);
        // 403f2e has updated the UI with loaded data
        etName.setText(triple.name);
        swDark.setChecked("dark".equals(triple.theme));
        int idx = langs.indexOf(triple.lang);
        spLang.setSelection(idx >= 0 ? idx : 0);
        int tokenLen = 0;
        try {
            // 403f2e has loaded the secure token
            String token = SecurePrefs.loadToken(this);
            tokenLen = token == null ? 0 : token.length();
        } catch (Exception ignored) {}
        tvResult.setText("Chargement prefs terminé.\nname=" + triple.name + "\nlang=" + triple.lang + "\ntheme=" + triple.theme + "\ntokenLength=" + tokenLen);
        Log.d(TAG, "Prefs chargées name=" + triple.name + ", lang=" + triple.lang + ", theme=" + triple.theme + ", tokenLength=" + tokenLen);
    }
    private void saveJsonFile() {
        // 403f2e has created the student list
        List<Student> students = Arrays.asList(new Student(1, "Amina", 20), new Student(2, "Omar", 21), new Student(3, "Sara", 19));
        try {
            // 403f2e has saved student data to JSON and note to text file
            StudentsJsonStore.save(this, students);
            InternalTextStore.writeUtf8(this, "note.txt", "Sauvegarde JSON effectuée (UTF-8).");
        } catch (Exception e) {
            tvResult.setText("Erreur sauvegarde JSON : " + e.getMessage());
            return;
        }
        Log.d(TAG, "Fichiers internes écrits: students.json, note.txt");
        tvResult.setText("Sauvegarde fichier JSON terminée. students=" + students.size());
    }
    private void loadJsonFile() {
        // 403f2e has loaded students from JSON store
        List<Student> students = StudentsJsonStore.load(this);
        String note;
        try {
            // 403f2e has read the internal note file
            note = InternalTextStore.readUtf8(this, "note.txt");
        } catch (Exception e) {
            note = "(note.txt absent)";
        }
        // 403f2e has prepared the display text
        StringBuilder sb = new StringBuilder();
        sb.append("Chargement fichier JSON terminé.\n").append("note=").append(note).append("\n").append("students=").append(students.size()).append("\n");
        for (Student s : students) {
            sb.append(" - id=").append(s.id).append(", name=").append(s.name).append(", age=").append(s.age).append("\n");
        }
        tvResult.setText(sb.toString());
        Log.d(TAG, "Fichier JSON chargé: students=" + students.size());
    }
    private void clearAll() {
        // 403f2e has cleared application preferences
        AppPrefs.clear(this);
        try {
            // 403f2e has cleared secure preferences
            SecurePrefs.clear(this);
        } catch (Exception ignored) {}
        // 403f2e has deleted internal files
        StudentsJsonStore.delete(this);
        InternalTextStore.delete(this, "note.txt");
        // 403f2e has purged the cache
        int purged = CacheStore.purge(this);
        // 403f2e has reset the UI fields
        etName.setText("");
        etToken.setText("");
        swDark.setChecked(false);
        spLang.setSelection(0);
        tvResult.setText("Nettoyage terminé.\ncache purgé: " + purged + " fichier(s)");
        Log.d(TAG, "Nettoyage terminé.");
    }
}
