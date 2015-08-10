package view;

import dataParse.AlbumDataPrep;
import processing.core.*;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Textarea;
import controlP5.Textfield;

public class ToolViewer extends PApplet {
	
	private AlbumDataPrep x;
	private ControlP5 cp5;
	private Textfield textfield;
	
	public ToolViewer(){
		 
	}	
	
	public void setup(){
		size(300,600);
		PFont font = createFont("arial",20);
		textFont(font);
		cp5 = new ControlP5(this);
		
		//Input textfield 1
		textfield = cp5.addTextfield("input")
					   .setPosition(40,50)
		 	           .setSize(200,40)
		 	           .setFont(font)
		 	           .setFocus(true)
		 	           .setColor(color(255,0,0));
		textfield.setAutoClear(false);
		
	}
	
	public void draw(){
		background(0);
		  fill(255);
	}

}
