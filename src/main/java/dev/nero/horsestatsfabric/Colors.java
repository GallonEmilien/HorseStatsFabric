package dev.nero.horsestatsfabric;

public enum Colors
{
    FULL   (0x7ba37a),
    EIGHTY (0xa5c0a4),
    SIXTY  (0xfcbb8c),
    FORTY  (0xfa9246),
    TWENTY (0xf59792),
    ZERO   (0xf05951);

    private int color;

    Colors(int color)
    {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
