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

        for (int i = capacity; i < newCapacity; i++)
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



    public static void addTransaction(int sender, int receiver, long amount, String status) // نفس شغل ال addUser
    //    انا هنا حولت ال int amount ل long عشان موضوع الفلوس و البالانس <<<<<<--------
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

    public static int findUserByEmailOrPhone(String key) // Method that helps to search for users by both emails or phone nums
    {
        for (int i = 0; i < User_Count; i++)
        {
            if ( User_Emails[i].equalsIgnoreCase(key) || User_Phone_Numbers[i].equalsIgnoreCase(key) )
            {
                return i;
            }
        }

        return -1;
    }
    // Unused method
    // نقدر نعمل فكرة اننا نبعت فلوس ب ايميل او رقم موبايل مش رقم موبايل بس



    //==============================================================================================================================\\

    public static boolean validEmail(String email)
    {
        if ( ! email.contains("@") || ! email.endsWith(".com") ||  email.startsWith(".") )
        {
            return false;
        }
        return true; // حفظنا بقى مبنعملش else عشان كده كده ال ريتيرن بتقفل الميثود علطول
    }

    public static boolean validPhone(String phone)
    {
        if (phone.length() != 11 )
        {
            return false;
        }
        return true; // same
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
            if (Transaction_Statuses[i].equalsIgnoreCase("SUCCESS"))
            {
                successTrans++;
                totalSuccessAmount += Transaction_Amounts[i];
            }
            else if (Transaction_Statuses[i].equalsIgnoreCase("FAILED"))
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


    // Search For Users Method
    //=========================

    public static void searchUser()
    {
        System.out.println("===================================");
        System.out.println("========= << SEARCH USER >> =======");
        System.out.println("===================================");
        System.out.print("Enter User ID, Name, Phone, or Email to search : ");

        String query;
        query = input.nextLine();

        boolean found = false;
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


            if (String.valueOf(User_IDs[i]).equals(query)
                    ||
                    User_Names[i].equalsIgnoreCase(query)
                    ||
                    User_Phone_Numbers[i].equals(query)
                    ||
                    User_Emails[i].equalsIgnoreCase(query))
            {

                System.out.println("===================================");
                System.out.println("User Found : ");
                System.out.println("===========");
                System.out.println("ID     : " + User_IDs[i]);
                System.out.println("Name   : " + User_Names[i]);
                System.out.println("Email  : " + User_Emails[i]);
                System.out.println("Phone  : " + User_Phone_Numbers[i]);
                System.out.println("Balance: " + moneyFormat(User_Balances[i]));
                System.out.println("Status : " + status);

                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No user found matching : " + query);
        }

        System.out.println("===================================");
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



    // Update Users Info Method
    //==========================

    public static void updateUser()
    {
        System.out.println("===================================");
        System.out.println("====== << UPDATE USER INFO >> =====");
        System.out.println("===================================");
        System.out.print("Enter User ID to update : ");
        String id;
        id = input.nextLine();

        int targetIndex = -1;

        for (int i = 0; i < User_Count; i++)
        {
            if (String.valueOf(User_IDs[i]).equals(id))
            {
                targetIndex = i;
                break;
            }
        }

        if ( targetIndex == -1 )
        {
            System.out.println("===================================");
            System.out.println("User ID not found !");
            System.out.println("===================================");
            return;
        }

        System.out.println("===================================");

        System.out.println("Updating user : " + User_Names[targetIndex]);
        System.out.print("Enter new name ( or press enter to keep current ) : ");
        String newName;
        newName= input.nextLine();

        System.out.println("===================================");

        if ( ! newName.isEmpty() ) // .isEmpty() --> بترجع ترو لو النص مفهوش حاجه فاضي تماما و بترجع فولس لو في اي حاجه بقى حرف حرفين المهم مش فاضي
        {
            User_Names[targetIndex] = newName;
        }

        System.out.println("===================================");

        System.out.print("Enter new email (or press enter to keep current) : ");
        String newEmail;
        newEmail = input.nextLine();

        System.out.println("===================================");

        if ( !newEmail.isEmpty() )
        {
            User_Emails[targetIndex] = newEmail;
        }

        System.out.println("===================================");

        System.out.print("Enter new phone number (or press enter to keep current) : ");

        String newPhone;
        newPhone = input.nextLine();

        System.out.println("===================================");

        if (!newPhone.isEmpty())
        {
            User_Phone_Numbers[targetIndex] = newPhone;
        }

        System.out.println("User information updated successfully!");
        System.out.println("===================================");
    }








    // Show All Users Transactions Method
    //====================================

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

                                " || Sender: " + User_Names[Transaction_Senders[i]] +

                                " || Receiver: " + User_Names[Transaction_Receivers[i]] +

                                " || Amount: " + moneyFormat(Transaction_Amounts[i]) +

                                " || Status: " + Transaction_Statuses[i]
                );
            }
        }
        System.out.println("===================================");
    }


    // Block User Method
    //===================

    public static void blockAccount()
    {
        System.out.println("===================================");
        System.out.print("Enter ID , email , phone or name of the user to block : ");
        String choice_block;
        choice_block = input.nextLine();

        int count = countMatches(choice_block);

        if (count == 0)
        {
            System.out.println("===================================");
            System.out.println("User not found !");
            return;
        }


        if (count > 1) // لو الاسم متكرر لازم يبحث بطريقة تانية
        {
            System.out.println("===================================");
            System.out.println("More than one user found ! , Please use another way .");
            return;
        }

        for (int i = 0; i < User_Count; i++)
        {
            if ( doesMatch (i , choice_block) )
            {
                if ( ! Active_Users[i] ) // يعني اليوزر معموله بلوك اساسا
                {
                    System.out.println("===================================");
                    System.out.println("Account is already blocked !");
                }
                else
                {
                    Active_Users[i] = false;
                    System.out.println("===================================");
                    System.out.println(User_Names[i] + "'s account has been blocked !"); // The output --> [User]'s account has been blocked !
                }

                return;

            }
        }
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
            System.out.println("===================================");
            System.out.println("User not found !");
            return;
        }


        if (count > 1) //عملتها if بس عشان قبلها return و لو ال return تحققت بتطلع علطول من الميثود
        {
            System.out.println("===================================");
            System.out.println("More than one user found ! , Please use another way .");
            return;
        }

        for (int i = 0; i < User_Count; i++)
        {
            if (doesMatch (i , choice_unblock) )
            {
                if (Active_Users[i]) // يعني اليوزر موجود اساسا مش معموله بلوك
                {
                    System.out.println("===================================");
                    System.out.println("Account is already active !");
                }
                else
                {
                    Active_Users[i] = true;
                    System.out.println("===================================");
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

                    if (Transaction_Receivers[j] == i && Transaction_Statuses[j].equalsIgnoreCase("SUCCESS"))
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

                    searchUser();
                    break;

                case "3":

                    viewAllUsers();
                    break;

                case "4":

                    updateUser();
                    break;
                case "5":

                    showAllTransaction();
                    break;

                case "6":

                    blockAccount();
                    break;

                case "7":

                    unblockAccount();
                    break;

                case "8":

                    viewUserDetails();
                    break;

                case "9":

                    admin_logout();
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
    //=============================\\


    // 1 Show Balance

    public static void showBalance (int user)
    {

        System.out.println("\n===================================");
        System.out.println("Your balance is " + moneyFormat(User_Balances[user]));
        System.out.println("===================================\n");
    }

    // =====================================================================


    // 2 transfer money

    public static void transferMoney(int user) // user = sender
    {

        if (!Active_Users[user])
        {
            System.out.println("Your account is blocked, you can't transfer money !");
            System.out.println("===================================");
            return;
        }


        System.out.println("===================================");

        System.out.print("Enter the Recipient's phone number : ");
        String phone;
        phone = input.nextLine();

        System.out.println("===================================");

        int receiver = -1; // لسه ملاقيناش المستلم

        for (int i = 0; i < User_Count ; i++)
        {
            if (User_Phone_Numbers[i].equals(phone))
            {
                //اتشك الرقم اللي دخله اليوزر موجود ولا لا
                receiver = i;
                break;
            }
        }

        if ( receiver == -1 )
        {
            System.out.println("Recipient's phone number not found !"); // Haven't found the user yet
            System.out.println("===================================");
            return;
        }

        if ( receiver == user ) // if the sender & the receiver are the same person
        {
            System.out.println("You can't transfer money to yourself !");
            System.out.println("===================================");
            return;
        }

        System.out.println("Recipient : " + User_Names[receiver]);
        System.out.println("===================================");

        System.out.print("Enter amount (EGP) : " );

        double amountInput = input.nextDouble();
        input.nextLine(); // تاخد الانتر

        long amount = (long) (amountInput * 100 );

//        long amount = Math.round(amountInput * 100);



        System.out.println("===================================");

        if ( ! Active_Users[receiver] )
        {
            System.out.println("Recipient's account is blocked, You can't transfer money !");
            System.out.println("===================================");

            addTransaction(user, receiver, amount , "FAILED"); // بتخزن انها عملية فاشله
            return;
        }

        if ( amount <= 0 )
        {
            System.out.println("Invalid amount, Amount must be greater than 0 !");
            System.out.println("===================================");
            return;
        }

        if ( amount > User_Balances[user] )
        {
            System.out.println("Not enough balance");
            System.out.println("===================================");

            addTransaction(user, receiver, amount , "FAILED");
            return;
        }

        if (Transaction_Count >= Transaction_IDs.length)
        {
            System.out.println("Transaction history is full"); // سجل العمليات مفهوش مكان
            // حلينا المشكلة دي بال arrays الجديدة
            System.out.println("===================================");

            return;
        }

        User_Balances[user] -= amount;
        User_Balances[receiver] += amount;

        // خصم المبلغ من اليوزر و اضافاته عند المستلم

        addTransaction(user, receiver, amount , "SUCCESS");

        System.out.println("Transfer done successfully !");
        System.out.println("Sent : " + moneyFormat(amount) + " to " + User_Names[receiver]);
        System.out.println("===================================");
        System.out.println("Your new balance is " + moneyFormat( User_Balances[user] ) );
        System.out.println("===================================");

    }

    //==================================================================================

    // 3 show transaction history


    public static void showTransactionHistory(int user) // كلها كانت غلط
    {
        boolean found = false;

        for (int k = 0; k < Transaction_Count ; k++)
        {
            int sender = Transaction_Senders[k];
            int receiver = Transaction_Receivers[k];

            // The user sees what he sent (successful & failed transactions) and what he received (only the successful) شرح الكود اللي تحت

            if ( sender == user || ( receiver == user && Transaction_Statuses[k].equalsIgnoreCase("SUCCESS") ) )
            {
                found = true;

                if ( sender == user )
                {
                    System.out.println("===================================");
                    System.out.println(

                            "Transaction ID : " + Transaction_IDs[k]
                                    +
                                    " || Sent to : " + User_Names[receiver]
                                    +
                                    " || Amount : " +  moneyFormat( Transaction_Amounts[k] )
                                    +
                                    " || Status : " + Transaction_Statuses[k]

                    );
                    System.out.println("===================================");
                }
                else
                {
                    System.out.println("===================================");

                    System.out.println(

                            "Transaction ID : " + Transaction_IDs[k]
                                    +
                                    "   || Received from : " + User_Names[sender]
                                    +
                                    "   || Amount : " + moneyFormat( Transaction_Amounts[k] )
                                    +
                                    "   || Status : " + Transaction_Statuses[k]

                    );
                    System.out.println("===================================");
                }
            }
        }

        if ( ! found )
        {
            System.out.println("No transaction yet !");
            System.out.println("===================================");
        }
    }

    //================================================================================

    // 4 Add favourites

    public static void addFavorite(int user)
    {
        if ( Favorites_Count[user] >= Favorites[user].length )
        {
            System.out.println("===================================");
            System.out.println("Your favorites list is full ! ( MAX 10 )");
            System.out.println("===================================");
            return;
        }



        System.out.println("===================================");
        System.out.print("Enter the phone number of the user you want to add : ");
        String phone = input.nextLine();

        System.out.println("===================================");

        int fav = -1;

        for (int i = 0; i < User_Count; i++) // لف على كل يوزر واحد واحد اعمل check على رقم الموبايل
        {
            if (User_Phone_Numbers[i].equals(phone))
            {
                fav = i; //احفظه مكانه
                break; // لقيته مش محتاج الف تاني
            }
        }

        if (fav == -1)
        {
            System.out.println("===================================");
            System.out.println("User not found !");
            System.out.println("===================================");

            return;
        }

        if (fav == user)
        {
            System.out.println("===================================");
            System.out.println("You cannot add yourself !");
            System.out.println("===================================");

            return;
        }

        for (int i = 0; i < Favorites_Count[user]; i++)
        {
            if (Favorites[user][i] == fav)
            {
                System.out.println("===================================");
                System.out.println("Already in favorites !");
                System.out.println("===================================");

                return;
            }
        }

        Favorites[user][Favorites_Count[user]] = fav; // بيحط المفضل الجديد في أول خانة فاضية في قايمة مفضلين اليوزر
        Favorites_Count[user]++;


        System.out.println("===================================");
        System.out.println(User_Names[fav] + " has been added to your favorites !");
        System.out.println("===================================");
    }

    //================================================================================

    // 5 Remove favourites

    public static void removeFavorite(int user)
    {
        if ( Favorites_Count[user] == 0 )
        {
            System.out.println("===================================");
            System.out.println("Your favorites list is empty !");
            System.out.println("===================================");
            return;
        }


//        for (int i = 0; i < Favorites_Count[user]; i++)
//        {
//            int fav = Favorites[user][i];
//
//            System.out.println("===================================");
//            System.out.println(
//                    (i + 1) + ". " + User_Names[fav]
//                            +
//                    "  ||  " + User_Emails[fav]
//                            +
//                    "  ||  " + User_Phone_Numbers[fav]
//            );
//            System.out.println("===================================");
//            //بيطبع قائمه المفضله لليوزر ده
//        }


        // طب ليه نعمل كل ده طالما ينفع نستدعي الميثود اللي بتظهر المفضلة
        //========================


        showFavorites(user); // بدل الكلام الكتير اللي فوق معمول كومنت ده (هو هو بالمللي)


        System.out.println("===================================");
        System.out.print("Enter the phone number of the user you want to remove : ");
        String choice = input.nextLine();

        System.out.println("===================================");

        int index = -1;

        for (int i = 0; i < Favorites_Count[user]; i++)
        {
            int fav = Favorites[user][i];

            if ( User_Phone_Numbers[fav].equals(choice) )
            {
                index = i;
                break;
            }
        }

        if ( index == -1 )
        {
            System.out.println("===================================");
            System.out.println("This user is not in your favorites !");
            System.out.println("===================================");
            return;
        }

        for (int i = index; i < Favorites_Count[user] - 1 ; i++)  // بتحرك كل اللي بعده خظوة ل ورا عشان نسد الخانة اللي اتحذفت
        {
            Favorites[user][i] = Favorites[user][i + 1];
            //عشان مكان الاسم اللي اتحذف يجي الاسم اللي بعده عشان ميبقاش فيه اندكس فاضي
        }

        Favorites_Count[user]--;
        //بتنقص عدد المفضل واحد عند اليوزر ده

        // Favorites[user][Favorites_Count[user]] = 0; // بيخلي الاراي منظمة لما يعمل check تاني
        // عادي ممكن نمسحه دا تبع الزيادات

        System.out.println("===================================");
        System.out.println("The contact has been removed from your favorites !");
        System.out.println("===================================");
    }

    //================================================================================


    // 6 Show favourites


    public static void showFavorites(int user)
    {
        if ( Favorites_Count[user] == 0 )
        {
            System.out.println("===================================");
            System.out.println("Your favorites list is empty !");
            System.out.println("===================================");

            return;
        }


        System.out.println("===================================");
        System.out.println("Your favorites (" + Favorites_Count[user] + "/10) :");
        System.out.println("===================================");

        for (int i = 0; i < Favorites_Count[user]; i++)
        {
            int favorite = (Favorites[user][i]); // مكان المفضل في الاراي بتاعت اليوزر (الاندكس بتاعه)


            System.out.println("===================================");
            System.out.println(
                    (i + 1) + ". " + User_Names[favorite]
                            +
                            "  ||  " + User_Emails[favorite]
                            +
                            "  ||  " + User_Phone_Numbers[favorite]
            );
            System.out.println("===================================");
        }
    }

    //===================================================================================

    // 7 search recipient

    public static void searchRecipient(int user){

        System.out.println("===================================");
        System.out.print("Enter the phone number, email or username of the user to search for : ");

        String searching_recipient;
        searching_recipient = input.nextLine();

        System.out.println("===================================");

        boolean found_user = true;

        int recipient_index = -1;

        for (int i =0; i< User_Count ; i++)
        {

            if (searching_recipient.equalsIgnoreCase(User_Emails[i]) ||
                    searching_recipient.equalsIgnoreCase(User_Phone_Numbers[i])||
                    searching_recipient.equalsIgnoreCase(User_Names[i]))
            {
                recipient_index = i;
                break;
            }
        }

        if (recipient_index == -1)
        {
            System.out.println("User not found");
            System.out.println("===================================");
            return;
        }

        System.out.println("Name : " + User_Names[recipient_index]);
        System.out.println("ID : " + User_IDs[recipient_index]);
        System.out.println("Phone numbers : " + User_Phone_Numbers[recipient_index]);
        System.out.println("Email : " + User_Emails[recipient_index]);
        System.out.println("===================================");

        while (found_user)
        {

            System.out.println("Choose a number to proceed : ");
            System.out.println("===================================");

            System.out.println("1 . Transfer money to this user");
            System.out.println("2 . Return to User panel");

            System.out.println("===================================");
            System.out.print("Enter your choice  : ");

            String user_choice;
            user_choice = input.nextLine();

            if (user_choice.equals("1"))
            {
                transferMoney(user);
                return;
            }
            else if (user_choice.equals("2"))
            {
                return;
            }
            else
            {
                System.out.println("Not valid choice ! Try again.");
                System.out.println("===================================");
            }
        }
    }

    //================================================================

    // 8 View profile details

    static void viewProfile(int user)
    {
        String status;
        if (Active_Users[user])
        {
            status = "Active";
        }
        else
        {
            status = "Blocked";
        }

        System.out.println("===================================");
        System.out.println("Username      : "+ User_Names[user]);
        System.out.println("ID            : "+ User_IDs[user]);
        System.out.println("Email Address : "+ User_Emails[user]);
        System.out.println("Phone number  : "+ User_Phone_Numbers[user]);
        System.out.println("Password      : "+ User_Passwords[user]);
        System.out.println("Status        : "+ status);
        System.out.println("Balance       : "+ moneyFormat(User_Balances[user]));
    }

    //========================================================================

    // 9 Update profile information

    static void updateProfile(int user) // // //
    {
        while (true)
        {
            System.out.println("===================================");
            System.out.println("Choose a number to update your profile : ");
            System.out.println("1. Change your name");
            System.out.println("2. Change your email");
            System.out.println("3. Change your phone number");
            System.out.println("4. Change your password");
            System.out.println("5. Back");
            System.out.println("===================================");
            System.out.print("Enter your choice : ");

            String choice;
            choice = input.nextLine();

            switch (choice)
            {
                case "1":

                    System.out.println("===================================");
                    System.out.print("Enter your new Username : ");
                    User_Names[user] = input.nextLine(); // بنخزنه ف الاراي بتاعت الاسماء
                    System.out.println("===================================");
                    System.out.println("Your Username has been successfully updated to : " + User_Names[user]);
                    System.out.println("===================================");

                    return;

                case "2":

                    System.out.println("===================================");
                    System.out.print("Enter your new Email : ");
                    String newEmail;
                    newEmail = input.nextLine();
                    System.out.println("===================================");

                    while ( ! validEmail(newEmail) ) // Checking validation
                    {
                        System.out.println("Invalid Email ! It must contain ( @ ) & end with ( .com ) ");
                        System.out.println("===================================");
                        System.out.print("Enter your new Email : ");
                        newEmail = input.nextLine();
                        System.out.println("===================================");
                        System.out.println("Your Email has been successfully updated to : " + newEmail);
                        System.out.println("===================================");
                    }

                    User_Emails[user] = newEmail;
                    return;

                case "3":

                    System.out.println("===================================");
                    System.out.print("Enter your new Phone Number : ");
                    String newPhone;
                    newPhone = input.nextLine();
                    System.out.println("===================================");

                    while ( ! validPhone(newPhone) ) // Checking validation
                    {
                        System.out.println("Invalid Email ! It must contain ( @ ) & end with ( .com ) ");
                        System.out.println("===================================");
                        System.out.print("Enter your new phone number : ");
                        newPhone = input.nextLine();
                        System.out.println("===================================");
                        System.out.println("Your Phone Number has been successfully updated to : " + newPhone);
                        System.out.println("===================================");
                    }

                    User_Phone_Numbers[user] = newPhone;
                    return;

                case "4":

                    System.out.println("===================================");
                    System.out.print("Enter your new Password : ");
                    User_Passwords[user] = input.nextLine();
                    System.out.println("===================================");
                    System.out.println("Your  Password has been successfully updated to : " + User_Passwords[user]);
                    System.out.println("===================================");

                    return;

                case "5":
                    return;

                default:

                    System.out.println("===================================");
                    System.out.println("Invalid choice. Please try again ! ");
                    System.out.println("===================================");
            }
        }
    }

    //=================================================================================

    // 10 Logout

    public static void user_logout()
    {
        System.out.println("======================");
        System.out.println("    Logging out...");
        System.out.println("======================");
        System.out.println("  User logged out.");
        System.out.println("======================");
    }

    public static void admin_logout()
    {
        System.out.println("======================");
        System.out.println("    Logging out...");
        System.out.println("======================");
        System.out.println("  Admin logged out.");
        System.out.println("======================");
    }







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
            System.out.println("4 . Add Favorite");
            System.out.println("5 . Remove Favorite");
            System.out.println("6 . Show Favorites");
            System.out.println("7 . Search Recipient");
            System.out.println("8 . View Profile");
            System.out.println("9 . Update Profile");
            System.out.println("10. Logout");

            System.out.print("Enter your choice : ");
            String choice_user;
            choice_user = input.nextLine();

            switch (choice_user)
            {
                case "1":

                    showBalance(user);
                    break;

                case "2":

                    transferMoney(user);
                    break;

                case "3":

                    showTransactionHistory(user);
                    break;

                case "4":

                    addFavorite(user);
                    break;

                case "5":

                    removeFavorite(user);
                    break;

                case "6":

                    showFavorites(user);
                    break;

                case "7":

                    searchRecipient(user);
                    break;

                case "8":

                    viewProfile(user);
                    break;

                case "9":

                    updateProfile(user);
                    break;

                case "10":

                    user_logout();
                    in_user = false;
                    break;

                default:
                    System.out.println("=================================");
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("=================================");
            }
        }

    }


    static void signUp()
    {
        System.out.println("=================================");
        System.out.println("========== <<SIGN UP>> ==========");
        System.out.println("=================================");


        // Email
        //=======

        System.out.println("===================================");
        System.out.print("Enter your new Email : ");
        String newEmail;
        newEmail = input.nextLine();
        System.out.println("===================================");

        while (!validEmail(newEmail) || findUserByEmailOrPhone(newEmail) != -1) // Checking validation
        {
            System.out.println("Invalid Email ! It must contain ( @ ) & end with ( .com ) ");
            System.out.println("===================================");
            System.out.print("Enter your new Email : ");
            newEmail = input.nextLine();
            System.out.println("===================================");
        }


        // Phone number
        //==============

        System.out.println("===================================");
        System.out.print("Enter your new phone number : ");
        String newPhone_number;
        newPhone_number = input.nextLine();
        System.out.println("===================================");

        while (!validPhone(newPhone_number) || findUserByEmailOrPhone(newPhone_number) != -1)
        {
            System.out.println("Invalid phone number ! It must contain ( 11 numbers ) ");
            System.out.println("===================================");
            System.out.print("Enter your new phone number : ");
            newPhone_number = input.nextLine();
        }

        // Check Username
        //================

        String username;
        boolean check_for_username;

        do {
            check_for_username = true;

            System.out.println("===================================");
            System.out.print("Enter your username : ");
            username = input.nextLine();

            for (int i = 0; i < User_Count; i++)
            {
                if (username.equalsIgnoreCase( User_Names[i] ))
                {
                    System.out.println("====================================");
                    System.out.println("Invalid or already used username ! Please try again.");
                    System.out.println("====================================");
                    check_for_username = false;
                    break;
                }
            }
        }
        while (!check_for_username);


        // Check Password
        //================

        String pass;
        boolean check_pass;

        do
        {
            check_pass = true;

            System.out.println("====================================");
            System.out.print("Enter a strong password (more than 8 characters) : ");
            pass = input.nextLine();

            if (pass.length() < 9)
            {
                System.out.println("Weak password! Try again");
                check_pass = false;
            }
        }
        while (!check_pass);


        // يبيدأ من لزيرو يعيني
        //=======================

        addUser(username, newEmail, newPhone_number, pass, 0, true);

        System.out.println("You've signed up successfully!");
        System.out.println("Your ID is : " + User_IDs[User_Count - 1]);
        System.out.println("=================================");
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

            System.out.println("1 . Login as Admin");
            System.out.println("2 . Login as User");
            System.out.println("3 . Sign Up");
            System.out.println("4 . Exit");

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

                    signUp();

                case "4":

                    System.out.println("======================");
                    System.out.println("Exiting the program...");
                    System.out.println("======================");
                    System.out.println("       Goodbye!");
                    System.out.println("======================");

                    System.out.println("Eng / Nour Eldeen");
                    System.out.println("Eng / Nour");
                    System.out.println("Eng / Monira");
                    System.out.println("Eng / Mohammed Waleed");
                    System.out.println("======================");
                    System.out.println("تحياتي تيم ابو الأنوار");
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
