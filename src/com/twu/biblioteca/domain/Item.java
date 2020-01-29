package com.twu.biblioteca.domain;

public interface Item {
    @Override
    public String toString();

    public void setIsCheckout(boolean isCheckout);

    public boolean isCheckout();

    public int getId();

    public void setId(int id);

    public String getTitle();

    public String getType();

    public int getUserId();

    public void setUserId(int id);
}
