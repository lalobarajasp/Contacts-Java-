package contacts;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option;
        boolean correctOption = false;

        App app = new App();
        ObjectOutputStream oout = null;

        if (args.length != 0) {
            File file = new File(args[0]);
            if (file.exists()){
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    app = (App) ois.readObject();

                    FileOutputStream fops = new FileOutputStream(file);
                    oout = new ObjectOutputStream(fops);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else  {
                try {
                    FileOutputStream fileout = new FileOutputStream(args[0]);
                    oout = new ObjectOutputStream(fileout);
                    app = new App();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else  {
            app = new App();
        }



        do{
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            option = sc.nextLine();
            switch (option){
                case "add":
                    app.addGeneralContact();
                    if (oout != null){
                        try {
                            oout.writeObject(app);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    correctOption = true;
                    break;

                case "list":
                    list(app);
                    System.out.println();
                    System.out.println("[list] Enter action ([number], back): ");
                    int number;
                    String action = sc.nextLine();
                    if (action.equals("back")) {
                        break;
                    } else {
                        try {
                            number = Integer.parseInt(action);
                            contactInfo(app, number);
                        } catch (Exception e) {
                            System.out.println("Wrong action!");
                            break;
                        }
                    }
                    System.out.println();
                    String submenu;
                    do {
                        System.out.println("[record] Enter action (edit, delete, menu): ");
                        submenu = sc.nextLine();
                        if(submenu.equals("menu")) {
                            break;
                        } else if (submenu.equals("edit")) {
                            editContact(app,number - 1);
                            System.out.println();
                            if (oout != null) {
                                try {
                                    oout.writeObject(app);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        } else if (submenu.equals("delete")) {
                            remove(app,number);
                            System.out.println();
                            if (oout != null) {
                                try {
                                    oout.writeObject(app);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }


                    } while (!submenu.equals("menu"));
                    correctOption = true;
                    break;

                case "search":
                    List<Integer> search = app.search();
                    System.out.println();
                    System.out.println("[search] Enter action ([number], back, again): ");
                    String click;
                    do {
                        click = sc.nextLine();
                        if (click.equals("back")) {
                            break;
                        } else if (click.equals("again")){
                            search = app.search();
                            System.out.println();
                            System.out.println("[search] Enter action ([number], back, again): ");
                            continue;
                        } else {
                            int num = Integer.parseInt(click);
                            contactInfo(app,num);
                            System.out.println();
                            System.out.println("[record] Enter action (edit, delete, menu): ");
                            String menuOption;
                            do {
                                menuOption = sc.nextLine();
                                if (menuOption.equals("menu")) {
                                    click = "back";
                                    break;
                                } else if (menuOption.equals("edit")) {
                                    editContact(app, search.get(num - 1));
                                    System.out.println();
                                    if (oout != null) {
                                        try {
                                            oout.writeObject(app);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    System.out.println("[record] Enter action (edit, delete, menu): ");

                                } else if (menuOption.equals("delete")) {
                                    remove(app, search.get(num));
                                    System.out.println();
                                    if (oout != null) {
                                        try {
                                            oout.writeObject(app);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    System.out.println("[record] Enter action (edit, delete, menu): ");
                                }

                            }while (!menuOption.equals("menu"));
                        }

                    } while (!click.equals("back"));
                    correctOption = true;
                    break;

                case "count":
                    count(app);
                    correctOption = true;
                    break;

                case "exit":
                    correctOption = false;
                    break;

                default:
                    correctOption = true;
            }

            System.out.println();
        }while (correctOption);



        sc.close();
    }

    private static void count(App app){
        System.out.println("The Phone Book has " + app.countGeneralContact() + " records.");
    }

    private static void remove(App app, int id) {
        app.removeGeneralContact(id);
    }

    private static void list(App app){
        app.list();
    }

    private static void contactInfo(App app, int number){
        app.contactInfo(number);
    }

    private static void editContact(App app, int number) {
        app.editContact(number);
    }





}
