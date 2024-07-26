package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import principal.GamePanel;
import principal.UtiliyTool;

public class TileManeger {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManeger(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[180];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        setup(0, "000", true,false);
        setup(1, "001", false,false);
        setup(2, "002", false,false);
        setup(3, "003", false,true);
        setup(4, "004", false,true);
        setup(5, "005", false,false);
        setup(6, "006", false,false);
        setup(7, "007", false,false);
        setup(8, "008", false,false);
        setup(9, "009", false,false);
        setup(10, "010", false,false);
        setup(11, "011", false,false);
        setup(12, "012", false,false);
        setup(13, "013", false,false);
        setup(14, "014", false,false);
        setup(15, "015", false,false);
        setup(16, "016", true,false);
        setup(17, "017", false, false);
        setup(18, "018", false, false);
        setup(19, "019", false, false);
        setup(20, "020", true,false);
        setup(21, "021", true,false);
        setup(22, "022", true,false);
        setup(23, "023", true,false);
        setup(24, "024", true,false);
        setup(25, "025", true,false);
        setup(26, "026", true,false);
        setup(27, "027", true,false);
        setup(28, "028", true,false);
        setup(29, "029", true,false);
        setup(30, "030", true,false);
        setup(31, "031", true,false);
        setup(32, "032", true, false);
        setup(33, "033", false, false);
        setup(34, "034", false, false);
        setup(35, "035", true,false);
        setup(36, "036", false,false);
        setup(37, "037", false,false);
        setup(38, "038", false,false);
        setup(39, "039", false,false);
        setup(40, "040", true,false);
        setup(41, "041", true,false);
        setup(42, "042", true,false);
        setup(43, "043", true,false);
        setup(44, "044", true,false);
        setup(45, "045", true,false);
        setup(46, "046", false, false);
        setup(47, "047", true,false);
        setup(48, "048", true,false);
        setup(49, "049", true,false);
        setup(50, "050", true,false);
        setup(51, "051", true,false);
        setup(52, "052", true,false);
        setup(53, "053", true,false);
        setup(54, "054", false,false);
        setup(55, "055", true,false);
        setup(56, "056", true,false);
        setup(57, "057", true,false);
        setup(58, "058", true,false);
        setup(59, "059", true,false);
        setup(60, "060", true,false);
        setup(61, "061", true,false);
        setup(62, "062", true,false);
        setup(63, "063", true,false);
        setup(64, "064", true,false);
        setup(65, "065", true,false);
        setup(66, "066", true,false);
        setup(67, "067", true,false);
        setup(68, "068", true,false);
        setup(69, "069", true,false);
        setup(70, "070", true,false);
        setup(71, "071", true,false);
        setup(72, "072", true,false);
        setup(73, "073", true,false);
        setup(74, "074", true,false);
        setup(75, "075", true,false);
        setup(76, "076", true,false);
        setup(77, "077", true,false);
        setup(78, "078", true,false);
        setup(79, "079", true,false);
        setup(80, "080", true,false);
        setup(81, "000", false, false);
        setup(82, "000", false, false);
        setup(83, "000", false, false);
        setup(84, "000", false, false);
        setup(85, "000", false, false);
        setup(86, "000", false, false);
        setup(87, "000", false, false);
        setup(88, "000", false, false);
        setup(89, "000", false, false);
        setup(90, "000", false, false);
        setup(91, "000", false, false);
        setup(92, "000", false, false);
        setup(93, "000", false, false);
        setup(94, "000", false, false);
        setup(95, "000", false, false);
        setup(96, "000", false, false);
        setup(97, "000", false, false);
        setup(98, "000", false, false);
        setup(99, "000", false, false);
        setup(100, "000", false, false);
        setup(101, "000", false, false);
        setup(102, "000", false, false);
        setup(103, "000", false, false);
        setup(104, "000", false, false);
        setup(105, "000", false, false);
        setup(106, "000", false, false);
        setup(107, "000", false, false);
        setup(108, "000", false, false);
        setup(109, "000", false, false);
        setup(110, "000", false, false);
        setup(111, "000", false, false);
        setup(112, "000", false, false);
        setup(113, "000", false, false);
        setup(114, "000", false, false);
        setup(115, "000", false, false);
        setup(116, "000", false, false);
        setup(117, "000", false, false);
        setup(118, "000", false, false);
        setup(119, "000", false, false);
        setup(120, "000", false, false);
        setup(121, "000", false, false);
        setup(122, "000", false, false);
        setup(123, "000", false, false);
        setup(124, "000", false, false);
        setup(125, "000", false, false);
        setup(126, "000", false, false);
        setup(127, "000", false, false);
        setup(128, "000", false, false);
        setup(129, "000", false, false);
        setup(130, "000", false, false);
        setup(131, "000", false, false);
        setup(132, "000", false, false);
        setup(133, "000", false, false);
        setup(134, "000", false, false);
        setup(135, "000", false, false);
        setup(136, "000", false, false);
        setup(137, "000", false, false);

    }

    public void setup(int index, String imageName, boolean collision, boolean isSolid) {
        UtiliyTool uTool = new UtiliyTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].isSolid = isSolid;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/pulo.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
 
 
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
 
             int tileNum = mapTileNum[worldCol][worldRow]; 
             int worldX = worldCol * gp.tileSize;
             int worldY = worldRow * gp.tileSize;
             int screenX = worldX - gp.player.worldX + gp.player.screenX;
             int screenY = worldY - gp.player.worldY + gp.player.screenY;
 
             if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
             ){
              g2.drawImage(tile[tileNum].image, screenX, screenY, null);
 
             }
             worldCol++;
 
             if(worldCol == gp.maxWorldCol){
                 worldCol = 0;
                 worldRow++;
             }
        }
     }
    
}
