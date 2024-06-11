package com.kzerk.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_profile);

        ImageButton button_list = (ImageButton) findViewById(R.id.list_button);
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        ImageButton button_calendar = (ImageButton) findViewById(R.id.calendar_button);
        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Calendar.class);
                view.getContext().startActivity(intent);
            }
        });

        ImageButton button_org = (ImageButton) findViewById(R.id.org_button);
        button_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Organization.class);
                view.getContext().startActivity(intent);
            }
        });
        EditText etUsername;
        etUsername = (EditText) findViewById(R.id.first);
        etUsername.setHint("Имя");
        EditText etUsername1;
        etUsername1 = (EditText) findViewById(R.id.second);
        etUsername1.setHint("Фамилия");
        EditText etUsername2;
        etUsername2 = (EditText) findViewById(R.id.ot);
        etUsername2.setHint("Отчество");
        EditText etUsername3;
        etUsername3 = (EditText) findViewById(R.id.uni);
        etUsername3.setHint("ВУЗ");
        EditText etUsername4;
        etUsername4 = (EditText) findViewById(R.id.fac);
        etUsername4.setHint("Факультет");
        EditText etUsername5;
        etUsername4 = (EditText) findViewById(R.id.interes);
        etUsername4.setHint("Интересы (теги)");

        String[] tmp_arr = getResources().getStringArray(R.array.test_int);
        ListView listView = (ListView) findViewById(R.id.testtest);

        ArrayAdapter<String> adapter_r = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp_arr);
        listView.setAdapter(adapter_r);
    }
}