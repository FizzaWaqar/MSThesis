package mPointJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;
/**
 * Created by fizza on 2/20/2018.
 */
public class mPointHash {
    public HashMap<Integer, mPoint> readFile(String filename) {
        HashMap<Integer, mPoint> mpoint= new HashMap<Integer, mPoint>();
        try {
            Scanner in = new Scanner(new File(filename));
            String line;
            in.next();
            while(in.hasNext())
            {
                line = in.next();
                String[] keyvalue = line.split(",");

                Integer key = Integer.parseInt(keyvalue[1]);
                if(mpoint.containsKey(key)) {
                    System.out.println("KEY Exists");
                    mpoint.get(key).x.add(Double.parseDouble(keyvalue[2]));
                    mpoint.get(key).y.add(Double.parseDouble(keyvalue[3]));
                }
              else
                {
                    int tempid=Integer.parseInt(keyvalue[1]);
                    ArrayList<Double> tempx= new ArrayList<Double>();
                    ArrayList<Double> tempy= new ArrayList<Double>();
                    //ArrayList<Double> tempt= new ArrayList<Double>();

                    tempx.add(Double.parseDouble(keyvalue[2]));
                    tempy.add(Double.parseDouble(keyvalue[3]));
                    //tempt.add(Double.parseDouble(keyvalue[4]));

                    System.out.println("Creating new mpoint object");
                    mPoint tempmpoint=new mPoint(tempid, tempx, tempy);
                    mpoint.put(tempmpoint.getTrid(),tempmpoint);
                }
            }
            Set<Map.Entry<Integer, mPoint>> entries = mpoint.entrySet();
            for(Map.Entry<Integer, mPoint> mp: entries){
                System.out.println(mp.getKey());
                mPoint printmp= mp.getValue();
                printmp.print();

            }
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return mpoint;
    }
}
