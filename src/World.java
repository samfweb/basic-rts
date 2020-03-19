import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Provides a container all the different objects in the game world, 
 * and schedules their interactions.
 * 
 */
public class World {
	
	public static final String MAP_PATH = "assets/main.tmx";
	public static final String NAMES_PATH = "assets/names.csv";
	public static final String OBJECTS_PATH = "assets/objects.csv";
	//how many pixels away a unit is from click to select
	public static final int SELECT_DIST = 32; 

	private Map map;
	private Camera camera;
	private Player player;
	private Input input;
	private InputHandler inputHandler;
	private String[] nameList;
	private SelectableObject selection;
	private int delta;
	//Observer for when worldObjectList needs updating
	private boolean worldObjectsModified = true;

	
	/* 3 separate array lists to hold the three different world object types
	 * Object is added to relevant list when created.
	 * Keeping these separate provides control over rendering order 
	 */
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	private ArrayList<Building> buildingList = new ArrayList<Building>();
	private ArrayList<Resource> resourceList = new ArrayList<Resource>();
	
	//World object list hold all current world objects
	private ArrayList<WorldObject> worldObjectList = new ArrayList<WorldObject>();
	
	//Array list to hold objects scheduled for removal
	private ArrayList<WorldObject> removalList = new ArrayList<WorldObject>();

	
	/** Creates and populates a new world with a map, player, camera and random names list. 
	 * @throws SlickException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public World() throws SlickException, FileNotFoundException, IOException{
		map = new Map(MAP_PATH);
		player = new Player();
		camera = new Camera();
		inputHandler = new InputHandler();
		//Creates list of random names
		nameList = Populate.nameList(NAMES_PATH);
		//Populates initial map with units, resources and buildings
		Populate.world(this, OBJECTS_PATH);
		//Selects first sprite created, if one exists
		if (spriteList.size() > 0) {
			select(spriteList.get(0));
		}
	}
		
	/** Updates the world state.
	 * @param input User input collected by GameContainer
	 * @param delta Amount of time since last update call in milliseconds
	 */
	public void update(Input input, int delta) {
		//Updates world's input and delta
		this.input = input;
		this.delta = delta;
		inputHandler.update(this, input);
		
		//Updates worldObjectList
		updateWorldObjectList();
		
		//Update all WorldObjects
		updateWorldObjects();
		
		//Remove any objects scheduled for removal
		removeObjects();
		
		//Update camera and player
		camera.update(this);
		player.update(this);
		
		//Clear key presses
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
	}
	
	/** Renders the game screen by drawing map and all WorldObjects
	 * 
	 * @param g Slick2D Graphics object
	 */
	public void render(Graphics g) {
		map.render((int)camera.globalXToScreenX(0),
				   (int)camera.globalYToScreenY(0));
		
		renderWorldObjects(g);
		
		//Renders player text overlay
		player.render(g);
	}
	
	/** Selects a new selectable object or nothing.
	 * 
	 * @param object SelectableObject to be selected
	 */
	public void select(SelectableObject object) {
		//Deselects previous selection
		if (selection != null) {
			selection.deselect();
		}
		if (object != null) {
			object.select();
		}
		//Selects new selection
		this.selection = object;
		camera.followObject(selection);
	}
	
	/**
	 * Removes any world objects scheduled for removal.
	 */
	public void removeObjects() {
		for (WorldObject object : removalList) {
			//Attempt removal from each list
			spriteList.remove(object);
			buildingList.remove(object);
			resourceList.remove(object);
		}
		//Clear the removal list
		removalList.clear();
	}
	
	/* Small functions --------------------------------------------------------- 
	 * Note: These were created in this fashion, so that the order of different class's
	 * update and render can be easily changed should the need arise.
	 */
	
	//Update world object list
	public void updateWorldObjectList() {
		//If the list needs to be modified
		if (worldObjectsModified) {
			//Create new list of all world objects
			worldObjectList = new ArrayList<WorldObject>(resourceList);
			worldObjectList.addAll(buildingList);
			worldObjectList.addAll(spriteList);
			//Set observer to false
			worldObjectsModified = false;
		}
	}
	
	/**
	 * Updates world objects.
	 */
	public void updateWorldObjects() {
		for (WorldObject worldObject : worldObjectList) {
			worldObject.update(this);
		}
	}
	
	/**
	 * Renders world objects.
	 *
	 * @param g the g
	 */
	public void renderWorldObjects(Graphics g) {
		for (WorldObject worldObject : worldObjectList) {
			worldObject.render(g);
		}
	}
		
	/* Getters & Setters --------------------------------------------------------- */
	
	/**
	 * Gets the world object list.
	 *
	 * @return the world object list
	 */
	public ArrayList<WorldObject> getWorldObjectList() {
		return new ArrayList<WorldObject>(this.worldObjectList);
	}
	
	/**
	 * Adds the Sprite to sprite list.
	 *
	 * @param sprite the sprite
	 */
	public void addToSpriteList(Sprite sprite) {
		spriteList.add(sprite);
	}
	
	/**
	 * Adds the Building to building list.
	 *
	 * @param building the building
	 */
	public void addToBuildingList(Building building) {
		buildingList.add(building);
	}
	
	/**
	 * Adds the Resource to resource list.
	 *
	 * @param resource the resource
	 */
	public void addToResourceList(Resource resource) {
		resourceList.add(resource);
	}
	
	/**
	 * Schedules the removal of an object.
	 *
	 * @param worldObject the world object
	 */
	public void scheduleRemoval(WorldObject worldObject) {
		removalList.add(worldObject);
	}
	
	/**
	 * Sets the world objects observer to true.
	 */
	public void modifyWorldObjects() {
		this.worldObjectsModified = true;
	}
	
	/**
	 * Gets the name list.
	 *
	 * @return the name list
	 */
	public String[] getNameList() {
		return this.nameList;
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public Input getInput() {
		return this.input;	
	}
	
	/**
	 * Gets the delta.
	 *
	 * @return the delta
	 */
	public int getDelta() {
		return this.delta;	
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return this.map;	
	}
	
	/**
	 * Gets the camera.
	 *
	 * @return the camera
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Gets the selection.
	 *
	 * @return the selection
	 */
	public SelectableObject getSelection() {
		return this.selection;
	}
	
	/**
	 * Gets the map width.
	 *
	 * @return the map width
	 */
	public double getMapWidth() {
		return map.getWidth() * map.getTileWidth();
	}
	
	/**
	 * Gets the map height.
	 *
	 * @return the map height
	 */
	public double getMapHeight() {
		return map.getHeight() * map.getTileHeight();
	}
}

