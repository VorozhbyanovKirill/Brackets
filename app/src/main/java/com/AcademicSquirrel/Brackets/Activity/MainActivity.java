/**
 * Author: Kirill Vorozhbyanov
 * E-mail: VorozhbyanovKirill@gmail.com
 */

package com.AcademicSquirrel.Brackets.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.AcademicSquirrel.Brackets.Brackets;
import com.AcademicSquirrel.Brackets.DictionaryBrackets;
import com.AcademicSquirrel.Brackets.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Вход в программу.
 * Инициализация и обработка данных программы.
 */

public class MainActivity extends AppCompatActivity {
    // Ключ для сохранения массива с логами.
    private final String KEY_LOGS = "LOGS";
    // Максимальное количество элементов массива с логами.
    private final Integer MAX_SIZE_LOGS = 512;
    // Ключ для заполнения заголовка списка.
    private final String KEY_HEADER = "HEADER";
    // Ключ для заполнения тела списка.
    private final String KEY_BODY = "BODY";

    // Список логов.
    private ArrayList<HashMap<String, String>> logs;
    private SimpleAdapter adapter;

    private Brackets brackets;
    private ListView lvLogs;
    private EditText etMathExpression;

    private View.OnClickListener addMathExpression = new View.OnClickListener() {
        public void onClick(View v) {
            if (logs.size() >= MAX_SIZE_LOGS)
                logs.clear();

            String mathExpression = etMathExpression.getText().toString();
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_HEADER, "VALID - " + brackets.checkBrackets(mathExpression).toString().toUpperCase());
            map.put(KEY_BODY, mathExpression);
            logs.add(map);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            logs = (ArrayList<HashMap<String, String>>) savedInstanceState.get(KEY_LOGS);
        else
            logs = new ArrayList<HashMap<String, String>>();

        lvLogs = (ListView) findViewById(R.id.lvLogs);

        adapter = new SimpleAdapter(this, logs, android.R.layout.simple_list_item_2,
                new String[]{KEY_HEADER, KEY_BODY},
                new int[]{android.R.id.text1, android.R.id.text2});

        lvLogs.setAdapter(adapter);
        etMathExpression = (EditText) findViewById(R.id.etMathExpression);
        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(addMathExpression);

        // Создаем словарь скобок.
        ArrayList<DictionaryBrackets> dictionaryBrackets = new ArrayList<DictionaryBrackets>();
        dictionaryBrackets.add(new DictionaryBrackets('(', ')'));
        dictionaryBrackets.add(new DictionaryBrackets('{', '}'));
        dictionaryBrackets.add(new DictionaryBrackets('[', ']'));
        dictionaryBrackets.add(new DictionaryBrackets('<', '>'));

        brackets = new Brackets(dictionaryBrackets);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_LOGS, logs);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_main) {
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Диалоговое окно.
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.title_dialog_info)
                .setMessage(R.string.text_dialog_info)
                .setCancelable(false)
                .setNegativeButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
