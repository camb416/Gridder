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

    Boolean isColorsVisible;

    ColorManager cm;

    PImage img;
    int imgWidth;
    int imgHeight;
    int margin = 5;

    // float gradientPosition = 0.0f;

    PGraphics buff;

    int numColors;
    int[] colors;



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

        isColorsVisible = true;

        cm = new ColorManager(this, 8,"tools/gradient-player/colors.png");



        int uiy = 360;
        int swatchSize = cm.getSwatchSize();

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
        switch (key) {
            case ' ':
                gAutoScrollToggle.toggle();
                break;
            case 'd':
            case 'D':
                cp5.setVisible(!cp5.isVisible());
                isColorsVisible = !isColorsVisible;

                break;
        }

    }

    public void update() {
        if (gAutoScrollToggle.getBooleanValue()) {
            cm.setGradientPosition(cm.getGradientPosition() + gSpeedSlider.getValue());
            gPosSlider.setValue(cm.getGradientPosition());

        } else {
            cm.setGradientPosition(gPosSlider.getValue());
        }


        if (bUseLights.getBooleanValue()) lights();

    }

    public void draw() {
        update();


        background(0);



        if (isColorsVisible) {


            cm.draw();

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


    }

}
