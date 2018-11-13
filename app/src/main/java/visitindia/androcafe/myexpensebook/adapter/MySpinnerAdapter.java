package visitindia.androcafe.myexpensebook.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import visitindia.androcafe.myexpensebook.R;
import visitindia.androcafe.myexpensebook.model.SpinnerItem;


public class MySpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<SpinnerItem> arrayList;

    public MySpinnerAdapter(FragmentActivity addFragment, ArrayList<SpinnerItem> arrayList) {
        context=addFragment;
        this.arrayList=arrayList;
    }

    public MySpinnerAdapter(Context context, ArrayList<SpinnerItem> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.spinner_layout,null);
        }

        TextView textView=view.findViewById(R.id.textview);
        ImageView imageView=view.findViewById(R.id.imageview);


        textView.setText(arrayList.get(i).getList_name());
        imageView.setImageResource(arrayList.get(i).getList_image());

        return view;
    }
}
