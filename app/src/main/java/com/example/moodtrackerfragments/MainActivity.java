package com.example.moodtrackerfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                String name = s.toString();

                if (currentFragment instanceof HappyFragment) {
                    ((HappyFragment) currentFragment).updateName(name);
                } else if (currentFragment instanceof NeutralFragment) {
                    ((NeutralFragment) currentFragment).updateName(name);
                } else if (currentFragment instanceof SadFragment) {
                    ((SadFragment) currentFragment).updateName(name);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // הגדרת מאזין ללחיצות בתפריט
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            // בדיקה איזה כפתור נלחץ
            if (id == R.id.nav_happy) {
                selectedFragment = new HappyFragment();
                bottomNav.setBackgroundColor(Color.rgb(250, 234, 5));
            } else if (id == R.id.nav_neutral) {
                selectedFragment = new NeutralFragment();
                bottomNav.setBackgroundColor(Color.rgb(166, 168, 173));
            } else if (id == R.id.nav_sad) {
                selectedFragment = new SadFragment();
                bottomNav.setBackgroundColor(Color.rgb(66, 115, 237));
            }

            // החלפת הפרגמנט בפועל
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // הצגת פרגמנט ברירת מחדל בכניסה לאפליקציה
        if (savedInstanceState == null) {
            bottomNav.setBackgroundColor(Color.rgb(250, 234, 5));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HappyFragment())
                    .commit();
        }
    }
}