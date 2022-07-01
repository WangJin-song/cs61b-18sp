package byog.Core;

/**
 * @author Marcus
 * @create 2022-06-22 21:13
 */
public class MapGenerationParameters {
    private int width;
    private int height;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public static MapGenerationParameters defaultParameters() {
        MapGenerationParameters mgp = new MapGenerationParameters();
        mgp.width = 80;
        mgp.height = 30;
        return mgp;
    }
}
