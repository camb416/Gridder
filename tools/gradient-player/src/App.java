import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class App extends PApplet {

    PImage img;
    int imgWidth;
    int imgHeight;
    int margin = 5;

    float gradientPosition = 0.0f;
    float gradientAdder = 0.01f;

    PGraphics buff;
    int colorA;
    int colorB;
    int colorC;

    int swatchSize;

    public static void main(String[] args) {
        println(args);
        PApplet.main("App", args);
    }

    public void settings() {
        size(1280, 720);

    }

    public void setup() {
        buff = createGraphics(320,imgHeight);
        img = loadImage("gradient.jpg");
        imgWidth = 320;
        imgHeight = (int) (((float) img.height / (float) img.width) * 320.0f);
        buff = createGraphics(320,imgHeight);

        buff.beginDraw();
        buff.image(img,0,0,imgWidth, imgHeight);
        buff.endDraw();

        swatchSize = (imgWidth-margin*2)/3;


    }

    public void update() {
        gradientPosition += gradientAdder;
        if (gradientPosition > 1.0f) gradientPosition = 0.0f;




    }

    public void draw() {
        update();



        background(0);



        colorA = buff.get((int)(gradientPosition*320.0f),imgHeight/3);
        colorB = buff.get((int)(gradientPosition*320.0f),imgHeight/2);
        colorC = buff.get((int)(gradientPosition*320.0f),imgHeight/3*2);

        image(buff, margin, margin, imgWidth, imgHeight);
        stroke(255);
        noFill();
        rect(margin,margin,imgWidth,imgHeight);
        noStroke();
        fill(red(colorA),green(colorA),blue(colorA));
        ellipse(1280 / 2, 720 / 2, 300, 300);
        fill(red(colorB),green(colorB),blue(colorB));
        ellipse(1280 / 2 + 50, 720 / 2 + 100, 300, 300);
        fill(red(colorC),green(colorC),blue(colorC));
        ellipse(1280 / 2 - 50, 720 / 2 + 100, 300, 300);




        stroke(255);
        noFill();
       // line(gradientPosition * imgWidth + margin, margin, gradientPosition * imgWidth + margin, margin + imgHeight);
        fill(colorA);
        rect((int)(gradientPosition*320.0f),imgHeight/3,5,5);
        fill(colorB);
        rect((int)(gradientPosition*320.0f),imgHeight/2,5,5);
        fill(colorC);
        rect((int)(gradientPosition*320.0f),imgHeight/3*2,5,5);

        fill(colorA);
        rect(margin,margin*2+imgHeight,swatchSize,swatchSize);
        fill(colorB);
        rect(margin*2 + swatchSize,margin*2+imgHeight,swatchSize,swatchSize);
        fill(colorC);
        rect(margin*3 + swatchSize*2,margin*2+imgHeight,swatchSize,swatchSize);



    }

}
