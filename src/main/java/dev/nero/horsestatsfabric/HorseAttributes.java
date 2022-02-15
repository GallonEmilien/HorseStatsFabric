package dev.nero.horsestatsfabric;

public enum HorseAttributes
{
    DONKEY(15,30,1.25,5,7.525,7.525),
    HORSE(15,30,1.25,5,4.8,14.5),
    LLAMA(15,30,0,0,0,0);

    private double minHealth;
    private double maxHealth;
    private double minJump;
    private double maxJump;
    private double minSpeed;
    private double maxSpeed;

    HorseAttributes(double minHealth, double maxHealth, double minJump, double maxJump, double minSpeed, double maxSpeed)
    {
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.minJump = minJump;
        this.maxJump = maxJump;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public double getMinHealth() {
        return minHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getMinJump() {
        return minJump;
    }

    public double getMaxJump() {
        return maxJump;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
