package adventure.cove;

public class World {
    private String[][] world;
    private int size_x = 0;
    private int size_y = 0;
    
    public World(String unparsedWorld)
    {
        world = ParseWorld(unparsedWorld);
    }
    
    private String[][] ParseWorld(String unparsedWorld)
    {
        
        for (int key = 0; key != unparsedWorld.length(); key++)
        {
            if (unparsedWorld.charAt(key) == '\n')
            {
                size_x = key;
                System.out.print("found");
                size_y = unparsedWorld.length() / size_x;
                break;
            }
        }
        
        System.out.println(size_x + " " + size_y);
        
        return new String[size_x][size_y];
    }
}
