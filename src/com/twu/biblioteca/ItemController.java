package com.twu.biblioteca;

import com.twu.biblioteca.domain.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemController {
    private ItemRepository itemRepository;
    private String itemType = "";

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

    public boolean checkoutItem(int id) {
        Item item = itemRepository.getById(id);
        if (!item.isCheckout()) {
            item.setIsCheckout(true);
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
            itemRepository.update(b);
            return true;
        }
        return false;
    }

    public String getItemType() {
        return itemType;
    }
}
