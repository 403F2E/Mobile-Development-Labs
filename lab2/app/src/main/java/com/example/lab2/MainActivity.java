package com.example.lab2;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
// 403f2e has created the main activity class
public class MainActivity extends AppCompatActivity {
    // 403f2e has created UI components
    private EditText surfaceInput, piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 403f2e has done layout setting
        setContentView(R.layout.activity_main);
        // 403f2e has done view mapping from layout
        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);
        // 403f2e has done click listener assignment
        findViewById(R.id.button_calcul).setOnClickListener(v -> calculer());
    }
    // 403f2e has created the logic for tax calculation
    private void calculer() {
        // 403f2e has done input parsing
        double surface = Double.parseDouble(surfaceInput.getText().toString());
        int pieces = Integer.parseInt(piecesInput.getText().toString());
        boolean piscine = piscineCheckbox.isChecked();
        // 403f2e has done calculation steps
        double impotBase = surface * 2;
        double supplement = pieces * 50 + (piscine ? 100 : 0);
        double total = impotBase + supplement;
        // 403f2e has done result display
        resultView.setText("Impôt total : " + total + " DH");
    }
}
