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
public class Topic implements Subscribable, Messageable {

    private String title;
    private String desc;
    private LocalDateTime created;
    private List<User> admins;
    private List<String> tags;
    private List<Event> events;

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
