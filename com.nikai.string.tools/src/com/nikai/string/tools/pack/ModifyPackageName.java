package com.nikai.string.tools.pack;

import java.io.File;
import java.io.IOException;

import com.nikai.string.tools.trim.TrimFile;

/**
 * 该类用来修改java文件的包名
 * @author 尼凯
 * @2017年3月21日
 * @version 1.0.0
 */
public class ModifyPackageName
{
	public static void main(String[] args) throws IOException
	{
		// File src = new File(
		// "D:/lab/xplan/virgo-workspace/org.eclipse.virgo.nano.artifact/src");
		// File javaFile = new File(
		// "D:/lab/xplan/virgo-workspace/org.eclipse.virgo.nano.artifact/src/org/eclipse/virgo/nano/artifact/bundle/BundleBridge.java");
		// System.out.println(TrimFile.countPackage(src, javaFile));
		String filepath = "C:\\org.eclipse.virgo.nano.repository";
		getfiles(new File(filepath), new File(filepath, "src"));
	}

	/**
	 * 
	 * @param dir
	 * @param src
	 * @throws IOException
	 * @author 尼凯
	 * @version 2017年3月21日下午10:29:14
	 */
	public static void getfiles(File dir, File src) throws IOException
	{
		// File dir = new File(directory);
		if (!dir.exists())
		{
			return;
		}
		TrimFile.copydirectory(dir.getAbsolutePath());
		File[] files = dir.listFiles();

		for (File file : files)
		{

			if (file.isDirectory())
			{
				getfiles(file, src);
			}
			if (file.isFile())
			{
				modifyfiles(file.getAbsolutePath(), src);
			}
		}

	}

	/**
	 * 
	 * @param filePath
	 * @param src
	 * @throws IOException
	 * @author 尼凯
	 * @version 2017年3月21日下午10:29:33
	 */
	public static void modifyfiles(String filePath, File src)
			throws IOException
	{
		String copyFilePath;
		if (filePath.startsWith("C"))
		{
			copyFilePath = filePath.replaceFirst("C", "D");
			src = new File(src.getAbsolutePath().replaceFirst("C", "D"));
			System.out.println(copyFilePath);
		}
		else
		{
			return;
		}
		File fileCopy = new File(copyFilePath);

		fileCopy.createNewFile();
		TrimFile.modifyPackageName(filePath, fileCopy, src);

	}
}
