package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Generate a new world
 * @author Marcus
 * @create 2022-06-22 17:08
 */
public class MapGenerator {
//    private static final int WIDTH = 80;
//    private static final int HEIGHT = 30;
//    private static final long SEED = 12345;
//    private static final Random RANDOM = new Random(SEED);

    /**
     * Generate a world using parameters provided by MapGenerationParameters.
     * @param mgp Parameters used to generate the world.
     * @return
     */
    public static WorldState generate(MapGenerationParameters mgp) {
        WorldState world = new WorldState(mgp);
        world.fillWithNothing();
        return world;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        MapGenerationParameters mgp = MapGenerationParameters.defaultParameters();
        ter.initialize(mgp.getWidth(), mgp.getHeight());
        WorldState ws = MapGenerator.generate(mgp);
        // draws the world to the screen
        ter.renderFrame(ws.terrainGrid());
    }
}
