package test.task.haulmont.operations;

import java.util.List;
import java.util.UUID;

public interface Operations<T> {
    void create(T t);
    T find(UUID id);
    List<T> getAll();
    void deleteAll(List<T> t);

}
