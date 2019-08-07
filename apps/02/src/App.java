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

        background(0);

        if (isColorsVisible) {

            cm.draw();

        }

        int rings = 64;
        int ringWidth = 20;
        int dotSize = 10;
        float d = 10.0f;
        float angle_off = HALF_PI / 2.0f;
        noStroke();
        fill(255);
        for (int j = 0; j < rings; j++) {
            for (int i = 0; i < (j + 1); i++) {
                float angle_it = TWO_PI / ((float) j + 1);

                angle_off = HALF_PI + count / 100.0f * j / 50.0f;
                fill(cm.getColor(j % 7));
                ellipse(cos((float) i * angle_it + angle_off) * j * ringWidth + 1280 / 2, sin((float) i * angle_it + angle_off) * j * ringWidth + 720 / 2, dotSize, dotSize);
            }
        }

    }

}
