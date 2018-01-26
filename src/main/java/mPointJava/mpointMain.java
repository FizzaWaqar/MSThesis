package mPointJava; /**
 * Created by fizza on 1/16/2018.
 */
import com.vividsolutions.jts.geom.GeometryFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;


public class mpointMain {

    public static Timestamp dateParse(String DT)
    {
        final List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>(3); //Initializing Date Format Arraylist

        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss"));

        for (SimpleDateFormat formatString : dateFormats)
        {
            try
            {
               return new Timestamp(formatString.parse(DT).getTime()); // returning timestamps
            }
            catch (ParseException e) {}
        }

        return null;
    }


    public static void main(String[] args) throws FileNotFoundException, ParseException {

        GeometryFactory geometryFactory = new GeometryFactory();
        String filename="src/main/resources/trips.csv"; //input csv file... schema (objectID, TrajectoryID, Xcord, Ycord, Time)
        File file=new File(filename);
        Scanner inputFile;
        try {
            PrintStream out = new PrintStream(new File("src/main/resources/output_latest.txt")); //writing the output to text file
            System.setOut(out);
            inputFile = new Scanner(file);
            inputFile.next();

            List<Integer> tid= new ArrayList<Integer>(); //Initializing Integer Array List to store Trajectory IDs
            List<Double> coordx= new ArrayList<Double>(); //Initializing Double Array List to store x- Coordinates
            List<Double> coordy= new ArrayList<Double>(); //Initializing Double Array List to store y- Coordinates
            List<Timestamp> time=new ArrayList<Timestamp>();//Initializing Timestamp List to store Time

            while(inputFile.hasNext())
            {
                String data = inputFile.next();
                String[] values= data.split(",");

                int id=Integer.parseInt(values[1]); //TrajectoryID
                double x=Double.parseDouble(values[2]); //Xcord
                double y=Double.parseDouble(values[3]); //Ycord
                String DT=values[4];

                //Adding to lists
                tid.add(id);
                coordx.add(x);
                coordy.add(y);
                time.add(dateParse(DT));
                ;
                //System.out.println(x +" " + y);

            }

            System.out.println("Number of Instances: " + tid.size()); //Number of Data Rows

            int max=0;
            for(int m=0; m<tid.size(); m++)
            {
                if(tid.get(m) > max)
                {
                    max=tid.get(m);
                }
            }
            System.out.println("Number of Trajectories: " + max); // max is the total number of trajectories in the dataset

            mPoint[] mpoints=new mPoint[max+1]; //initializing mpoint arraylist of the max size. each trajectory will make one mPoint object

            int j=0;
            for(int m=1; m<= max; m++) //outerloop for all trajectories
            {
                int count = Collections.frequency(tid,m); //get size of each trajectory based on the frequency of trajectory IDs.

                if (count>1) //at least two points make a trajectory so if size is greater than 1 then create mPoint object...
                {
                    int start=tid.indexOf(m); //get the start of each trajectory
                    int end=start+count; //get the end of each trajectory

                    ArrayList<Double> x=new ArrayList<Double>(); //sub arrayList of x-coordinates against each trajectory
                    ArrayList<Double> y=new ArrayList<Double>(); //sub arrayList of y-coordinates against each trajectory
                    ArrayList<Timestamp> t=new ArrayList<Timestamp>(); //sub arrayList of timestamps against each trajectory

                    for(int n=0; n<count; n++) //Innerloop.. size of the loop is dependent on trajectory size
                    {
                        //adding x,y,t to sub arrays
                        x.add(coordx.subList(start, end).get(n));
                        y.add(coordy.subList(start, end).get(n));
                        t.add(time.subList(start,end).get(n));

                    }

                    mpoints[m]=new mPoint(count,m,x,y,t); //creating mPoint object for each trajectory
                    mpoints[m].setTrid(m); //TrajectoryID
                    mpoints[m].setX(x);    // x-coordinates
                    mpoints[m].setY(y);    //y-coordinates
                    mpoints[m].setT(t);    //Timestamps


                    System.out.println("TID " + mpoints[m].getTrid()); //get Trajectory ID
                    System.out.println("LinStrings Size " + count); //Size of the trajectory
                    System.out.println("Timestamps of Trajectory " + m + " is " +mpoints[m].getT()); //get Timestamps of each trajectory
                    System.out.println("Linestring of Trajectory ID # " + m + " is " +mpoints[m].getLineString()); //get Linestring of each trajectory
                    System.out.println("");
                }
                else{
                    System.out.println("No Trajectory Found against # " + m + " ID");
                    System.out.println("");
                }

            }

            inputFile.close();



        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}
