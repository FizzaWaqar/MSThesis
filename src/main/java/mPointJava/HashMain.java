package mPointJava;

/**
 * Created by fizza on 2/20/2018.
 */
public class HashMain {
    public  static  void main(String[] args)
    {

        String filename="src/main/resources/mpoints.csv";
        mPointHash check= new mPointHash();
        check.readFile(filename);
    }
}
