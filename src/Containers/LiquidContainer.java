package Containers;

public class LiquidContainer extends HeavyContainer{
    public LiquidContainer(int id, int weight){
        super(id,weight);
    }
    public double consumption(){
        return 4.0*this.weight;
    }

}
