package contacts.implementation;



import contacts.contract.GeneralContact;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People extends GeneralContact {
    private String surname;
    private String birthDate;
    private String gender;

    public People(Long id, String name, String phoneNumber,  String surname, String birthDate, String gender) {
        super(id, name, phoneNumber);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public People() {
        super();
        this.surname = "";
        this.birthDate = "";
        this.gender = "";
    }

    @Override
    public Map<String, String> getAllPossibleFields() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("name","Name");
        map.put("surname","Surname");
        map.put("birthDate","Birth date");
        map.put("gender","Gender");
        map.put("phoneNumber", "Number");
        return map;
    }

    @Override
    public void setField(String field, String value) {
        switch (field){
            case "name":{
                this.setName(value);
                break;
            }
            case "surname":{
                this.setSurname(value);
                break;
            }
            case "birthDate":{
                this.setBirthDate(value);
                break;
            }
            case "gender":{
                this.setGender(value);
                break;
            }
            case "phoneNumber":{
                this.setPhoneNumber(value);
                break;
            }

            default:
                System.out.println("Bad field name!");
        }

    }

    @Override
    public String getFieldValue(String field) {
        switch (field) {
            case "name":{
                return getName();
            }
            case "surname":{
                return getSurname();
            }
            case "birthDate":{
                return getBirthDate();
            }
            case "gender":{
                return getGender();
            }
            case "phoneNumber":{
                return getPhoneNumber();
            }
            default:
                System.out.println("Bad field name!");
                return "";
        }
    }

    @Override
    public String getFullName() {
        return getName() + " " + getSurname();
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        super.setTimeCreated(LocalDateTime.now());
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {

        if (isValidBirthDate(birthDate)) {
            this.birthDate = birthDate;
            super.setTimeCreated(LocalDateTime.now());
        } else  {
            this.birthDate = "[no data]";
            System.out.println("Bad birth date!");
            super.setTimeLastEdit(LocalDateTime.now());
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (isValidGender(gender)) {
            this.gender = gender;
            super.setTimeCreated(LocalDateTime.now());
        } else  {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
            super.setTimeLastEdit(LocalDateTime.now());
        }
    }


    //Validations
    public boolean isValidGender(String gender) {
        String regex = "^[MF]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gender);
        boolean answer = matcher.matches();
        return answer;
    }

    public boolean isValidBirthDate(String birthDate) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthDate);
        boolean answer = matcher.matches();
        return answer;
    }





}
