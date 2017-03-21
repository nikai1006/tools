package com.nikai.string.tools.Test;

public class TestMain
{
	public static void main(String[] args)
	{
//		String target
		String prefix = "package ";
		String instring = "package org.eclipse.virgo.kernel.artifact.bundle;//fasdyaf";
		String substr = instring.substring(prefix.length(),
				instring.indexOf(";"));
		System.out.println(instring + "----------->>>>>>>>>"
				+ substr);
	}
}
