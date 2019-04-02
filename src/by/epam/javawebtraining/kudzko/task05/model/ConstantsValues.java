package by.epam.javawebtraining.kudzko.task05.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConstantsValues {

    private static String[] consts = readConsts();

    public static final int GO_AROUND_TIME_MILSEC = Integer.parseInt(consts[0]);
    public static final int SEAPORT_STORE_CAPACITY = Integer.parseInt(consts[1]);
    public static final int AMOUNT_BERSTHS = Integer.parseInt(consts[2]);
    public static final int AMOUNT_SHIPS = Integer.parseInt(consts[3]);
    public static final int SHIP_STORE_CAPACITY = Integer.parseInt(consts[4]);
    public static final int AMOUNT_CONTEINERS = Integer.parseInt(consts[5]);

    private static String[] readConsts() {

        Properties properties = new Properties();
        try {
            FileInputStream constant = new FileInputStream
                    ("F:\\git_rep\\task5\\src\\by\\epam\\javawebtraining\\kudzko\\task05\\model\\ConstantsValues.properties");
            properties.load(constant);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] consts = new String[6];
        consts[0] = properties.getProperty("GO_AROUND_TIME_MILSEC");
        consts[1] = properties.getProperty("SEAPORT_STORE_CAPACITY");
        consts[2] = properties.getProperty("AMOUNT_BERSTHS");
        consts[3] = properties.getProperty("AMOUNT_SHIPS");
        consts[4] = properties.getProperty("SHIP_STORE_CAPACITY");
        consts[5] = properties.getProperty("AMOUNT_CONTEINERS");
        return consts;
    }
}
