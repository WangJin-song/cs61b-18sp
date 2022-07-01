package byog.lab5;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 12345;
    private static final Random RANDOM = new Random(SEED);
    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - i - 1;
        }
        return s + 2 * effectiveI;
    }

    /**
     * Computes relative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero.
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - i - 1;
        }
        return -effectiveI;
    }

    /**
     * Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi++) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            //world[xCoord][yCoord] = t;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        // hexagons have 2 * s rows. this code iterates up from the bottom row
        for (int yi = 0; yi < 2 * s; yi++) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }

    /**
     * Initialize a world with nothing.
     * @param height height of the world
     * @return
     */
    public static TETile[][] initWorld(int height, int width) {
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    /**
     * get 19 left lower corner of the hexagon
     * @param s size of the hexagon
     * @return
     */
    public static Position[] startPoint(int s) {
        Position[] positions = new Position[19];
        positions[0] = new Position(s - 1, 2 * s);
        positions[1] = new Position(s - 1, 4 * s);
        positions[2] = new Position(s - 1, 6 * s);
        positions[3] = new Position(3*s - 2, s);
        positions[4] = new Position(3*s - 2, 3 * s);
        positions[5] = new Position(3*s - 2, 5 * s);
        positions[6] = new Position(3*s - 2, 7 * s);
        positions[7] = new Position(5*s - 3, 0);
        positions[8] = new Position(5*s - 3, 2 * s);
        positions[9] = new Position(5*s - 3, 4 * s);
        positions[10] = new Position(5*s - 3, 6 * s);
        positions[11] = new Position(5*s - 3, 8 * s);
        positions[12] = new Position(7*s - 4, s);
        positions[13] = new Position(7*s - 4, 3 * s);
        positions[14] = new Position(7*s - 4, 5 * s);
        positions[15] = new Position(7*s - 4, 7 * s);
        positions[16] = new Position(9*s - 5, 2 * s);
        positions[17] = new Position(9*s - 5, 4 * s);
        positions[18] = new Position(9*s - 5, 6 * s);
        return positions;
    }

    /**
     * set the tiles of the hexagon
     * @return
     */
    public static TETile setTile() {
        TETile tile;
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: tile = Tileset.FLOWER; break;
            case 1: tile = Tileset.MOUNTAIN; break;
            case 2: tile = Tileset.GRASS; break;
            case 3: tile = Tileset.SAND; break;
            default: tile = Tileset.TREE;
        }
        return tile;
    }

    private static final int WIDTH = 27;
    private static final int HEIGHT = 30;
    private static final int SIZE = 3;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = initWorld(HEIGHT, WIDTH);
        Position[] positions = startPoint(SIZE);
        for (int i = 0; i < 19; i++) {
            TETile tile = setTile();
            addHexagon(world, positions[i], SIZE, tile);
        }
        // draws the world to the screen
        ter.renderFrame(world);
    }
}
