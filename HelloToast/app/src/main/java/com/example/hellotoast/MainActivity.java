package com.example.hellotoast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
// 403f2e has created
public class MainActivity extends AppCompatActivity {
private int count=0;
private TextView textCount;
@Override
protected void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
// 403f2e has done
textCount=findViewById(R.id.text_count);
Button buttonToast=findViewById(R.id.button_toast);
Button buttonCount=findViewById(R.id.button_count);
buttonToast.setOnClickListener(v->{
Toast.makeText(this,"Bonjour !",Toast.LENGTH_SHORT).show();
});
buttonCount.setOnClickListener(v->{
count++;
textCount.setText(String.valueOf(count));
});
}
}