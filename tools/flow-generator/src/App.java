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
        img.loadPixels();
        int width = img.width;
        int height = img.height;
        field = new PVector[128][72];
        for (int i = 0; i < 128; i++) {
            // field[i] = new PVector[72];
            for (int j = 0; j < 72; j++) {

                float thisx = 0.0f;
                float thisy = 0.0f;
                if(i>0 && j>0 && i<(width-1) && j<(height-1)){


                int p = j * width + i;
                int nw = p - width - 1;
                int n = p - width;
                int ne = p - width + 1;
                int w = p - 1;
                int e = p + 1;
                int sw = p + width - 1;
                int s = p + width;
                int se = p + width + 1;

                thisx = (float) (red(img.pixels[e]) - red(img.pixels[w]))/255.0f;
                thisy = (float) (red(img.pixels[s]) - red(img.pixels[n]))/255.0f;
                }
                field[i][j] = new PVector(thisx,thisy);

            }
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

        stroke(255, 0, 0);
        noFill();
        for (int i = 0; i < 128; i++) {
            // field[i] = new PVector[72];
            for (int j = 0; j < 72; j++) {
                line(i * 10, j * 10, i * 10 + field[i][j].x * 20.0f, j * 10 + field[i][j].y * 20.0f);
            }
        }


    }

}
