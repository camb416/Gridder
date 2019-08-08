import processing.core.PApplet;

public class App extends PApplet {

    Boolean isColorsVisible;

    Boolean bUseStroke;

    ColorManager cm;

    float count;
    float dCount;
    float prevDCount;

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
        dCount = floor((float)frameCount/150);
     if(prevDCount != dCount){
         Boolean o = cm.setGradientPosition(cm.getGradientPosition() + 0.05f);
         if(!o){
             cm.setGradientPosition(0.0f);
         }
     }


        count += (dCount - count) / 2.0f;
        prevDCount = dCount;
    }

    public void draw() {
        update();

        // blendMode(BLEND);
        background(cm.getColor(7));

        int rings = 32;
        int ringWidth =16;
        int dotSize = 128;
        int margin = 16;

        float angle_off = HALF_PI / 2.0f;
        noStroke();

        for (int j = 0; j < rings; j++) {
            int ringCount;
            switch(j){
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
                    ringCount = (int)((j+1)*2*PI*(float)dotSize/((float)dotSize+(float)margin))*2;
                    break;
            }
            for (int i = 0; i < ringCount; i++) {
                float angle_it = (TWO_PI / (ringCount));
                dotSize = 16 ;

                float mod = -1.0f;
                if(j%2 == 0) mod = 1.0f;
                angle_off = count * angle_it * mod;
                fill(cm.getColor(i % 4));
                // blendMode(SCREEN);
                ellipse(cos((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 1280 / 2, sin((float) i * angle_it + angle_off) * j * (ringWidth+margin) + 720 / 2, dotSize, dotSize);
            }
        }

        if (isColorsVisible) {

            cm.draw();

        }

    }

}
