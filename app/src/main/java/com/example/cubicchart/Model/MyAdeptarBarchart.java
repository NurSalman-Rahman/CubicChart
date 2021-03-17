package com.example.cubicchart.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cubicchart.R;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MyAdeptarBarchart extends RecyclerView.Adapter<MyAdeptarBarchart.ViewHolder>  {




    private Context mcontext;
    private List<BarChart> mdata;

    public MyAdeptarBarchart(Context mcontext,List<BarChart>mdata)
    {
        this.mcontext = mcontext;
        this.mdata =mdata;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        //view varible assign all
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.barchart, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        barChartFunction(holder,position);

    }

    private void barChartFunction(ViewHolder holder, int position) {
        final ArrayList<BarEntry> yVals = new ArrayList<>();
        BarChart data = mdata.get(position);


        final ArrayList<String>xVals = new ArrayList<>();




        yVals.add(new BarEntry(0,Float.parseFloat(data.getY_data1())));
        yVals.add(new BarEntry(1,Float.parseFloat(data.getY_data2())));
        yVals.add(new BarEntry(2,Float.parseFloat(data.getY_data3())));


            xVals.add(data.getY_data1());
            xVals.add(data.getY_data2());
            xVals.add(data.getY_data3());


        BarDataSet dataSet = new BarDataSet(yVals, "GRAPH");
        ArrayList<IBarDataSet>dataset1 = new ArrayList<>();

        dataset1.add(dataSet);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.GREEN);
        dataSet.setValueTextSize(-16f);

        BarData datas = new BarData(dataset1);



        holder.mModel.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        holder.mModel.setData(datas);
        holder.mModel.animateY(2000);

        XAxis xAxis = holder.mModel.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawAxisLine(true);
        xAxis.isCenterAxisLabelsEnabled();
        xAxis.setGranularityEnabled(true);




        YAxis rightAxis = holder.mModel.getAxisRight();
        rightAxis.setEnabled(false);



        holder.mModel.setMaxVisibleValueCount(5);

        holder.mModel.setFitBars(true);

    }

    @Override
    public int getItemCount() {

        if(mdata == null)
            return 0;
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        com.github.mikephil.charting.charts.BarChart mModel ;



        public ViewHolder(View itemView)
        {

            super(itemView);

           mModel = itemView.findViewById(R.id.barChartId);




        }



    }

}
