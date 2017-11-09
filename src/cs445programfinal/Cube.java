package cs445programfinal;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class Cube {
    public float x, y, z;

    public Cube(int x, int y, int z){
        
        this.x=x;
        this.y=y;
        this.z=z;
    }
    
    // method: renderCube
    // purpose: method to render cube with a width, height, and length of 2 
    public void renderCube(){
        
        glBegin(GL_QUADS);
        // top
        glColor3f(0f, 0.5f, 0.5f);
        glNormal3f(0.0f, 1.0f, 0.0f);
        glVertex3f(x + -1f, y + 1f, z + 1f);
        glVertex3f(x + 1f, y + 1f, z + 1f);
        glVertex3f(x + 1f, y + 1f, z + -1f);
        glVertex3f(x + -1f, y + 1f, z + -1f);

        // front
        glColor3f(1f, 5.0f, 0.0f);
        glNormal3f(0.0f, 0.0f, 1.0f);
        glVertex3f(x + 1f, y + -1f, z + 1f);
        glVertex3f(x + 1f, y + 1f, z + 1f);
        glVertex3f(x + -1f, y + 1f, z + 1f);
        glVertex3f(x + -1f, y + -1f, z + 1f);

        // right
        glColor3f(0.5f, 0.0f, 0.5f);
        glNormal3f(1.0f, 0.0f, 0.0f);
        glVertex3f(x + 1f, y + 1f, z + -1f);
        glVertex3f(x + 1f, y + 1f, z + 1f);
        glVertex3f(x + 1f, y + -1f, z + 1f);
        glVertex3f(x + 1f, y + -1f, z + -1f);

        //left
        glColor3f(0.0f, 0.0f, 1f);
        glNormal3f(-1.0f, 0.0f, 0.0f);
        glVertex3f(x + -1f, y + -1f, z + 1f);
        glVertex3f(x + -1f, y + 1f, z + 1f);
        glVertex3f(x + -1f, y + 1f, z + -1f);
        glVertex3f(x + -1f, y + -1f, z + -1f);

        // bottom
        glColor3f(1f, 1f, 1f);
        glNormal3f(0.0f, -1.0f, 0.0f);
        glVertex3f(x + -1f, y + -1f, z + 1f);
        glVertex3f(x + 1f, y + -1f, z + 1f);
        glVertex3f(x + 1f, y + -1f, z + -1f);
        glVertex3f(x + -1f, y + -1f, z + -1f);

        //back
        glColor3f(1.0f, 0f, 0f);
        glNormal3f(0.0f, 0.0f, -1.0f);
        glVertex3f(x + 1f, y + 1f, z + -1f);
        glVertex3f(x + 1f, y + -1f, z + -1f);
        glVertex3f(x + -1f, y + -1f, z + -1f);
        glVertex3f(x + -1f, y + 1f, z + -1f);
    }
    
    // method: renderChunk
    // purpose: method to render a chunk of cube by inputted dimension
    public void renderChunk(int xyz){
        List<Cube> cubes = new ArrayList<Cube>();

        for(int i = 0; i < xyz; i++) {
            for(int j = 0; j < xyz; j++) {
                for(int k = 0; k < xyz; k++) {
                    cubes.add(new Cube(2*i, 2*j, 2*k));
                }
            }
        }

        for(int l = 0; l < cubes.size(); l++) {
            cubes.get(l).renderCube();
        }
    }
}
