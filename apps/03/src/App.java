import processing.core.PApplet;

public class App extends PApplet {

    Boolean isColorsVisible;

    Boolean bUseStroke;

    ColorManager cm;

    float count;
    float dCount;

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
        dCount++;
        count += (dCount - count) / 4.0f;
    }

    public void draw() {
        update();

        background(cm.getColor(7));



        int rings = 32;
        int ringWidth =64;
        int dotSize = 32;

        float angle_off = HALF_PI / 2.0f;
        noStroke();

        for (int j = 0; j < rings; j++) {
            for (int i = 0; i < ((j*2) + 1); i++) {
                float angle_it = TWO_PI / ((float) j*2 + 1);
                dotSize = 32 + (i+j)%32;
                angle_off = HALF_PI + count / 100.0f * j / 50.0f;
                fill(cm.getColor(j % 4));
                ellipse(cos((float) i * angle_it + angle_off) * j * ringWidth + 1280 / 2, sin((float) i * angle_it + angle_off) * j * ringWidth + 720 / 2, dotSize, dotSize);
            }
        }

        if (isColorsVisible) {

            cm.draw();

        }

    }

}
