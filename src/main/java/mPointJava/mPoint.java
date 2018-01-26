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
    int size; //mPoint size
    int trid; //mPoint ID
    ArrayList<Double> x; //x-coordinates
    ArrayList<Double> y; //y-coordinates
    ArrayList<Timestamp> t; //Timestamps

    ArrayList<Coordinate> cords= new ArrayList<Coordinate>(); //Initializing Coordinate objects Arraylist

    GeometryFactory geometryFactory = new GeometryFactory();

   //mPoint Constructor
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



    Coordinate[] temp = new Coordinate[this.getSize()];
    public LineString getLineString() //get lineString for each trajectory
    {

        for(int i=0; i<this.getSize(); i++)
        {
            cords.add(new Coordinate(x.get(i), y.get(i))); //Populating Coordinate objects Arraylist
        }

        LineString lineString = geometryFactory.createLineString(cords.toArray(temp)); //creating linestrings
        return lineString;
    }

}

