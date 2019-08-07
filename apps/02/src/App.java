import processing.core.PApplet;

public class App extends PApplet {

    Boolean isColorsVisible;

    Boolean bUseStroke;

    ColorManager cm;

    float count;
    float dCount;

    float xmag, ymag = 0;
    float newXmag, newYmag = 0;

    public static void main(String[] args) {
        println(args);
        PApplet.main("App", args);
    }

    public void settings() {
        size(1280, 720, P3D);

    }

    public void setup() {

        bUseStroke = false;
        isColorsVisible = true;

        cm = new ColorManager(this, 8,"tools/gradient-player/colors.png");

        count = dCount = 0.0f;

    }
public void mousePressed(){
        dCount = 0.0f;
}
    public void keyPressed() {
        switch (key) {
            case ' ':
                cm.playPause();
                break;
            case 'd':
            case 'D':
                cm.toggleVisibility();
                break;
        }

    }

    public void update() {
dCount ++;
count += (dCount-count)/4.0f;
    }

    public void draw() {
        update();

        background(0);

        if (isColorsVisible) {

            cm.draw();

        }
/*
        pushMatrix();
        translate(width / 2, height / 2, -30);

        newXmag = mouseX / (float) (width) * TWO_PI;
        newYmag = mouseY / (float) (height) * TWO_PI;

        float diff = xmag - newXmag;
        if (abs(diff) > 0.01) {
            xmag -= diff / 4.0;
        }

        diff = ymag - newYmag;
        if (abs(diff) > 0.01) {
            ymag -= diff / 4.0;
        }

        rotateX(-ymag);
        rotateY(-xmag);

        scale(120);
        beginShape(QUADS);
        if (bUseStroke) {
            strokeWeight(1.0f / 120.0f);
            stroke(255);
        } else {
            noStroke();
        }

        fill(cm.getColor(0));
        vertex(-1, 1, 1);
        fill(cm.getColor(1));
        vertex(1, 1, 1);
        fill(cm.getColor(2));
        vertex(1, -1, 1);
        fill(cm.getColor(3));
        vertex(-1, -1, 1);

        fill(cm.getColor(1));
        vertex(1, 1, 1);
        fill(cm.getColor(4));
        vertex(1, 1, -1);
        fill(cm.getColor(5));
        vertex(1, -1, -1);
        fill(cm.getColor(2));
        vertex(1, -1, 1);

        fill(cm.getColor(4));
        vertex(1, 1, -1);
        fill(cm.getColor(6));
        vertex(-1, 1, -1);
        fill(cm.getColor(7));
        vertex(-1, -1, -1);
        fill(cm.getColor(5));
        vertex(1, -1, -1);

        fill(cm.getColor(6));
        vertex(-1, 1, -1);
        fill(cm.getColor(0));
        vertex(-1, 1, 1);
        fill(cm.getColor(3));
        vertex(-1, -1, 1);
        fill(cm.getColor(7));
        vertex(-1, -1, -1);

        fill(cm.getColor(6));
        vertex(-1, 1, -1);
        fill(cm.getColor(4));
        vertex(1, 1, -1);
        fill(cm.getColor(1));
        vertex(1, 1, 1);
        fill(cm.getColor(0));
        vertex(-1, 1, 1);

        fill(cm.getColor(7));
        vertex(-1, -1, -1);
        fill(cm.getColor(5));
        vertex(1, -1, -1);
        fill(cm.getColor(2));
        vertex(1, -1, 1);
        fill(cm.getColor(3));
        vertex(-1, -1, 1);

        endShape();

        popMatrix();
*/

int rings = 64;
int ringWidth = 20;
int dotSize = 10;
float d = 10.0f;
float angle_off = HALF_PI/2.0f;
noStroke();
        fill(255);
        for(int j=0;j<rings;j++) {
            for (int i = 0; i < (j+1); i++) {
                float angle_it = TWO_PI / ((float)j + 1);

                angle_off = HALF_PI + count / 100.0f * j / 50.0f;
                fill(cm.getColor(j%7));
                ellipse(cos((float)i* angle_it + angle_off) * j*ringWidth  + 1280/2, sin((float)i* angle_it + angle_off) * j * ringWidth  + 720/2, dotSize, dotSize);
            }
        }

    }

}
