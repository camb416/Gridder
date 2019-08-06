import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class App extends PApplet {

    PImage img;
    int imgWidth;
    int imgHeight;
    int margin = 5;

    float gradientPosition = 0.0f;
    float gradientAdder = 0.0002f;

    PGraphics buff;
    int colorA;
    int colorB;
    int colorC;
    int colorD;

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
        buff = createGraphics(320,imgHeight);
        img = loadImage("tools/gradient-player/colors.png");
        imgWidth = 320;
        imgHeight = (int) (((float) img.height / (float) img.width) * 320.0f);
        buff = createGraphics(img.width,img.height);

        buff.beginDraw();
        buff.image(img,0,0);
        buff.endDraw();

        swatchSize = (imgWidth-margin*3)/4;


    }

    public void update() {
        gradientPosition += gradientAdder;
        if (gradientPosition > 1.0f) gradientPosition = 0.0f;




    }

    public void draw() {
        update();



        background(0);



        colorA = buff.get((int)(gradientPosition*img.width),(int)((68+0*100)));
        colorB = buff.get((int)(gradientPosition*img.width),(int)((68+1*100)));
        colorC = buff.get((int)(gradientPosition*img.width),(int)((68+2*100)));
        colorD = buff.get((int)(gradientPosition*img.width),(int)((68+3*100)));

        image(buff, margin, margin, imgWidth, imgHeight);
        stroke(255);
        noFill();
        rect(margin,margin,imgWidth,imgHeight);

//        noStroke();
//        fill(red(colorA),green(colorA),blue(colorA));
//        ellipse(1280 / 2, 720 / 2, 300, 300);
//        fill(red(colorB),green(colorB),blue(colorB));
//        ellipse(1280 / 2 + 50, 720 / 2 + 100, 300, 300);
//        fill(red(colorC),green(colorC),blue(colorC));
//        ellipse(1280 / 2 - 50, 720 / 2 + 100, 300, 300);




        stroke(255);
        noFill();
       // line(gradientPosition * imgWidth + margin, margin, gradientPosition * imgWidth + margin, margin + imgHeight);
        fill(colorA);
        rect((int)(gradientPosition*320.0f),(int)((68+0*100)/4.5f),5,5);
        fill(colorB);
        rect((int)(gradientPosition*320.0f),(int)((68+1*100)/4.5f),5,5);
        fill(colorC);
        rect((int)(gradientPosition*320.0f),(int)((68+2*100)/4.5f),5,5);
        fill(colorD);
        rect((int)(gradientPosition*320.0f),(int)((68+3*100)/4.5f),5,5);

        fill(colorA);
        rect(margin,margin*2+imgHeight,swatchSize,swatchSize);
        fill(colorB);
        rect(margin*2 + swatchSize,margin*2+imgHeight,swatchSize,swatchSize);
        fill(colorC);
        rect(margin*3 + swatchSize*2,margin*2+imgHeight,swatchSize,swatchSize);
        fill(colorD);
        rect(margin*4 + swatchSize*3,margin*2+imgHeight,swatchSize,swatchSize);



      //  background(0.5);

        pushMatrix();
        translate(width/2, height/2, -30);

        newXmag = mouseX/(float)(width) * TWO_PI;
        newYmag = mouseY/(float)(height) * TWO_PI;

        float diff = xmag-newXmag;
        if (abs(diff) >  0.01) {
            xmag -= diff/4.0;
        }

        diff = ymag-newYmag;
        if (abs(diff) >  0.01) {
            ymag -= diff/4.0;
        }

        rotateX(-ymag);
        rotateY(-xmag);

        scale(120);
        beginShape(QUADS);
        noStroke();

        fill(colorA); vertex(-1,  1,  1);
        fill(colorB); vertex( 1,  1,  1);
        fill(colorC); vertex( 1, -1,  1);
        fill(colorD); vertex(-1, -1,  1);

        fill(colorB); vertex( 1,  1,  1);
        fill(colorD); vertex( 1,  1, -1);
        fill(colorA); vertex( 1, -1, -1);
        fill(colorC); vertex( 1, -1,  1);

        fill(colorD); vertex( 1,  1, -1);
        fill(colorB); vertex(-1,  1, -1);
        fill(colorC); vertex(-1, -1, -1);
        fill(colorA); vertex( 1, -1, -1);

        fill(colorB); vertex(-1,  1, -1);
        fill(colorA); vertex(-1,  1,  1);
        fill(colorD); vertex(-1, -1,  1);
        fill(colorC); vertex(-1, -1, -1);

        fill(colorB); vertex(-1,  1, -1);
        fill(colorD); vertex( 1,  1, -1);
        fill(colorB); vertex( 1,  1,  1);
        fill(colorA); vertex(-1,  1,  1);

        fill(colorC); vertex(-1, -1, -1);
        fill(colorA); vertex( 1, -1, -1);
        fill(colorC); vertex( 1, -1,  1);
        fill(colorD); vertex(-1, -1,  1);

        endShape();

        popMatrix();

    }

}
