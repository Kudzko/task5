package by.epam.javawebtraining.kudzko.task05.model;

import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.OutOfStoreCapacityException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {
    public final int REMOVE_INDEX = 0;
    public int CAPACITY;
    private List<Container> containers;

    public Store(int capacity) {
        containers = new CopyOnWriteArrayList<>();
        this.CAPACITY = capacity;
    }

    public Store(int capacity, List<Container> containers) {
        this.containers = containers;
        this.CAPACITY = capacity;
    }

    public void put(Container container) throws OutOfStoreCapacityException {
        if (containers.size() < CAPACITY) {
            containers.add(container);
        }else {
            throw  new OutOfStoreCapacityException();

        }
    }

    public Container get() {
//        if (!containers.isEmpty()) {
            return containers.remove(REMOVE_INDEX);
//        }else{
//            throw new EmptyStoreException();
//        }
    }

    public boolean hasPlace (){
        return (containers.size() < CAPACITY) ? true : false;
    }

    public boolean isEmpty (){
        return containers.isEmpty();
    }

    public void addConteiner(Container container){
        containers.add(container);
    }

}
