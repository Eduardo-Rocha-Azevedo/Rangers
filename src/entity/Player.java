package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import principal.GamePanel;
import principal.KeyHandler;
import principal.UtiliyTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	private boolean jumping;
    private double jumpSpeed;
	private double fallingSpeed = 0;
	private boolean onGround = false;
	private double jumpStrength = 10;
	int spriteCounter = 0;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWith / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;

		solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;

		solidArea.width = 32;
		solidArea.height = 32;

		setDefultValues();
		getPlayerImage();
	}

	public void setDefultValues() {
		worldX = gp.tileSize * 1;
		worldY = gp.tileSize * 47;
		groundY = worldY; // Setting the initial ground position gp.tileSize*48;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}

	public BufferedImage setup(String imageName) {
		UtiliyTool uTool = new UtiliyTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;

	}

	public void update() {
		// Movimentação horizontal
		if (keyH.left || keyH.right) {
			if (keyH.left) {
				direction = "left";
				worldX -= speed; // Movimento à esquerda
			} else if (keyH.right) {
				direction = "right";
				worldX += speed; // Movimento à direita
			}
	
			// Verificar colisões horizontais
			collisiOn = false;
			gp.cChecker.checkTile(this);
	
			if (collisiOn) {
				if (direction.equals("left")) {
					worldX += speed; // Corrige a posição
					System.out.println("Collision on left. Adjusted X to: " + worldX);
				} else if (direction.equals("right")) {
					worldX -= speed; // Corrige a posição
					System.out.println("Collision on right. Adjusted X to: " + worldX);
				}
			}
			// Atualizar o sprite
			updateSprite();
		}
	
		// Lógica de pulo e gravidade
		if (jumping) {
			fallingSpeed += gravity;
			worldY += fallingSpeed;
	
			// Verificar se o jogador atingiu o chão
			int tileY = (worldY + gp.tileSize) / gp.tileSize;
			int tileX = worldX / gp.tileSize;
			int tileNum = gp.tileM.mapTileNum[tileX][tileY];
	
			if (gp.tileM.tile[tileNum].collision) {
				worldY = tileY * gp.tileSize - gp.tileSize;
				jumping = false;
				fallingSpeed = 0;
				System.out.println("Collision with ground. Adjusted Y to: " + worldY);
			}
		} else {
			// Verificar se o jogador está no ar
			int tileX = worldX / gp.tileSize;
			int tileY = (worldY + gp.tileSize) / gp.tileSize;
			int tileNum = gp.tileM.mapTileNum[tileX][tileY];
	
			if (!gp.tileM.tile[tileNum].collision) {
				jumping = true;
				fallingSpeed += gravity;
				System.out.println("In air. FallingSpeed: " + fallingSpeed);
			}
		}
	
		// Lógica de pulo
		if (keyH.jump && !jumping) {
			jumping = true;
			fallingSpeed = -jumpStrength;
			System.out.println("Jumping. FallingSpeed: " + fallingSpeed);
		}
	}
	
	private void updateSprite() {
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	private boolean isOnGround() {
		// Verifique se o jogador está no chão
		return worldY >= groundY;
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			String objName = gp.obj[i].name;

			switch (objName) {
				case "Key":
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMassage("You got a key!");
					break;

				case "Door":
					if (hasKey > 0) {
						gp.playSE(3);
						gp.ui.showMassage("You unlocked the door!");
						gp.obj[i] = null;
						hasKey--;
					} else {
						gp.ui.showMassage("You need a key!");
					}
					break;

				case "Boots":
					gp.playSE(2);
					gp.ui.showMassage("Speed up!");
					speed++;
					gp.obj[i] = null;
					break;

				case "Chest":
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
			}
		}
	}

	public void draw(Graphics g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}    
				if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;} 
				break;
     
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
