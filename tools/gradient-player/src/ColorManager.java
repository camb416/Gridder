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


    public ColorManager(PApplet _app, int _numColors, String _filePath){

        margin = 5;
        swatchSize = (int) ((320 - 3 * margin) / 4.0f);
        gradientPosition = 0.0f;

        app = _app;
        filePath = _filePath;
        numColors =  _numColors;

        numColors = 8;
        colors = new int[numColors];

        buff = app.createGraphics(320+10, imgHeight);
        img = app.loadImage(filePath);
        imgWidth = 320;
        imgHeight = (int) (((float) img.height / (float) img.width) * 320.0f);
        buff = app.createGraphics(img.width, img.height);

        cui = app.createGraphics(320,640);

        buff.beginDraw();
        buff.image(img, 0, 0);
        buff.endDraw();

    }

    public void setup(){
        isVisible = false;
    }
    public void update(){
        if (gradientPosition > 1.0f) gradientPosition = 0.0f;

        for (int i = 0; i < numColors; i++) {
            colors[i] = buff.get((int) (gradientPosition * img.width), (int) ((68 + i * 100)));
        }
    }
    public void draw(){

        this.update();
        cui.beginDraw();
        cui.fill(255);
        cui.image(buff, margin, margin, imgWidth, imgHeight);

        cui.fill(255);
        cui.stroke(255);

        for (int i = 0; i < numColors; i++) {
            cui.rect((int) (gradientPosition * 320.0f), (int) ((68 + i * 100) / 4.5f) + 5, 2, 2);
        }

        for (int i = 0; i < numColors; i++) {
            cui.fill(colors[i]);
            cui.rect(margin + (swatchSize + margin) * (i % 4), margin * 2 + imgHeight + (i / 4) * (swatchSize + margin), swatchSize, swatchSize);
        }

        cui.endDraw();
        app.image(cui,margin,margin);
    }

    public Boolean isVisible(){

        return isVisible;
    }

    public int getSwatchSize(){
        return swatchSize;
    }
    public float getGradientPosition(){
        return gradientPosition;
    }
    public void setGradientPosition(float _gp){
        gradientPosition = _gp;
    }
    public int getColor(int _whichColor){

        return colors[_whichColor];
    }


}
