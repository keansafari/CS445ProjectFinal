/***************************************************************
* file: FPCameraController.java
* author: Lenny Yang, Kean Jafari, Hanbo Ye, Christian Angelo
* class: CS 445 – Computer Graphics
*
* assignment: Program Final
* date last modified: 11/08/2017
*
* purpose: This class creates a first person camera controller
****************************************************************/ 
package cs445programfinal;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.Sys;

public class FPCameraController {
    //3d vector to store the camera's position in
    private Vector3f position = null;
    private Vector3f lPosition = null;
    //the rotation around the Y axis of the camera
    private float yaw = 0.0f;
    //the rotation around the X axis of the camera
    private float pitch = 0.0f;
    private Vector3Float me;
    
    public FPCameraController(float x, float y, float z){
        //instantiate position Vector3f to the x y z params.
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x,y,z);
        lPosition.x = 0f;
        lPosition.y = 15f;
        lPosition.z = 0f;
    }
    
    // method: yaw
    // purpose: increment the camera's current yaw rotation
    public void yaw(float amount){
        //increment the yaw by the amount param
        yaw += amount;
    }
    
    // method: pitch
    // purpose: increment the camera's current yaw rotation
    public void pitch(float amount){
        //increment the pitch by the amount param
        pitch -= amount;
    }
    
    // method: walkForward
    // purpose: moves the camera forward relative to its current rotation (yaw)
    public void walkForward(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // method: walkBackwards
    // purpose: moves the camera backward relative to its current rotation (yaw)
    public void walkBackwards(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }
    
    // method: strafeLeft
    // purpose: strafes the camera left relative to its current rotation (yaw)
    public void strafeLeft(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // method: strafeRight
    // purpose: strafes the camera right relative to its current rotation (yaw)
    public void strafeRight(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // method: moveUp
    // purpose: moves the camera up relative to its current rotation (yaw)
    public void moveUp(float distance){
        position.y -= distance;
    }
    
    // method: moveDown
    // purpose: moves the camera down
    public void moveDown(float distance){
        position.y += distance;
    }
    
    // method: lookThrough
    // purpose: translates and rotate the matrix so that it looks through the camera
    //this does basically what gluLookAt() does
    public void lookThrough(){
        //roatate the pitch around the X axis
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //roatate the yaw around the Y axis
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        glTranslatef(position.x, position.y, position.z);
    }
    
    // method: gameLoop
    // purpose: graphic render loop 
    public void gameLoop(){
        FPCameraController camera = new FPCameraController(0, 0, 0);
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //length of frame
        float lastTime = 0.0f; // when the last frame was
        long time = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = 0.05f;
        //hide the mouse
        Mouse.setGrabbed(true);
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            time = Sys.getTime();
            lastTime = time;
            //distance in mouse movement
            //from the last getDX() call.
            dx = Mouse.getDX();
            //distance in mouse movement
            //from the last getDY() call.
            dy = Mouse.getDY();
            //controll camera yaw from x movement fromt the mouse
            camera.yaw(dx * mouseSensitivity);
            //controll camera pitch from y movement fromt the mouse
            camera.pitch(dy * mouseSensitivity);
            /*
            when passing in the distance to move
            *we times the movementSpeed with dt this is a time scale
            *so if its a slow frame u move more then a fast frame
            *so on a slow computer you move just as fast as on a fast computer
            */
            //move forward
            if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)){
            camera.walkForward(movementSpeed);
            }
            //move backwards
            if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            camera.walkBackwards(movementSpeed);
            }
            //strafe left 
            if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
                camera.strafeLeft(movementSpeed);
            }
            //strafe right
            if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
                camera.strafeRight(movementSpeed);
            }
             //move up
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                camera.moveUp(movementSpeed);
            }
            //move down
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                camera.moveDown(movementSpeed);
            }
            //set the modelview matrix back to the identity
            glLoadIdentity();
            //look through the camera before you draw anything
            camera.lookThrough();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            //you would draw your scene here.
            render();
            //draw the buffer to the screen
            Display.update();
            Display.sync(60);
            }
        Display.destroy(); 
    }
    
    // method: render
    // purpose: graphic render 
    private void render() {
        try{
            
            glEnable(GL_DEPTH_TEST);
            glBegin(GL_QUADS);
            
            // top
            glColor3f(0f, 0.5f, 0.5f);
            glNormal3f(0.0f, 1.0f, 0.0f);
            glVertex3f(-1f, 1f, 1f);
            glVertex3f(1f, 1f, 1f);
            glVertex3f(1f, 1f, -1f);
            glVertex3f(-1f, 1f, -1f);

            // front
            glColor3f(1f, 5.0f, 0.0f);
            glNormal3f(0.0f, 0.0f, 1.0f);
            glVertex3f(1f, -1f, 1f);
            glVertex3f(1f, 1f, 1f);
            glVertex3f(-1f, 1f, 1f);
            glVertex3f(-1f, -1f, 1f);

            // right
            glColor3f(0.5f, 0.0f, 0.5f);
            glNormal3f(1.0f, 0.0f, 0.0f);
            glVertex3f(1f, 1f, -1f);
            glVertex3f(1f, 1f, 1f);
            glVertex3f(1f, -1f, 1f);
            glVertex3f(1f, -1f, -1f);
            
            //left
            glColor3f(0.0f, 0.0f, 1f);
            glNormal3f(-1.0f, 0.0f, 0.0f);
            glVertex3f(-1f, -1f, 1f);
            glVertex3f(-1f, 1f, 1f);
            glVertex3f(-1f, 1f, -1f);
            glVertex3f(-1f, -1f, -1f);
            
            // bottom
            glColor3f(1f, 1f, 1f);
            glNormal3f(0.0f, -1.0f, 0.0f);
            glVertex3f(-1f, -1f, 1f);
            glVertex3f(1f, -1f, 1f);
            glVertex3f(1f, -1f, -1f);
            glVertex3f(-1f, -1f, -1f);
            
            //back
            glColor3f(1.0f, 0f, 0f);
            glNormal3f(0.0f, 0.0f, -1.0f);
            glVertex3f(1f, 1f, -1f);
            glVertex3f(1f, -1f, -1f);
            glVertex3f(-1f, -1f, -1f);
            glVertex3f(-1f, 1f, -1f);
            
            glEnd();
        }
        catch(Exception e){
        }
    }
}

