/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

/**
 *
 * @author nickc
 */
public class Event {
    String type;
    Vector2D locationStart;
    Vector2D locationEnd;
    String[] data;
    
    // Constructer for event
    Event (String type_, Vector2D locationStart_, Vector2D locationEnd_, String[] data_)
    {
        type = type_;
        locationStart = locationStart_;
        locationEnd = locationEnd_;
        data = data_;
    }
    
    // Gets
    public String getType() { return type; }
    public String[] getData() { return data; }
    
    // Check if a vector is in the event area
    public Boolean inEventArea(Vector2D pos)
    {
        int area = Math.abs((locationStart.getX() - locationEnd.getX()) * (locationStart.getY() - locationEnd.getY()));
        
        // Calculate the area between the vector and the start/end
        double base1 =  (locationStart.getX() - locationEnd.getX());
        double height1 = (locationStart.getY() - pos.getY());
        double area1 = (base1+height1)/2;
        
        double base2 =  (locationStart.getY() - locationEnd.getY());
        double height2 = (locationStart.getX() - pos.getX());
        double area2 = (base2+height2)/2;
        
        double base3 =  (locationStart.getX() - locationEnd.getX());
        double height3 = (locationEnd.getY() - pos.getY());
        double area3 = (base3+height3)/2;
        
        double base4 =  (locationStart.getY() - locationEnd.getY());
        double height4 = (locationEnd.getY() - pos.getY());
        double area4 = (base1+height1)/2;
        
        // If the area is equal to all the area combinded, return true.
        return (area1 + area2 + area3 + area4) == area;
    }
}
