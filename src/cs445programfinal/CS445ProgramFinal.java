/***************************************************************
* file: CS445ProgramFinal.java
* author: Lenny Yang, Kean Jafari, Hanbo Ye, Christian Angelo
* class: CS 445 – Computer Graphics
*
* assignment: Program Final
* date last modified: 11/08/2017
*
* purpose: This program creates a 3D world similar to Minecraft
****************************************************************/ 
package cs445programfinal;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class CS445ProgramFinal {
    private FPCameraController fp = new FPCameraController(0f,0f,0f);
    private DisplayMode displayMode;
    
    // method: start
    // purpose:  method to create the window, initalize the GL and render the 
    //graphics in this order    
    public void start() {
        try {
            createWindow();
            initGL();
        fp.gameLoop();//render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // method: createWindow
    // purpose:  method to set the display window not fullscreen, set dimensions
    // and set the title of the window as well as creating it    
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] =
        Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }   
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle("CS445 Final Program");
        Display.create();
    }
    
    // method: initGL
    // purpose:  method to initialize the GL options 
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    // method: main
    // purpose: takes in user input from text file and renders in openGL window  
    public static void main(String[] args) {
        CS445ProgramFinal basic = new CS445ProgramFinal();
        basic.start();
    }
    
}