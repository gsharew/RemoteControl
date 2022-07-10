
import CommandExecutors.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Thread {

    ServerSocket serverSocket;
    Socket socket;
    Locker locker;
    Unlocker unlocker;
    Restarter restarter;
    Screenshot screenshot;
    Halter halter;
    PageDownScroller pageDownScroller;
    PageUpScroller pageUpScroller;
    String incommingCommand;
    Boolean serverRunning;
    static int previousPort = -1;
    Main() {
        locker = new Locker();
        unlocker = new Unlocker();
        restarter = new Restarter();
        halter = new Halter();
        screenshot = new Screenshot();
        pageDownScroller = new PageDownScroller();
        pageUpScroller = new PageUpScroller();
    }

    public void initiateTheConnection() {
        Main main = new Main();
        main.start();
    }

    @Override
    public void run() {
        try {
            int  maxInputChance = 0, port = 0;
            Scanner input = null;
            if(previousPort == -1)
            while(maxInputChance < 3)
            {
                try {
                    if(maxInputChance == 0)
                    {
                        System.out.println("Please make sure that your mobile app server port is the same us with the current");
                        System.out.println("Enter a Port range between 4 and 6 integers");
                        input = new Scanner(System.in);
                    }

                     port = input.nextInt();
                     if (port < 1000 || port > 65535) {
                        System.out.println("Invalid Port");
                        maxInputChance++;
                    } else {
                         previousPort = port;
                        break;
                    }
                }

                catch (InputMismatchException e)
                {
                    System.out.println("Invalid Input");
                    System.exit(0);
                }
            }

            if(maxInputChance >=3 )
            {
                System.out.println("You have reached max allowed chance");
                System.out.println("Bye...");
                System.exit(0);
            }

            System.out.println("The Server is Listening on Port " + previousPort);
            serverSocket = new ServerSocket(previousPort);
            socket = serverSocket.accept();
            serverRunning = true;
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                incommingCommand = dataInputStream.readUTF();
                if (incommingCommand.startsWith("DeviceName:")) {
                    String SystemName = InetAddress.getLocalHost().getHostName();
                    dataOutputStream.writeUTF("ServerName" + SystemName);
                    System.out.println(
                            "Connected to: " + incommingCommand.substring(11));
                    System.out.println("To Stop The Server hit CTRL+C");
                }

                if (incommingCommand.equals("stillConnected")) {
                    dataOutputStream.writeUTF("yesConnected");
                }

                else if (incommingCommand.equals("Lock"))
                {
                    Locker.lockTheComputer(dataOutputStream);
                }

                else if (incommingCommand.equals("PNEXT")) {
                    PageDownScroller.ScrollDownTheComputer();
                }

                else if (incommingCommand.equals("PPREVIOUS")) {
                    PageUpScroller.ScrollUpTheComputer();
                }

                else if(incommingCommand.equals("ShutDown"))
                {
                    Halter.shutDownTheComputer(dataOutputStream);
                }

                else if(incommingCommand.equals("Restart"))
                {
                    Restarter.restartTheComputer(dataOutputStream);
                }

                else if(incommingCommand.equals("ScreenShot"))
                {
                    Screenshot.screenshotTheComputer(dataOutputStream);
                }

                else if(incommingCommand.contains("UnLock"))
                {
                    //Now check if the server is running on window or linux
                    String osType = System.getProperty("os.name", "unknown");

                    //if the OS is windows
                    if(osType.contains("Window"))
                    {
                        dataOutputStream.writeUTF("Unlock is not supported on window");
                    }

                    //if the OS is linux
                    else if(osType.contains("Linux"))
                    {
                        String ComputerPassword = incommingCommand;
                        Unlocker.unLockTheComputer(ComputerPassword);
                    }

                    else
                    {
                        dataOutputStream.writeUTF("Unable to read your system property");
                    }
                }

                else if(incommingCommand.startsWith("OpeningFile:"))
                {
                    //the number found on that substring is the length of OpeningFile:file://
                    String actualUri = incommingCommand.substring(19);
                    FilesToOpen.openTheRequestedFile(actualUri);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        } catch (IOException e) {
            try {
                if(serverSocket != null)
                    serverSocket.close();
            } catch (IOException e1) {
                e.printStackTrace();
            }
            System.out.println("Make sure the port is not taken");
            // make the server to relisten again if it was disconnected from the client
            initiateTheConnection();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}