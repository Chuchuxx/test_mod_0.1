package com.chuchu;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;


public class ChestBlockEntity extends BlockEntity implements ImplementedInventory{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);


    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    public ChestBlockEntity(){
        super(CMMain.CBE);
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {

        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }
    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag,items);
    }





}
