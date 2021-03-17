package com.example.cubicchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cubicchart.Database.database;
import com.example.cubicchart.Model.BarChart;
import com.example.cubicchart.Model.MyAdeptarBarchart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewBar;


    LineChart lineChart;
    List<Double> lineChartList = new ArrayList<Double>();

    //

    EditText editText1,editText2,editText3;
    Button Savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intFunction();
        intLisiner();
        addDataToGraph();
    }






    //Check Input
    private void intInputCheck() {

        if (editText1.getText().toString().isEmpty())
        {
            editText1.setError("This field is required");
        }
        else if (editText2.getText().toString().isEmpty())
        {
            editText2.setError("This field is required");
        }
        else if (editText3.getText().toString().isEmpty())
        {
            editText3.setError("This field is required");
        }
        else{
            SaveToDatabase();
            editText1 .setText("");
            editText2 .setText("");
            editText3 .setText("");
           lineChart.notifyDataSetChanged();
        }
    }



    //Set on Click Save Button

    private void intLisiner() {

        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                intInputCheck();
            }
        });
    }





    //Bar chart



    private void intFunction()
    {

        lineChart = findViewById(R.id.linechart);


        Savebutton = findViewById(R.id.savebutton);

        editText1 = findViewById(R.id.editvalue1);
        editText2 = findViewById(R.id.editvalue2);
        editText3 = findViewById(R.id.editvalue3);



        recyclerViewBar = findViewById(R.id.RecycleBarchart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBar.setLayoutManager(layoutManager);

    }



    private database db;

    public void addDataToGraph()
    {



        lineChartList.clear();
        db = new database(this);

        for (int i = 0; i < db.queryBarData().size(); i++)
        {

            BarChart barChart = db.queryBarData().get(i);
            Double value = (Double.valueOf(Integer.parseInt(barChart.getY_data1()) +Integer.parseInt( barChart.getY_data2())
                    + Integer.parseInt(barChart.getY_data3()))) / 3 ;

            lineChartList.add(value);


        }


        showChartData();

    }

    private void showChartData() {


        MyAdeptarBarchart adeptarBarchart = new MyAdeptarBarchart(this,db.queryBarData());

        recyclerViewBar.setAdapter(adeptarBarchart);
        lineChartFunction();

    }




    private void lineChartFunction()
    {
        //Data


        ArrayList<Entry> Values = new ArrayList<>();

        for(int i = 0;i< lineChartList.size(); i++)
        {
            Values.add(new Entry(   i      ,      Float.parseFloat(String.valueOf(lineChartList.get(i)))));
        }

        //Set Data


        LineDataSet set1 = new LineDataSet(Values,"Data Set 1");

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        set1.setCubicIntensity(0.05f);
        set1.setDrawFilled(true);


        set1.setDrawCircles(true);
        set1.setLineWidth(.5f);
        set1.setCircleRadius(.2f);
        set1.setCircleColor(Color.BLACK);
        //set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.RED);
        set1.setFillColor(Color.GREEN);
        set1.setFillAlpha(100);
        //set1.setDrawHorizontalHighlightIndicator(false);



        ArrayList<ILineDataSet>dataSets = new ArrayList<>();

        dataSets.add(set1);


        LineData datas = new LineData(dataSets);

        datas.setValueTextSize(6f);
        datas.setDrawValues(true);
        datas.setValueTextColor(Color.RED);
        datas.setDrawValues(true);


        //Data Set Show in Line Chart
        lineChart.setData(datas);

        lineChart.animateXY(5000,5000);

        //lineChart.animateX(5000);   // best








    }
    public void SaveToDatabase()
    {
        db = new database(this);

        String yvalue1 = editText1.getText().toString();
        String yvalue2 = editText2.getText().toString();
        String yvalue3 = editText3.getText().toString();

        db.saveData(new com.example.cubicchart.Model.BarChart(yvalue1,yvalue2,yvalue3));

        addDataToGraph();


        db.close();




    }



}//end