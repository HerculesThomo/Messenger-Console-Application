package MainUtilities;

import DaoImplementation.InboxDao;
import DaoImplementation.MessageDao;
import DaoImplementation.SentDao;
import DaoImplementation.UserDao;
import Database.Database;
import Entities.Inbox;
import Entities.Message;
import Entities.Sent;
import Entities.User;
import Files.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author herth
 */
public class Menu {

    Scanner scan = new Scanner(System.in);
    Files f = new Files();
    Login login = new Login();

    public Menu() {
        Database.getInstance();
    }

    public String Menu() {

        String username = null;
        System.out.println("(=======================================)");
        System.out.println("(=== Welcome to Messanger Application ==)");
        System.out.println("(==============Please Login============) ");
        System.out.println("(========Give me your username==========)");
        username = scan.next();
        System.out.println("(========Give me your password===========");
        System.out.println("(=======================================)");
        String password = scan.next();

        boolean result = login.isUser(username, password);
        if (result == true) {
        } else {
            System.out.println("Not valid username or password"
                    + " please try again");
            return Menu();
        }
        UsersMove(username, "Loged in ");

        options(username);
        return username;
    }

    private void showSuperAdminsMenu(String choice, String Menu) {

        String username = Menu;
        switch (choice) {

            case "1":
                Menu();
            case "2":
                MessageDao allMessages = new MessageDao();
                UsersMove("SuperAdmin ", " see all messages");
                List<Message> allM = allMessages.findAllMessages();
                for (Message i : allM) {
                    System.out.println(i);
                }
                choice(username);
                break;
            case "3":
                MessageDao createM = new MessageDao();
                int rows = createM.countMessages();
                InboxDao inboxDao = new InboxDao();
                SentDao sentDao = new SentDao();
                UserDao userDao = new UserDao();
                int userId = userDao.getUsersId(username);
                String[] values = objectMessage(username);
                Message message = new Message(values[0], values[1], values[2], values[3]);
                createM.createMessage(message);
                inboxDao.savedToInbox(values[1], values[2], userId, rows);
                sentDao.savedToSent(values[1], values[3], userId, rows);
                UsersMove("SuperAdmin ", " create Message");
                choice(username);
                break;
            case "4":
                MessageDao updateM = new MessageDao();
                List<Message> allMe = updateM.findAllMessages();
                for (Message i : allMe) {
                    System.out.println(i);
                }
                String[] value = objectMessage(username);
                System.out.println("Give the id of the message you want to change");
                int idM = scan.nextInt();
                Message mess = new Message(idM, value[0], value[1], value[2], value[3]);
                updateM.updateMessage(mess);
                UsersMove("SuperAdmin ", "update a message");
                choice(username);
                break;
            case "5":
                UserDao createU = new UserDao();
                List<String> userLists = createU.findAllUsersName();
                for (String u : userLists) {
                    System.out.println(u);
                }
                String[] data = objectUser();
                User user = new User(data[0], data[1], data[2], data[3], data[4], data[5]);
                UsersMove("SuperAdmin ", "create the user " + data[0]);
                createU.createUser(user);
                choice(username);
                break;
            case "6":
                UserDao updateU = new UserDao();
                String[] datas = objectUser();
                System.out.println("Give the id");
                int id = scan.nextInt();
                User use = new User(id, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5]);
                updateU.updateUser(use);
                UsersMove("SuperAdmin ", " update the user " + datas[0]);
                choice(username);
                break;
            case "7":
                UserDao deleteU = new UserDao();
                List<String> delUser = deleteU.findAllUsersName();
                for (String u : delUser) {
                    System.out.println(u);
                }
                System.out.println("Give the name of the user you want to delete");
                String duser = scan.next();
                if (!delUser.contains(duser)) {
                    do {
                        System.out.println("The username you wanna delete does not exists");
                        System.out.println("Give the username you wanna delete");
                        duser = scan.next();
                    } while (!delUser.contains(duser));
                }
                deleteU.deleteUser(duser);
                UsersMove("SuperAdmin ", "delete the user " + duser);
                choice(username);
                break;
            case "8":
                UserDao allU = new UserDao();
                List<User> all = allU.getAllUsers();
                for (User s : all) {
                    System.out.println(s);
                }
                UsersMove("SuperAdmin ", "search for all users");
                choice(username);
                break;
        }
    }

    private void showAdminsMenu(String choice) {

        switch (choice) {
            case "1":
                Menu();
                break;
            case "2":
                MessageDao allMessages = new MessageDao();
                List<Message> allM = allMessages.findAllMessages();
                for (Message i : allM) {
                    System.out.println(i);
                }
                UsersMove("Admin ", "search for all messages");
                break;
        }
    }

    private void showUsersMenu(String choice, String Menu) {

        String username = Menu;
        switch (choice) {
            case "1":
                Menu();
                break;
            case "2":
                MessageDao createM = new MessageDao();
                String[] values = objectMessage(username);
                Message message = new Message(values[0], values[1], values[2], values[3]);
                createM.createMessage(message);
                UsersMove(username, "create a message");
                choice(username);
                break;
            case "3":
                try {
                UserDao use = new UserDao();
                InboxDao updateInbox = new InboxDao();
                List<Inbox> upList = updateInbox.findAllInboxMessages(use.getUsersId(username));
                for (Inbox i : upList) {
                    System.out.println(i);
                }
                scan.nextLine();
                System.out.println("Give the changed Data");
                String change = scan.nextLine();
                System.out.println("Give the Senter");
                String from = scan.next();
                updateInbox.updateInboxMessage(change, from, use.getUsersId(from));
                UsersMove(username, "update a message");
                choice(username);}
                catch(NullPointerException ex) {
                    System.out.println("No message to update");
                    options(username);
                }
                break;
            case "4":
                try {
                InboxDao deleteInbox = new InboxDao();
                UserDao in = new UserDao();
                List<Inbox> delInbox = deleteInbox.findAllInboxMessages(in.getUsersId(username));
                for (Inbox i : delInbox) {
                    System.out.println(i);
                }
                System.out.println("Give from whom you wanna delete the message");
                String sender = scan.next();
                if (!delInbox.contains(sender)) {
                    do {
                        System.out.println("The sender does not exists");
                        System.out.println("Enter another sender");
                        sender = scan.next();
                    } while (!delInbox.contains(sender));
                }
                deleteInbox.deleteInboxMessage(sender);
                UsersMove(username, "delete an inbox message");
                choice(username);
                break;}
                catch (NullPointerException ex) {
                    System.out.println("No inbox to delete");
                    options(username);
                }
            case "5":
                try {
                UserDao inbox = new UserDao();
                InboxDao findAllInboxMessages = new InboxDao();
                List<Inbox> allInMe = findAllInboxMessages.findAllInboxMessages(inbox.getUsersId(username));
                for (Inbox i : allInMe) {
                    System.out.println(i);
                }
                UsersMove(username, "search for all inbox messages");
                choice(username);}
                catch(NullPointerException ex) {
                    System.out.println("No inbox message");
                    options(username);
                }
                break;
            case "6":
                try {
                UserDao upSent = new UserDao();
                SentDao updateSent = new SentDao();
                List<Sent> allMess = updateSent.findAllSentMessages(upSent.getUsersId(username));
                for (Sent i : allMess) {
                    System.out.println(i);
                }
                System.out.println("Give the changed data");
                String data = scan.next();
                System.out.println("Give the receiver");
                String to = scan.next();
                updateSent.updateSentMessage(data, upSent.getUsersId(to), to);
                UsersMove(username, "update a sent message");
                choice(username);}
                catch(NullPointerException ex) {
                    System.out.println("No message to update");
                    options(username);
                }
                break;
            case "7":
                SentDao deleteSent = new SentDao();
                UserDao delSent = new UserDao();
                try {
                    List<Sent> allMessage = deleteSent.findAllSentMessages(delSent.getUsersId(username));
                    for (Sent i : allMessage) {
                        System.out.println(i);
                    }

                    System.out.println("Give the name of receiver");
                    deleteSent.deleteSentMessage(scan.next());
                    UsersMove(username, "delete a sent message");
                    choice(username);
                } catch (NullPointerException ex) {
                    System.out.println("There is no message to Delete");
                    options(username);
                }
                break;
            case "8":
                try {
                UserDao usersId = new UserDao();
                SentDao AllSentMessages = new SentDao();
                List<Sent> allM = AllSentMessages.findAllSentMessages(usersId.getUsersId(username));
                for (Sent i : allM) {
                    System.out.println(i);
                }
                UsersMove(username, "search for all sent messages");
                choice(username);}
                catch(NullPointerException ex) {
                    System.out.println("You have not sent any message");
                    options(username);
                }
                break;

        }

    }

    private String[] objectUser() {

        UserDao user = new UserDao();
        String description = null;
        System.out.println("Give the username");
        String username = scan.next().toLowerCase();
        if (user.findAllUsersName().contains(username)) {
            do {
                System.out.println("This username is already exists");
                System.out.println("Give another username differnet from" + username);
                username = scan.next().toLowerCase();
            } while (user.findAllUsersName().contains(username));
        }
        System.out.println("Give the password until 8 characters");
        String password = scan.next();
        if (password.length() > 8) {
            password = password.substring(0, 8);
        }
        System.out.println("Give the first name until 20 characters");
        String fname = scan.next();
        if (fname.length() > 20) {
            fname = fname.substring(0, 20);
        }
        System.out.println("Give the last name until 20 characters");
        String lname = scan.next();
        if (lname.length() > 20) {
            lname = lname.substring(0, 20);
        }
        System.out.println("Give the role between ('A', 'B' or 'C')");
        String role = scan.next();
        if (role.equals("A")) {
            description = "Only View Data";
        } else if (role.equals("B")) {
            description = "View and Edit data";
        } else if (role.equals("C")) {
            description = "View Edit and Delete data";
        } else {
            role = "B";
            description = "View and Edit data";
        }
        String[] data = {username, password, fname, lname, role, description};

        return data;
    }

    private String[] objectMessage(String username) {

        UserDao user = new UserDao();
        List<String> userList = user.findAllUsersName();
        for (String u : userList) {
            System.out.println(u);
        }
        LocalDate a = LocalDate.now(ZoneId.systemDefault());
        String locdate = a.toString();
        String date = locdate;
        scan.nextLine();
        System.out.println("Type a message until 250 characters");
        String data = scan.nextLine();
        if (data.length() > 250) {
            data = data.substring(0, 250);
        }
        scan.nextLine();
        String sender = username;
        System.out.println("Receiver");
        String receiver = scan.next();
        if (!userList.contains(receiver)) {
            System.out.println("Wrong receiver name");
            System.out.println("Give another receiver");
            receiver = scan.next();
        }
        String[] values = {date, data, sender, receiver};

        return values;
    }

    private static String prepareUserNameForFile(String username) {

        UserDao user = new UserDao();
        List<String> i = user.findAllUsersName();
        for (String s : i) {
            if (s.equals(username)) {
                return s;
            } else {
                System.out.println("This name does not exists");
                return null;
            }

        }

        return null;
    }

    private void UsersMove(String username, String move) {

        Files.setFileName(username);
        Files.logToFile(username + "," + move);
        Files.closeFile();
    }

    private void options(String username) {

        String choice = null;
        switch (username) {
            case "admin":
                showSuperAdminsMenu(isSuperAdminsChoices(), username);
                break;
            case "user1":
                showAdminsMenu(isAdminsChoices());
                break;
            default:
                showUsersMenu(isUsersChoices(), username);
                break;
        }
    }

    private static String isSuperAdminsChoices() {

        String choice;
        Scanner input = new Scanner(System.in);
        Set<String> choices = new TreeSet<>();
        choices.add("1");
        choices.add("2");
        choices.add("3");
        choices.add("4");
        choices.add("5");
        choices.add("6");
        choices.add("7");
        choices.add("8");
        choices.add("9");
        choices.add("10");
        do {
            System.out.print("\"Hello super admin!!\" \n"
                    + "Give 1 to Logout \n"
                    + "Give 2 to See All Messages \n"
                    + "Give 3 to Create a Message\n"
                    + "Give 4 to Update a Message\n"
                    + "Give 5 to Create a User \n"
                    + "Give 6 to Update a User \n"
                    + "Give 7 to Delete a User \n"
                    + "Give 8 to Find All Users\n");

            choice = input.next();

        } while (!choices.contains(choice));
        return choice;
    }

    private static String isAdminsChoices() {

        String choice;
        Scanner input = new Scanner(System.in);
        Set<String> choices = new TreeSet<>();
        choices.add("1");
        choices.add("2");
        do {
            System.out.print("\"Hello Admin\" \n"
                    + "Give 1 to return Logout  \n"
                    + "Give 2 to View the messages \n");

            choice = input.next();

        } while (!choices.contains(choice));
        return choice;
    }

    private static String isUsersChoices() {

        String choice;
        Scanner input = new Scanner(System.in);
        Set<String> choices = new TreeSet<>();
        choices.add("1");
        choices.add("2");
        choices.add("3");
        choices.add("4");
        choices.add("5");
        choices.add("6");
        choices.add("7");
        choices.add("8");
        choices.add("9");

        do {
            System.out.print("\"Hello User\" \n"
                    + "Give 1 to Logout \n"
                    + "Give 2 to Create a Message \n"
                    + "Give 3 to Update Inbox \n"
                    + "Give 4 to Delete Inbox \n"
                    + "Give 5 to See All Inbox Messages \n"
                    + "Give 6 to Update Sent Message\n"
                    + "Give 7 to Delete Sent message\n"
                    + "Give 8 to See All Sent Messages\n");

            choice = input.next();

        } while (!choices.contains(choice));
        return choice;
    }

    private void choice(String username) {

        String choice;
        Scanner input = new Scanner(System.in);
        Set<String> choices = new TreeSet<>();
        choices.add("1");
        choices.add("2");
        choices.add("3");

        do {
            System.out.print("Give 1 to return to the Menu \n"
                    + "Give 2 to Logout \n"
                    + "Give 3 to Terminate the program");
            choice = input.next();

        } while (!choices.contains(choice));

        switch (choice) {
            case "1":
                options(username);
                break;
            case "2":
                Menu();
                break;
            case "3":
                break;

        }
    }

}
