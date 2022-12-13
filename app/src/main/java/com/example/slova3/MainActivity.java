package com.example.slova3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    LinearLayout screen;
    EditText inputText;
    TextView textSlova,textPoz;
    Button butProverka;
    Switch switchLanguage;
    ObjectAnimator colorFade;

    public static ArrayList<String> SlovaEndNoEditing=new ArrayList<>(); //изначальное
    public static ArrayList<String> SlovaRusNoEditing=new ArrayList<>(); //изначальное

    public static ArrayList<String> SlovaEnd=new ArrayList<>(); //будет редактирование
    public static ArrayList<String> SlovaRus=new ArrayList<>(); //будет редактирование

    int NomerSlova=0;
    public static boolean Language;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlovaEndNoEditing.clear();
        SlovaRusNoEditing.clear();
        butProverka=findViewById(R.id.button);
        screen = (LinearLayout) findViewById(R.id.layoutScreen);
        inputText=findViewById(R.id.editTextInput);
        textSlova = findViewById(R.id.textViewSlova);
        textPoz=findViewById(R.id.textViewPodzcasca);

        BufferedReader reader=null;
        try
        {
            reader =new BufferedReader(new InputStreamReader(getAssets().open("SLOVA")));

            String receiveString="";

            while ( (receiveString = reader.readLine()) != null )
            {
                String []split = receiveString.split(" - ");
                SlovaEnd.add(split[0]);
                SlovaRus.add(split[1]);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        SlovaEndNoEditing.addAll(SlovaEnd);
        SlovaRusNoEditing.addAll(SlovaRus);

        switchLanguage = findViewById(R.id.switchLanguage);
        switchLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    Language=true;
                    Text(Language);
                }
                else
                {
                    Language=false;
                    Text(Language);
                }
            }
        });

        Text(Language);
    }

    public void clickProverka(View view)
    {
        Input(Language);
        NomerSlova++;
        Text(Language);

        inputText.setText("");
    }

    public void clickCancel(View viev)
    {
        if(NomerSlova >0)
        {
            NomerSlova--;
            Text(Language);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Не надо", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    void Input(boolean language)//Смена текста зависимости от swithc
    {

        String slovonaProverky;
        if(language==true)
        {
             slovonaProverky=SlovaEnd.get(NomerSlova);
        }
        else
        {
             slovonaProverky=SlovaRus.get(NomerSlova);
        }
        String slovoInput=inputText.getText().toString();

        if(slovoInput.equals(slovonaProverky))
        {

             colorFade = ObjectAnimator.ofObject(screen, "backgroundColor", new ArgbEvaluator(), Color.argb(255,124,252,0), 0xFFB7ABC3);

        }
        else
        {
            colorFade = ObjectAnimator.ofObject(screen, "backgroundColor", new ArgbEvaluator(), Color.argb(255,220,20,60), 0xFFB7ABC3);
        }

        colorFade.setDuration(1500);
        colorFade.start();

    }
    void Text(boolean language)//Проверка соотсвествие введенного
    {
        if(SlovaRus.size()-1<NomerSlova)
        {
            NomerSlova=0;
            Toast toast = Toast.makeText(getApplicationContext(), "Давай по новой", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(language==false)
        {

            textSlova.setText(SlovaEnd.get(NomerSlova));
            textPoz.setText(SlovaRus.get(NomerSlova));
        }
        else
        {
            textSlova.setText(SlovaRus.get(NomerSlova));
            textPoz.setText(SlovaEnd.get(NomerSlova));
        }



    }
    public void clickVisible(View view)
    {
        if(textPoz.getVisibility()==View.VISIBLE)
        {
            textPoz.setVisibility(View.INVISIBLE);
        }
        else
        {
            textPoz.setVisibility(View.VISIBLE);
        }

    }

    public void clickPerehod(View view)
    {
        Intent intent = new Intent(view.getContext(), ListSlov.class);
        view.getContext().startActivity(intent);
    }

    public void clickCmenaSlov(View view)
    {
        if(ListSlov.arrayListCheck.size() != 0)
        {
            SlovaEnd.clear();
            SlovaRus.clear();
            for (int i=0;i<ListSlov.arrayListCheck.size();i++)
            {
                if(ListSlov.arrayList.get(i).check==true)
                {
                    SlovaEnd.add(ListSlov.arrayListCheck.get(i).SlovaEnglish);
                    SlovaRus.add(ListSlov.arrayListCheck.get(i).SlovaRussun);
                }

            }
            Text(Language);
            colorFade = ObjectAnimator.ofObject(screen, "backgroundColor", new ArgbEvaluator(), Color.argb(255,231,188,235), 0xFFB7ABC3);
            colorFade.setDuration(1500);
            colorFade.start();
        }


    }
}