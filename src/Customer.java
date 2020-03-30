public class Customer {


    private final String firstName;
    private final String laseName;
    private final String cprNr;
    private final Account account;

    Customer(String firstName, String lastName, String cprNr, Account account) {
       this.firstName = firstName;
       this.laseName = lastName;
       this.cprNr = cprNr;
       this.account = account;

    }
    @Override
    public String toString(){
        return "\nCustomer Information\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + laseName + "\n" +
                "CPR: " + cprNr + "\n" +
                account;
    }
    public String basicInfo(){
        return "First Name: " + firstName +
                " Last Name: " + laseName +
                " CPR: " + cprNr +
                " Account Number: " + account.getAccountNumber();
    }
    Account getAccount(){
        return account;
    }

}
