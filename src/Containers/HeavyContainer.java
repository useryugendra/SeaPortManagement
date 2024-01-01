package Containers;

public class HeavyContainer extends Container{
    public HeavyContainer(int id, int weight){
        super(id,weight);
    }

    @Override
    public double consumption() {
        return 3.0*this.weight;
    }

    @Override
    public boolean equals(Container cont) {
        return cont.getClass().getSimpleName().equals("HeavyContainer") && cont.id==this.id && cont.weight==this.weight;
    }

}
