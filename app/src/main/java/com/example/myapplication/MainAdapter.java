package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends BaseExpandableListAdapter {

    ArrayList<String> listGroup;
    HashMap<String, ArrayList<String>> listChild;

    public MainAdapter(ArrayList<String> listGroup,HashMap<String, ArrayList<String>> listChild){
        this.listGroup=listGroup;
        this.listChild=listChild;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listChild.get(listGroup.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChild.get(listGroup.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_expandable_list_item_1,viewGroup, false);
        TextView textView = view.findViewById(android.R.id.text1);
        String sGroup = String.valueOf(getGroup(i));
        textView.setText(sGroup);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLUE);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_selectable_list_item,viewGroup, false);
        TextView textView = view.findViewById(android.R.id.text1);
        String sChild = String.valueOf(getChild(i,i1));
        textView.setText(sChild);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(viewGroup.getContext(),sChild,Toast.LENGTH_SHORT).show();
;           }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
