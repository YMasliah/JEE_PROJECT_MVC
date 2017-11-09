package springapp.business;

public class Message {

    Integer number;

    String text;

    public Message() {
        super();
    }

    public Message(String text) {
        super();
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}