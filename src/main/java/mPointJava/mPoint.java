package mPointJava;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.GeometryFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by fizza on 1/19/2018.
 */
public class mPoint {
    int size;
    int trid;
    ArrayList<Double> x;
    ArrayList<Double> y;
    ArrayList<Timestamp> t;

    //ArrayList<Double> check= new ArrayList<Double>();
    ArrayList<Coordinate> cords= new ArrayList<Coordinate>();



    GeometryFactory geometryFactory = new GeometryFactory();
    public mPoint() {
    }

    public mPoint(int size, int trid, ArrayList<Double> x, ArrayList<Double> y, ArrayList<Timestamp> t) {
        this.size = size;
        this.trid = trid;
        this.x = x;
        this.y = y;
        this.t = t;
    }


    public ArrayList<Timestamp> getT() {
        return t;
    }

    public void setT(ArrayList<Timestamp> t) {
        this.t = t;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTrid(int trid) {
        this.trid = trid;
    }

    public void setX(ArrayList x) {
        this.x = x;

    }

    public void setY(ArrayList y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public int getTrid() {
        return trid;
    }

    public ArrayList getX() {
        return x;
    }

    public ArrayList getY() {
        return y;
    }

    /* public ArrayList<Double> checking()
     {

         for(int i=0; i<size; i++)
         {
             check.add(x.get(i));
         }

        return check;

     }*/
    Coordinate[] temp = new Coordinate[this.getSize()];
    public LineString createLineString()
    {

        for(int i=0; i<this.getSize(); i++)
        {
            cords.add(new Coordinate(x.get(i), y.get(i)));
        }

        LineString lineString = geometryFactory.createLineString(cords.toArray(temp));
        return lineString;
    }

}

