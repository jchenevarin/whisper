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
public class Event implements Subscribable, Messageable {

    private String title;
    private String desc;
    private LocalDateTime date;
    private Location location;

    private Boolean limitedAccess;
    private LocalDateTime registrationOpening;
    private List<User> registered;
    private List<User> awaiting;
    private List<User> canceled;

    private Boolean enabledChat;
    private Boolean enabledMod;
    private List<Message> messages;

    private List<User> subscribers;

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
