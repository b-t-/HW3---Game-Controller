// Bryan Thov and Yinglong Li
// HW 3 - Game Controller
// IDD Fall '15
// 9/11/15
// Serial Communication Loosely Based Off of code by upgrdman (Youtube Account) - http://farrellf.com/arduino/Updated_jSerialComm_Demo.java

import java.util.Scanner;
import com.fazecast.jSerialComm.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;

public class game_controller {

        public static void main(String[] args) {
                try {
                    //Constructor
                    Robot robot = new Robot();
                    SerialPort[] serialports = SerialPort.getCommPorts();

                    //Displaying Ports
                    System.out.println("Select a port:");
                    int i = 1;
                    for(SerialPort port : serialports)
                            System.out.println(i++ +  ": " + port.getSystemPortName());

                    //Choosing Port
                    Scanner s = new Scanner(System.in);
                    int chosenPort = s.nextInt();
                    SerialPort serialPort = serialports[chosenPort - 1];

                    //Open Port
                    if(serialPort.openPort())
                            System.out.println("Port opened successfully.");
                    else {
                            System.out.println("Unable to open the port.");
                            return;
                    }

                    //Get input stream
                    Scanner scan = new Scanner(serialPort.getInputStream());
                    InputStream in = serialPort.getInputStream();
                    char instr[] = {'x', 'x', 'x', 'x', 'x'};
                    char x = 'a';
                    int g = 0;
                    try {
                        while(true) {
                                x = (char)in.read();
                                g = 0;
                                while (x != 'a') {
                                    instr[g] = x;
                                    x = (char)in.read();
                                    g++;
                                }
                                //System.out.println(instr);
                                
                                //Options:
                                //instr[0] = j or x - jump
                                //instr[1] = d or x - dash
                                //instr[2] = l or x - left
                                //instr[3] = r or x - right
                                //instr[4] = s or x - shoot

                                if(instr[0] == 'j'){
                                    robot.keyPress(KeyEvent.VK_UP);
                                }

                                if(instr[1] == 'd'){
                                    robot.keyPress(KeyEvent.VK_DOWN);
                                }

                                if(instr[2] == 'l'){
                                    robot.keyPress(KeyEvent.VK_LEFT);
                                }

                                if(instr[3] == 'r'){
                                    robot.keyPress(KeyEvent.VK_RIGHT);
                                }

                                if(instr[4] == 's'){
                                    robot.keyPress(KeyEvent.VK_SPACE);
                                }

                                try {
                                    Thread.sleep(200);
                                } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }

                                if(instr[0] == 'j'){
                                    robot.keyRelease(KeyEvent.VK_UP);
                                }

                                if(instr[1] == 'd'){
                                    robot.keyRelease(KeyEvent.VK_DOWN);
                                }

                                if(instr[2] == 'l'){
                                    robot.keyRelease(KeyEvent.VK_LEFT);
                                }

                                if(instr[3] == 'r'){
                                    robot.keyRelease(KeyEvent.VK_RIGHT);
                                }

                                if(instr[4] == 's'){
                                    robot.keyRelease(KeyEvent.VK_SPACE);
                                }
                            }
                    } catch (Exception e) {e.printStackTrace();}

                } catch (Exception f) {f.printStackTrace();}
        }

}