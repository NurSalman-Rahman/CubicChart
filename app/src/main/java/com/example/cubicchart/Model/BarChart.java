package com.example.cubicchart.Model;

public class BarChart
{
    int id;
    String y_data1;
    String y_data2;
    String y_data3;

    public BarChart(int id, String y_data1, String y_data2, String y_data3)
    {
        this.id = id;
        this.y_data1 = y_data1;
        this.y_data2 = y_data2;
        this.y_data3 = y_data3;
    }


    public BarChart(String y_data1, String y_data2, String y_data3)
    {
        this.y_data1 = y_data1;
        this.y_data2 = y_data2;
        this.y_data3 = y_data3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getY_data1() {
        return y_data1;
    }

    public void setY_data1(String y_data1) {
        this.y_data1 = y_data1;
    }

    public String getY_data2() {
        return y_data2;
    }

    public void setY_data2(String y_data2) {
        this.y_data2 = y_data2;
    }

    public String getY_data3() {
        return y_data3;
    }

    public void setY_data3(String y_data3) {
        this.y_data3 = y_data3;
    }
}
