package contacts.implementation;



import contacts.contract.GeneralContact;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


public class Organizations extends GeneralContact {
    private String address;

    public Organizations(Long id, String name, String phoneNumber, String address) {
        super(id, name, phoneNumber);
        this.address = address;
    }

    public Organizations() {
        super();
        this.address = "";
    }

    @Override
    public Map<String, String> getAllPossibleFields() {
        Map<String,String> map=new LinkedHashMap<>();
        map.put("name","Organization name");
        map.put("address","Address");
        map.put("phoneNumber", "Phone Number");
        return map;
    }

    @Override
    public void setField(String field, String value) {
        switch (field){
            case "name":{
                this.setName(value);
                break;
            }
            case "address":{
                this.setAddress(value);
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
        switch (field){
            case "name":{
                return getName();
            }
            case "address":{
                return getAddress();
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
        return getName();
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != "") {
            this.address = address;
            super.setTimeLastEdit(LocalDateTime.now());
        } else {
            this.address = "[no data]";
            System.out.println("Bad address!");
            super.setTimeLastEdit(LocalDateTime.now());
        }

    }



}
