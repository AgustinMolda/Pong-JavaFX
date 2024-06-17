package com.pong.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    private static final int width= 800;
    private static final int height = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double BALL_R = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = height/2;
    private double playerTwoYPos = height/2;
    private double ballXPos = width/2;
    private double ballYPos = width/2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private int playerTwoXPos = width -PLAYER_WIDTH;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("P O N G");
        Canvas canvas = new Canvas(width,height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse controls
        canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
        canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();


    }

    private void run(GraphicsContext gc){
        //setBackgorundColor
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width,height);

        //setTextColor
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(gameStarted){
            //set ball movements
            ballXPos+=ballXSpeed;
            ballYPos+=ballYSpeed;

            //computer
            if(ballXPos < width - width/4){
                playerTwoYPos = ballYPos - PLAYER_HEIGHT /2;

            }else {
                playerTwoYPos = ballYPos > playerTwoYPos + PLAYER_HEIGHT /2 ? playerTwoYPos += 1 : playerTwoYPos -1;

            }

            //draw ball
            gc.fillOval(ballXPos,ballYPos,BALL_R, BALL_R);

        }else {

            //set the start Text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("on Click",width/2, height/2);

            //reset the ball position
            ballXPos = width/2;
            ballYPos = height/2;


            //reset speed & direction
                ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
                ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;

        }

         //ballStayInCanvas
            if(ballYPos > height || ballYPos< 0) ballYSpeed *=-1;

            //computer gets a point
        if(ballXPos  < playerOneXPos  - PLAYER_WIDTH){
            scoreP2++;
            gameStarted = false;
        }

        //you get a point
        if(ballXPos  > playerTwoXPos  + PLAYER_WIDTH){
            scoreP1++;
            gameStarted = false;
        }

        //increse the ball speed
        if()
    }

    public static void main(String[] args) {
        launch();
    }
}