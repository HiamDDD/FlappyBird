package ru.samsung.gamestudio;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {

    private int width = 200;
    private int height = 700;
    private int gapHeight = 400;
    private int speed = 10;

    private int gapY ;
    private int padding = 100;
    private int x;
    private int distanceBetweenTubes;
    private Random random;
    private Texture textureUpperTube;
    private Texture textureDownTube;

    public boolean isHit(Bird bird) {
        if (bird.getX() + bird.getWidth() >= x && bird.getX() <= x + width && bird.getY() <= gapY - gapHeight / 2){
            return true;
        }
        if (bird.getY() + bird.getHeight() >= gapY + gapHeight / 2 && bird.getX() + bird.getWidth() >= x && bird.getX() <= x + width)
            return true;
        return false;
    }

    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
    }
    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);

        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    public void dispose() {
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }

    public void move() {
        x -= speed;

        if (x < -width) {
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

}
