package CommandExecutors;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
public class Screenshot
{
    public static void screenshotTheComputer(DataOutputStream dataOutputStream)
    {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

            //path to create a directory on Pictures
            String path1 = "RemoteControlScreenShots";
            File pathAsFile = new File(path1);

            if (!Files.exists(Paths.get(path1))) {
                pathAsFile.mkdir();
            }
 
            int total_items_on_the_dir = pathAsFile.list().length;
            File file = new File(path1 + "/screen-shot" + total_items_on_the_dir + ".png");

            //checks if screenshot is captured correctly
            boolean status = ImageIO.write(bufferedImage, "png", file);

            //means if the screen is captured correctly
            if(status)
            {
                dataOutputStream.writeUTF("Saved on the current Folder");
            }

            else 
            {
                dataOutputStream.writeUTF("Unable to Capture the Screen Shot");
            }

            //System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());
 
        } catch (Exception ex) {
           System.out.println("Error Capturing ScreenShot");
        }
    }
}