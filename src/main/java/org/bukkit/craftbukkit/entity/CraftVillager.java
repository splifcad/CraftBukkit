package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityVillager;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CraftVillager extends CraftAgeable implements Villager, InventoryHolder {

    public CraftVillager(CraftServer server, EntityVillager entity) {
        super(server, entity);
    }

    @Override
    public EntityVillager getHandle() {
        return (EntityVillager) entity;
    }

    @Override
    public String toString() {
        return "CraftVillager";
    }

    public EntityType getType() {
        return EntityType.VILLAGER;
    }

    public Profession getProfession() {
        return Profession.getProfession(getHandle().getProfession());
    }

    public void setProfession(Profession profession) {
        Validate.notNull(profession);
        getHandle().setProfession(profession.getId());
    }

    @Override
    public Inventory getInventory() {
        return new CraftInventory(getHandle().inventory);
    }

    @Override
    public Career getCareer() {
        switch (getProfession()) {
            case FARMER:
                switch (getHandle().bx) {
                    case 1:
                        return Career.FARMER;
                    case 2:
                        return Career.FISHERMAN;
                    case 3:
                        return Career.SHEPHERD;
                    case 4:
                        return Career.FLETCHER;
                }
                break;
            case LIBRARIAN:
                switch (getHandle().bx) {
                    case 1:
                        return Career.LIBRARIAN;
                }
                break;
            case PRIEST:
                switch (getHandle().bx) {
                    case 1:
                        return Career.CLERIC;
                }
                break;
            case BLACKSMITH:
                switch (getHandle().bx) {
                    case 1:
                        return Career.ARMOR;
                    case 2:
                        return Career.WEAPON;
                    case 3:
                        return Career.TOOL;
                }
            case BUTCHER:
                switch (getHandle().bx) {
                    case 1:
                        return Career.BUTCHER;
                    case 2:
                        return Career.LEATHER;
                }
                break;
        }

        throw new IllegalArgumentException("Unknown career for villager " + this);
    }

    @Override
    public void setCareer(Career career) {
        switch (getProfession()) {
            case FARMER:
                switch (career) {
                    case FARMER:
                        getHandle().bx = 1;
                        return;
                    case FISHERMAN:
                        getHandle().bx = 2;
                        return;
                    case SHEPHERD:
                        getHandle().bx = 3;
                        return;
                    case FLETCHER:
                        getHandle().bx = 4;
                        return;
                }
            case LIBRARIAN:
                switch (career) {
                    case LIBRARIAN:
                        getHandle().bx = 1;
                        return;
                }
            case PRIEST:
                switch (career) {
                    case CLERIC:
                        getHandle().bx = 1;
                        return;
                }
            case BLACKSMITH:
                switch (career) {
                    case ARMOR:
                        getHandle().bx = 1;
                        return;
                    case WEAPON:
                        getHandle().bx = 2;
                        return;
                    case TOOL:
                        getHandle().bx = 3;
                        return;
                }
            case BUTCHER:
                switch (career) {
                    case BUTCHER:
                        getHandle().bx = 1;
                        return;
                    case LEATHER:
                        getHandle().bx = 2;
                        return;
                }
        }

        throw new IllegalArgumentException("Unknown career " + career + " for villager " + this);
    }

    @Override
    public int getCareerLevel() {
        return getHandle().by;
    }

    @Override
    public void setCareerLevel(int level) {
       getHandle().by = level;
    }

    @Override
    public boolean isWilling() {
        return getHandle().bu;
    }

    @Override
    public void setWilling(boolean willing) {
        getHandle().bu = willing;
    }
}
