package com.example.slova3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSlov extends AppCompatActivity {

    ListView listView;
    static public ArrayList<SlovaClass> arrayList= new ArrayList<>();
    static public ArrayList<SlovaClass> arrayListCheck= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_slov);
        listView = findViewById(R.id.ListSlova);
        AdaptorSlova adaptorSlova;


        int j=0;

        if(arrayList.size() ==0 )
        {

            for (int i=0;i<MainActivity.SlovaEndNoEditing.size();i++)
            {
                j++;
                arrayList.add(new SlovaClass(j,MainActivity.SlovaEndNoEditing.get(i),MainActivity.SlovaRusNoEditing.get(i),false));
            }
            adaptorSlova=new AdaptorSlova(this,R.layout.list_item,arrayList);
        }

        else
        {
            arrayListCheck.clear();
            int count = arrayList.size();
            for (int i=0;i<count;i++)
            {
                j++;
                arrayListCheck.add(new SlovaClass(j,MainActivity.SlovaEndNoEditing.get(i),MainActivity.SlovaRusNoEditing.get(i),arrayList.get(i).check));

            }
            adaptorSlova=new AdaptorSlova(this,R.layout.list_item,arrayListCheck);
        }

        listView.setAdapter(adaptorSlova);


    }

    public void clickSaveSlov(View view)
    {
        arrayListCheck.clear();
        int j=0;
        int count = arrayList.size();
        for (int i=0;i<count;i++)
        {
            j++;
            arrayListCheck.add(new SlovaClass(j,MainActivity.SlovaEndNoEditing.get(i),MainActivity.SlovaRusNoEditing.get(i),arrayList.get(i).check));

        }

        Toast toast = Toast.makeText(getApplicationContext(), "Типо да", Toast.LENGTH_SHORT);
        toast.show();
    }
}