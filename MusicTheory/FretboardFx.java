import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Polygon;
import javafx.scene.control.ComboBox;
    
/**
* 
* @author Christian Bonin
* @version 1/3/2020
*/
public class FretboardFx extends Application
{
    String[] scale = null;
    String key = null;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgba(0,0,0,1.0);");
        Scene scene = new Scene(root, 1200, 768);
        
        Canvas canvas = new Canvas(900,700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        
        Group notePanel = new Group();
        Rectangle noteRect = new Rectangle(240, 600, Color.GRAY);
    
        Font strFont = Font.font(null, FontWeight.BOLD, 20);
        int strTxtX = 120;
        Color strTxtCol = Color.RED;
        
        Text str1 = new Text("E");
        str1.setFill(strTxtCol);
        str1.setX(strTxtX);
        str1.setY(330);
        str1.setFont(strFont);
        
        NoteList str1Notes = new NoteList("E");
        
        Text str2 = new Text("A");
        str2.setFill(strTxtCol);
        str2.setX(strTxtX);
        str2.setY(280);
        str2.setFont(strFont);
        
        NoteList str2Notes = new NoteList("A");
        
        Text str3 = new Text("D");
        str3.setFill(strTxtCol);
        str3.setX(strTxtX);
        str3.setY(230);
        str3.setFont(strFont);
        
        NoteList str3Notes = new NoteList("D");
        
        Text str4 = new Text("G");
        str4.setFill(strTxtCol);
        str4.setX(strTxtX);
        str4.setY(180);
        str4.setFont(strFont);
        
        NoteList str4Notes = new NoteList("G");
        
        Text str5 = new Text("B");
        str5.setFill(strTxtCol);
        str5.setX(strTxtX);
        str5.setY(130);
        str5.setFont(strFont);
        
        NoteList str5Notes = new NoteList("B");
        
        Text str6 = new Text("E");
        str6.setFill(strTxtCol);
        str6.setX(strTxtX);
        str6.setY(80);
        str6.setFont(strFont);
        
        NoteList str6Notes = new NoteList("E");
        
        Text keyText = new Text("Key: A");
        keyText.setFill(strTxtCol);
        keyText.setX(strTxtX-20);
        keyText.setY(390);
        keyText.setFont(strFont);
        
        //combobox to select the scale
        ComboBox scaleBox = new ComboBox();
        scaleBox.getItems().add("Pentatonic Minor");
        scaleBox.getItems().add("Pentatonic Major");
        scaleBox.getItems().add("Minor");
        scaleBox.getItems().add("Major");
        scaleBox.setValue("Pentatonic Minor");
        
        scaleBox.setLayoutY(500);
        scaleBox.setLayoutX(50);
    
        NoteList keyList = new NoteList("A");
        scale = keyList.getMinorPentatonic();
        
        scaleBox.setOnAction(event -> {
                                            switch((String)scaleBox.getValue())
                                            {
                                                case "Pentatonic Minor":
                                                    scale = keyList.getMinorPentatonic();
                                                    break;
                                                case "Pentatonic Major":
                                                    scale = keyList.getMajorPentatonic();
                                                    break;
                                                case "Minor":
                                                    scale = keyList.getMinor();
                                                    break;
                                                case "Major":
                                                    scale = keyList.getMajor();
                                                    break;
                                            }
                                            clearScreen(gc);
                                            paintFretboard(gc);
                                            str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                            str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                            str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                            str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                            str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                            str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                      });
    
        //All clickable arrows and their event handlers
        //first for the key
        Polygon keyArrR = new Polygon(strTxtX+60.0, 380.0, strTxtX+60.0, 400.0, strTxtX+80.0, 390.0);
        keyArrR.setFill(Color.RED);
        keyArrR.setOnMouseClicked(event -> {
                                                keyList.shiftRootRight();
                                                keyText.setText("Key: " + keyList.rootNote.tone);
                                                switch((String)scaleBox.getValue())
                                                {
                                                    case "Pentatonic Minor":
                                                        scale = keyList.getMinorPentatonic();
                                                        break;
                                                    case "Pentatonic Major":
                                                        scale = keyList.getMajorPentatonic();
                                                        break;
                                                    case "Minor":
                                                        scale = keyList.getMinor();
                                                        break;
                                                    case "Major":
                                                        scale = keyList.getMajor();
                                                        break;
                                                }
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
        
        Polygon keyArrL = new Polygon(strTxtX-40.0, 380.0, strTxtX-40.0, 400.0, strTxtX-60.0, 390.0);
        keyArrL.setFill(Color.RED);
        keyArrL.setOnMouseClicked(event -> {
                                                keyList.shiftRootLeft();
                                                keyText.setText("Key: " + keyList.rootNote.tone);
                                                switch((String)scaleBox.getValue())
                                                {
                                                    case "Pentatonic Minor":
                                                        scale = keyList.getMinorPentatonic();
                                                        break;
                                                    case "Pentatonic Major":
                                                        scale = keyList.getMajorPentatonic();
                                                        break;
                                                    case "Minor":
                                                        scale = keyList.getMinor();
                                                        break;
                                                    case "Major":
                                                        scale = keyList.getMajor();
                                                        break;
                                                }
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
        //and then to change string tunings
        Polygon str6ArrL = new Polygon(strTxtX-40.0, 70.0, strTxtX-40.0, 90.0, strTxtX-60.0, 80.0);
        str6ArrL.setFill(Color.RED);
        str6ArrL.setOnMouseClicked(event ->{
                                                str6Notes.shiftRootLeft();
                                                str6.setText(str6Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str6ArrR = new Polygon(strTxtX+45.0, 70.0, strTxtX+45.0, 90.0, strTxtX+65.0, 80.0);
        str6ArrR.setFill(Color.RED);
        str6ArrR.setOnMouseClicked(event ->{
                                                str6Notes.shiftRootRight();
                                                str6.setText(str6Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str5ArrL = new Polygon(strTxtX-40.0, 120.0, strTxtX-40.0, 140.0, strTxtX-60.0, 130.0);
        str5ArrL.setFill(Color.RED);
        str5ArrL.setOnMouseClicked(event ->{
                                                str5Notes.shiftRootLeft();
                                                str5.setText(str5Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
        
        Polygon str5ArrR = new Polygon(strTxtX+45.0, 120.0, strTxtX+45.0, 140.0, strTxtX+65.0, 130.0);
        str5ArrR.setFill(Color.RED);
        str5ArrR.setOnMouseClicked(event ->{
                                                str5Notes.shiftRootRight();
                                                str5.setText(str5Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                  
        Polygon str4ArrL = new Polygon(strTxtX-40.0, 170.0, strTxtX-40.0, 190.0, strTxtX-60.0, 180.0);
        str4ArrL.setFill(Color.RED);
        str4ArrL.setOnMouseClicked(event ->{
                                                str4Notes.shiftRootLeft();
                                                str4.setText(str4Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str4ArrR = new Polygon(strTxtX+45.0, 170.0, strTxtX+45.0, 190.0, strTxtX+65.0, 180.0);
        str4ArrR.setFill(Color.RED);
        str4ArrR.setOnMouseClicked(event ->{
                                                str4Notes.shiftRootRight();
                                                str4.setText(str4Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str3ArrL = new Polygon(strTxtX-40.0, 220.0, strTxtX-40.0, 240.0, strTxtX-60.0, 230.0);
        str3ArrL.setFill(Color.RED);
        str3ArrL.setOnMouseClicked(event ->{
                                                str3Notes.shiftRootLeft();
                                                str3.setText(str3Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str3ArrR = new Polygon(strTxtX+45.0, 220.0, strTxtX+45.0, 240.0, strTxtX+65.0, 230.0);
        str3ArrR.setFill(Color.RED);
        str3ArrR.setOnMouseClicked(event ->{
                                                str3Notes.shiftRootRight();
                                                str3.setText(str3Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str2ArrL = new Polygon(strTxtX-40.0, 270.0, strTxtX-40.0, 290.0, strTxtX-60.0, 280.0);
        str2ArrL.setFill(Color.RED);
        str2ArrL.setOnMouseClicked(event ->{
                                                str2Notes.shiftRootLeft();
                                                str2.setText(str2Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        Polygon str2ArrR = new Polygon(strTxtX+45.0, 270.0, strTxtX+45.0, 290.0, strTxtX+65.0, 280.0);
        str2ArrR.setFill(Color.RED);
        str2ArrR.setOnMouseClicked(event ->{
                                                str2Notes.shiftRootRight();
                                                str2.setText(str2Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                               });
                                           
        Polygon str1ArrL = new Polygon(strTxtX-40.0, 320.0, strTxtX-40.0, 340.0, strTxtX-60.0, 330.0);
        str1ArrL.setFill(Color.RED);
        str1ArrL.setOnMouseClicked(event ->{
                                                str1Notes.shiftRootLeft();
                                                str1.setText(str1Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
            
        Polygon str1ArrR = new Polygon(strTxtX+45.0, 320.0, strTxtX+45.0, 340.0, strTxtX+65.0, 330.0);
        str1ArrR.setFill(Color.RED);
        str1ArrR.setOnMouseClicked(event ->{
                                                str1Notes.shiftRootRight();
                                                str1.setText(str1Notes.rootNote.tone);
                                                clearScreen(gc);
                                                paintFretboard(gc);
                                                str1Notes.paintNotes(scale, 350, keyList.rootNote.tone, gc);
                                                str2Notes.paintNotes(scale, 300, keyList.rootNote.tone, gc);
                                                str3Notes.paintNotes(scale, 250, keyList.rootNote.tone, gc);
                                                str4Notes.paintNotes(scale, 200, keyList.rootNote.tone, gc);
                                                str5Notes.paintNotes(scale, 150, keyList.rootNote.tone, gc);
                                                str6Notes.paintNotes(scale, 100, keyList.rootNote.tone, gc);
                                           });
                                           
        notePanel.getChildren().add(noteRect);
        notePanel.getChildren().add(str1);
        notePanel.getChildren().add(str2);
        notePanel.getChildren().add(str3);
        notePanel.getChildren().add(str4);
        notePanel.getChildren().add(str5);
        notePanel.getChildren().add(str6);
        notePanel.getChildren().add(str6ArrL);
        notePanel.getChildren().add(str6ArrR);
        notePanel.getChildren().add(str5ArrL);
        notePanel.getChildren().add(str5ArrR);
        notePanel.getChildren().add(str4ArrL);
        notePanel.getChildren().add(str4ArrR);
        notePanel.getChildren().add(str3ArrL);
        notePanel.getChildren().add(str3ArrR);
        notePanel.getChildren().add(str2ArrL);
        notePanel.getChildren().add(str2ArrR);
        notePanel.getChildren().add(str1ArrL);
        notePanel.getChildren().add(str1ArrR);
        notePanel.getChildren().add(keyText);
        notePanel.getChildren().add(keyArrR);
        notePanel.getChildren().add(keyArrL);
        notePanel.getChildren().add(scaleBox);
        
        root.setCenter(canvas);
        root.setLeft(notePanel);
        root.setAlignment(notePanel, Pos.CENTER_LEFT);
        
        paintFretboard(gc);
        str1Notes.paintNotes(keyList.getMinorPentatonic(), 350, keyList.rootNote.tone, gc);
        str2Notes.paintNotes(keyList.getMinorPentatonic(), 300, keyList.rootNote.tone, gc);
        str3Notes.paintNotes(keyList.getMinorPentatonic(), 250, keyList.rootNote.tone, gc);
        str4Notes.paintNotes(keyList.getMinorPentatonic(), 200, keyList.rootNote.tone, gc);
        str5Notes.paintNotes(keyList.getMinorPentatonic(), 150, keyList.rootNote.tone, gc);
        str6Notes.paintNotes(keyList.getMinorPentatonic(), 100, keyList.rootNote.tone, gc);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void clearScreen(GraphicsContext gc)
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,900,700);
    }
    
    public void paintFretboard(GraphicsContext gc)
    {
        int xPos = 50;
        //paint back
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(xPos,100,900,300);
        //paint frets
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        for(int i = 0; i < 18; i++)
        {
            if(i == 0)
            {
                gc.setStroke(Color.BLACK);
                gc.strokeLine(xPos,100,xPos,400);
                xPos += 50;
                gc.setStroke(Color.GRAY);
            }else{
                gc.strokeLine(xPos,100,xPos,400);
                xPos += 50;
            }
        }
        xPos = 25;
        //paint fret markings
        for(int i = 0; i < 18; i++)
        {
            gc.setFill(Color.BLACK);
            gc.fillText(Integer.toString(i),xPos,100);
            if(i == 3 || i == 5 || i == 7 || i == 9 || i == 15)
            {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillOval(xPos-20, 225, 40, 40);
            }
            if(i == 12)
            {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillOval(xPos-20, 175, 40, 40);
                gc.fillOval(xPos-20, 275, 40, 40);
            }
            xPos += 50;
        }
        //paint strings
        int strokeWid = 8;
        gc.setStroke(Color.BLACK);
        int yPos = 372;
        for(int i = 0; i < 6; i++)
        {
            gc.setLineWidth(strokeWid);
            gc.strokeLine(0, yPos, 900, yPos);
            strokeWid--;
            yPos -= 50;
        }
    }
}
