package visitindia.androcafe.myexpensebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class ProfileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    public static String MyPref="mypref";
    public static String name="username";
    public static String salary="Salary";

    EditText editText_name,editText_salary;
    CheckBox checkBox;

    Button btnSave,btnCancel;

    String rs="\u20B9";
    byte[] utf8 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            utf8 = rs.getBytes("UTF-8");
            rs = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        editText_name=findViewById(R.id.editText_name);
        editText_salary=findViewById(R.id.editText_salary);

        btnSave=findViewById(R.id.button_save);
        btnCancel=findViewById(R.id.button_cancel);

        checkBox=findViewById(R.id.checkbox);

        sharedPreferences=getSharedPreferences(MyPref,MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        if(sharedPreferences.getString(name,"").equals(""))
        {
            editText_name.setText("");
        }
        else
        {
            editText_name.setText(sharedPreferences.getString(name,null));
            editText_name.setSelection(sharedPreferences.getString(name,null).length());
        }

        if(sharedPreferences.getString(salary,"").equals(""))
        {
            editText_salary.setText("");
        }
        else
        {
            editText_salary.setText(""+sharedPreferences.getString(salary,null));
            editText_salary.setSelection(sharedPreferences.getString(salary,null).length());
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(name,""+editText_name.getText().toString());
                editor.putString(salary,""+editText_salary.getText().toString());

                if(checkBox.isChecked())
                {
                    clearalldata();
                }
                Toast.makeText(ProfileActivity.this,"Your profile is saved successfully",Toast.LENGTH_LONG).show();
                editor.commit();
            }
        });
    }

    private void clearalldata() {
        DatabaseHelper databaseHelper=new DatabaseHelper(ProfileActivity.this);
        databaseHelper.clearAllata();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ProfileActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
