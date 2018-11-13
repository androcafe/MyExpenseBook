package visitindia.androcafe.myexpensebook.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import visitindia.androcafe.vj.R;
import visitindia.androcafe.vj.model.StatusDataModel;

public class MyExpandabelAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> listDataChild;
    List<StatusDataModel> arraylist=new ArrayList<>();

    int a=0;
    int c=0;
    public MyExpandabelAdapter(FragmentActivity activity, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, List<StatusDataModel> list) {
        this.context = activity;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
        this.arraylist=list;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listDataChild.get(this.listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listDataChild.get(this.listDataHeader.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        System.out.println("head "+headerTitle);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_list, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textview_month);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        final String childText = (String) getChild(i, i1);
        System.out.println(" "+childText);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_list, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.textView_cat);

        if(childText.contains("Income"))
            txtListChild.setBackgroundResource(R.color.colorOlive);
        else if(childText.contains("Spend"))
            txtListChild.setBackgroundResource(R.color.colorlightYellow);
        else
            txtListChild.setBackgroundResource(R.color.colorWhite);

        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
