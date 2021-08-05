package test.task.haulmont.operations;

import java.util.UUID;

public interface Operations<T> {
    void create(T t);
    T find(UUID id);
    T update(UUID id);
    void delete(UUID id);
}
