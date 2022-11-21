package com.ikeharad.mymod;

import net.minecraft.util.IStringSerializable;

public enum EnumEyeCount implements IStringSerializable {
    ZERO("zero"),
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight");

    private final String name;

    private EnumEyeCount(String name){
        this.name=name;
    }

    @Override
    public String getName() {
        return null;
    }

    public static EnumEyeCount getValueFromInteger(int num){
        switch (num){
            default:
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
        }
    }

    public int getMeta(){
        switch (this){
            case ZERO:
                return 0;
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
        }
        return 0;
    }
}
