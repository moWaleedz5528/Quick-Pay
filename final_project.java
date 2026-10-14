import java.util.Arrays;
import java.util.Scanner;
public class final_project {

    static Scanner input = new Scanner(System.in);

    // The Variables
    //===============
    //==============================================================================================================================\\


    //=================

    static int User_Count = 0; // Inintially (قيمة مبدأيه)
    static int capacity = 5; // السعة الحالية للأراي
    static int [] User_IDs = new int[capacity];
    static String [] User_Names = new String[capacity];
    static String [] User_Emails = new String[capacity];
    static String [] User_Phone_Numbers = new String[capacity];
    static String [] User_Passwords = new String[capacity];
    static long [] User_Balances = new long[capacity]; // مكتوب نستخدم لونج احسن من دابل
    
    static boolean [] Active_Users = new boolean[capacity];

    // active = true يعني المستخدم نشط  ; active = false يعني المستخدم غير نشط او معموله بلوك من الادمن


    static int [][] Favorites = new int[capacity][10]; // الارقام المفضلة لكل مستخدم
    // لكل مستخدم 10 ارقام مفضلة بس

    static int [] Favorites_Count = new int[capacity];
    

    static int Transaction_Count = 0; // عدد العمليات الحالية
    static int Transaction_Capacity = 5; // السعة الحالية للأراي

    static int [] Transaction_IDs = new int[Transaction_Capacity]; 
    static int [] Transaction_Senders = new int[Transaction_Capacity];
    static int [] Transaction_Receivers = new int[Transaction_Capacity];
    static long [] Transaction_Amounts = new long[Transaction_Capacity]; // زي ما قولت ال لونج احسن من الدابل
    static String [] Transaction_Statuses = new String[Transaction_Capacity];
    
    // static int [] Transaction_Types = new int[500]; // 1 = send money , 2 = add money , 3 = withdraw money
    // لسه بفكر فيها

    
    //==============================================================================================================================\\


    // Arrays Methods
    //================

    public static void growUses() // بتشتغل اول ما ال array تتملى ف بتزود سايز ال array كل مرة لما تزيد ب 1
    {
        int newCapacity = capacity + 1;
        User_IDs = Arrays.copyOf(User_IDs, newCapacity);
        User_Names = Arrays.copyOf(User_Names, newCapacity);
        User_Emails = Arrays.copyOf(User_Emails, newCapacity);
        User_Phone_Numbers = Arrays.copyOf(User_Phone_Numbers, newCapacity);
        User_Passwords = Arrays.copyOf(User_Passwords, newCapacity);
        User_Balances = Arrays.copyOf(User_Balances, newCapacity);
        Active_Users = Arrays.copyOf(Active_Users, newCapacity);
        Favorites_Count = Arrays.copyOf(Favorites_Count, newCapacity);

        Favorites = Arrays.copyOf(Favorites, newCapacity);

        for (int i = 0; i < newCapacity; i++)
        {

            Favorites[i] = new int[10];
            // عملت لوب عشان كل أراي من المفضله ليها أراي مخصصه ليها اللي هما ال 10 يوزرز

        }

        capacity = newCapacity; // new declaration.
    }


    //==============================================================================================================================\\


    public static void growTransactions()
    {
        int newCapacity = Transaction_Capacity + 1;

        Transaction_IDs = Arrays.copyOf(Transaction_IDs, newCapacity);
        Transaction_Senders = Arrays.copyOf(Transaction_Senders, newCapacity);
        Transaction_Receivers = Arrays.copyOf(Transaction_Receivers, newCapacity);
        Transaction_Amounts = Arrays.copyOf(Transaction_Amounts, newCapacity);
        Transaction_Statuses = Arrays.copyOf(Transaction_Statuses, newCapacity);

        Transaction_Capacity = newCapacity;
    }



    public static void addTransaction(int sender, int receiver, int amount, String status) // نفس شغل ال addUser
    {
        if ( Transaction_Count >= Transaction_Capacity )
        {
            growTransactions();
        }

        Transaction_IDs[Transaction_Count] = 1001 + Transaction_Count; // ال id بيبدأ من 1001 نفس فكرة ال userIDs
        // ممكن نختار اي رقم مش لازم 1001 ممكم اي رقم بس انا ماشي على نفس النمط فوق ال IDs من 101 هنا هتبدأ من 1001

        Transaction_Senders[Transaction_Count] = sender;
        Transaction_Receivers[Transaction_Count] = receiver;
        Transaction_Amounts[Transaction_Count] = amount;
        Transaction_Statuses[Transaction_Count] = status; // 1 . Successful , 2 . Failed , 3 . Pending

        Transaction_Count++;

    }




    //==============================================================================================================================\\
    

    public static void addUser(String name, String email, String phone_number, String password, long balance, boolean Active ) // The parameters
    {

        if (User_Count >= capacity)
        {
            growUses();

//            System.out.println("Maximum number of users reached ! ");
//            return; // عشان يخرج علطول لو مفيش مكان في ال array
        }


        User_Names[User_Count] = name; 
        User_Emails[User_Count] = email;
        User_Phone_Numbers[User_Count] = phone_number;
        User_Passwords[User_Count] = password;
        User_Balances[User_Count] = balance;
        Active_Users[User_Count] = Active;

        User_IDs[User_Count] = 101 + User_Count; // هيطبع الآي دي بتاع كل شخص من 101

        User_Count++;

        // بيخزن بيانات اليوزر اللي الاندكس بتاعه 0 و بعد ما يخلص تخزين يزود الاندكس 1 عشان يدخل على اليوزر الجديد

    }

    // Users
    //=======

    public static void enteringUsers()
    {
        addUser("Noor", "noor@quickpay.com", "01234567890", "noor123", 800000, true);
        addUser("Nour", "nour@quickpay.com", "01223344556", "nour123", 700000, true);
        addUser("Monira", "monira@quickpay.com", "01233445566", "monira123", 600000, true);
        addUser("Mohammed", "mohammed@quickpay.com", "01234455667", "mohammed123", 500000, true);
        addUser("Ahmed", "ahmed@quickpay.com", "01234556677", "ahmed123", 400000, false);

        // هنا خزنت الفلوس على هيئة قروش ده اللي مطلوب
        // مش فارقة الترتيب ها مشوا الدنيا
    }



    // Helping Methods \\
    //=================\\

    // ميثود بتشوف رقم الاندكس متطابق مع الحاجه اللي بنبحث عنها لليوزر ولا لا
    //======================================================================

    public static boolean doesMatch(int i, String key) // بتاخد رقم الاندس بتاع اليوزر تفحصه ف الارايز و بعد كده تشوف في تطابق بين أيا كان اللي هيدخله الادمن سواء اسم او اي حاجه تانيه و تشوف هيتطابق مع الاندكس ولا لا
    {
        return String.valueOf(User_IDs[i]).equals(key) // ( String.valueOf ) --> بتحول اي رقم ل سترينج يعني هتحول الأي دي بتاع كل يوزر لبياناته اللي هي الاسم و الايميل و هكذا عشان نقدر نعمله بلوك او نشيله ب اي طريقة
                || // ( || )==> OR
                User_Names[i].equalsIgnoreCase(key)
                ||
                User_Emails[i].equalsIgnoreCase(key)
                ||
                User_Phone_Numbers[i].equalsIgnoreCase(key);

    }

    // ميثود بتعد تشابهات الاسماء و لو في اسمين متشابهين بتقول ان الاسم متكرر ابحث بطريقة تانيه
    //==========================================================================================

    public static int countMatches(String key)
    {
        int count = 0;

        for (int i = 0; i < User_Count; i++)
        {
            if ( doesMatch(i, key) )
            {
                count++;
            }
        }
        return count;
    }

    // لو اكتر من يوزر هنقول ف ميثودز البلوك duplicate name  لو اسم واحد تمام لو مفيش اصلا يعني 0 هيقول not found
    // شوفوها ف ميثود ال unblock و هتفهموا


    // ميثود بتحول القروش ل جنيهات زي ما مطلوب
    //===========================================

    public static String moneyFormat(long piasters)
    {
        long pounds = piasters / 100; // الجنيهات
        long rest = piasters % 100; // القروش (عملناها بال % modules عشان تطلع الباقي من ناتج القسمة بتاعت الجنيهات و هو القروش )

        if ( rest < 10 ) // لو القروش اقل من 10 قروش يخليها ( 01. ) مثلا و ممكن بدل ال 1 اي رقم لحد 9
        {
            return pounds + ".0" + rest + " EGP"; // هنا هيطلعها مثلا 100.03
        }
        else // هنا هيطلعها عادي مثال 100.3
        {
            return pounds + "." + rest + " EGP";
        }
    }



    //==============================================================================================================================\\


    //==============================// Login \\==============================\\
    //==============================//=======\\==============================\\




    // Asking Try Again or Exit Method \\
    //=================================\\

    // \\ سيبوها دلوقتي هنعملها في الاخر مع ال sign up
    //=============================================\\

    public static boolean askingTryAgain() // NOT USED YETTTTTT
    {
        while (true)
        {
            System.out.println("1. Try again");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine().trim();

            switch (choice)
            {
                case "1":
                    return true;  // Try again

                case "2":
                    return false; // Exit

                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    // مفيش return هنا ف اللوب بتلف وتسأل تاني
            }
        }
    }


    //==============================================================================================================================\\

    // For Admins
    //============
    public static boolean Admin_Login()
    {
        System.out.println("==================================");

        System.out.print("Admin Username : ");
        String userName;
        userName = input.nextLine();

        System.out.print("Admin Password : ");
        String password;
        password = input.nextLine();

        System.out.println("==================================");
        
        if(userName.equals("admin") && password.equals("admin123"))
        {
            System.out.println("Welcome, Admin !");
            return true; 
        }
        else
        {
            System.out.println("Wrong Username or Password !");
            return false; 
        }
    }

    // For Users
    //===========
    public static int User_Login()
    {
        System.out.println("==================================");
        System.out.print("User Email : ");
        String email;
        email = input.nextLine();
    
        System.out.print("User Password : ");
        String password;
        password = input.nextLine();
        System.out.println("==================================");

        for (int i = 0; i < User_Count; i++)
        {
            if (User_Emails[i].equals(email) && User_Passwords[i].equals(password))
            {
                if (!Active_Users[i]) 
                {
                // لو مش متسجل انه ترو في الاكتف هيرجع -1
                // ترو "true" , "active" اكتف

                System.out.println("This account is blocked !");
                return -1;
                }

                System.out.println("Welcome, " + User_Names[i] + " !");
                return i;   //  اليوزر اللي دخل اندكس
            }
        }

            System.out.println("Wrong Email or Password !");
            return -1;
            // و هنا سايب السطرين دول بره عشان كده كده لو اليوزر مش موجود هيرجع ال -1 و مش هيكمل ف الميثود هيرجع القيمة علطول و مش هيبص ع الباقي

            // ال -1 هنا في الميثود دي معناها ان الدخول فشل سواء باسوورد غلط او يوزر او اي حاجه غلط
    }


    //==============================================================================================================================\\


    // Admin Methods
    //===============

    // اكتبوا ميثودز الادمن هنا \\
    //============================


    // Show Statistics Method
    //========================

    public static void showStatistics()
    {
        int activeCount = 0;
        int blockedCount = 0;
        long totalBalance = 0;

        for (int i = 0; i < User_Count; i++)
        {
            if (Active_Users[i])
            {
                activeCount++;
            }

            else
            {
                blockedCount++;
            }

            totalBalance += User_Balances[i];
        }

        int successTrans = 0;
        int failedTrans = 0;
        long totalSuccessAmount = 0; // = total transferred // مجموع الفلوس الناجحة بس

        for (int i = 0; i < Transaction_Count; i++)
        {
            if (Transaction_Statuses[i].equalsIgnoreCase("Successful"))
            {
                successTrans++;
                totalSuccessAmount += Transaction_Amounts[i];
            }
            else if (Transaction_Statuses[i].equalsIgnoreCase("Failed"))
            {
                failedTrans++;
            }
        }

        // ---------
        //

        System.out.println("====================================");
        System.out.println("========= << STATISTICS >> =========");
        System.out.println("====================================");

        System.out.println("          ||    Users   ||          ");
        System.out.println("------------------------------------");
        System.out.println("Total Registered Users : " + User_Count);
        System.out.println("Active Users           : " + activeCount);
        System.out.println("Blocked Accounts       : " + blockedCount);
        System.out.println("Total Balances         : " + moneyFormat(totalBalance));

        System.out.println("====================================");

        System.out.println("        ||  Transactions  ||        ");
        System.out.println("------------------------------------");
        System.out.println("Total Transactions : " + Transaction_Count);
        System.out.println("Successful Transactions: " + successTrans);
        System.out.println("Failed Transactions    : " + failedTrans);
        System.out.println("Total Successful Amount: " + moneyFormat(totalSuccessAmount));
        System.out.println("===================================");

        // عايزين نضيف total balances يعني تجمع الفلوس اللي موجودة كلها
        // و نضيف total transactions
        // و نضيف خانة add users في ال admin panel
        // و نضيف sign up


    }


    // View All Users Method
    //=======================

    public static void viewAllUsers()
    {
        System.out.println("===================================");
        System.out.println("========= << ALL USERS >> =========");
        System.out.println("===================================");

        if (User_Count == 0)
        {
            System.out.println("No users registered yet.");
        }
        else
        {
            for (int i = 0; i < User_Count; i++)
            {
                String status;
                if (Active_Users[i])
                {
                    status = "Active";
                }
                else
                {
                    status = "Blocked";
                }

                System.out.println(
                        "ID: " + User_IDs[i] +

                        " || Name: " + User_Names[i] +

                        " || Email: " + User_Emails[i] +

                        " || Phone: " + User_Phone_Numbers[i] +

                        " || Balance: " + moneyFormat(User_Balances[i]) +

                        " || Status: " + status
                );
            }
        }
        System.out.println("===================================");
    }


    // Show All Users Method
    //=======================

    public static void showAllTransaction() {
    System.out.println("===================================");
    System.out.println("===== << ALL TRANSACTIONS >> ======");
    System.out.println("===================================");

    if (Transaction_Count == 0)
    {
    System.out.println("No transactions recorded yet.");
    }
    else
    {
        for (int i = 0; i < Transaction_Count; i++)
        {
            System.out.println(
                        "Transaction ID: " + Transaction_IDs[i] +

                        " || Sender: " + Transaction_Senders[i] +

                        " || Receiver: " + Transaction_Receivers[i] +

                        " || Amount: " + moneyFormat(Transaction_Amounts[i]) +

                        " || Status: " + Transaction_Statuses[i]
            );
        }
    }
        System.out.println("===================================");
    }


    // Unblock Users Method
    //======================

    public static void unblockAccount()
    {
        System.out.println("===================================");
        System.out.print("Enter ID , email , phone or name of the user to unblock : ");
        String choice_unblock;
        choice_unblock = input.nextLine();

        int count = countMatches(choice_unblock);

        if (count == 0)
        {
            System.out.println("User not found !");
            return;
        }


        if (count > 1) //عملتها if بس عشان قبلها return و لو ال return تحققت بتطلع علطول من الميثود
        {
            System.out.println("More than one user found ! , Please use another way .");
            return;
        }

        for (int i = 0; i < User_Count; i++)
        {
            if (doesMatch (i , choice_unblock) )
            {
                if (Active_Users[i]) // يعني اليوزر موجود اساسا مش معموله بلوك
                {
                    System.out.println("Account is already active !");
                }
                else
                {
                    Active_Users[i] = true;
                    System.out.println(User_Names[i] + "'s account has been unblocked !"); // The output --> [User]'s account has been unblocked !
                }

                return;

            }
        }
    }

    // View User's Details Method
    //=============================

    public static void viewUserDetails()
    {
        System.out.println("===================================");
        System.out.print("Enter ID , email , phone or name of the user to view : ");
        String choice_user_details;
        choice_user_details = input.nextLine();

        int count = countMatches(choice_user_details);

        if (count == 0)
        {
            System.out.println("User not found !");
            return;
        }

        if (count > 1)
        {
            System.out.println("More than one user found !");
            return;
        }

        //فوق هنا اتأكدنا انه يوزر واحد بس ف نعمل لوب و نلف و نجيبه


        for (int i = 0; i < User_Count; i++)
        {


            String status;
            if (Active_Users[i])
            {
                status = "Active";
            }
            else
            {
                status = "Blocked";
            }




            if ( doesMatch (i , choice_user_details) )
            {
                int sent = 0;
                int recieved = 0;

                for (int j = 0; j < Transaction_Count; j++)
                {
                    if ( Transaction_Senders[j] == i)
                    {
                        sent++;
                    }

                    if (Transaction_Receivers[j] == i && Transaction_Statuses[j].equalsIgnoreCase("Successful"))
                    {
                        recieved++;
                    }
                }

                System.out.println("=====================================");
                System.out.println("========= <<User Details >> =========");
                System.out.println("=====================================");

                System.out.println("ID          : " + User_IDs[i]);
                System.out.println("Name        : " + User_Names[i]);
                System.out.println("Email       : " + User_Emails[i]);
                System.out.println("Phone       : " + User_Phone_Numbers[i]);
                System.out.println("Balance     : " + moneyFormat(User_Balances[i]));
                System.out.println("Status      : " + status);
                System.out.println("Favourites  : " + Favorites_Count[i]);
                System.out.println("Sent        : " + sent + " transaction(s)");
                System.out.println("Recieved    : " + recieved + " transaction(s)");

                System.out.println("=====================================");

                return;
            }
        }
    }


    







    //==============================================================================================================================\\



    //=============\\
    // Admin Panel \\
    //=============\\

    public static void adminPanel() {
        
        boolean in_admin = true;

        while (in_admin)
        {

            System.out.println("===================================");
            System.out.println("========= <<ADMIN PANEL>> =========");
            System.out.println("===================================");

            System.out.println("1 . Show Statistics");
            System.out.println("2 . Search for User");
            System.out.println("3 . View All Users");
            System.out.println("4 . Update User Information");
            System.out.println("5 . View all Transactions");
            System.out.println("6 . Block Account");
            System.out.println("7 . Unblock Account");
            System.out.println("8 . View User Details");
            System.out.println("9 . Logout");

            
            System.out.print("Enter your choice : ");
            String choice_admin;
            choice_admin = input.nextLine();


            switch (choice_admin) 
            {
            case "1":

                showStatistics();
                break;

            case "2":

//                 searchUser();
                break;

            case "3":

                viewAllUsers();
                break;

            case "4":

                // updateUser()
                break;
            case "5":

                showAllTransaction();
                break;

            case "6":

                // blockAccount()
                break;

            case "7":

                unblockAccount();
                break;

            case "8":

                viewUserDetails();
                break;

            case "9":

                System.out.println("======================");
                System.out.println("    Logging out...");
                System.out.println("======================");
                System.out.println("  Admin logged out.");
                System.out.println("======================");
                
                in_admin = false;
                break;

            default:
                System.out.println("=================================");
                System.out.println("Invalid choice. Please try again.");
                System.out.println("=================================");
            }

        }
        
    }


    //==============================================================================================================================\\


    // User Methods
    //==============

    // اكتبوا ميثودز اليوزر هنا \\








    //==============================================================================================================================\\



    //============\\
    // User Panel \\
    //============\\

    static void userPanel(int user) {

        boolean in_user = true;

        while (in_user)
        {
            
            System.out.println("==================================");
            System.out.println("========= <<USER PANEL>> =========");
            System.out.println("==================================");

            System.out.println("1 . Show Balance");
            System.out.println("2 . Transfer Money");
            System.out.println("3 . Transaction History");
            System.out.println("4 . Show Favorites");
            System.out.println("5 . Add Favorite");
            System.out.println("6 . Remove Favorite");
            System.out.println("7 . Search Recipient");
            System.out.println("8 . View Profile");
            System.out.println("9 . Update Profile");
            System.out.println("10 . Logout");

            System.out.print("Enter your choice : ");
            String choice_user;
            choice_user = input.nextLine();

            switch (choice_user) 
            {
                case "1":

                    // showBalance(user)
                    break;

                case "2":

                    // transferMoney(user)
                    break;

                case "3":

                    // showTransactionHistory(user)
                    break;

                case "4":

                    // showFavorites(user)
                    break;

                case "5":

                    // addFavorite(user)
                    break;

                case "6":

                    // removeFavorite(user)
                    break;

                case "7":

                    // searchRecipient(user)
                    break;

                case "8":

                    // viewProfile(user)
                    break;

                case "9":

                    // updateProfile(user)
                    break;

                case "10":

                    System.out.println("======================");
                    System.out.println("    Logging out...");
                    System.out.println("======================");
                    System.out.println("  User logged out.");
                    System.out.println("======================");
                    
                    in_user = false;
                    break;

                default:
                    System.out.println("=================================");
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("=================================");
            }
        }
        
    }



    
    // ========================\\
    //         The Main        \\
    // ========================\\

    public static void main(String[] args) {

        enteringUsers(); // لازم نناديها عشان تفاصيل اليوزرز تتسجل

        boolean running = true;

        while (running) 
        {

            System.out.println("=================================");
            System.out.println("========= <<QUICK PAY>> =========");
            System.out.println("=================================");

            System.out.println( "1 . Login as Admin");
            System.out.println( "2 . Login as User");
            System.out.println( "3 . Exit");

            System.out.print("Enter your choice : ");
            String choice_main;
            choice_main = input.nextLine();

            switch (choice_main) 
            {
                case "1":
                    if (Admin_Login())
                    {
                        adminPanel();
                    }
                    break;

                case "2":
                    int user = User_Login(); // عملتها int عشان احل مشكلة ال return 
                    
                    // عشان الميثود دي بترجع رقم مش boolean
                    // عشان كده لازم نخزن اللي طالع من الميثود في int عشان لو صح يطلع اليوزر صاحب الاندكس اللي عليه الدور

                    if (user != -1)
                    {
                    userPanel(user);
                    }
                    break;

                case "3":

                    System.out.println("======================");
                    System.out.println("Exiting the program...");
                    System.out.println("======================");
                    System.out.println("       Goodbye!");
                    System.out.println("======================");

                    running = false;
                    break;

                default:
                    System.out.println("=================================");
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("=================================");
            }

        }

        


        

        //=========================================================================================================\\
//         for (int i = 0; i < User_Count; i++)
//         {
//             System.out.println(User_Names[i] + " --> " + User_IDs[i]); // مجرد تجربة بتأكد من صحة ال User IDs
//         }
        //=========================================================================================================\\

        // for (int i = 0; i < Transaction_Count; i++)
        //  {
        //      System.out.println(" --> " + Transaction_IDs[i]); // مجرد تجربة بتأكد من صحة ال User IDs
        //  }

    }
}

// حولت كل ال cases ل سترينج عشان لو اليوزر دخل حرف مش رقم بيعمل ايرور و يقفل البرنامج اما لو سترينج زي كده و اليوزر كتب حاجه غلط هيروح ع الديفولت علطول كانه كتب كلمة غلط
