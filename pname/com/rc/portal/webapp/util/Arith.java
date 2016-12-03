// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Arith.java

package com.rc.portal.webapp.util;

import java.math.BigDecimal;

public class Arith
{

    private static final int DEF_DIV_SCALE = 10;

    public Arith()
    {
    }

    public double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public double div(double v1, double v2)
    {
        return div(v1, v2, 10);
    }

    public double div(double v1, double v2, int scale)
    {
        if(scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else
        {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public double round(double v, int scale)
    {
        if(scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else
        {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }
}
