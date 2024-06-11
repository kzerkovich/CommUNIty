package com.kzerk.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final String[] test = new String[]{"12.04.2024, 17:00, ф-т КНиИТ\n#космос #день_космонавтики #космическая_вечеринка\n" +
            "«Космическая вечеринка» — это уникальное и захватывающее событие, которое перенесет тебя" +
            "в мир неизведанных галактик и мерцающих звезд!",
            "13.04.2024, 15:00, ин-т Физики\n#день_открытых_лабораторий #институт_физики\n" +
                    "Приглашаем Вас на знакомство с направлением «Биотехнические системы и технологии», " +
                    "профиль: Методы и устройства обработки биосигналов!",
            "14.04.2024, 12:00, Биологический ф-т\n#день_открытых_дверей #биологический_факультет\n" +
                    "Биологический факультет приглашает на День открытых дверей!\n" +
                    "На нем приемная комиссия ответит на самые актуальные вопросы, связанные с поступлением!\n" +
                    "А Сотрудники факультета расскажут о своих инновационных исследованиях!\n" +
                    "Студенты покажут факультет и подарят памятные стикеры!",
            "19.04.2024, 15:40, ф-т Иностранных языков\n#киноклуб #ин_яз_топ\n" +
                    "Любишь смотреть кино? \n" +
                    "Хочешь узнать что-то новое о режиссерах и актерах?\n" +
                    "Тогда приходи в 19 апреля в 15:40 на наш Киноклуб в 208а аудитории!\n" +
                    "После просмотра фильма ты сможешь поделиться своими впечатлениями!",
            "11.04.2024, 15:30, и-т ИиМО\n#история #квиз #туристическое_общество\n" +
                    "Проверь свои знания о России \n" +
                    "В этот четверг, 11 апреля, в 401 аудитории 11 корпуса в 15:30 состоится квиз от туристского общества!",
            "14.04.2024, 18:00, и-т ФКиС\n#спорт #забег #кубок_ректора\n" +
                    "Описание Пробное описание Пробное описание Пробное описание Пробное описание Пробное" +
                    "описание Пробное описание Пробное описание Пробное описание",
            "26.03.2024, 15:00, КНиИТ\nспорт тест слово\nПробное " +
                    "описание Пробное описание Пробное описание Пробное описание Пробное описание Пробное" +
                    "описание Пробное описание Пробное описание Пробное описание",
            "26.03.2024, 15:00, КНиИТ\nспорт тест слово\nПробное " +
                    "описание Пробное описание Пробное описание Пробное описание Пробное описание Пробное" +
                    "описание Пробное описание Пробное описание Пробное описание"
    };

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_main);

        ImageButton button_settings = (ImageButton) findViewById(R.id.settings_profile);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Profile.class);
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

        listView = findViewById(R.id.list_act);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, test);

        listView.setAdapter(adapter);
    }
}