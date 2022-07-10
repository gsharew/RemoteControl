package CommandExecutors;

import java.awt.*;
import java.awt.event.*;
public class PageUpScroller
{
     private static Robot robot;

     public static void ScrollUpTheComputer()
     {
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Error happended");
        }

        //pressing the key up
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        
        //release the pressed key
        robot.keyRelease(KeyEvent.VK_PAGE_UP);

     }
}