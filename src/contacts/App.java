package contacts;


import contacts.contract.GeneralContact;
import contacts.implementation.Organizations;
import contacts.implementation.People;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private final List<GeneralContact> generalContacts;
    public App() {
        generalContacts = new ArrayList<>();
    }

    public void addGeneralContact() {
        Long id = (long) (generalContacts.size() + 1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type (person, organization): ");

        switch (sc.nextLine()){
            case "person":
                People people = new People();
                System.out.println("Enter the name: ");
                people.setName(sc.nextLine());
                System.out.println("Enter the surname: ");
                people.setSurname(sc.nextLine());
                System.out.println("Enter the birth date: ");
                people.setBirthDate(sc.nextLine());
                System.out.println("Enter the gender (M, F): ");
                people.setGender(sc.nextLine());
                System.out.println("Enter the number:");
                people.setPhoneNumber(sc.nextLine());
                System.out.println("A record added.");
                people.setId(id);
                generalContacts.add(people);
                break;
            case "organization":
                Organizations organizations = new Organizations();
                System.out.println("Enter the organization name: ");
                organizations.setName(sc.nextLine());
                System.out.println("Enter the address: ");
                organizations.setAddress(sc.nextLine());
                System.out.print("Enter the number:");
                organizations.setPhoneNumber(sc.nextLine());
                System.out.println("A record added.");
                organizations.setId(id);
                generalContacts.add(organizations);
                break;
            default:
                break;
        }

    }


    public void list() {
        if (generalContacts.size() == 0) {
            System.out.println("No records to list");
        } else {
            for (GeneralContact contact : generalContacts) {
                System.out.println(contact.getId() + ". " + contact.getFullName());
            }
        }
    }

    public void contactInfo(int number) {
        if (generalContacts.size() == 0) {
            System.out.println("No records to list!");
        } else {
            GeneralContact generalContact = generalContacts.get(number - 1);
            for (String fields : generalContact.getAllPossibleFields().keySet()) {
                System.out.println(generalContact.getAllPossibleFields().get(fields) + ": " + generalContact.getFieldValue(fields));
            }
            System.out.println("Time created: " + generalContact.getTimeCreated().withNano(0).withSecond(0));
            System.out.println("Time last edit: "+ generalContact.getTimeLastEdit().withNano(0).withSecond(0));
        }
    }

    public void editContact(int number) {
        if (generalContacts.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            Scanner sc = new Scanner(System.in);
            GeneralContact contact = generalContacts.get(number);
            Object[] nameOption = contact.getAllPossibleFields().keySet().toArray();
            System.out.println("Select a field ("+ Arrays.toString(nameOption).replace("[","").replace("]","")+")");
            String field = sc.nextLine();
            System.out.println("Enter "+contact.getAllPossibleFields().get(field).toLowerCase()+" :");

            contact.setField(field,sc.nextLine());
            System.out.println("Saved");
            this.contactInfo(number+1);
        }
    }


    public void removeGeneralContact(int id) {

        if (generalContacts.size() == 0 ) {
            System.out.println("No records to remove!");
        } else {
            for (GeneralContact generalContact : generalContacts) {
                if (generalContact.getId() == id) {
                    generalContacts.remove(id - 1);
                    restructureID();
                    break;
                }
            }
            System.out.println("The record removed!");
        }
    }

    public void restructureID() {
        for (int i = 0; i < generalContacts.size(); i++) {
            GeneralContact contact = generalContacts.get(i);
            contact.setId(i + 1L);
        }

    }

    public Long countGeneralContact() {

        Long count = 0L;
        for (GeneralContact generalContact : generalContacts){
            if (generalContact != null){
                count++;
            }else {
                System.out.println("No records to count!");
            }
        }
        return count;
    }

    public List<Integer> search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter search query: ");
        String search = sc.nextLine();
        Pattern pattern = Pattern.compile(search.toLowerCase());

        List<Integer> integerList = new ArrayList<>();

        for (GeneralContact generalContact : generalContacts){
            for (String str : generalContact.getAllPossibleFields().keySet()) {
                Matcher matcher = pattern.matcher(generalContact.getFieldValue(str).toLowerCase());

                if(matcher.find()){
                    integerList.add(generalContacts.indexOf(generalContact));
                    break;
                }
            }
        }
        System.out.println("Found "+ integerList.size()+" results:");

        int count = 1;
        for (int integer : integerList) {
            System.out.println(count + ". " + generalContacts.get(integer).getFullName());
            count++;
        }

        return integerList;


    }











}
