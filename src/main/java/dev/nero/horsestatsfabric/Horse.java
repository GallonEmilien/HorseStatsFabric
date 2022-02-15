package dev.nero.horsestatsfabric;

import net.minecraft.text.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class Horse
{
    public static final double MIN_HEALTH = 15;
    public static final double MAX_HEALTH = 30;
    public static final double MIN_JUMP_HEIGHT = 1.25;
    public static final double MAX_JUMP_HEIGHT = 5;
    public static final double MIN_SPEED = 4.8;
    public static final double MAX_SPEED = 14.5;
    private final double MIN_SLOTS = 3;
    private final double MAX_SLOTS = 15;

    private double health;
    private double jumpHeight;
    private double speed;
    private int slots;
    private String owner;

    private boolean horse;

    public Horse(double health, double jumpHeight, double speed, int slots, String owner, boolean horse)
    {
        this.horse = horse;
        this.health = health;
        this.owner = owner;
        this.jumpHeight = jumpHeight;
        this.speed = speed;
        this.slots = slots;
        convert();
    }

    //To delete
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void convert()
    {
        jumpHeight = (
                - 0.1817584952 * Math.pow(jumpHeight, 3) +
                        3.689713992 * Math.pow(jumpHeight, 2) +
                        2.128599134 * jumpHeight - 0.343930367
        ); // convert to blocks
        speed = speed * 43; // convert to m/s

        //Round
        health = (double)Math.round(health * 100d) / 100d;
        jumpHeight = (double)Math.round(jumpHeight * 100d) / 100d;
        speed = (double)Math.round(speed * 100d) / 100d;

    }

    public int getAttributeColor(double minValue, double value, double maxValue)
    {
        double percentage = ((value - minValue) / (maxValue - minValue)) * 100;

        if (percentage > 98) {
            return Colors.FULL.getColor();
        } else if (percentage > 80) {
            return Colors.EIGHTY.getColor();
        } else if (percentage > 60) {
            return Colors.SIXTY.getColor();
        } else if (percentage > 40) {
            return Colors.FORTY.getColor();
        } else if (percentage > 20) {
            return Colors.TWENTY.getColor();
        } else {
            return Colors.ZERO.getColor();
        }
    }

    public String toString()
    {
        String t;
        if(horse) {
            t = "Health: " + MIN_HEALTH + "/" + getHealth() + "/" + "" + MAX_HEALTH + " " +
                    "Jump: " + MIN_JUMP_HEIGHT + "/" + getJumpHeight() + "/" + "" + MAX_JUMP_HEIGHT + " " +
                    "Speed: " + MIN_SPEED + "/" + getSpeed() + "/" + "" + MAX_SPEED + " ";
        } else {
            t = "Health: "+getHealth()+
                " / Jump: "+getJumpHeight()+
                " / Speed: "+getSpeed()+
                " / Slots: "+getSlots();
        }

        if(getOwner() != null)
        {
            t = t + " / Owner :"+getOwner();
        }

        return t;
    }

    public double getHealth() {
        return health;
    }

    public double getJumpHeight() {
        return jumpHeight;
    }

    public double getSpeed() {
        return speed;
    }

    public int getSlots() {
        return slots;
    }

    public String getOwner() {
        return owner;
    }
}
