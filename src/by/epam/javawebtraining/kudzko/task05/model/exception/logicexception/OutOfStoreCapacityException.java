package by.epam.javawebtraining.kudzko.task05.model.exception.logicexception;

import by.epam.javawebtraining.kudzko.task05.model.exception.LogicException;

public class OutOfStoreCapacityException extends LogicException {
    public OutOfStoreCapacityException() {
    }

    public OutOfStoreCapacityException(String message) {
        super(message);
    }

}
