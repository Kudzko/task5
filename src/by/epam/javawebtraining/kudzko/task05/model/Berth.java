package by.epam.javawebtraining.kudzko.task05.model;

import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.EmptyStoreException;
import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.OutOfStoreCapacityException;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Berth {
    public static final Logger LOGGER;
    private static int idCounter = 0;

    private int id = 0;
    private Store store;
    private Lock berthLock = new ReentrantLock();

    static {
        LOGGER = Logger.getRootLogger();
    }

    {
        idCounter++;
    }

    public Berth(Store store) {
        this.store = store;
        this.id = idCounter;
    }

    public boolean isFree() {
        return berthLock.tryLock();

    }

    public void downLoadStore(Ship ship) throws OutOfStoreCapacityException {

        while (ship.hasConteiners() && store.hasPlace()) {
            if (store.hasPlace()) {
                store.put(ship.getInternalStore().get());

            }
        }
    }

    public void upLoadStore(Ship ship) throws EmptyStoreException {
        Store shipInternalStore = ship.getInternalStore();
        while (shipInternalStore.hasPlace() && !store.isEmpty()) {
            if (!store.isEmpty()) {
                try {
                    shipInternalStore.put(store.get());
                } catch (OutOfStoreCapacityException e) {
                    LOGGER.warn("Impossible to upload containers from Storage" +
                            " through berth. No containers into storage " +
                            "anymore", e);
                    break;
                }
            }
        }
    }

    public void leave() {
        berthLock.unlock();
    }

    public int getId() {
        return id;
    }
}
