package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.repository.ItemRepository;
import com.twu.biblioteca.security.Session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemController {
    private ItemRepository itemRepository;
    private String itemType = "";
    private User currentUser;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.currentUser = Session.getUser();
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public List<Item> getAvailableItemsOfType() {
        return itemRepository.getAll().stream().filter(item -> item.getType().contains(itemType)).filter(item -> !item.isCheckout()).collect(Collectors.toList());
    }

    public List<Item> getAllItemsOfType() {
        return itemRepository.getAll().stream().filter(item -> item.getType().contains(itemType)).collect(Collectors.toList());
    }

    public boolean checkoutItem(int itemId) {
        Item item = itemRepository.getById(itemId);
        if (!item.isCheckout()) {
            item.setIsCheckout(true);
            item.setUserId(Session.getUser().getId());
            itemRepository.update(item);
            return true;
        }
        return false;
    }

    public Item getById(int id) {
        return itemRepository.getById(id);
    }

    public boolean returnItem(String title) {
        Optional<Item> item = itemRepository.getAll().stream().filter(b -> b.getTitle().equals(title)).findFirst();
        if (item.isPresent() && item.get().isCheckout()) {
            Item b = item.get();
            b.setIsCheckout(false);
            b.setUserId(0);
            itemRepository.update(b);
            return true;
        }
        return false;
    }

    public List<Item> getCheckOutBooksOfUserById(int id) {
        return itemRepository.getAll().stream().filter(i -> i.isCheckout()).filter(i -> i.getUserId() == (id)).filter(i -> i.getType().equals("book")).collect(Collectors.toList());
    }


    public String getItemType() {
        return itemType;
    }
}
