package com.example.slova3;

public class SlovaClass
{
    int idSlova;
    String  SlovaEnglish;
    String  SlovaRussun;
    Boolean check;

    public SlovaClass(int id,String slovaEnglish, String slovaRussun, Boolean check)
    {
        idSlova=id;
        SlovaEnglish = slovaEnglish;
        SlovaRussun = slovaRussun;
        this.check = check;
    }

    public int getId() {
        return idSlova;
    }

    public String getSlovaEnglish() {
        return SlovaEnglish;
    }

    public String getSlovaRussun() {
        return SlovaRussun;
    }

    public Boolean getCheck() {
        return check;
    }
}
