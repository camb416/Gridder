import processing.core.PApplet;

public class App extends PApplet {

    Boolean isColorsVisible;

    Boolean bUseStroke;

    ColorManager cm;

    float count;
    float dCount;
    float prevDCount;

    int rows;
    int cols;
    float rowHeight, colWidth;

    public static void main(String[] args) {
        println(args);
        PApplet.main("App", args);
    }

    public void settings() {
        size(1280, 720, P3D);

    }

    public void setup() {

        rows = 128;
        cols = 128;
        rowHeight = 8;
        colWidth = 8;

        bUseStroke = false;
        isColorsVisible = true;

        cm = new ColorManager(this, 8, "tools/gradient-player/colors.png");

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
        fill(red(b),green(b),blue(b),64);
        rect(0,0,1280,720);

        int rings = 32;
        int ringWidth = 16;
        int dotSize = 4;
        int margin = -16;

        float angle_off = HALF_PI / 2.0f;
        noStroke();
       // pushMatrix();
        translate(1280 * 2 / 3, 720 * 2 / 3);
        for (int j = 0; j < rows; j++) {
            int ringCount;
            switch (j) {
                case 0:
                    ringCount = 1;
                    break;
                case 1:
                    ringCount = 5;
                    break;
                case 2:
                    ringCount = 12;
                    break;
                default:
                    ringCount = (int) ((j + 1) * 2 * PI * (float) dotSize / ((float) dotSize + (float) margin)) * 2;
                    break;
            }


            for (int i = 0; i < cols; i++) {
                rotateZ(frameCount / 250000.0f);

                float angle_it = (TWO_PI / (ringCount));
                // dotSize = 16 ;

                float mod = -1.0f;
                if (j % 2 == 0) mod = 1.0f;
                angle_off = count * angle_it * mod;
                //fill(255);
                int c = cm.getColor(j%5);
                fill(red(c), green(c), blue(c), 255);
                noStroke();
                //  blendMode(SCREEN);
                // ellipse(cos((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 1280 / 2, sin((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 720 / 2, dotSize, dotSize);
                ellipse(-1280 * 2 / 3 + margin + i * colWidth + (colWidth / 2.0f) * (j % 2), -720 * 2 / 3 + margin + j * rowHeight, dotSize, dotSize);
            }
        }
       // popMatrix();

        if (isColorsVisible) {

            cm.draw();

        }

    }

}
