package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * @author Marcus
 * @create 2022-06-22 21:20
 */
public class WorldState {
    private TETile[][] world;
    public WorldState(MapGenerationParameters mgp) {
        world = new TETile[mgp.getWidth()][mgp.getHeight()];
    }
    public int getWidth() {
        return world.length;
    }
    public int getHeight() {
        return world[0].length;
    }
    public void setTile(int x, int y, TETile tile) {
        world[x][y] = tile;
    }
    public void fillWithNothing() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                setTile(x, y, Tileset.NOTHING);
            }
        }
    }
    public TETile[][] terrainGrid() {
        return world;
    }
}
