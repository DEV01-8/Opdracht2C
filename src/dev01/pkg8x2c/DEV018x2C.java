/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x2c;

/**
 *
 * @author Johan Bos
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import processing.core.PApplet;

/**
 *
 * @author Johan Bos
 */
public class DEV018x2C extends PApplet {

    public static ArrayList<Integer> studentNR = new ArrayList<>();
    public static ArrayList<Integer> leeftijd = new ArrayList<>();
    public static ArrayList<Double> ANA = new ArrayList<>();
    public static ArrayList<Double> DEV = new ArrayList<>();
    public static ArrayList<Double> PRJ = new ArrayList<>();
    public static ArrayList<Double> SKL = new ArrayList<>();

    //Read text file and place columns in Arraylists
    public static void ReadText() {
        try {
            //Read text file
            BufferedReader br = new BufferedReader(new FileReader("C:\\studentcijfers.txt"));

            br.readLine(); // This will read the first line
            String line1 = null;    //Skip first line
            DecimalFormat decimalFormat = new DecimalFormat("#");

            //Clear Arraylists for correct order
            studentNR.clear();
            leeftijd.clear();
            ANA.clear();
            DEV.clear();
            PRJ.clear();
            SKL.clear();

            while ((line1 = br.readLine()) != null) {
                String[] columns = line1.split("\t");
                studentNR.add(decimalFormat.parse(columns[0]).intValue());
                leeftijd.add(decimalFormat.parse(columns[1]).intValue());
                ANA.add(decimalFormat.parse(columns[2]).doubleValue());
                DEV.add(decimalFormat.parse(columns[3]).doubleValue());
                PRJ.add(decimalFormat.parse(columns[4]).doubleValue());
                SKL.add(decimalFormat.parse(columns[5]).doubleValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void settings() {
        size(800, 800);
    }

    @Override
    public void setup() {
        //Set Title
        surface.setTitle("Scatterplot");
    }

    //Convert size window with min and max values to fit in.
    public void Convert(double d1, double d2, int minX, int maxX, int minY, int maxY, int beginX, int beginY) {
        try {
            float pointA = map((float) d1, minX, maxX, beginX, (beginX + 200));
            float pointB = map((float) d2, maxY, minY, beginY, (beginY + 200));

            //Create point on map with x and y
            fill(0, 0, 0);
            ellipse(pointA, pointB, 4, 4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get maximum value of ArrayList with type Double
    public int getMax(ArrayList<Double> list) {
        int max = (int) Double.MIN_VALUE;
        for (double ds : list) {
            for (double d : list) {
                if (d > max) {
                    max = (int) d;
                }
            }
        }
        return max;
    }

    //Get minimum value of ArrayList with type Double
    public int getMin(ArrayList<Double> list) {
        int min = (int) Double.MAX_VALUE;
        for (double ds : list) {
            for (double d : list) {
                if (d < min) {
                    min = (int) d;
                }
            }
        }
        return min;
    }

    public void createScatter(ArrayList<Double> X, ArrayList<Double> Y, int beginX, int beginY) {
        //Insert points to create scatter
        try {
            int minX = getMin(X);
            int minY = getMin(Y);
            int maxX = getMax(X);
            int maxY = getMax(Y);

            for (int i = 0; i < Y.size(); i++) {
                Convert(X.get(i), Y.get(i), minX, maxX, minY, maxY, beginX, beginY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        int x = 0;
        int y = 0;
        int sizeX = 200;
        int sizeY = 200;

        for (int i = 0; i < 16; i++) {
            fill(255, 255, 255);            //Create white rectangle
            rect(x, y, sizeX, sizeY);       //Create rectangle of size 200x200 at (200, 0)

            x = x + 200;                    //After creating rectangle go 200 pixels to right;

            //if 4 rectangles next eachother, x = 0 and go down 200 pixels
            if (x == 800) {
                x = 0;
                y = y + 200;
            }

            // if 9 rectangles are made, stop;
            if (y == 800) {
                break;
            }
        }

        //CREATE TEXT
        fill(0, 0, 0);              //Create black text
        textSize(30);               //TextSize
        text("ANA", 75, 100);      //Text
        text("DEV", 275, 300);      //Text
        text("PRJ", 475, 500);      //Text
        text("SKL", 675, 700);      //Text

        int ROW1 = 0;
        int ROW2 = 200;
        int ROW3 = 400;
        int ROW4 = 600;
        int COLUMN1 = 0;
        int COLUMN2 = 200;
        int COLUMN3 = 400;
        int COLUMN4 = 600;

        //createScatter( ArrayX, ArrayY, COLUMN, ROW )
        createScatter(DEV, ANA, COLUMN2, ROW1);    //ROW 1 COLUMN 2
        createScatter(PRJ, ANA, COLUMN3, ROW1);    //ROW 1 COLUMN 3
        createScatter(SKL, ANA, COLUMN4, ROW1);    //ROW 1 COLUMN 4

        createScatter(ANA, DEV, COLUMN1, ROW2);    //ROW 2 COLUMN 1
        createScatter(ANA, PRJ, COLUMN1, ROW3);    //ROW 3 COLUMN 1
        createScatter(ANA, SKL, COLUMN1, ROW4);    //ROW 4 COLUMN 1

        createScatter(PRJ, DEV, COLUMN3, ROW2);    //ROW 2 COLUMN 3
        createScatter(SKL, DEV, COLUMN4, ROW2);    //ROW 2 COLUMN 4

        createScatter(DEV, PRJ, COLUMN2, ROW3);    //ROW 3 COLUMN 2
        createScatter(DEV, SKL, COLUMN2, ROW4);    //ROW 4 COLUMN 2

        createScatter(SKL, PRJ, COLUMN4, ROW3);    //ROW 3 COLUMN 4
        createScatter(PRJ, SKL, COLUMN3, ROW4);    //ROW 4 COLUMN 3
        
        //ROW 1 COLUMN 2
        fill(255, 0, 0);
        textSize(11);
        text(3, 205, 194);
        text(6, 285, 194);
        text(9, 385, 194);
        
        //ROW 2 COLUMN 1
        fill(255, 0, 0);
        textSize(11);
        text(3, 2, 215);
        text(6, 2, 300);
        text(9, 2, 385);
       
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{DEV018x2C.class.getName()});
        ReadText();
       
    }

}
