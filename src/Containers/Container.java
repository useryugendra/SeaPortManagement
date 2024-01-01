package Containers;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Container implements Comparable<Container>{
    protected int id;
    protected int weight;
    public Container(int id, int weight){
        this.id=id;
        this.weight=weight;
    }
    public abstract boolean equals(Container cont);
    public abstract double consumption();
    public int getWeight(){
        return this.weight;
    }
    public int getID(){
        return id;
    }

    public int compareTo(Container other) {
        return id < other.id ? -1 : (id == other.id ? 0 : 1);
    }
    static public ArrayList<ArrayList<Container>> getContainersSortedByType(ArrayList<Container> containers) {
        Collections.sort(containers);
        ArrayList<Container> basic_conts = new ArrayList<Container>();
        ArrayList<Container> heavy_conts = new ArrayList<Container>();
        ArrayList<Container> refrigerated_conts = new ArrayList<Container>();
        ArrayList<Container> liquid_conts = new ArrayList<Container>();

        for(Container cont : containers)
            if(cont instanceof BasicContainer)
                basic_conts.add(cont);
            else if(cont instanceof RefrigiratedContainer)
                refrigerated_conts.add(cont);
            else if(cont instanceof LiquidContainer)
                liquid_conts.add(cont);
            else
                heavy_conts.add(cont);

        ArrayList<ArrayList<Container>> res = new ArrayList<ArrayList<Container>>();
        res.add(basic_conts);
        res.add(heavy_conts);
        res.add(refrigerated_conts);
        res.add(liquid_conts);

        return res;
    }

}
