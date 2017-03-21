package com.nikai.string.tools.trim;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Filedo
{

	public static void main(String[] args)
	{
		File f1 = new File("../MethodProject1/productlist.txt");
		File f2 = new File("../MethodProject1/productlist1.txt");
		// int b=0;
		String line = "";
		try
		{
			FileReader reader = new FileReader(f1);
			FileWriter writer = new FileWriter(f2);
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);
			while ((line = br.readLine()) != null)
			{
				System.out.println(line);
				bw.write(line);
				bw.newLine();

			}
			reader.close();
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}