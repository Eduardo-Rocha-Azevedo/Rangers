package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1,up2, down1, down2, left1, left2, right1, right2;
	public String direction;

	public int spriteCouter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public int solidAreaDefultX, solidAreaDefultY;
	public boolean collisioOn = false;

	 // Variáveis para o pulo
	 public boolean jumping = false;
	 public double velocityY = 0;
	 public double gravity = 0.5;
	 public double jumpPower = 10;
	 public int groundY; // Define a posição Y do solo
    public boolean collisiOn;
	

	    
}
