package QuickPay;
import java.util.*;
public class QuickPay {
    static Scanner scan =new Scanner(System.in);
    public static void main(String[] args) {
        int lasttransfer[]={0};
        int userids[]={0,1,2,3,4,5,6,7,8,9};
        String usernames[]={"Ahmed","Mohamed","Youssef","Mostafa","Hasan","Ahmed1","Mazen","Fatma","Doha","Jana"};
        String emails[]={"Ahmed@gmail.com","Mohamed@gmail.com","Youssef@gmail.com","Mostafa@gmail.com","Hasan@gmail.com","Ahmed1@gmail.com","Mazen@gmail.com","Fatma@gmail.com","Doha@gmail.com","Jana@gmail.com"};
        String phonenumbers[]={"01200000000","01200000001","01200000002","01200000003","01200000004","01200000005","01200000006","01200000007","01200000008","01200000009"};
        String passwords[]={"000000","000001","000002","000003","000004","000005","000006","000007","000008","000009"};
        long balanceinpiastre[]={1000000,500000,2000000,1500000,100000,120000,250000,380000,1300000,300000};
        boolean active[]={true,true,true,false,true,true,true,false,true,true};
        String transactionsids[]=new String[100];
        transactionsids[0]="0101";
        String transactionsenders[]=new String[100];
        transactionsenders[0]="Ahmed@gmail.com";
        String transactionreceivers[]=new String[100];
        transactionreceivers[0]="Mohamed@gmail.com";
        boolean transactionsuccess[]=new boolean[100];
        transactionsuccess[0]=true;
        long transactionamounts[]=new long[100];
        transactionamounts[0]=100000;
        String favouritecontacts[][]=new String[userids.length][userids.length-1];
        String adminusername="ADMIN";
        String adminpassword="1234";
        boolean logout=false;
        do {
        int login=0;
            System.out.println("======= Quick Pay =======");
        System.out.println("1. log in as admin");
        System.out.println("2. log in as user");
        System.out.println("3. exit");
        System.out.print("Choose an option:");
        login=scan.nextInt();
        while(login>3||login<1){
            System.out.print("Please enter a valid number:");
            login=scan.nextInt();
            scan.nextLine();
        }
        scan.nextLine();
        switch(login){
            case 1:
                //admin login
                System.out.print("Enter admin user name:");
                String username=scan.nextLine();
                System.out.print("Enter your password:");
                String password=scan.nextLine();
                if (username.equals(adminusername)&&password.equals(adminpassword)){
                    logout=false;
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
                    while(running&&!logout){
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
                            case 9:
                                logout=true;
                        }
                        if (running&&!logout){
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
                int index;
                boolean running=false;
                boolean loginsuccess=false;
                do {System.out.print("Enter user name:");
                username=scan.nextLine();
                System.out.print("Enter your password:");
                password=scan.nextLine();
                index=-1;
                for (int i=0;i< usernames.length;i++){
                    if (username.equals(usernames[i])){
                        index=i;
                    }
                }if(index>-1){if (password.equals(passwords[index])){
                    loginsuccess=true;
                }}else {
                    System.out.println("Wrong username or password");
                }
                }while (!loginsuccess);
                do {
                int operation;
                do {System.out.println("======User Panel======");
                System.out.println("1. Show Balance\n" +
                        "2. Transfer Money\n" +
                        "3. Transaction History\n" +
                        "4. Show Favorites\n"+
                        "5. Add Favorite\n" +
                        "6. Remove Favorite\n" +
                        "7. Search Recipient\n" +
                        "8. View Profile\n" +
                        "9. Update Profile\n" +
                        "10. Logout\n\nChoose an option:");
                operation=scan.nextInt();
                if (operation>9||operation<1){
                    System.out.println("invalid input");
                }
                }while(operation>9||operation<1);
                switch (operation){
                    case 1:
                        ShowBalance(index,balanceinpiastre);
                        System.out.print("To exit type false ,to stay type true : ");
                        running=scan.nextBoolean();
                        break;
                    case 2:
                        TransferMoney(emails,phonenumbers,balanceinpiastre,index,userids,transactionsuccess,lasttransfer,transactionsids,transactionsenders,transactionreceivers,transactionamounts);
                        System.out.print("To exit type false ,to stay type true : ");
                        running=scan.nextBoolean();
                        break;
                    case 3:

                }}while (running);
                break;
            case 3:
                System.out.println("Thank you for using Quick Pay");
                break;
        }
        }while (logout);
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
        int index=-1;
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
    static void ShowBalance(int index,long balance[]){
        System.out.println("Your balance is : "+ balance[index]/100+"."+balance[index]%100);
    }
    static void TransferMoney(String emails[],String phonenumbers[],long balances[],int userindex,int userids[],boolean success[],int lasttrans[]
    ,String transactionid[],String transactionsender[],String transactionreciever[],long transactionamount[]){
        boolean wrong = true;
        int transferindex=-1;
        do {
        System.out.println("1. email\n2. phone number\n\nchoose a way to transfer: ");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice){
            case 1:
                System.out.println("Enter the email of the user you are transferring to: ");
                String email=scan.nextLine();
                for (int i=0;i< emails.length;i++){
                    if (email.equals(emails[i])){
                        transferindex=i;
                        wrong=false;
                    }
                }
                break;
            case 2:
                System.out.println("Enter the phone number of the user you are transferring to: ");
                String phonenum=scan.nextLine();
                for (int i=0;i< phonenumbers.length;i++){
                    if (phonenum.equals(phonenumbers[i])){
                        transferindex=i;
                        wrong=false;
                    }
                }
                break;
        }
    }while (wrong);
        boolean issuccess=false;
        do {
            System.out.println("Your balance: " + balances[userindex] / 100 + "." + balances[userindex] % 100);
            System.out.println("Enter the amount you want to transfer in piastre: ");
            long transfer = scan.nextLong();
            if (transfer < balances[userindex]) {
                balances[userindex] -= transfer;
                balances[transferindex] += transfer;
                System.out.println("Transferred successfully");
                lasttrans[0] += 1;
                success[lasttrans[0]] = true;
                transactionid[lasttrans[0]] = (userids + "" + transferindex + lasttrans);
                transactionsender[lasttrans[0]] = emails[userindex];
                transactionreciever[lasttrans[0]] = emails[transferindex];
                transactionamount[lasttrans[0]] = transfer;
                issuccess = true;
            } else {
                lasttrans[0] += 1;
                success[lasttrans[0]] = false;
                transactionid[lasttrans[0]] = (userids + "" + transferindex + lasttrans);
                transactionsender[lasttrans[0]] = emails[userindex];
                transactionreciever[lasttrans[0]] = emails[transferindex];
                transactionamount[lasttrans[0]] = transfer;
                System.out.println("Transaction failed");
            }
        }while (!issuccess);
    }
}