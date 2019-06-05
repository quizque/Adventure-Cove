package adventure.cove;

public class World {
    public char[][] world;
    private char[][] world_bak;
    public int size_x = 0;
    public int size_y = 0;
    
    public World(String displayMap)
    {
        ParseWorld(displayMap);
    }
    
    private void ParseWorld(String unparsedWorld)
    {
        
        for (int key = 0; key != unparsedWorld.length(); key++)
        {
            if (unparsedWorld.charAt(key) == '\n')
            {
                size_x = key;
                size_y = unparsedWorld.length() / size_x;
                break;
            }
        }
        
//        System.out.println(size_x + " " + size_y);
        
        world = new char[size_x][size_y];
        
        for (int y = 0; y != size_y; y++) {
            for (int x = 0; x != size_x; x++)
                world[x][y] = unparsedWorld.charAt(x + (size_x*y + y));
            }
        
        world_bak = world;
//        for (int y = 0; y != size_y; y++) {
//            for (int x = 0; x != size_x; x++)
//                System.out.print(world[x][y]);
//            System.out.print('\n');
//        }
    }
    
    public void resetWorld()
    {
        world = world_bak;
    }
}
