/**
 * Copyright 2014 anderScore GmbH
 */
package com.anderscore.froscon.demo;
	
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Ralf Bommersbach
 *
 */
public class TextFlowDemoMainApp extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			String fontName = "Helvetica";
			String fontName2 = "Times New Roman";
			
			TextFlow textFlow = new TextFlow();
			textFlow.setLayoutX(40);
			textFlow.setLayoutY(20);
			
			
			Text text0 = new Text("Closed ");
			text0.setFont(Font.font(fontName, 36));
			text0.setFill(Color.DARKTURQUOISE);
			text0.strikethroughProperty().setValue(true);
			
			Text text1 = new Text("Open Source ");
			text1.setFont(Font.font(fontName, 36));
			text1.setFill(Color.DARKORANGE);
			
			// Optional: Ersetze text1 ("Open Source") durch entsprechendes piktogramm
//			ImageView image = new ImageView(new Image(Main.class.getResourceAsStream("os.png")));
//			image.translateYProperty().setValue(10);
				
			Text text2 = new Text("ist ");
			text2.setFont(Font.font(fontName, FontWeight.BOLD, 36));
			
			Text text3 = new Text("eine tolle ");
			text3.setFont(Font.font(fontName2, FontPosture.ITALIC, 40));
			text3.underlineProperty().setValue(true);
			text3.setFill(Color.DARKVIOLET);
			
			Text text4 = new Text("Sache!");
			text4.setFont(Font.font(fontName, FontWeight.BOLD, 42));
			text4.setFill(Color.DARKRED);
			text4.setRotate(25);

			
			/* Spielerei: Ein paar überflüssige Effekte demonstrieren ;) */
//			addRotationEffect(image);
//			addGlowEffect(image);
//			addDropShadowEffect(text3);
//			addDropShadowEffect(image);
			
			// Vorteil Textflow: Effekte auf alle enthaltenen Nodes anwenden
//			addDropShadowEffect(textFlow);
//			addRotationEffect(textFlow);
			
		
			// den Object-Graph bauen
			textFlow.getChildren().addAll(text0, text1, text2, text3, text4);
//			textFlow.getChildren().addAll(text0, image, text2, text3, text4);
			
			BorderPane root = new BorderPane();
			AnchorPane ap = new AnchorPane();
			ap.getChildren().add(textFlow);
				
			root.setCenter(ap);	
			Scene scene = new Scene(root,800, 600);	
			
			// Optional: horizontales Wrapping bei Window resize 
//			scene.widthProperty().addListener(
//					(observable, oldVal, newVal) -> textFlow.setMaxWidth(newVal.doubleValue())  
//					);
				
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** kleine helper Methode um Effekte auf eine Node anzuwenden
	 * 
	 * @param node Die Graph Node auf welche die Effekte angewandt werden
	 */
	@SuppressWarnings("unused")
	private void addRotationEffect(Node node) {
		RotateTransition rt = new RotateTransition(Duration.seconds(4), node);
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();
	}
	
	@SuppressWarnings("unused")
	private void addDropShadowEffect(Node node) {		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(10.0);
		ds.setOffsetX(10.0);
		ds.setColor(Color.BLACK);
		node.setEffect(ds);
		
		Reflection reflection = new Reflection(); 
	    ds.setInput(reflection);    
		   

	}

	@SuppressWarnings("unused")
	private void addGlowEffect(Node node) {   
        node.setEffect(new Glow(0.8));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
