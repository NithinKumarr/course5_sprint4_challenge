package com.Sales;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalesDataAnalyzer {
    public List<SalesRecord> readFile(String fileName) {
        String line="";
        int count=-1;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null) {
                count++;
            }
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(count);
        SalesRecord [] salesRecords=new SalesRecord[count];
        List<SalesRecord>list=new ArrayList<>();
        int count1=0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line= bufferedReader.readLine();
            while ((line = bufferedReader.readLine() )!= null) {
                String[] s1 = line.split(",");
                salesRecords[count1] = new SalesRecord(s1[0], Integer.parseInt(s1[1]), Integer.parseInt(s1[2]), s1[3], Double.parseDouble(s1[4]),
                        Double.parseDouble(s1[5]), Integer.parseInt(s1[6]));
                list.add(salesRecords[count1]);
                System.out.println(count1);
                count1++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData){
        Comparator <SalesRecord> comparator = new Comparator<SalesRecord>() {
            @Override
            public int compare(SalesRecord o1, SalesRecord o2) {
                return (int) (o2.getAmount()-o1.getAmount());
            }
        };
//
        Collections.sort(salesData,comparator);
        return salesData;
    }
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData){
        Comparator <SalesRecord> comparator =
                (o1,o2)->{
                    return (int) (o2.getTime_on_site()- o1.getTime_on_site());
                };
        Collections.sort(salesData,comparator);
        SalesRecord maxTimeSpent = salesData.get(0);
        return maxTimeSpent;
    }



}