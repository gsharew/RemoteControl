package CommandExecutors;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.Desktop.Action;
public class FilesToOpen{
        public static void openTheRequestedFile(String uri)
        {
             Desktop desktop = Desktop.getDesktop();
             boolean isSupported = desktop.isSupported(Action.OPEN);
        
             if(isSupported)
             {
                try {
                    File file = new File(uri);
                    desktop.open(file);
                } 
                catch(IllegalArgumentException e)
                {
                    System.out.println("Unable to open the file");
                }

                catch (IOException e) {
                    System.out.println("The file has no associated application to open");
                }

                catch(NullPointerException e)
                {
                    System.out.println("The file is null or corrupted");
                }

                catch(SecurityException  e)
                {
                    System.out.println("Permission denied to open the file");
                }
             }

             else 
             {
                System.out.println("XDG-OPEN is Not supported");
             }
        }
}
