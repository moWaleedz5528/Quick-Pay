# Quick Pay

Quick Pay is a small console app written in Java that works like a simple mobile wallet. Users can log in, check their balance and send money to each other, and an admin can manage the accounts. It was made as our final project for the programming course.

We didn't use any database or ArrayList. Everything is stored in plain arrays, and the arrays grow by one slot when they get full. Because of that, all data is lost when the program closes.

## Team

- Nour Eldeen
- Nour
- Monira
- Mohammed Waleed

## How to run

You need Java (JDK) installed. Put `final_project.java` in a folder, open a terminal there and run:

```
java final_project
```

Or just open it in IntelliJ / Eclipse and press run.

## Login info

Admin:

- username: `admin`
- password: `admin123`

Users (the program adds these when it starts, you log in with the email):

| Name     | Email                 | Password    | Phone       | Status  |
|----------|-----------------------|-------------|-------------|---------|
| Noor     | noor@quickpay.com     | noor123     | 01234567890 | Active  |
| Nour     | nour@quickpay.com     | nour123     | 01223344556 | Active  |
| Monira   | monira@quickpay.com   | monira123   | 01233445566 | Active  |
| Mohammed | mohammed@quickpay.com | mohammed123 | 01234455667 | Active  |
| Ahmed    | ahmed@quickpay.com    | ahmed123    | 01234556677 | Blocked |

Ahmed starts as blocked on purpose, so you can test what happens with a blocked account.

## What it does

### User panel

1. Show balance
2. Transfer money (you enter the recipient's phone number)
3. Transaction history
4. Add a favorite (max 10)
5. Remove a favorite
6. Show favorites
7. Search for a recipient by phone, email or name, and send money to him from there
8. View profile
9. Update profile (name, email, phone or password)
10. Logout

### Admin panel

1. Statistics (number of users, active/blocked, total balances, transactions count and total successful amount)
2. Search for a user
3. View all users
4. Update user information
5. View all transactions
6. Block an account
7. Unblock an account
8. View user details
9. Logout

The admin can find a user by ID, name, email or phone. If two users have the same name the program says more than one user was found and asks you to use something else.

## Some rules in the program

- Money is saved in piasters (as `long`) and shown as pounds, for example `8000.00 EGP`. We did this to avoid the problems of using double with money.
- User IDs start from 101 and transaction IDs start from 1001.
- A blocked user can't log in and can't transfer.
- If you send money to a blocked account, or you don't have enough balance, the transfer fails and it is still saved in the history as `FAILED`.
- You can't send money to yourself.
- In the history, the sender sees all of his transactions (successful and failed), but the receiver only sees the successful ones.
- All menu choices are read as text, so typing a letter instead of a number won't crash the menus. It just says invalid choice.

## Files

Everything is in one file, `final_project.java`. It is split into sections: the arrays and variables, helper methods (search, money format), login, admin methods, user methods and then `main`.

## Things we didn't do

- No sign up. New users can't be added while the program is running.
- Nothing is saved to a file, so balances go back to the start every time you run it.
- Email and phone number are not checked for format or for duplicates when updating.
- The amount in a transfer must be a whole number of pounds.

## Notes

The comments in the code are a mix of English and Arabic because we wrote them while working together. Sorry about that.
