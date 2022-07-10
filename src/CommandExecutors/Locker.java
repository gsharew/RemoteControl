package CommandExecutors;

import java.io.DataOutputStream;
import java.io.IOException;
public class Locker
{
    private static Runtime runtime = Runtime.getRuntime();

	public static void lockTheComputer(DataOutputStream dataOutputStream)
    {
                //Now check if the server is running on window or linux
                String osType = System.getProperty("os.name", "unknown");

                //if the OS is windows
                if(osType.contains("Window"))
                {
                    try {
                        runtime.exec("Rundll32.exe user32.dll,LockWorkStation");
                    }catch (IOException e) {
                        System.out.println("Error happened while locking your computer");
                    }
                }
                
                //if the OS is linux
                else if(osType.contains("Linux"))
                {
                    try {
                        runtime.exec("dbus-send --type=method_call --dest=org.gnome.ScreenSaver /org/gnome/ScreenSaver org.gnome.ScreenSaver.Lock");
                    } catch (IOException e) {
                        System.out.println("Error happend while Locking the machine");
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