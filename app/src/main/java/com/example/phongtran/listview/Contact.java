package com.example.phongtran.listview;

/**
 * Created by phong tran on 11/5/2017.
 */

public class Contact {
    public String hoTen;
    public int sdt;
    public Contact(String ht, int so)
    {
        this.hoTen=ht;
        this.sdt=so;
    }
    public String getHoTen()
    {
        return hoTen;
    }
    public int getSdt()
    {
        return sdt;
    }
    public void setHoTen(String hoTen)
    {
        this.hoTen=hoTen;
    }
    public void setSdt(int sdt)
    {
        this.sdt=sdt;
    }
    @Override
    public String toString()
    {
        return hoTen+ "-" + sdt;
    }
}
