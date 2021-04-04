package com.samplecode.restapi.utils;

public class Vec2d 
{
    public int x;
    public int y;

    public Vec2d(int xVal, int yVal)
    {
        x = xVal;
        y = yVal;
    }

    public Vec2d(Vec2d another)
    {
        x = another.x;
        y = another.y;
    }

    public Vec2d MutableAdd(Vec2d another)
    {
        x += another.x;
        y += another.y;

        return this;
    }

    public Vec2d UnmutableAdd(Vec2d another)
    {
        return new Vec2d(x + another.x, y + another.y);
    }

    public Vec2d MutableScalarProduct(int scalar)
    {
        x = scalar * x;
        y = scalar * y;

        return this;
    }

    public Vec2d UnmutableScalarProduct(int scalar)
    {
        return new Vec2d(scalar * x, scalar * y);
    }
}