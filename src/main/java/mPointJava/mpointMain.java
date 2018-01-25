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
        final List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>(3);

        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss"));

        for (SimpleDateFormat formatString : dateFormats)
        {
            try
            {
               return new Timestamp(formatString.parse(DT).getTime());
            }
            catch (ParseException e) {}
        }

        return null;
    }


    public static void main(String[] args) throws FileNotFoundException, ParseException {

        GeometryFactory geometryFactory = new GeometryFactory();
        String filename="src/main/resources/trips.csv";
        File file=new File(filename);
        Scanner inputFile;
        try {
            PrintStream out = new PrintStream(new File("src/main/resources/output_latest.txt"));
            System.setOut(out);
            inputFile = new Scanner(file);
            inputFile.next();

            List<Integer> tid= new ArrayList<Integer>();
            List<Double> coordx= new ArrayList<Double>();
            List<Double> coordy= new ArrayList<Double>();
            List<Timestamp> time=new ArrayList<Timestamp>();

            //int i=0;
            while(inputFile.hasNext())
            {
                String data = inputFile.next();
                String[] values= data.split(",");

                int id=Integer.parseInt(values[1]);
                double x=Double.parseDouble(values[2]);
                double y=Double.parseDouble(values[3]);
                String DT=values[4];

                tid.add(id);
                coordx.add(x);
                coordy.add(y);
                time.add(dateParse(DT));
                ;
                //System.out.println(x +" " + y);

            }

            System.out.println("Number of Instances: " + tid.size());

            int max=0;
            for(int m=0; m<tid.size(); m++)
            {
                if(tid.get(m) > max)
                {
                    max=tid.get(m);
                }
            }
            System.out.println("Number of Trajectories: " + max);

            mPoint[] mpoints=new mPoint[max+1];

            int j=0;
            for(int m=1; m<= max; m++)
            {
                int count = Collections.frequency(tid,m);

                if (count>1)
                {
                    int start=tid.indexOf(m);
                    int end=start+count;

                    ArrayList<Double> x=new ArrayList<Double>();
                    ArrayList<Double> y=new ArrayList<Double>();
                    ArrayList<Timestamp> t=new ArrayList<Timestamp>();

                    for(int n=0; n<count; n++)
                    {
                        x.add(coordx.subList(start, end).get(n));
                        y.add(coordy.subList(start, end).get(n));
                        t.add(time.subList(start,end).get(n));

                    }

                    mpoints[m]=new mPoint(count,m,x,y,t);
                    mpoints[m].setTrid(m);
                    mpoints[m].setX(x);
                    mpoints[m].setY(y);
                    mpoints[m].setT(t);


                    System.out.println("TID " + mpoints[m].getTrid());
                    System.out.println("LinStrings Size " + count);
                    System.out.println("Timestamps of Trajectory " + m + " is " +mpoints[m].getT());
                    System.out.println("Linestring of Trajectory ID # " + m + " is " +mpoints[m].createLineString());
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
/*


        double[] cordx={72.85630199,72.8626665,72.86699216};
        double[] cordy={33.3697771,33.3655341,33.37024549};
        double[] time= {1010,1025,1035};

        Coordinate[] cords = new Coordinate[3];

        Coordinate checkCord=new Coordinate(14.2,85.3);
        System.out.print("Checking" + checkCord);

        for(int i=0; i<3; i++)
        {
            cords[i]= new Coordinate(cordx[i],cordy[i]);

            System.out.print(cords[i]);
        }

        System.out.println("TO STRING"+Arrays.toString(cords));
      //  System.out.println("AS LIST"+ Arrays.asList(cords));
      //  System.out.println("AS STREAM"+ Arrays.stream(cords));
        LineString lineString = geometryFactory.createLineString(cords);
        System.out.println("Linestring" + lineString);

        Geometry envelope = lineString.getEnvelope();
        System.out.print("Envelope" + envelope);

        MultiLineString check= geometryFactory.createMultiLineString(new LineString[]
        {
            lineString
        });


*/
    }

}
