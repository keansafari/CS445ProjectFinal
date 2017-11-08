/***************************************************************
* file: CS445Program1.java
* author: Lenny Yang
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 10/10/2017
*
* purpose: This program reads from a file called coordinates.txt
* and creates a window that displays the options of the given values 
* for Lines, Ellipses, and Circles
*
****************************************************************/ 
package cs445programfinal;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class CS445ProgramFinal {
    private FPCameraController fp = new FPCameraController(0f,0f,0f);
    private DisplayMode displayMode;
    
    public void start() {
        try {
            createWindow();
            initGL();
        fp.gameLoop();//render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
    
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    public static void main(String[] args) {
        CS445ProgramFinal basic = new CS445ProgramFinal();
        basic.start();
    }
    
}