import controlP5.ControlP5;
import controlP5.Slider;
import controlP5.Toggle;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class ColorManager {

    PApplet app;
    Boolean isVisible;
    int numColors;
    String filePath;
    int[] colors;

    PGraphics buff;
    PImage img;
    int imgWidth, imgHeight;

    PGraphics cui;

    int margin;
    float gradientPosition;

    int swatchSize;

    ControlP5 cp5;
    Slider gPosSlider;
    Toggle gAutoScrollToggle;
    Slider gSpeedSlider;
    int pd;


    public ColorManager(PApplet _app, int _numColors, String _filePath) {

        pd = _app.pixelDensity;
        margin = 5;
        swatchSize = (int) ((320 - 3 * margin) / 4.0f);
        gradientPosition = 0.0f;

        app = _app;
        filePath = _filePath;
        numColors = _numColors;

        numColors = 8;
        colors = new int[numColors];

        buff = app.createGraphics(320 + 10, imgHeight);
        img = app.loadImage(filePath);

        imgWidth = 320;
        imgHeight = (int) (((float) img.height / (float) img.width) * 320.0f);
        buff = app.createGraphics(img.width, img.height);

        cui = app.createGraphics(320+ margin, 640);

        buff.beginDraw();
        buff.image(img, 0, 0);
        buff.endDraw();

        buff.loadPixels();

        cp5 = new ControlP5(app);

        isVisible = false;

        int uiy = 360;

        gPosSlider = cp5.addSlider("v1")
                .setPosition(margin, uiy)
                .setSize(320, (swatchSize - margin) / 2)
                .setRange(0, 1.0f)
                .setValue(0.5f)
                .setColorCaptionLabel(app.color(20, 20, 20));

        gAutoScrollToggle = cp5.addToggle("auto")
                .setPosition(margin, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 + margin)
                .setSize(160 - margin, (swatchSize - margin) / 2)
                .setValue(true);

        gSpeedSlider = cp5.addSlider("speed")
                .setPosition(margin * 2 + 160, gPosSlider.getPosition()[1] + (swatchSize - margin) / 2 + margin)
                .setSize(130 - margin, (swatchSize - margin) / 2)
                .setValue(0.0002f)
                .setRange(0.0f, 0.002f);

        cp5.hide();

    }

    public void setup() {
        //  isVisible = false;
    }

    public void update() {
        if (gradientPosition > 1.0f) gradientPosition = 0.0f;

        for (int i = 0; i < numColors; i++) {
            int buffWidth = buff.width;
            int xPos = (int) (gradientPosition * buffWidth);
            int yPos = (int) ((68*2 + i * 100*2));
            //colors[i] = buff.get(xPos, yPos); // this doesn't when not in Processing IDE
            colors[i] = buff.pixels[1440 * 2 * yPos + xPos* 2]; // account for pixeldensity of 2

//        int red = colors[i] >> 16 & 0xFF;
//        int green = colors[i] >> 8 & 0xFF;
//        int blue = colors[i] & 0xFF;
//
//        int nothing = 0;


        }

        if (gAutoScrollToggle.getBooleanValue()) {
            if(!setGradientPosition(getGradientPosition() + gSpeedSlider.getValue())){
                setGradientPosition(0.0f);
            }
            gPosSlider.setValue(getGradientPosition());

        } else {
            setGradientPosition(gPosSlider.getValue());
        }

    }

    public void draw() {

        this.update();
        if (isVisible) {

            cui.beginDraw();
            cui.fill(255);
            cui.image(buff, margin, margin, imgWidth, imgHeight);
            //cui.image(buff,0,0);

            cui.fill(255);
            cui.stroke(255);

            // draw the little white indicators
            for (int i = 0; i < numColors; i++) {
                cui.rect((int) (gradientPosition * 320.0f), (int) ((68 + i * 100) / 4.5f) + 5, 2, 2);
            }

            // draw the swatches
            for (int i = 0; i < numColors; i++) {
                cui.fill(colors[i]);
                cui.rect(margin + (swatchSize + margin) * (i % 4), margin * 2 + imgHeight + (i / 4) * (swatchSize + margin), swatchSize, swatchSize);
            }

            cui.endDraw();
            app.image(cui, margin, margin);
        }
    }

    public Boolean isVisible() {

        return isVisible;
    }

    public int getSwatchSize() {
        return swatchSize;
    }

    public float getGradientPosition() {
        return gradientPosition;
    }

    public Boolean setGradientPosition(float _gp) {

        if(_gp >= 0.0f && _gp <= 1.0f ){
            gradientPosition = _gp;
            gPosSlider.setValue(_gp);
            return true;
        }
        return false;

    }

    public int getColor(int _whichColor) {

        return colors[_whichColor];
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
        cp5.setVisible(!cp5.isVisible());
    }

    public void playPause() {
        gAutoScrollToggle.toggle();
    }


}
