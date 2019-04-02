package by.epam.javawebtraining.kudzko.task05.model.exception.logicexception;

import by.epam.javawebtraining.kudzko.task05.model.exception.LogicException;

public class EmptyStoreException extends LogicException {
    public EmptyStoreException() {
    }

    public EmptyStoreException(String message) {
        super(message);
    }
}
