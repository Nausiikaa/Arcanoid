import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button; 
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Graphics;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.animation.Animation; 
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class Bola extends Application
{
    static int dx = 1;
    static int dy = 1;
    boolean direccion = false;
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage escenario)
    {
        Group root = new Group();
        Circle circulo = new Circle(20.0f);
        Rectangle rectangulo = new Rectangle(300,485,100,15);
        Scene escena = new Scene(root, 500, 500);
        Random random = new Random();
        int ranX = random.nextInt((int)(escena.getWidth() - 20));
        int ranY = random.nextInt((int)(escena.getHeight()- 20));
        if(ranX < 20)
        {
            ranX = ranX + 20;
        }
        if(ranY < 20)
        {
            ranY = ranY + 20;
        }
        circulo.setFill(Color.RED);
        circulo.setCenterX(ranX);
        circulo.setCenterY(ranY);
        rectangulo.setFill(Color.BLUE);
        root.getChildren().add(circulo);
        root.getChildren().add(rectangulo);
        Timeline timeline = new Timeline();
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() 
            { 
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.LEFT) 
                    {
                        direccion = true;
                    }
                    else if(event.getCode() == KeyCode.RIGHT)
                    {
                        direccion = false;
                    }
                }
            });
        KeyFrame a = new KeyFrame(Duration.seconds(0.017), event ->{
                    double xMin = circulo.getBoundsInParent().getMinX();
                    double yMin = circulo.getBoundsInParent().getMinY();
                    double xMax = circulo.getBoundsInParent().getMaxX();
                    double yMax = circulo.getBoundsInParent().getMaxY();
                    if (xMin < 0 || xMax > escena.getWidth()) {
                        dx = dx * -1;
                    }
                    if (yMin < 0) {
                        dy = dy * -1;
                    }
                    if(rectangulo.getBoundsInParent().intersects(circulo.getBoundsInParent()))
                    {
                        dy = dy * -1;
                    }
                    circulo.setTranslateX(circulo.getTranslateX() + dx);
                    circulo.setTranslateY(circulo.getTranslateY() + dy);
                    if(rectangulo.getBoundsInParent().getMinX() > 0 && rectangulo.getBoundsInParent().getMaxX() < 500){
                        if(direccion == false)
                        {
                            rectangulo.setTranslateX(rectangulo.getTranslateX() + 1);
                        }
                        else
                        {
                            rectangulo.setTranslateX(rectangulo.getTranslateX() - 1);
                        }
                    }
                    else if(rectangulo.getBoundsInParent().getMinX() == 0)
                    {

                        if(direccion == false)
                        {
                            rectangulo.setTranslateX(rectangulo.getTranslateX() + 1);
                        }


                    }
                    else
                    {

                        if(direccion == true)
                        {
                            rectangulo.setTranslateX(rectangulo.getTranslateX() - 1);
                        }

                    }
                    if(circulo.getBoundsInParent().getMaxY() == 540){
                        Label texto = new Label("Game Over");
                        root.getChildren().add(texto);
                        texto.setTranslateX(243);
                        texto.setTranslateY(250);
                        timeline.stop();
                    }
                }
            );
            
        timeline.getKeyFrames().add(a);    
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        escenario.setScene(escena);
        escenario.show();
    }
}