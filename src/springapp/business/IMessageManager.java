package springapp.business;

import java.util.Collection;

public interface IMessageManager {

    void add(String message);

    int removeAll();

    Collection<Message> findAll();

}