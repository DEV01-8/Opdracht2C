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
    
    public static ArrayList studentNR = new ArrayList();
    public static ArrayList leeftijd = new ArrayList();
    public static ArrayList ANA = new ArrayList();
    public static ArrayList DEV = new ArrayList();
    public static ArrayList PRJ = new ArrayList();
    public static ArrayList SKL = new ArrayList();


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
    
    public void Convert(double d1, double d2) {
        try {
            float pointA = map((float) d1, 0, 400, 0, 400);
            float pointB = map((float) d2, 400, 0, 0, 400);
           
            //Create point on map with x and y
            ellipse(pointA, pointB, 6, 6);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        rect(0, 0, 400,400);
        
         //Insert points to create scatter
        try {
            for (int i = 0; i < ANA.size(); i++) {
                Convert((double) studentNR.get(i), (double) ANA.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        PApplet.main(new String[]{DEV018x2C.class.getName()});
        ReadText();
    }
    
}
