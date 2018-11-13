package visitindia.androcafe.myexpensebook.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import visitindia.androcafe.myexpensebook.DatabaseHelper;
import visitindia.androcafe.myexpensebook.ProfileActivity;
import visitindia.androcafe.myexpensebook.R;
import visitindia.androcafe.myexpensebook.SpinnerInterface;
import visitindia.androcafe.myexpensebook.adapter.MySpinnerAdapter;
import visitindia.androcafe.myexpensebook.model.SpinnerItem;


public class AddFragment extends Fragment implements SpinnerInterface {


    ArrayList<SpinnerItem> arrayList = new ArrayList<>();

    Spinner spinner;
    EditText editText_money;
    Button button_add;
    LinearLayout linearLayout;

    String category = "";

    Context context;
    DatabaseHelper databaseHelper;

    public static AddFragment newInstance() {

        Bundle args = new Bundle();

        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, null);

        databaseHelper = new DatabaseHelper(getActivity());

        spinner = view.findViewById(R.id.spinner);
        editText_money = view.findViewById(R.id.editText_money);
        button_add = view.findViewById(R.id.button_add);

        linearLayout = view.findViewById(R.id.linearlayout);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }
        });


        for (int i = 0; i < expenseCat.length; i++) {
            SpinnerItem spinnerItem = new SpinnerItem(expenseCat[i], expenseImg[i]);
            arrayList.add(spinnerItem);
        }

        MySpinnerAdapter myCustomAdapter = new MySpinnerAdapter(getActivity(), arrayList);
        spinner.setAdapter(myCustomAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(),"Selected Country is "+arrayList.get(i).getList_name(),Toast.LENGTH_LONG).show();
                category = arrayList.get(i).getList_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                category = "Family";
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_add.setEnabled(false);
                button_add.setBackgroundResource(R.drawable.btn_bg_click);
                String money = editText_money.getText().toString();

                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String date = format.format(today);

                int flag = 0;
                if (money.equals("")) {
                    editText_money.setError(getResources().getString(R.string.error_add_money));
                    flag = 1;
                }

                if (flag == 0) {
                    boolean res = databaseHelper.insertData(date, category, money);
                    if (res) {
                        editText_money.setText("");
                        button_add.setEnabled(true);
                        button_add.setBackgroundResource(R.drawable.btn_bg);
                        Toast.makeText(getActivity(), "Values Inserted Successfully", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    button_add.setEnabled(true);
                    button_add.setBackgroundResource(R.drawable.btn_bg);
                }
                System.out.println(date);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Confirm this fragment has menu items.
        setHasOptionsMenu(true);
    }

    protected void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the fragment menu items.
        inflater.inflate(R.menu.menu_home, menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.action_profile)
        {
            Intent intent=new Intent(getActivity(),ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}