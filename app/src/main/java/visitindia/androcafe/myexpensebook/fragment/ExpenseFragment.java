package visitindia.androcafe.myexpensebook.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visitindia.androcafe.myexpensebook.DatabaseHelper;
import visitindia.androcafe.myexpensebook.ProfileActivity;
import visitindia.androcafe.myexpensebook.R;
import visitindia.androcafe.myexpensebook.adapter.MyListAdapter;
import visitindia.androcafe.myexpensebook.model.ListModel;


public class ExpenseFragment extends Fragment{
    ListView listView;
    DatabaseHelper databaseHelper;
    MyListAdapter myListAdapter;

    List<ListModel> arrayList;

    public static TextView textView_income,textView_spend,textView_diff;

    String rs="\u20B9";
    byte[] utf8 = null;

    public static ExpenseFragment newInstance() {
        Bundle args = new Bundle();
        ExpenseFragment fragment = new ExpenseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, null);

        hideKeyboard(view);

        listView=view.findViewById(R.id.listview);

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date=format.format(today);

        checkForMonth(date);

        SharedPreferences sharedPreferences=getContext().getSharedPreferences(ProfileActivity.MyPref, Context.MODE_PRIVATE);

        databaseHelper=new DatabaseHelper(getActivity());
        arrayList=databaseHelper.getAllData();
        myListAdapter=new MyListAdapter(getActivity(),arrayList);
        listView.setAdapter(myListAdapter);

        try {
            utf8 = rs.getBytes("UTF-8");
            rs = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        long spend=0;
        for(int i=0;i<arrayList.size();i++)
        {
            spend=spend+Long.parseLong(arrayList.get(i).getExpendMoney());
        }
        textView_income=view.findViewById(R.id.textview_credit);
        textView_spend=view.findViewById(R.id.textview_debit);
        textView_diff=view.findViewById(R.id.textview_diff);


        String income=sharedPreferences.getString(ProfileActivity.salary,null);
        textView_income.setText("Income : "+income+" "+rs);

        if(income.equals(""))
        {
            textView_spend.setTextColor(Color.GREEN);
        }
        else
        {
            if(Long.parseLong(income)>=spend)
            {
                long diff=Long.parseLong(income) - spend;
                textView_diff.setText("Balance : "+diff+" "+rs);
                textView_diff.setTextColor(Color.BLUE);
            }
            else {
                textView_diff.setTextColor(Color.RED);
                long diff=Long.parseLong(income) - spend;
                textView_diff.setText("Balance : "+diff+" "+rs);
            }

        }

        textView_spend.setText("Spend : "+spend+" "+rs);


        return view;
    }

    private void checkForMonth(String date) {
        String months=date.substring(5,7);

        List<ListModel> list=new ArrayList<>();
        databaseHelper=new DatabaseHelper(getActivity());
        list=databaseHelper.getAllData();

        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getDate().substring(5,7).equals(months))
            {

            }
            else
            {
                databaseHelper.deleteallprevmonthdata(list.get(i).getId());
            }
        }

    }



    protected void hideKeyboard(View view)
    {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
