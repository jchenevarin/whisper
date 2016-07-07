package model;

import model.entities.User;

import java.util.List;

/**
 * Author: jch
 * Date: 16-06-24
 * Description: TODO
 */
public interface Subscribable {
    List<User> getSubscribers();
    boolean addSubscriber(User subscriber);
    boolean removeSubscriber(User subscriber);
}
