package Main;

import Containers.*;
import Ports.Port;
import Ships.Ship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner io = new Scanner(new File(args[0]));
        ArrayList<Container> containers = new ArrayList<Container>();
        ArrayList<Port> ports= new ArrayList<Port>();
        ArrayList<Ship> ships= new ArrayList<Ship>();
        final int NumOfQueries = io.nextInt();
        for(int i=0;i<NumOfQueries;i++){
            final int choice=io.nextInt();
            switch (choice){
                case 1: {
                    final int contId = containers.size();
                    final int portId= io.nextInt();
                    final int weight=io.nextInt();
                    Container cont;
                    if(io.hasNextInt()){
                        if(weight>3000){
                            cont= new HeavyContainer(contId,weight);
                        }
                        else{
                            cont= new BasicContainer(contId,weight);
                        }
                    }
                    else{
                        if(io.next().charAt(0)=='L'){
                            cont = new LiquidContainer(contId,weight);
                        }
                        else{
                            cont = new RefrigiratedContainer(contId,weight);
                        }
                    }
                    ports.get(portId).getContainers().add(cont);
                    containers.add(cont);
                    break;
                }
                case 2: {		// Create a ship.
                    final int ship_ID = ships.size();
                    final int port_ID = io.nextInt();
                    final int totalWeightCapacity = io.nextInt();
                    final int maxNumberOfAllContainers = io.nextInt();
                    final int maxNumberOfHeavyContainers = io.nextInt();
                    final int maxNumberOfRefrigeratedContainers = io.nextInt();
                    final int maxNumberOfLiquidContainers = io.nextInt();
                    final double fuelConsumptionPerKM = io.nextDouble();

                    // Add the generated ship to the port's "current" ArrayList and the general "ships" ArrayList.
                    ships.add(new Ship(ship_ID, ports.get(port_ID), totalWeightCapacity,
                            maxNumberOfAllContainers, maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers,
                            maxNumberOfLiquidContainers, fuelConsumptionPerKM));
                    break;
                }
                case 3: {
                    int portId= ports.size();
                    double x=io.nextDouble();
                    double y=io.nextDouble();
                    ports.add(new Port(portId,x,y));
                    break;
                }
                case 4: {
                    int shipId=io.nextInt();
                    int contId=io.nextInt();
                    ships.get(shipId).load(containers.get(contId));
                    break;
                }
                case 5: {
                    int shipId=io.nextInt();
                    int contId=io.nextInt();
                    ships.get(shipId).unLoad(containers.get(contId));
                    break;
                }
                case 6: {
                    int shipId=io.nextInt();
                    int portId=io.nextInt();
                    ships.get(shipId).sailTo(ports.get(portId));
                    break;
                }
                case 7: {
                    int shipId= io.nextInt();
                    double fuel=io.nextDouble();
                    ships.get(shipId).reFuel(fuel);
                    break;
                }
                default: {
                    System.out.println("invalid operation!!!");
                    break;
                }
            }
        }
        io.close();
        PrintStream op= new PrintStream(new File(args[1]));
        for(Port p: ports){
            op.println(p.toString());
        }
        op.close();
    }
}