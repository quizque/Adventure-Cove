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
    
    Event (String type_, Vector2D locationStart_, Vector2D locationEnd_, String[] data_)
    {
        type = type_;
        locationStart = locationStart_;
        locationEnd = locationEnd_;
        data = data_;
    }
    
    public String getType() { return type; }
    public String[] getData() { return data; }
    
    public Boolean inEventArea(Vector2D pos)
    {
        int area = (locationStart.getX() - locationEnd.getX()) * (locationStart.getY() - locationEnd.getY());
        
        double area1 = Math.abs((locationStart.getX()-pos.getX())*(locationStart.getY()-pos.getY()));
        double area2 = Math.abs((locationEnd.getX()-pos.getX())*(locationStart.getY()-pos.getY()));
        double area3 = Math.abs((locationStart.getX()-pos.getX())*(locationEnd.getY()-pos.getY()));
        double area4 = Math.abs((locationEnd.getX()-pos.getX())*(locationEnd.getY()-pos.getY()));
        
        
        if ((area1 + area2 + area3 + area4) == area)
            return true;
        return false;
    }
}
