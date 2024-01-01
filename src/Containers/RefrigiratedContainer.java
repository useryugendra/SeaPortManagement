package Containers;

public class RefrigiratedContainer extends HeavyContainer{
    public RefrigiratedContainer(int id, int weight){
        super(id,weight);
    }
    public double consumption(){
        return 5.0*this.weight;
    }

}
