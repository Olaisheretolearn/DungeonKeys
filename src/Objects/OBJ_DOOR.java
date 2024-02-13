package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends SuperObject{

    public OBJ_DOOR(){
        name = "Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
