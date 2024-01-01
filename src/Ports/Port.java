package Ports;

import Containers.Container;
import Interfaces.IPort;
import Ships.Ship;


import java.util.ArrayList;
import java.util.Collections;

public class Port implements IPort {
    private int id;
    private double x;
    private double y;
    ArrayList<Container> containers;
    ArrayList<Ship> history;
    ArrayList<Ship> current;

    public Port(int portId, double x, double y) {
        this.id=portId;
        this.x=x;
        this.y=y;
    }

    void Port(int id, double x, double y){
        this.id=id;
        this.x=x;
        this.y=y;
    }
    public ArrayList<Container> getContainers(){
        return this.containers;
    }
    public void incomingShip(Ship s){
        current.add(s);
        if(!history.contains(s)){
            history.add(s);
        }

    }
    public void outgoingShip(Ship s){
        current.remove(s);
    }

    public double getDistance(Port p){
        final double x=p.x;
        final double y=p.y;
        return Math.sqrt(Math.pow((x-this.x),2)+Math.pow((y-this.y),2));
    }

    public String toString() {
        String str = String.format("Port %d: (%.2f, %.2f)\n", id, x, y);
        ArrayList<ArrayList<Container>> sorted = Container.getContainersSortedByType(containers);
        for(ArrayList<Container> conts : sorted)
            if(!conts.isEmpty()) {
                str += "  " + conts.get(0).getClass().getSimpleName() + ":";
                for(Container cont : conts)
                    str += " " + cont.getID();
                str += "\n";
            }

        Collections.sort(current);
        for(Ship ship : current)
            str += ship.toString();

        return str;
    }
}





