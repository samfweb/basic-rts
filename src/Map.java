import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
/** 
 * Provides a map for tile state detection.
 */
public class Map extends TiledMap{
	
	//Layer of map that stores solid data
	public static final int PROPERTY_LAYER = 0;
	public static final String SOLID_PROPERTY = "solid";
	public static final String OCCUPIED_PROPERTY = "occupied";
	
	//boolean array to record solids
	private boolean[][] solid;
	private boolean[][] occupied;
	
	/**
	 * Instantiates a new map. <br>
	 * Creates and populates a solid array and occupied array for object placement checks
	 *
	 * @param filePath the file path to the tiled map
	 * @throws SlickException
	 */
	public Map(String filePath) throws SlickException {
		super(filePath);
		//Create boolean array solid to record while tiles are solid
		solid = new boolean[this.getWidth()][this.getHeight()];
		occupied = new boolean[this.getWidth()][this.getHeight()];
		
		//Loop through all tiles in map
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				
				//Get tileid
				int tileId = this.getTileId(i, j, PROPERTY_LAYER);
				//Check if occupied
				String tileValue = getTileProperty(tileId, OCCUPIED_PROPERTY, "false");
				//If true, add it to occupied array
				if (tileValue.equals("true")) {
					occupied[i][j]  =  true;
				}
				
				//Check if solid
				tileValue = getTileProperty(tileId, SOLID_PROPERTY, "false");
				//If true, add it to solid array
				if (tileValue.equals("true")) {
					solid[i][j]  =  true;
				}
			}
		}
		
	}
	
	/* Getters -------------------------------------------------------------------- */
	
	/**
	 * Checks if tile is solid.
	 *
	 * @param x int: the tile's x coordinate
	 * @param y int: the tile's y coorindate
	 * @return true, if is solid tile
	 */
	public boolean isSolidTile(int x, int y) {
		return solid[x][y];
	}
	
	/**
	 * Checks if occupied tile.
	 *
	 * @param x int: the tile's x coordinate
	 * @param y int: the tile's y coorinate
	 * @return true, if is occupied tile
	 */
	public boolean isOccupiedTile(int x, int y) {
		return occupied[x][y];
	}
	
	/**
	 * Checks if location on map is solid.
	 *
	 * @param x double: global x coordinate
	 * @param y double: global y coordinate
	 * @return true, if is solid
	 */
	public boolean isSolid(double x, double y) {
		int tileX = getXTileID(x);
		int tileY = getYTileID(y);
		
		return isSolidTile(tileX, tileY);
	}
	
	/**
	 * Checks if location on map is occupied.
	 *
	 * @param x double: global x coordinate
	 * @param y double: global y coordinate
	 * @return true, if is occupied
	 */
	public boolean isOccupied(double x, double y) {
		int tileX = getXTileID(x);
		int tileY = getYTileID(y);
		
		return isOccupiedTile(tileX, tileY);
	}
	
	/**
	 * Gets the x tile ID.
	 *
	 * @param x the global x coordinate
	 * @return the x tile ID
	 */
	public int getXTileID(double x) {
		return (int)x/this.getTileWidth();
	}
	
	/**
	 * Gets the y tile ID.
	 *
	 * @param y the global y coordinate
	 * @return the y tile ID
	 */
	public int getYTileID(double y) {
		return (int)y/this.getTileHeight();
	}
}
