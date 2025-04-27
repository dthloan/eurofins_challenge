package supports.enums;

public enum ERROR_MESSAGES {
    MISSING_CUSTOMER("Customer or Items cannot be null or empty"),
    MISSING_ITEMS("Cannot create the quote for a null item.");

    public String msg;
    ERROR_MESSAGES(String msg){
        this.msg = msg;
    }

}
