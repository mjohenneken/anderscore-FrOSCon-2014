/**
 * Copyright 2014 anderScore GmbH
 */
package com.anderscore.froscon.demo;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Ralf Bommersbach
 *
 */
public class ModelViewerMainApp extends Application {



    @Override
    public void start(Stage primaryStage) {
       
        primaryStage.setTitle("Imported Model Viewer");
        
        try {     	
            // Das Model aus der FXML Datei laden
            FXMLLoader loader = new FXMLLoader(); 
            loader.setLocation(ModelViewerMainApp.class.getResource("model/x-wing.fxml"));
            Group mustang = (Group) loader.load();  
               
            // Das Model soll sich um die Y-Achse drehen
            RotateTransition rt = new RotateTransition(Duration.seconds(10), mustang);
            rt.axisProperty().set(new Point3D(0, 1, 0));
            rt.setByAngle(360);
            rt.setInterpolator(Interpolator.LINEAR);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.play();
            
            // Kamera initialisieren (Position, Field of View, ...)
            PerspectiveCamera camera = new PerspectiveCamera(true);
            camera.getTransforms().addAll(
            		new Rotate(0, Rotate.X_AXIS),
                    new Rotate(0, Rotate.Y_AXIS),
                    new Rotate(180, Rotate.Z_AXIS),
                    new Translate(0, 0, -20));     
            
            camera.fieldOfViewProperty().set(45);
   
            // Object Graph bauen
            Group root = new Group();       
            root.getChildren().add(camera);
            root.getChildren().add(mustang);
             
            // eigene Lichtquelle (optional)
//            addLight(root);       
            
            SubScene subScene = new SubScene(root, 1024, 768, true, SceneAntialiasing.BALANCED);
            subScene.setFill(Color.TRANSPARENT);
            subScene.setCamera(camera);
                     
            Scene scene = new Scene(new Group(subScene));
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    private void addLight(Group root) {
        // Lichtquelle
      AmbientLight ambient = new AmbientLight(Color.rgb(40, 40, 40));
      root.getChildren().add(ambient);
      
      PointLight lightSource = new PointLight(Color.rgb(40, 80, 190));
      lightSource.translateXProperty().bind(new SimpleDoubleProperty(-400));
      lightSource.translateYProperty().bind(new SimpleDoubleProperty(-1000));
      lightSource.translateZProperty().bind(new SimpleDoubleProperty(-8000));
      lightSource.lightOnProperty().bind(new SimpleBooleanProperty(true));
      root.getChildren().add(lightSource);
    }
    
//    // Kamera initialisieren (Position, Field of View, ...)
//    PerspectiveCamera camera = new PerspectiveCamera(false);
//    camera.getTransforms().addAll(
//    		new Rotate(0, Rotate.X_AXIS),
//            new Rotate(0, Rotate.Y_AXIS),
//            new Rotate(180, Rotate.Z_AXIS),
//            new Translate(-200, -800, -3200));                
//    camera.fieldOfViewProperty().set(45);

}