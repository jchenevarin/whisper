package model.entities;

import model.Messageable;
import model.Subscribable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: jch
 * Date: 16-06-24
 * Description: TODO
 */
public class Group implements Subscribable, Messageable {

    private String name;
    private String desc;
    private Boolean opened;
    private LocalDateTime created;
    private List<User> members;
    private List<User> subscribers;
    private List<Message> messages;

    @Override
    public List<Message> getMessages() {
        return null;
    }

    @Override
    public boolean addMessage(Message message) {
        return false;
    }

    @Override
    public boolean removeMessage(Message message) {
        return false;
    }

    @Override
    public List<User> getSubscribers() {
        return null;
    }

    @Override
    public boolean addSubscriber(User subscriber) {
        return false;
    }

    @Override
    public boolean removeSubscriber(User subscriber) {
        return false;
    }
}
