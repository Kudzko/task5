package by.epam.javawebtraining.kudzko.task05.model;

public class Container {
    private static int idNumber=0;
    private int id=0;

    {
        idNumber++;
    }

    public Container() {
       this.id = idNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
