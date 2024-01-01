package Ships;

import Containers.Container;
import Containers.HeavyContainer;
import Containers.LiquidContainer;
import Containers.RefrigiratedContainer;
import Interfaces.IShip;
import Ports.Port;

import java.util.ArrayList;

public class Ship implements IShip, Comparable<Ship>{
    private int id;
    private double fuel;
    private Port currentPort;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private int maxNumberOfRefrigeratedContainers;
    private int maxNumberOfLiquidContainers;
    private double fuelConsumptionPerKM;
    private int totalWeightCapacity;
    private int totalWeight;
    private int numOfTotalContainers;
    private int numOfHeayContainers;
    private int numOfRefrigiratedContainers;
    private int numOfLiquidContainers;
    ArrayList<Container> containers;
    public Ship(int ID, Port p,
                int totalWeightCapacity,
                int maxNumberOfAllContainers,
                int maxNumberOfHeavyContainers,
                int maxNumberOfRefrigeratedContainers,
                int maxNumberOfLiquidContainers,
                double fuelConsumptionPerKM){
        this.id=ID;
        this.currentPort=p;
        this.fuelConsumptionPerKM=fuelConsumptionPerKM;
        this.maxNumberOfAllContainers=maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers=maxNumberOfHeavyContainers;
        this.maxNumberOfRefrigeratedContainers=maxNumberOfRefrigeratedContainers;
        this.maxNumberOfLiquidContainers=maxNumberOfLiquidContainers;
        this.totalWeightCapacity=totalWeightCapacity;
        totalWeight=0;
        numOfTotalContainers=0;
        numOfHeayContainers=0;
        numOfLiquidContainers=0;
        numOfRefrigiratedContainers=0;
        Port currentPort=p;
        p.incomingShip(this);

    }
    public ArrayList<Container> getContainers(){
        return containers;
    }
    public boolean sailTo(Port p){
        double totalFuelCost=fuelConsumptionPerKM;
        for(Container c: containers){
            totalFuelCost+=c.consumption();
        }
        final double fuelReq=totalFuelCost*currentPort.getDistance(p);
        final boolean sailFlag=this.fuel>=fuelReq;
        if(sailFlag){
            currentPort.outgoingShip(this);
            p.incomingShip(this);
            this.fuel-=fuelReq;
        }
        return sailFlag;
    }

    public void reFuel(double newFuel){
        this.fuel+=newFuel;
    }

    public boolean load(Container cont){
        final boolean loadFlag;
        final int targetWeight=this.totalWeight+cont.getWeight();
        if(!currentPort.getContainers().contains(cont) ||
            numOfTotalContainers+1>maxNumberOfAllContainers ||
            totalWeight+cont.getWeight()>this.totalWeightCapacity){
            loadFlag=false;
        }
        else if(cont instanceof HeavyContainer){
            if(numOfHeayContainers+1>maxNumberOfHeavyContainers){
                loadFlag=false;
            }
            else if(cont instanceof LiquidContainer){
                loadFlag=numOfLiquidContainers+1<=maxNumberOfLiquidContainers;
            }
            else if(cont instanceof RefrigiratedContainer){
                loadFlag=numOfRefrigiratedContainers+1<=maxNumberOfRefrigeratedContainers;
            }
            else{
                loadFlag=true;
            }
        }
        else{
            loadFlag=true;
        }

        if(loadFlag){
            this.totalWeight=totalWeight+cont.getWeight();
            numOfTotalContainers++;
            currentPort.getContainers().remove(cont);
            this.containers.add(cont);
            if(cont instanceof HeavyContainer){
                numOfHeayContainers++;
                if(cont instanceof LiquidContainer){
                    numOfLiquidContainers++;
                }
                else if(cont instanceof RefrigiratedContainer){
                    numOfRefrigiratedContainers++;
                }
            }
        }
        return loadFlag;
    }

    public boolean unLoad(Container cont){
        final boolean unloadFlag= this.containers.contains(cont);
        if(unloadFlag){
            this.containers.remove(cont);
            currentPort.getContainers().add(cont);
            numOfTotalContainers--;
            totalWeight-=cont.getWeight();
            if(cont instanceof HeavyContainer){
                numOfHeayContainers--;
                if(cont instanceof LiquidContainer){
                    numOfLiquidContainers--;
                }
                if(cont instanceof RefrigiratedContainer){
                    numOfRefrigiratedContainers--;
                }
            }
        }
        return unloadFlag;
    }

    public int compareTo(Ship other) {
        return id < other.id ? -1 : (id == other.id ? 0 : 1);
    }
}
