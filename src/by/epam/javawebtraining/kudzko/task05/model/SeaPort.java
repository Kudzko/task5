package by.epam.javawebtraining.kudzko.task05.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SeaPort {
    private Store portStore;
    private List<Berth> berths;

    public SeaPort(List<Berth> berths) {
        this.portStore = new Store(100);
        this.berths = berths;
    }

    public Store getPortStore() {
        return portStore;
    }

    public void setPortStore(Store portStore) {
        this.portStore = portStore;
    }

    public List<Berth> getBerths() {
        return berths;
    }

    public void setBerths(List<Berth> berths) {
        this.berths = berths;
    }
}
