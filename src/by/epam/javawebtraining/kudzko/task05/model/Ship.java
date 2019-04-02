package by.epam.javawebtraining.kudzko.task05.model;

import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.EmptyStoreException;
import by.epam.javawebtraining.kudzko.task05.model.exception.logicexception.OutOfStoreCapacityException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Ship implements Runnable {
    public static final Logger LOGGER;
    public final int GO_AROUND_TIME_MILSEC = ConstantsValues.GO_AROUND_TIME_MILSEC;
    public int CAPACITY;
    private Store internalStore;
    private Thread thread;
    private SeaPort seaPort;
    private int id;

    private volatile boolean flow = true;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public Ship(SeaPort seaPort, int capacity) {
        this.thread = new Thread(this);
        this.CAPACITY = capacity;
        this.internalStore = new Store(CAPACITY);
        this.seaPort = seaPort;
    }

    @Override
    public void run() {
        LOGGER.info("[Thread id = " + this.thread.getId()+" name = "+ this
                .thread.getName() + "] " +
                " is " +
                "STARTED.]");
        while (flow) {

            List<Berth> berths = seaPort.getBerths();
            for (int i = 0; i < berths.size(); i++) {
                Berth currentBerth = berths.get(i);
                if (currentBerth.isEmpty()) {
                    try {
                        if (!internalStore.isEmpty()) {
                            try {
                                LOGGER.info("[ " + this.thread.getName() +
                                        "] containers are loading  from SHIP " +
                                        "to STORE");
                                currentBerth.downLoadStore(this);
                                LOGGER.info("[ " + this.thread.getName() +
                                        "] containers are loaded  from SHIP " +
                                        "to STORE");
                            } catch (OutOfStoreCapacityException e) {
                                LOGGER.warn("Berth can not downLoadStore! ", e);
                            }
                        } else {
                            try {
                                LOGGER.info("[ " + this.thread.getName() +
                                        "] containers are loading from STORE " +
                                        "to SHIP");
                                currentBerth.upLoadStore(this);
                                LOGGER.info("[ " + this.thread.getName() +
                                        "] containers are loaded from STORE " +
                                        "to SHIP");
                            } catch (EmptyStoreException e) {
                                LOGGER.warn("Berth can not upLoadStore! ", e);
                            }
                        }

                    } finally {
                        currentBerth.leave();
                    }

                }



            }

            try {
                LOGGER.info("[ "+this.thread.getName() + "] go around");
                TimeUnit.MILLISECONDS.sleep(GO_AROUND_TIME_MILSEC);
                LOGGER.info("[ "+this.thread.getName() + "] go back");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isFlow() {
        return flow;
    }

    public void setFlow(boolean flow) {
        this.flow = flow;
    }

    public boolean hasConteiners() {
        return !internalStore.isEmpty();
    }

    public Store getInternalStore() {
        return internalStore;
    }
}
