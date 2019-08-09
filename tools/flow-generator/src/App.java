import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class App extends PApplet {

    PImage img;
    PVector field[][];



    public static void main(String[] args) {
        PApplet.main("App", args);
    }

    public void settings() {
        size(1280, 720);

    }

    public void setup() {
        img = loadImage("tools/flow-generator/noise.png");

        field = new PVector[128][72];
        for(int i = 0; i<128;i++){
            // field[i] = new PVector[72];
        for(int j=0;j<72;j++) field[i][j] = new PVector();
        }
    }

    public void keyPressed() {
        switch (key) {

            case 'd':
            case 'D':

                break;
        }

    }

    public void update() {

    }

    public void draw() {
        update();

        background(0);
        fill(255);
        image(img, 0, 0, 1280, 720);

        stroke(255,0,0);
        noFill();
        for(int i = 0; i<128;i++){
            // field[i] = new PVector[72];
            for(int j=0;j<72;j++){
                line(i*10,j*10,i*10 + field[i][j].x*10, j*10 + field[i][j].y*10 );
            }
        }


    }

}
