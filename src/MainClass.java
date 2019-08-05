import processing.core.PApplet;

public class MainClass extends PApplet {

    int rings = 70;
    int ringDiameter = 10;
    int dotDiameter = 5;

    float frameDivider = 400.0f;



    public static void main(String[] args){
        println(args);
        PApplet.main( "MainClass", args);
    }
    public void settings(){
        size(1280,720);
    }
    public void setup(){

    }

    public void draw(){
        background(255,212,192);
        noStroke();

        ellipse(1280/2,720/2,dotDiameter, dotDiameter);

        for(int j=0;j<rings;j++) {
            for(int i=0;i<(j*2+3);i++){
                fill(i*j+64,i*j+128,212,i%3*64);
                float a = (float)i / (j*2+3) * TWO_PI + (float)frameCount/frameDivider/((j%5)-2);
                ellipse(1280/2 + cos(a)*ringDiameter*j,720/2 + sin(a)*ringDiameter*j,j%5+dotDiameter, j%5+dotDiameter);

            }

        }


    }


}
