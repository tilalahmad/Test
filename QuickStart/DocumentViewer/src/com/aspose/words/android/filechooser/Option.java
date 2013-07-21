/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.Words. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */

package com.aspose.words.android.filechooser;

import java.util.Locale;

public class Option implements Comparable<Option>
{

	public Option(String n, String d, String p)
    {
        name = n;
        data = d;
        path = p;
    }

    public String getName()
    {
        return name;
    }

    public String getData()
    {
        return data;
    }

    public String getPath()
    {
        return path;
    }

    public int compareTo(Option o)
    {
        if (this.name != null)
            return this.name.toLowerCase(Locale.US).compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
    
    private String name;
    private String data;
    private String path;
}
