package com.android.settings.alex;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.settings.alex.Halo;
import com.android.settings.alex.Graphics;
import com.android.settings.R;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_main_menu);

        List valueList = new ArrayList<String>();

        valueList.add("Halo");
        valueList.add("Graphics");

        listView=(ListView)findViewById(R.id.lvAlexMenu);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listView.getAdapter().getItem(i).toString().equals("Halo")) {
                    getFragmentManager().beginTransaction().replace(android.R.id.content, new Halo()).commit();
                } else if (listView.getAdapter().getItem(i).toString().equals("Graphics")) {
                    getFragmentManager().beginTransaction().replace(android.R.id.content, new Graphics()).commit();
                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't find associated Activity!", Toast.LENGTH_SHORT).show();
                }
                listView.setVisibility(View.INVISIBLE);
            }
        });

    }

}
