package Containers;

public class BasicContainer extends Container {
    public BasicContainer(int id, int weight){
        super(id,weight);
    }

    @Override
    public boolean equals(Container cont) {
        return cont.getClass().getSimpleName().equals("BasicContainer") && cont.id==this.id && cont.weight==this.weight;
    }

    @Override
    public double consumption() {
        return 2.5*this.weight;
    }
}
