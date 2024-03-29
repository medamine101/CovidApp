package com.example.covidapp;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class CSVData implements Comparable<CSVData>{
    public String State;
    public String Confirmed;
    public String Deaths;
    public String FIPS;
    public String IncidentRate;
    public String TotalTestResults;
    public String CaseFatalityRate;
    public String TestingRate;

    CSVData(String line) {
        System.out.println(line);
        String[] elements = line.split(",");
        State = elements[0];
        Confirmed = elements[5];
        Deaths = elements[6];
        FIPS = elements[9];
        IncidentRate = elements[10];
        TotalTestResults = elements[11];
        CaseFatalityRate = elements[13];

        try {
            TestingRate = elements[16];
        } catch (Exception e) {
            TestingRate = "";
        }
    }

    // public String toString() {
    // 	return "\"" + product + "\"," + "\"" + side + "\"," + "\"" + dateTime + "\"," + "\"" + FIPS + "\"," + "\""
    // 			+ unitPrice + "\"," + "\"" + fee + "\"," + "\"" + total + "\"";
    // }

    @Override
    public int compareTo(CSVData o) {
        if(this.State.compareTo(o.State) == 0)
            return this.Deaths.compareTo(o.Deaths);
        return this.State.compareTo(o.State);
    }
}

public class DataFetcher implements Updater{

    public static ArrayList<CSVData> listByState = new ArrayList<CSVData>();
    HelpFragment fragment;

    public static String GetTodayDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String todaysDate = sdf.format(new Date());

        return todaysDate;
    }

    private String getPreviousDate(String inputDate){

        SimpleDateFormat  format = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    //COVID-19 Data Repository by the Center for Systems Science and Engineering (CSSE) at Johns Hopkins University
    //URL: https://github.com/CSSEGISandData/COVID-19
    //Thanks to Johns Hopkins University for the data!
    public void FetchTodayData(Context context, HelpFragment caller){

        fragment = caller;
        
        String urlBeginning = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/";

        String urlEnd = ".csv";

        String todaysDate = GetTodayDate();

        String yesterdaysDate = getPreviousDate(todaysDate);

        String url = urlBeginning + yesterdaysDate + urlEnd;

        FetchFromCSV.fetch(context, this, url);

    }


    @Override
    public void toResponse(String response) {

        String[] lines = response.split("\\r?\\n");

        for (int i = 1; i < lines.length; i++){

            CSVData newData = new CSVData(lines[i]);

            listByState.add(newData);



        }

        fragment.toResponse();

    }
}
