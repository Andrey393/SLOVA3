package com.example.slova3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class AdaptorSlova extends ArrayAdapter<SlovaClass>
{
    private  Context mContex;
    private int mResource;

    public static  List <String> SlovaCheckEng=new ArrayList<>();
    public static  List <String> SlovaCheckRus=new ArrayList<>();

    public AdaptorSlova(@NonNull Context context, int resource, @NonNull ArrayList<SlovaClass> objects)
    {
        super(context, resource, objects);

        this.mContex=context;
        this.mResource=resource;
    }
    int id;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater= LayoutInflater.from(mContex);
        convertView = layoutInflater.inflate((mResource),parent,false);

        TextView txtId=convertView.findViewById(R.id.textViewId);
        TextView txtEng= convertView.findViewById(R.id.txtEnglish);
        TextView txtRus= convertView.findViewById(R.id.txtRussun);
        CheckBox checkBox=convertView.findViewById(R.id.chcLanguage);

        id=getItem(position).getId();
        txtId.setText(Integer.toString(id));
        txtEng.setText(getItem(position).getSlovaEnglish());
        txtRus.setText(getItem(position).getSlovaRussun());
        checkBox.setChecked(getItem(position).getCheck());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)//влажок выбран
                {
                    ListSlov.arrayList.get(position).check=true;
                    SlovaCheckEng.add(getItem(position).getSlovaEnglish());
                    SlovaCheckRus.add(getItem(position).getSlovaRussun());
                }
                else
                {
                    ListSlov.arrayList.get(position).check=false;
                    SlovaCheckEng.remove(getItem(position).getSlovaEnglish());
                    SlovaCheckRus.remove(getItem(position).getSlovaRussun());
                }
            }
        });
        return  convertView;

    }

}
