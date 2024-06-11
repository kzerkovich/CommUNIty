package com.kzerk.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView eventListView;
    private Map<String, List<String>> eventsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        eventListView = findViewById(R.id.eventListView);

        // Инициализация Map
        eventsMap = new HashMap<>();

        // Обработчик выбора даты
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                showEventsForDate(selectedDate);
            }
        });

        ImageButton button_settings = (ImageButton) findViewById(R.id.settings_profile);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Profile.class);
                view.getContext().startActivity(intent);
            }
        });

        ImageButton button_list = (ImageButton) findViewById(R.id.list_button);
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
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
        addEvent("2024-4-12", "Дата: 12 апреля\nМероприятие: «Космическая вечеринка»\nОрганизатор: КНиИТ\nВремя: 17:00");
        addEvent("2024-4-12", "Дата: 12 апреля\nМероприятие: Забег на Кубок Ректора\nОрганизатор: ИФКиС\nВремя: 18:00");
        addEvent("2024-4-13", "Дата: 13 апреля\nМероприятие: День Открытых Лабораторий\nОрганизатор: Институт Физики\nВремя: 15:00");
        addEvent("2024-4-14", "Дата: 14 апреля\nМероприятие: День Открытых Дверей\nОрганизатор: Биологический факультет\nВремя: 12:00");
        addEvent("2024-4-19", "Дата: 19 апреля\nМероприятие: Киноклуб\nОрганизатор: ф-т Иностранных языков\nВремя: 15:40");
    }

    private void showEventsForDate(String date) {
        List<String> events = eventsMap.get(date);
        if (events != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
            eventListView.setAdapter(adapter);
        } else {
            eventListView.setAdapter(null);
            // Если для выбранной даты нет событий, показываем сообщение
            Toast.makeText(this, "Нет событий для выбранной даты", Toast.LENGTH_SHORT).show();
        }
    }

    // Метод для добавления события для определенной даты
    private void addEvent(String date, String event) {
        List<String> events = eventsMap.get(date);
        if (events == null) {
            events = new ArrayList<>();
            eventsMap.put(date, events);
        }
        events.add(event);
    }
}