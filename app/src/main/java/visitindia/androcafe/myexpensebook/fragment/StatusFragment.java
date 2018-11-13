package visitindia.androcafe.myexpensebook.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import visitindia.androcafe.vj.DatabaseHelper;
import visitindia.androcafe.vj.ProfileActivity;
import visitindia.androcafe.vj.R;
import visitindia.androcafe.vj.SpinnerInterface;
import visitindia.androcafe.vj.adapter.MyExpandabelAdapter;
import visitindia.androcafe.vj.model.ListModel;
import visitindia.androcafe.vj.model.StatusDataModel;

public class StatusFragment extends Fragment implements SpinnerInterface{

    DatabaseHelper databaseHelper;

    List<ListModel> arraylist;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    MyExpandabelAdapter myExpandabelAdapter;

    int c=0;

    String rs="\u20B9";
    byte[] utf8 = null;

    long total=0;

    public static StatusFragment newInstance() {
        
        Bundle args = new Bundle();
        
        StatusFragment fragment = new StatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_status,null);

        ExpandableListView listView=view.findViewById(R.id.listview);


        try {
            utf8 = rs.getBytes("UTF-8");
            rs = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        databaseHelper=new DatabaseHelper(getActivity());

        arraylist=databaseHelper.getAllData();

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date=format.format(today);


        int home=0,fri=0,ele=0,groc=0,med=0,hot=0,edu=0,fit=0,tra=0,shop=0,ins=0,rec=0,ent=0,loan=0,tax=0,other=0;
        for(int i=0;i<arraylist.size();i++)
        {
            if(arraylist.get(i).getExpendOn().equals("Family"))
            {
                home=home+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Friends"))
            {
                fri=fri+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Electricity"))
            {
                ele=ele+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Grocery"))
            {
                groc=groc+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Medical"))
            {
                med=med+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Hotel"))
            {
                hot=hot+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Education"))
            {
                edu=edu+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Fitness"))
            {
                fit=fit+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Transport"))
            {
                tra=tra+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Shopping"))
            {
                shop=shop+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Insurance"))
            {
                ins=ins+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Loan"))
            {
                loan=loan+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Tax"))
            {
                tax=tax+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Other"))
            {
                other=other+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Recharge"))
            {
                rec=rec+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }
            if(arraylist.get(i).getExpendOn().equals("Entertainment"))
            {
                ent=ent+Integer.parseInt(arraylist.get(i).getExpendMoney());
            }

        }

        List<StatusDataModel> list=new ArrayList<>();
        list=databaseHelper.getAllStatusData();


        System.out.println("substring "+date.substring(5,7));
        String months=getMonth(date.substring(5,7));

        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getDate().equals(months+" - "+date.substring(2,4)))
            {
               // months=getMonth(list.get(i).getDate().substring(5,6));
                System.out.println("month "+months);
                databaseHelper.deleteStatusData(months+" - "+date.substring(2,4));
            }
        }


        databaseHelper.insertStatusData(months+" - "+date.substring(2,4),String.valueOf(home),String.valueOf(fri),String.valueOf(ele),String.valueOf(groc),String.valueOf(med),String.valueOf(hot),String.valueOf(edu),String.valueOf(fit),String.valueOf(tra),String.valueOf(shop),String.valueOf(ins),String.valueOf(rec),String.valueOf(ent),String.valueOf(loan),String.valueOf(tax),String.valueOf(other));

        list.clear();
        list=databaseHelper.getAllStatus();

      // preparing list data
        prepareListData(list);

        myExpandabelAdapter=new MyExpandabelAdapter(getActivity(),listDataHeader,listDataChild,list);
        listView.setAdapter(myExpandabelAdapter);
        return view;
    }

    private String getMonth(String substring) {
        String m="";
        if(substring.equals("01"))
        {
            m="jan";
        }
        if(substring.equals("02"))
        {
            m="feb";
        }
        if(substring.equals("03"))
        {
            m="mar";
        }
        if(substring.equals("04"))
        {
            m="apr";
        }
        if(substring.equals("05"))
        {
            m="may";
        }
        if(substring.equals("06"))
        {
            m="jun";
        }
        if(substring.equals("07"))
        {
            m="jul";
        }
        if(substring.equals("08"))
        {
            m="aug";
        }
        if(substring.equals("09"))
        {
            m="sep";
        }
        if(substring.equals("10"))
        {
            m="oct";
        }
        if(substring.equals("11"))
        {
            m="nov";
        }
        if(substring.equals("12"))
        {
            m="dec";
        }
        return m;
    }

    private void prepareListData(List<StatusDataModel> list) {
        listDataHeader = new ArrayList<String>();
        listDataChild= new HashMap<String, List<String>>();

        
        for(int i=0;i<list.size();i++)
        {
            System.out.println("substring "+list.get(i).getDate());
            String months=list.get(i).getDate();

            // Adding child data
            listDataHeader.add(""+months);

            // Adding child data
            List<String> cat = new ArrayList<String>();

            total=0;
            if(list.get(i).getHome().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getHome());
                cat.add("Family : " + list.get(i).getHome() + " " + rs);
            }
            if(list.get(i).getFriends().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getFriends());
                cat.add("Friends : " + list.get(i).getFriends() + " " + rs);
            }

            if(list.get(i).getEle().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getEle());
                cat.add("Electricity : " + list.get(i).getEle() + " " + rs);
            }

            if(list.get(i).getGroc().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getGroc());
                cat.add("Grocery : " + list.get(i).getGroc() + " " + rs);
            }

            if(list.get(i).getMed().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getMed());
                cat.add("Medical : " + list.get(i).getMed() + " " + rs);
            }

            if(list.get(i).getHot().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getHot());
                cat.add("Hotel : " + list.get(i).getHot() + " " + rs);
            }

            if(list.get(i).getEdu().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getEdu());
                cat.add("Education : " + list.get(i).getEdu());
            }

            if(list.get(i).getFit().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getFit());
                cat.add("Fitness : " + list.get(i).getFit() + " " + rs);
            }

            if(list.get(i).getTra().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getTra());
                cat.add("Transport : "+list.get(i).getTra()+" "+rs);
            }


            if(list.get(i).getShop().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getShop());
                cat.add("Shopping : "+list.get(i).getShop()+" "+rs);
            }

            if(list.get(i).getIns().equals("0"))
            {}
            else {
                total=total+Long.parseLong(list.get(i).getIns());
                cat.add("Insurance : " + list.get(i).getIns() + " " + rs);
            }

            if(list.get(i).getRec().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getRec());
                cat.add("Recharge : "+list.get(i).getRec()+" "+rs);
            }


            if(list.get(i).getEnt().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getEnt());
                cat.add("Entertainment : "+list.get(i).getEnt()+" "+rs);
            }


            if(list.get(i).getLoan().equals("0"))
            {}
            else
            {
                total=total+Long.parseLong(list.get(i).getLoan());
                cat.add("Loan : "+list.get(i).getLoan()+" "+rs);
            }


            if(list.get(i).getTax().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getTax());
                cat.add("Tax : "+list.get(i).getTax()+" "+rs);
            }


            if(list.get(i).getOthe().equals("0"))
            {}
            else{
                total=total+Long.parseLong(list.get(i).getOthe());
                cat.add("Other : "+list.get(i).getOthe()+" "+rs);
            }
            SharedPreferences sharedPreferences=getContext().getSharedPreferences(ProfileActivity.MyPref, Context.MODE_PRIVATE);
            cat.add("Income : "+sharedPreferences.getString(ProfileActivity.salary,null)+" "+rs);
            cat.add("Spend : "+total+" "+rs);
            listDataChild.put(months, cat);
        }

    }

}
