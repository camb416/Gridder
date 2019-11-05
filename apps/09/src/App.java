import processing.core.PApplet;

public class App extends PApplet {

    Boolean isColorsVisible;

    Boolean bUseStroke;

    ColorManager cm;

    float count;
    float dCount;
    float prevDCount;

int numDots;
    float rowHeight, colWidth;

    public static void main(String[] args) {
        println(args);
        PApplet.main("App", args);
    }

    public void settings() {
        size(1280, 720, P3D);
        pixelDensity(2);

    }

    public void setup() {

numDots = 2048;
        rowHeight = 8;
        colWidth = 8;

        bUseStroke = false;
        isColorsVisible = true;

        cm = new ColorManager(this, 8, "apps/07/colors.png");

        count = dCount = 0.0f;

    }

    public void mousePressed() {
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
        //   dCount = floor((float)frameCount/150);
//     if(prevDCount != dCount){
//         Boolean o = cm.setGradientPosition(cm.getGradientPosition() + 0.05f);
//         if(!o){
//             cm.setGradientPosition(0.0f);
//         }
//     }


//        count += (dCount - count) / 2.0f;
//        prevDCount = dCount;
    }

    public void draw() {
        update();

        // blendMode(BLEND);
        int b = cm.getColor(7);
//        fill(red(b),green(b),blue(b),32);
//        rect(0,0,1280,720);
        background(b);

        int rings = 32;
        int ringWidth = 16;
        int dotSize = 16;
        int margin = 16;

        float angle_off = HALF_PI / 2.0f;
        noStroke();
       pushMatrix();
        translate(1280 /2, 720/2);

            for (int i = 0; i < numDots; i++) {
                translate((float)i/16.0f,0, 0.001f);
                rotateX((float)frameCount/100000.0f);

                rotateZ((float)frameCount / 12500.0f);



                // dotSize = 16 ;

                float mod = -1.0f;


                //fill(255);
                int c = cm.getColor(i%5);
                fill(red(c), green(c), blue(c), 255);
                noStroke();
                //  blendMode(SCREEN);
                // ellipse(cos((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 1280 / 2, sin((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 720 / 2, dotSize, dotSize);
                ellipse(0,0, dotSize, dotSize);
            }

       popMatrix();

        if (isColorsVisible) {

            cm.draw();

        }

    }

}
