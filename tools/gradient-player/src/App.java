import controlP5.ControlP5;
import controlP5.Slider;
import controlP5.Toggle;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class App extends PApplet {

    ControlP5 cp5;
    Slider gPosSlider;
    Toggle gAutoScrollToggle;
    Slider gSpeedSlider;
    Toggle bUseLights;
    Toggle bUseStroke;

    PImage img;
    int imgWidth;
    int imgHeight;
    int margin = 5;

    float gradientPosition = 0.0f;
    float gradientAdder = 0.0002f;

    PGraphics buff;

    int numColors;
    int [] colors;

    int swatchSize;

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

        numColors = 8;
        colors = new int[numColors];

        buff = createGraphics(320, imgHeight);
        img = loadImage("tools/gradient-player/colors.png");
        imgWidth = 320;
        imgHeight = (int) (((float) img.height / (float) img.width) * 320.0f);
        buff = createGraphics(img.width, img.height);

        buff.beginDraw();
        buff.image(img, 0, 0);
        buff.endDraw();

        swatchSize = (int)((320-3*margin)/4.0f);
        int uiy = 360;

        cp5 = new ControlP5(this);
        gPosSlider = cp5.addSlider("v1")
                .setPosition(margin, uiy)
                .setSize(320, (swatchSize - margin) / 2)
                .setRange(0, 1.0f)
                .setValue(0.5f)
                .setColorCaptionLabel(color(20, 20, 20));

        gAutoScrollToggle = cp5.addToggle("auto")
                .setPosition(margin, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 + margin)
                .setSize(160 - margin, (swatchSize - margin) / 2);

        bUseLights = cp5.addToggle("lights")
                .setPosition(margin, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 * 2 + margin * 4)
                .setSize(160 - margin, (swatchSize - margin) / 2);

        gSpeedSlider = cp5.addSlider("speed")
                .setPosition(margin * 2 + 160, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 + margin)
                .setSize(130 - margin, (swatchSize - margin) / 2)
                .setValue(0.0002f)
                .setRange(0.0f, 0.002f);

        bUseStroke = cp5.addToggle("stroke")
                .setPosition(margin * 2 + 160, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 * 2 + margin * 4)
                .setSize(160 - margin, (swatchSize - margin) / 2);


    }

    public void keyPressed() {
        gAutoScrollToggle.toggle();
    }

    public void update() {
        if (gAutoScrollToggle.getBooleanValue()) {
            gradientPosition += gSpeedSlider.getValue();
            gPosSlider.setValue(gradientPosition);
            if (gradientPosition > 1.0f) gradientPosition = 0.0f;
        } else {
            gradientPosition = gPosSlider.getValue();
        }


        if (bUseLights.getBooleanValue()) lights();

    }

    public void draw() {
        update();


        background(0);

for(int i=0;i<numColors;i++){
    colors[i] = buff.get((int) (gradientPosition * img.width), (int) ((68 + i * 100)));
}

        fill(255);
        image(buff, margin, margin, imgWidth, imgHeight);

        fill(255);
        stroke(255);

        for(int i=0;i<numColors;i++){
            rect((int) (gradientPosition * 320.0f), (int) ((68 + i * 100) / 4.5f) + 5, 2, 2);
        }

        for(int i=0;i<numColors;i++){
            fill(colors[i]);
            rect(margin + (swatchSize+margin) * (i%4), margin * 2 + imgHeight + (i/4)*(swatchSize+margin), swatchSize, swatchSize);
        }

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
        if (bUseStroke.getBooleanValue()) {
            strokeWeight(1.0f / 120.0f);
            stroke(255);
        } else {
            noStroke();
        }

        fill(colors[0]);
        vertex(-1, 1, 1);
        fill(colors[1]);
        vertex(1, 1, 1);
        fill(colors[2]);
        vertex(1, -1, 1);
        fill(colors[3]);
        vertex(-1, -1, 1);

        fill(colors[1]);
        vertex(1, 1, 1);
        fill(colors[4]);
        vertex(1, 1, -1);
        fill(colors[5]);
        vertex(1, -1, -1);
        fill(colors[2]);
        vertex(1, -1, 1);

        fill(colors[4]);
        vertex(1, 1, -1);
        fill(colors[6]);
        vertex(-1, 1, -1);
        fill(colors[7]);
        vertex(-1, -1, -1);
        fill(colors[5]);
        vertex(1, -1, -1);

        fill(colors[6]);
        vertex(-1, 1, -1);
        fill(colors[0]);
        vertex(-1, 1, 1);
        fill(colors[3]);
        vertex(-1, -1, 1);
        fill(colors[7]);
        vertex(-1, -1, -1);

        fill(colors[6]);
        vertex(-1, 1, -1);
        fill(colors[4]);
        vertex(1, 1, -1);
        fill(colors[1]);
        vertex(1, 1, 1);
        fill(colors[0]);
        vertex(-1, 1, 1);

        fill(colors[7]);
        vertex(-1, -1, -1);
        fill(colors[5]);
        vertex(1, -1, -1);
        fill(colors[2]);
        vertex(1, -1, 1);
        fill(colors[3]);
        vertex(-1, -1, 1);

        endShape();

        popMatrix();


    }

}
