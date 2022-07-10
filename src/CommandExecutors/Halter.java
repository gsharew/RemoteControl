package CommandExecutors;

import java.io.DataOutputStream;
import java.io.IOException;

public class Halter
{
     private static Runtime runtime = Runtime.getRuntime();
	 public static void shutDownTheComputer(DataOutputStream dataOutputStream)
     {
        //Now check if the server is running on window or linux
        String osType = System.getProperty("os.name", "unknown");

        //if the OS is windows
        if(osType.startsWith("Window"))
        {
            try {
               runtime.exec("shutdown /s /f");
            } catch (IOException e) {
               System.out.println("Error happened");
            }
        }
        
        //if the OS is linux
        else if(osType.startsWith("Linux"))
        {
            try {
                runtime.exec("shutdown -P");
            } catch (IOException e) {
                System.out.println("Error happend while halting the machine");
            }
        }

        else
        {
            try {
                dataOutputStream.writeUTF("The os is unknown by the system");
            } catch (IOException e) {
                System.out.println("Error happened");
            }
        }

     }
}