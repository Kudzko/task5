package by.epam.javawebtraining.kudzko.task05.model;

import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.EmptyStoreException;
import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.OutOfStoreCapacityException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Berth {
    private Store store;
    private Lock berthLock = new ReentrantLock();


    public Berth(Store store) {
        this.store = store;
    }

    public boolean isEmpty() {
        return berthLock.tryLock();

    }

    public void downLoadStore(Ship ship) throws OutOfStoreCapacityException {

        while (ship.hasConteiners()) {
            if (store.hasPlace()) {
                store.put(ship.getInternalStore().get());

            }
        }
    }

    public void upLoadStore(Ship ship) throws EmptyStoreException {
        Store shipInternalStore = ship.getInternalStore();
        while (shipInternalStore.hasPlace()) {
            if (!store.isEmpty()) {
                try {
                    shipInternalStore.put(store.get());
                } catch (OutOfStoreCapacityException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public void leave(){
        berthLock.unlock();
    }
}
