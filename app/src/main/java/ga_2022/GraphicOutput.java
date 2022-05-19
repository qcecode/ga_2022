package ga_2022;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PrimitiveIterator;

import javax.imageio.ImageIO;

public class GraphicOutput {
    //public static String faltungString = "ggggggg";
    public static String faltungString = "gllrlll";
    public static String hydrophobString = "10110001";

    public void generateImage(Faltung faltung){
    int height = 1080;
    int width = 1920;
    int cellSize = 74;
    int boxDistance = cellSize+cellSize/2;

    int xPos = width/2;
    int yPos = height/2;

    char lastDirketion = 'u';
    char nextDirection;
    
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = image.createGraphics();

    Font font = new Font("Serif", Font.PLAIN, 30);
    g2.setFont(font);
    FontMetrics metrics = g2.getFontMetrics();
    int ascent = metrics.getAscent();

    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, width, height);
    int overlapCounter = 0;
    HashSet<Knoten> alreadyPrinted = new HashSet<>();
    for(int i = 0; i < faltung.size() ; i++){
        if(overlapCounter < faltung.getOverlapList().size()){
            if(faltung.getOverlapList().get(overlapCounter).getKotenSet().contains(faltung.getKotenList().get(i))){ 
                ArrayList<Knoten> knotenList = new ArrayList<>(faltung.getOverlapList().get(overlapCounter).getKotenSet());
                alreadyPrinted.addAll(knotenList);
                int x1Pos = xPos;
                int y1Pos = yPos;
                String numberString = "";
                for(int j = 0; j < knotenList.size(); j++){
                    if(knotenList.get(j).hydrophob == false){
                        g2.setColor(Color.black);
                        g2.drawRect(x1Pos, y1Pos, cellSize, cellSize);
                    } else {
                        g2.setColor(Color.BLACK);
                        g2.fillRect(x1Pos, y1Pos, cellSize, cellSize);
                    }
                    numberString += Integer.toString(knotenList.get(j).knotenNummer);
                    if(j < knotenList.size()-1){
                        numberString += "/";
                        x1Pos += 20 ;
                        y1Pos += 20 ;
                    }
                }
                overlapCounter++;
                g2.setColor(Color.blue);
                g2.drawString(numberString, x1Pos + cellSize/2 - metrics.stringWidth(numberString)/2 , y1Pos + cellSize/2 + metrics.getAscent()/2 );
            }
        }

        if(!alreadyPrinted.contains(faltung.getKotenList().get(i))){
            if(faltung.getKotenList().get(i).hydrophob == false ){
                g2.setColor(Color.black);
                g2.drawRect(xPos, yPos, cellSize, cellSize);
            } else {
                g2.setColor(Color.black);
                g2.fillRect(xPos, yPos, cellSize, cellSize);
            }
            g2.setColor(Color.blue);
            g2.drawString(Integer.toString(i), xPos + cellSize/2 - metrics.stringWidth(Integer.toString(i))/2,
            yPos + cellSize/2 + ascent/2);
        }

        if(i < faltung.size()-1){
            nextDirection = getDirection(lastDirketion, faltung.faltung.charAt(i));
            if( nextDirection == 'r'){
                xPos = xPos+boxDistance;
                g2.drawLine(xPos, yPos+cellSize/2, xPos-boxDistance+cellSize, yPos+cellSize/2);
            }
            else if( nextDirection == 'l'){
                xPos = xPos-boxDistance;
                g2.drawLine(xPos+cellSize, yPos+cellSize/2, xPos+boxDistance, yPos+cellSize/2);
            }
            else if( nextDirection == 'u'){
                yPos = yPos-boxDistance;
                g2.drawLine(xPos+cellSize/2, yPos+cellSize, xPos+cellSize/2, yPos+boxDistance);
            }
            else if( nextDirection == 'd'){
                yPos = yPos+boxDistance;
                g2.drawLine(xPos+cellSize/2, yPos, xPos+cellSize/2, yPos-boxDistance+cellSize);
            }
            lastDirketion = nextDirection;
        }      
    }
    g2.drawString("Überlappungen: " + Integer.toString(faltung.getOverlapList().size()), 50, 50);
    g2.drawString("Bonds: " + Integer.toString(faltung.getBondSet().size()), 50, 100);
    g2.drawString("Fitness: " + Double.toString(faltung.getFitness()), 50, 150);

    String folder = "C:\\Users\\henri\\Documents\\ga\\ga_2022\\app\\build\\tmp";
    String filename = "bild.png";
    if (new File(folder).exists() == false) new File(folder).mkdirs();
    
    try {
        ImageIO.write(image, "png", new File(folder + File.separator + filename));
    } catch (IOException e) {
        e.printStackTrace();
        System.exit(0);
    }
}

private char getDirection(char lastDirketion, char heading){
        char nextDirection = lastDirketion;

        if(heading == 'r'){
            if( lastDirketion == 'r'){nextDirection ='d';}
            else if( lastDirketion == 'l'){nextDirection ='u';}
            else if( lastDirketion == 'u'){nextDirection ='r';}
            else if( lastDirketion == 'd'){nextDirection ='l';}
        } 
        else if(heading == 'l'){
            if( lastDirketion == 'r'){nextDirection ='u';}
            else if( lastDirketion == 'l'){nextDirection ='d';}
            else if( lastDirketion == 'u'){nextDirection ='l';}
            else if( lastDirketion == 'd'){nextDirection ='r';}
        }   
        return nextDirection;
    }
    
}
