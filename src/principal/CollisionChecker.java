package principal;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        entity.collisiOn = false; // Resetar colisão antes da verificação
    
        // Cálculo das bordas da área sólida
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
    
        int tileNum1, tileNum2;
    
        // Verificação de colisão vertical (teto e chão)
        if (entity.velocityY < 0) { // Subindo
            int newTopRow = (entityTopWorldY + (int) entity.velocityY) / gp.tileSize;
            tileNum1 = getSafeTileNum(entityLeftCol, newTopRow);
            tileNum2 = getSafeTileNum(entityRightCol, newTopRow);
    
            System.out.println("Checking vertical collision up");
            System.out.println("Tile1: " + tileNum1 + " isSolid: " + gp.tileM.tile[tileNum1].isSolid);
            System.out.println("Tile2: " + tileNum2 + " isSolid: " + gp.tileM.tile[tileNum2].isSolid);
    
            if (isValidTile(tileNum1) || isValidTile(tileNum2)) {
                entity.collisiOn = true;
                // Ajustar a posição para não atravessar o teto
                entity.worldY = (newTopRow + 1) * gp.tileSize - entity.solidArea.height;
                entity.velocityY = 0; // Para de subir
                System.out.println("Collision with ceiling. Adjusted Y to: " + entity.worldY);
            }
        }
    
        if (entity.velocityY > 0) { // Descendo
            int newBottomRow = (entityBottomWorldY + (int) entity.velocityY) / gp.tileSize;
            tileNum1 = getSafeTileNum(entityLeftCol, newBottomRow);
            tileNum2 = getSafeTileNum(entityRightCol, newBottomRow);
    
            System.out.println("Checking vertical collision down");
            System.out.println("Tile1: " + tileNum1 + " isSolid: " + gp.tileM.tile[tileNum1].isSolid);
            System.out.println("Tile2: " + tileNum2 + " isSolid: " + gp.tileM.tile[tileNum2].isSolid);
    
            if (isValidTile(tileNum1) || isValidTile(tileNum2)) {
                entity.collisiOn = true;
                // Ajustar a posição para não atravessar o chão
                entity.worldY = (newBottomRow * gp.tileSize) - entity.solidArea.y;
                entity.velocityY = 0; // Para de descer
                System.out.println("Collision with ground. Adjusted Y to: " + entity.worldY);
            }
        }
    
        if (entity.direction.equals("left")) {
            int newLeftCol = (entityLeftWorldX - (int) entity.speed) / gp.tileSize;
            tileNum1 = getSafeTileNum(newLeftCol, entityTopRow);
            tileNum2 = getSafeTileNum(newLeftCol, entityBottomRow);
        
            if (isValidTile(tileNum1) || isValidTile(tileNum2)) {
                entity.collisiOn = true;
                entity.worldX = (entityLeftCol + 1) * gp.tileSize;
            }
        }
        
        if (entity.direction.equals("right")) {
            int newRightCol = (entityRightWorldX + (int) entity.speed) / gp.tileSize;
            tileNum1 = getSafeTileNum(newRightCol, entityTopRow);
            tileNum2 = getSafeTileNum(newRightCol, entityBottomRow);
        
            if (isValidTile(tileNum1) || isValidTile(tileNum2)) {
                entity.collisiOn = true;
                entity.worldX = (entityRightCol - 1) * gp.tileSize + gp.tileSize - entity.solidArea.width;
            }
        }
    }
    
    // Função auxiliar para evitar acesso a índices fora dos limites
    private int getSafeTileNum(int col, int row) {
        if (col < 0 || col >= gp.tileM.mapTileNum.length || row < 0 || row >= gp.tileM.mapTileNum[0].length) {
            return 0; // Retornar um tile padrão não sólido se fora dos limites
        }
        return gp.tileM.mapTileNum[col][row];
    }
     
    private boolean isValidTile(int tileNum) {
        return gp.tileM.tile[tileNum].isSolid; // Verifica se o tile é sólido
    }
  
    public int checkObject(Entity entity, boolean player){
        int  index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //Get the entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisiOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisiOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                           if(gp.obj[i].collision == true){
                                entity.collisiOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisiOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;         
                }
                entity.solidArea.x = entity.solidAreaDefultX;
                entity.solidArea.y = entity.solidAreaDefultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefultY;
            }
        }
        return index;
    }
}
