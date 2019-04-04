package by.epam.javawebtraining.kudzko.task05.controller;

import by.epam.javawebtraining.kudzko.task05.model.*;
import by.epam.javawebtraining.kudzko.task05.util.ConteinerCreator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Controller {
    public static final int SEAPORT_STORE_CAPACITY = ConstantsValues
            .SEAPORT_STORE_CAPACITY;
    public static final int AMOUNT_BERSTHS = ConstantsValues.AMOUNT_BERSTHS;
    public static final int AMOUNT_SHIPS = ConstantsValues.AMOUNT_SHIPS;
    public static final int SHIP_STORE_CAPACITY = ConstantsValues
            .SHIP_STORE_CAPACITY;
    public static final int AMOUNT_CONTEINERS = ConstantsValues
            .AMOUNT_CONTEINERS;

    private static final ExecutorService poolShips = Executors.newFixedThreadPool
            (AMOUNT_SHIPS);

    public static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static void main(String[] args) {

        LOGGER.info("< Main has started. >");

        List<Container> containers = new CopyOnWriteArrayList<>();

        for (int i = 0; i < AMOUNT_CONTEINERS; i++) {
            containers.add(ConteinerCreator.createConteiner());
        }


        Store seaPortStore = new Store(100, containers);
        List<Berth> berths = new ArrayList<>();

        for (int i = 0; i < AMOUNT_BERSTHS; i++) {
            berths.add(new Berth(seaPortStore));
        }

        SeaPort seaPort = new SeaPort(berths);

        LOGGER.info("< SeaPort created. > ");
        LOGGER.info("< Threads starting... > ");

        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ships.add(new Ship(seaPort, SHIP_STORE_CAPACITY));
        }
        for (int i = 0; i < 10; i++) {
            poolShips.execute(ships.get(i));;
        }
        poolShips.shutdown();


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ships.get(i).stop();
        }

    }
}
