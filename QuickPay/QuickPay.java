package QuickPay;
import java.util.*;
public class QuickPay {
    static Scanner scan =new Scanner(System.in);
    public static void main(String[] args) {
        int userids[]={0,1,2,3,4,5,6,7,8,9};
        String usernames[]={"Ahmed","Mohamed","Youssef","Mostafa","Hasan","Ahmed1","Mazen","Fatma","Doha","Jana"};
        String emails[]={"Ahmed@gmail.com","Mohamed@gmail.com","Youssef@gmail.com","Mostafa@gmail.com","Hasan@gmail.com","Ahmed1@gmail.com","Mazen@gmail.com","Fatma@gmail.com","Doha@gmail.com","Jana@gmail.com"};
        String phonenumbers[]={"01200000000","01200000001","01200000002","01200000003","01200000004","01200000005","01200000006","01200000007","01200000008","01200000009"};
        String passwords[]={"000000","000001","000002","000003","000004","000005","000006","000007","000008","000009"};
        long balanceinpiastre[]={1000000,500000,2000000,1500000,100000,120000,250000,380000,1300000,300000};
        boolean active[]={true,true,true,false,true,true,true,false,true,true};
        String transactionsids[]={"1234","1000","1012","1050"};
        String transactionsenders[]={"Ahmed","Youssef","Ahmed1","Fatma"};
        String transactionreceivers[]={"Mohamed","Ahmed","Mazen","Ahmed1"};
        boolean transactionsuccess[]={true,true,false,true};
        long transactionamounts[]={100000,500000,150000,150000};
        String favouritecontacts[][]=new String[userids.length][userids.length-1];
        String adminusername="ADMIN";
        String adminpassword="1234";
        System.out.println("======= Quick Pay =======");
        System.out.println("1. log in as admin");
        System.out.println("2. log in as user");
        System.out.println("3. exit");
        System.out.print("Choose an option:");
        int login=scan.nextInt();
        while(login>3||login<1){
            System.out.print("Please enter a valid number:");
            login=scan.nextInt();
            scan.nextLine();
        }
        switch(login){
            case 1:
                //admin login
                scan.nextLine();
                System.out.print("Enter enter admin user name:");
                String username=scan.nextLine();
                System.out.print("Enter your password:");
                String password=scan.nextLine();
                if (username.equals(adminusername)&&password.equals(adminpassword)){
                    System.out.println("====== Admin Panel ======");
                    System.out.println("1. Show Statistics");
                    System.out.println("2. Search User");
                    System.out.println("3. View all users");
                    System.out.println("4. Update User");
                    System.out.println("5. View all transactions");
                    System.out.println("6. Block account");
                    System.out.println("7. Unblock account");
                    System.out.println("8. View user details");
                    System.out.println("9. Logout");
                    System.out.print("\nChoose an option:");
                    int operation= scan.nextInt();
                    while (operation>9||operation<1){
                        System.out.print("Please enter a valid value:");
                        operation= scan.nextInt();
                    }
                    boolean running=true;
                    while(running){
                        switch (operation){
                            case 1:
                                ShowStatistics(active,transactionsuccess,transactionamounts);
                                System.out.print("To exit type false ,to stay type true : ");
                                running=scan.nextBoolean();
                                break;
                            case 2:
                                int index=SearchUser(usernames,emails,phonenumbers,userids);
                                if (index> userids.length-1||index<0){
                                    System.out.println("There is no match.");

                                }   else {
                                    System.out.println("id: "+userids[index]);
                                    System.out.println("username: "+usernames[index]);
                                    System.out.println("email: "+emails[index]);
                                    System.out.println("phone number: "+phonenumbers[index]);
                                    System.out.println("password: "+passwords[index]);
                                    System.out.println("balance: "+(balanceinpiastre[index]/100)+"."+(balanceinpiastre[index]%100));
                                    System.out.println("account is active: "+active[index]);
                                }
                                System.out.print("To exit type false ,to stay type true : ");
                                running=scan.nextBoolean();
                                break;
                            case 3:
                                ViewAllUsers(userids,usernames,emails,phonenumbers,balanceinpiastre,active);
                                System.out.print("To exit type false ,to stay type true : ");
                                running=scan.nextBoolean();
                                break;
                            case 4:
                                UpdateUser(userids,usernames,emails,phonenumbers);
                                System.out.print("To exit type false ,to stay type true : ");
                                running=scan.nextBoolean();
                                break;
                        }
                        if (running){
                            System.out.println("====== Admin Panel ======");
                            System.out.println("1. Show Statistics");
                            System.out.println("2. Search User");
                            System.out.println("3. View all users");
                            System.out.println("4. Update User");
                            System.out.println("5. View all transactions");
                            System.out.println("6. Block account");
                            System.out.println("7. Unblock account");
                            System.out.println("8. View user details");
                            System.out.println("9. Logout");
                            System.out.print("\nChoose an option:");
                            operation= scan.nextInt();
                            while (operation>9||operation<1){
                                System.out.print("Please enter a valid value:");
                                operation= scan.nextInt();
                            }
                        }
                    }

                }else {
                    System.out.println("Wrong username or password");
                }
                break;
            case 2:
                //user login
                break;
            case 3:
                System.out.println("Thank you for using Quick Pay");
                break;
        }
    }
    static void ShowStatistics(boolean active[],boolean transactionsuccess[],long transactionsamount[]){
        System.out.println("Total number of registered users: "+active.length);
        int activenum=0;
        int blockednum=0;
        int success=0;
        int fail=0;
        for (int i=0;i< active.length;i++){
            if (active[i]){
                activenum++;
            }else {
                blockednum++;
            }
        }
        for (int i=0;i< transactionsuccess.length;i++){
            if (transactionsuccess[i]){
                success++;
            }else {
                fail++;
            }
        }
        long amount=0;
        for (int i=0;i<transactionsamount.length;i++){
            amount+=transactionsamount[i];
        }
        long amountpounds=amount/100;
        long amountpiastre=amount%100;
        System.out.println("Number of active users: "+activenum);
        System.out.println("Number of blocked users: "+blockednum);
        System.out.println("Total number of successful transactions: "+success);
        System.out.println("Total number of failed transactions: "+fail);
        System.out.println("Total amount of transfers: "+amountpounds+"."+amountpiastre);
    }
    static int SearchUser(String usernames[],String emails[],String phonenumbers[],int userids[]){
        System.out.println("Choose a way to look for user \n1. user ID\n2. Username\n3. Phone number\n4. email adress");
        System.out.print("Choose an option :");
        int operation=scan.nextInt();
        while (operation>4||operation<1){
            System.out.print("Please enter a valid number: ");
            operation=scan.nextInt();
        }
        scan.nextLine();
        int index=-2000;
        switch (operation){
            case 1:
                System.out.print("enter the id: ");
                int id=scan.nextInt();
                for (int i=0;i< userids.length;i++){
                    if (userids[i]==id){
                        index=i;
                    }
                }
                break;
            case 2:
                System.out.print("Enter the username: ");
                String username=scan.nextLine();
                for (int i=0;i<usernames.length;i++){
                    if (usernames[i].equals(username)){
                        index=i;
                    }
                }
                break;
            case 3:
                System.out.print("Enter phone number: ");
                String phone=scan.nextLine();
                for (int i=0;i< phonenumbers.length;i++){
                    if (phonenumbers[i].equals(phone)){
                        index=i;
                    }
                }
                break;
            case 4:
                System.out.print("Enter email: ");
                String mail=scan.nextLine();
                for (int i=0;i< emails.length;i++){
                    if (emails[i].equals(mail)){
                        index=i;
                    }
                }
                break;
        }
        return index;
    }
    static void ViewAllUsers(int usersid[],String usernames[],String emails[],String phonenumbers[],long balance[],boolean status[]){
        for (int i=0;i< usersid.length;i++){
            System.out.println("User id: "+usersid[i]);
            System.out.println("User name: "+usernames[i]);
            System.out.println("User email: "+emails[i]);
            System.out.println("User phone number: "+phonenumbers[i]);
            System.out.println("User balance: "+(balance[i]/100)+"."+(balance[i]%100));
            System.out.println("User status: "+status[i]);
            System.out.println("=========================");
        }
    }
    static void UpdateUser(int userids[],String usernames[],String emails[],String phonenums[]){
        System.out.print("enter the id: ");
        int index=-1;
        int id=scan.nextInt();
        for (int i=0;i< userids.length;i++){
            if (userids[i]==id){
                index=i;
            }
        }
        System.out.println("what do you want to update:\n1. username\n2. email address\n3. phone number");
        int update= scan.nextInt();
        scan.nextLine();
        while (update>3||update<1){
            System.out.print("Please enter a valid value:");
            update= scan.nextInt();
        }
        switch (update){
            case 1:
                String name;
                boolean namedup;
                do {
                    namedup=false;
                    System.out.print("Enter the new name: ");
                    name =scan.nextLine();
                    for (int i=0;i< usernames.length;i++){
                        if (usernames[i].equals(name)){
                            namedup=true;
                        }
                    }
                    if (namedup) System.out.println("Try another name");
                }while (namedup);
                usernames[index]=name;
                System.out.println("Name changed successfully.");
                break;
            case 2:
                boolean emaildup;
                boolean wrongformat;
                String email;
                int check=-1;
                do{emaildup=false;
                    wrongformat=false;
                    System.out.print("Enter the new email: ");
                    email =scan.nextLine();
                    if (!email.contains("@")){
                        wrongformat=true;
                    }
                    check = email.indexOf("@");
                    if (!email.substring(check).contains(".com")){
                        wrongformat=true;
                    }
                    for (int i=0;i< emails.length;i++){
                        if (emails[i].equals(email)){
                            emaildup=true;
                        }
                    }
                    if (emaildup||wrongformat) {
                        System.out.println("Wrong format of email already taken");
                    }
                } while (emaildup||wrongformat);
                emails[index]=email;
                System.out.println("Email changed successfully.");
                break;
            case 3:
                boolean phonedup;
                String phone;
                do {
                    phonedup=false;
                    System.out.print("Enter the new phone number: ");
                    phone =scan.nextLine();
                    for (int i=0;i< phonenums.length;i++){
                        if (phonenums[i].equals(phone)){
                            phonedup=true;
                        }
                    }
                    if (phonedup) System.out.println("Try another number");
                }while (phonedup);
                phonenums[index]=phone;
                System.out.println("phone number changed successfully.");
                break;
        }
    }

}