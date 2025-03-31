package me.chchnikolaou.unipiplishopping.user;

public enum UserType {

    CLIENT("Client", 0), STORE_OWNER("Store Owner", 1), ADMIN("Administrator", 2);

    private final String prefix;
    private final int layout;

    UserType(String prefix, int layout) {
        this.prefix = prefix;
        this.layout = layout;

    }

    public String getPrefix() {
        return prefix;
    }

    public int getLayout() {
        return layout;
    }


}
