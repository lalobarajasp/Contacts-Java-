package contacts.contract;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GeneralContact implements Serializable {
    private Long id;
    private String name;
    private String phoneNumber;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    public GeneralContact() {
        this.id = 0L;
        this.name = "";
        this.phoneNumber = "";
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    public GeneralContact(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    // Method to get all the possible fields this class can change
    public abstract Map<String, String> getAllPossibleFields();

    // Method to set a field and its value
    public abstract void setField(String field, String value);

    // Method to get the value of a field
    public abstract String getFieldValue(String field);

    public abstract String getFullName();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getPhoneNumber() {
        if (phoneNumber == "") {
            return "[no number]";
        } else {
            return phoneNumber;
        }

    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == ""){
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
            this.timeLastEdit = LocalDateTime.now();
        }
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            this.timeCreated= LocalDateTime.now();
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
            this.timeLastEdit = LocalDateTime.now();
        }
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }

    public boolean hasNumber() {
        if(getPhoneNumber() != null) {
            return true;
        } else {
            return false;
        }
    }

    //Validations
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex =  "^\\+?([\\da-zA-Z]{1,}[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean result = matcher.matches();

        return result;
    }


}
