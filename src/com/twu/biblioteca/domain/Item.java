package com.twu.biblioteca.domain;

public interface Item {
    @Override
    public String toString();

    public void setIsCheckout(boolean isCheckout);

    public boolean isCheckout();

    public int getID();

    public void setID(int id);

    public String getTitle();

    public String getType();
}
