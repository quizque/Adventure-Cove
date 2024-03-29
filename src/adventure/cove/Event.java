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
    String type; // What kind of event is it?
    Vector2D locationStart; // Where does the event trigger?
    Vector2D locationEnd; // Where dies the event no longer trigger?
    String[] data; // What data is stored inside the event?
    
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
        // If the area is equal to all the area combinded, return true.
        return (locationStart.getX() <= pos.getX() && locationEnd.getX() >= pos.getX() && locationStart.getY() <= pos.getY() && locationEnd.getY() >= pos.getY());
    }
}
