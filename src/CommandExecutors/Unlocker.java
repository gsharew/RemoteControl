package CommandExecutors;

import java.awt.*;
import java.awt.event.*;

public class Unlocker
{
    private static Robot robot = null;
    public static void unLockTheComputer(String ComputerPassword)
    {
        String actualPassword = ComputerPassword.substring(7);
        try {
          robot = new Robot();
          for(char c : actualPassword.toCharArray())
          {
               int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
               if (KeyEvent.CHAR_UNDEFINED == keyCode)
               {
                    throw  new RuntimeException("Key Code not found");
               }

               robot.keyPress(keyCode);
               robot.keyRelease(keyCode);
          }

          robot.keyPress(KeyEvent.VK_ENTER);
          robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            System.out.println("Error happened");
        }

        // robot.keyPress(KeyEvent.VK_ENTER);
        // robot.keyRelease(KeyEvent.VK_ENTER);
      
    }
}
