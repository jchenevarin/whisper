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
public class User implements Subscribable, Messageable {

    private String login;
    private String password;
    private LocalDateTime created;
    private String bio;
    private List<Group> groups;
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
