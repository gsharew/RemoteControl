package CommandExecutors;

import java.awt.*;
import java.awt.event.*;
public class PageDownScroller
{
     private static Robot robot;
     public static void ScrollDownTheComputer()
     {
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Error happended");
        }
        
        //press the page down keyboard
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        //release the pressed page down keyboard
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
     }
}