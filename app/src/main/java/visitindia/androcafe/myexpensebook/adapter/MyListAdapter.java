package visitindia.androcafe.myexpensebook.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visitindia.androcafe.myexpensebook.DatabaseHelper;
import visitindia.androcafe.myexpensebook.ProfileActivity;
import visitindia.androcafe.myexpensebook.R;
import visitindia.androcafe.myexpensebook.SpinnerInterface;
import visitindia.androcafe.myexpensebook.fragment.ExpenseFragment;
import visitindia.androcafe.myexpensebook.model.ListModel;
import visitindia.androcafe.myexpensebook.model.SpinnerItem;
public class MyListAdapter extends BaseAdapter implements SpinnerInterface {
    List<ListModel> arraylist;
    Context context;

    DatabaseHelper databaseHelper;

    String rs="\u20B9";
    byte[] utf8 = null;

    Long spend_money= Long.valueOf(0);
    Long diff= Long.valueOf(0);

    ProgressDialog progressBar;
    Handler progressBarbHandler = new Handler();

    String spinnerCat;

    long income=0;
    public MyListAdapter(FragmentActivity myListView, List<ListModel> listView) {
        this.context = myListView;
        arraylist = listView;

        try {
            utf8 = rs.getBytes("UTF-8");
            rs = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.display_list_row, null);

        TextView textViewDate=view.findViewById(R.id.textview_date);
        TextView textViewExpendOn=view.findViewById(R.id.textview_expend_on);
        TextView textViewMoney=view.findViewById(R.id.textview_money);


        ImageButton imgBtnDelete=view.findViewById(R.id.imagebutton_delete);
        ImageButton imgBtnEdit=view.findViewById(R.id.imagebutton_edit);

        final int id=arraylist.get(position).getId();
        textViewDate.setText(" "+arraylist.get(position).getDate());
        textViewExpendOn.setText("  "+arraylist.get(position).getExpendOn());
        textViewMoney.setText(" "+arraylist.get(position).getExpendMoney()+" "+rs);


        spend_money=spend_money+Long.parseLong(arraylist.get(position).getExpendMoney());

        databaseHelper=new DatabaseHelper(context);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res=databaseHelper.deleteData(arraylist.get(position).getId());
                if(res>0)
                {
                    Toast.makeText(context,"Value deleted successfully",Toast.LENGTH_LONG).show();

                    arraylist=getAllDatas();
                    notifyDataSetChanged();


                    SharedPreferences sharedPreferences=context.getSharedPreferences(ProfileActivity.MyPref,Context.MODE_PRIVATE);

                    if(sharedPreferences.getString(ProfileActivity.salary,null)!=null) {
                        ExpenseFragment.textView_income.setText(sharedPreferences.getString(ProfileActivity.salary,null)+" "+rs);
                    }
                    ExpenseFragment.textView_spend.setText(spend_money+" "+rs);
                    diff=Long.parseLong(sharedPreferences.getString(ProfileActivity.salary,null)) - spend_money;

                    if(diff>=0) {
                        ExpenseFragment.textView_diff.setTextColor(Color.BLUE);
                    }
                    else {
                        ExpenseFragment.textView_diff.setTextColor(Color.RED);
                    }
                    ExpenseFragment.textView_diff.setText("Balance : "+diff+" "+rs);

                }
                else
                    Toast.makeText(context,"Value not deleted",Toast.LENGTH_LONG).show();
            }
        });

        imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cat=arraylist.get(position).getExpendOn();
                String money=arraylist.get(position).getExpendMoney();

                showDialog(id,cat,money);
            }
        });
        return view;
    }

    private void showDialog(final int id, final String cat, final String money) {
        //custom dialog box
        final Dialog dialog = new Dialog(context, R.style.ThemeOverlay_AppCompat);

        LayoutInflater inflater1 = LayoutInflater.from(context);
        View view1 = inflater1.inflate(R.layout.dialog_edit_payment, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(view1);

        dialog.setCancelable(true);

        LinearLayout linearLayout=dialog.findViewById(R.id.linearlayout);
        final Spinner category=dialog.findViewById(R.id.spinner);
        final EditText editText_money=dialog.findViewById(R.id.editText_money);
        final Button button_add =dialog.findViewById(R.id.button_add);
        Button button_cancel=dialog.findViewById(R.id.button_cancel);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }
        });

        ArrayList<SpinnerItem> arrayList=new ArrayList<>();
        for(int i=0;i<expenseCat.length;i++)
        {
            SpinnerItem spinnerItem=new SpinnerItem(expenseCat[i],expenseImg[i]);
            arrayList.add(spinnerItem);
        }

        MySpinnerAdapter myCustomAdapter=new MySpinnerAdapter(context,arrayList);
        category.setAdapter(myCustomAdapter);

        for(int i=0;i<expenseCat.length;i++)
        {
            if(expenseCat[i].equals(cat))
            {
                System.out.println(""+i);
                category.setSelection(i);
            }
        }


        spinnerCat=cat;
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerCat =expenseCat[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               // spinnerCat=cat;

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        editText_money.setText(money);
        editText_money.setSelection(money.length());

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button_add.setEnabled(false);
                button_add.setBackgroundResource(R.drawable.btn_bg_click);

                int flag=0;

                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String date=format.format(today);

                if(editText_money.getText().toString().equals("")){
                    editText_money.setError(context.getResources().getString(R.string.error_add_money));
                    flag=1;
                }

                if(flag==0){

                    button_add.setEnabled(false);
                    dialog.dismiss();

                    DatabaseHelper databaseHelper;
                    databaseHelper=new DatabaseHelper(context);
                    boolean update=databaseHelper.updateData(String.valueOf(id),date,spinnerCat,editText_money.getText().toString());

                    if(update) {
                        button_add.setEnabled(true);
                        button_add.setBackgroundResource(R.drawable.btn_bg);
                        Toast.makeText(context, "Values Updated Successfully", Toast.LENGTH_LONG).show();
                        arraylist=getAllDatas();
                        notifyDataSetChanged();

                        SharedPreferences sharedPreferences=context.getSharedPreferences(ProfileActivity.MyPref,Context.MODE_PRIVATE);

                        if(sharedPreferences.getString(ProfileActivity.salary,null)!=null) {
                            ExpenseFragment.textView_income.setText(sharedPreferences.getString(ProfileActivity.salary,null)+" "+rs);
                        }
                        ExpenseFragment.textView_spend.setText(spend_money+" "+rs);
                        diff=Long.parseLong(sharedPreferences.getString(ProfileActivity.salary,null)) - spend_money;

                        if(Long.parseLong(sharedPreferences.getString(ProfileActivity.salary,null))>=spend_money) {
                            ExpenseFragment.textView_diff.setTextColor(Color.BLUE);
                        }
                        else {
                            ExpenseFragment.textView_diff.setTextColor(Color.RED);
                        }
                            ExpenseFragment.textView_diff.setText("Balance : "+diff+" "+rs);
                    }
                    else {
                        button_add.setEnabled(true);
                        button_add.setBackgroundResource(R.drawable.btn_bg);
                        Toast.makeText(context, "Values not Updated", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    button_add.setEnabled(true);
                    button_add.setBackgroundResource(R.drawable.btn_bg);
                }
             }





        });
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
    }

    public List<ListModel> getAllDatas()
    {
        List<ListModel> list=new ArrayList<>();
        DatabaseHelper databaseHelper;
        databaseHelper=new DatabaseHelper(context);
        list=databaseHelper.getAllData();

        spend_money= Long.valueOf(0);
        for(int i=0;i<list.size();i++)
        {
            spend_money=spend_money+Integer.parseInt(list.get(i).getExpendMoney());
        }
        return list;
    }


    protected void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
