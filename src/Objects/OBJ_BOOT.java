package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOT extends SuperObject {
    public OBJ_BOOT(){
        name = "Boots";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }}
