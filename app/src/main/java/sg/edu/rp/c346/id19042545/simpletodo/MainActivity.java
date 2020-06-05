package sg.edu.rp.c346.id19042545.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd,btnClear,btnDelete;
    ListView lvTask;
    Spinner sp;
    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        etTask = findViewById(R.id.editTextTask);
        lvTask = findViewById(R.id.listViewTask);
        sp= findViewById(R.id.spinner);
        alTask = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,alTask);

        lvTask.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTask.getText().toString().trim().length() > 0){
                    alTask.add(etTask.getText().toString().trim());
                    etTask.getText().clear();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this,"Please enter a task",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                adapter.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check=false;
                if(etTask.getText().toString().trim().length() > 0){
                    check=true;
                }
            if(!alTask.isEmpty() && check==true){
                int pos =Integer.parseInt(etTask.getText().toString());
                if(pos>alTask.size()-1||pos<0){
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_LONG).show();
                }else{
                    alTask.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            }else if(check==false){
                Toast.makeText(MainActivity.this,"Please enter a index",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_LONG).show();
            }
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint(R.string.type_a_new_task);
                        etTask.setInputType(InputType.TYPE_NULL);
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTask.setHint(R.string.type_a_new_index);
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
