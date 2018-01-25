import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
public class test {
    public static void main(String[] args) throws FileNotFoundException {

        GeometryFactory geometryFactory = new GeometryFactory();
        String filename="C:\\Users\\hp\\Desktop\\GeoSpark\\sample data\\mpoints.csv";
        File file=new File(filename);
        Scanner inputFile;
        try {
            inputFile = new Scanner(file);
            inputFile.next();

            List<Integer> tid= new ArrayList<Integer>();

            List<Double> coordx= new ArrayList<Double>();
            List<Double> coordy= new ArrayList<Double>();


            List<List> lines=  new ArrayList<List>();

            List<Double> time= new ArrayList<Double>();
            //int i=0;
            while(inputFile.hasNext())
            {
                String data = inputFile.next();
                String[] values= data.split(",");

                int id=Integer.parseInt(values[1]);
                double x=Double.parseDouble(values[2]);
                double y=Double.parseDouble(values[3]);
                double t=Double.parseDouble(values[4]);
                tid.add(id);
                coordx.add(x);
                coordy.add(y);
                time.add(t);
                //System.out.println(x +" " + y);

            }

            int count = Collections.frequency(tid,1);
            System.out.println(count);
            System.out.println(coordx.size());
/*
            int max=0;
            for(int m=0; m<tid.size(); m++)
            {
                if(tid.get(m) > max)
                {
                    max=tid.get(m);
                }
            }
            System.out.println("Max"+ max);

            ArrayList list = new ArrayList<learnJAVA.mPoint>();
            int trid=0;
            ArrayList x = new ArrayList();
            ArrayList y = new ArrayList();

            int counter=0;
            int flag = 0;

            for(int i=0; i<tid.size();i++)
            {

                if(tid.get(i) != flag)
                {
                   if(i != 0)
                   {

                       list.add(new learnJAVA.mPoint(trid,x,y));
                       x.clear();
                       y.clear();
                       trid = 0;


                   }

                   x.add(coordx.get(i));
                   y.add(coordy.get(i));



                }
                else
                {
                    x.add(coordx.get(i));
                    y.add(coordy.get(i));
                    trid = tid.get(i);

                }


                flag = tid.get(i);

                if(i == tid.size()-1)
                {
                    list.add(new learnJAVA.mPoint(trid,x,y));
                }

            }

        for(int i=0; i<list.size();i++)
        {
            learnJAVA.mPoint p = (learnJAVA.mPoint)list.get(i);


        }
/*
            int k=0;
            for(int m=1; m<=max; m++)
            {
                for (int n=0; n<= Collections.frequency(tid,m); n++)
                {
                    k=tid.indexOf(m);
                }
            }
            System.out.println(k);
   /*       //Coordinate[] cords = new Coordinate[coordx.size()/2];

            int j=0;
            for(int i=0; i<coordx.size()/2; i++)
            {

                cords[i]= new Coordinate(coordx.get(j),coordx.get(j+1));
                j=j+2;
                System.out.print(cords[i]);


            }

            // System.out.println("TO STRING"+Arrays.toString(cords));

            LineString lineString = geometryFactory.createLineString(cords);
            System.out.println("Linestring" + lineString);


            System.out.println("Boundary" );

            //System.out.println("Boundary" + MBR);

            Geometry envelope = lineString.getEnvelope();

            System.out.println("Envelope" );

            System.out.println("Envelope" + envelope.getEnvelopeInternal());

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



    }

}
*/