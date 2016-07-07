package model;

import model.entities.Message;

import java.util.List;

/**
 * Author: jch
 * Date: 16-06-24
 * Description: TODO
 */
public interface Messageable {
    List<Message> getMessages();
    boolean addMessage(Message message);
    boolean removeMessage(Message message);
}
