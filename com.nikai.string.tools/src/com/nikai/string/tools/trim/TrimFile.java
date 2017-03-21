package com.nikai.string.tools.trim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrimFile
{
	public static void main(String[] args) throws IOException
	{
		String filepath = "C:\\test\\copy";
		filepath = "C:\\org.eclipse.virgo.kernel.metadata.publisher-3.6.2.RELEASE.src";
		getfiles(new File(filepath));

	}

	public static void getfiles(File dir) throws IOException
	{
		// File dir = new File(directory);
		if (!dir.exists())
		{
			return;
		}
		copydirectory(dir.getAbsolutePath());
		File[] files = dir.listFiles();

		for (File file : files)
		{

			if (file.isDirectory())
			{
				getfiles(file);
			}
			if (file.isFile())
			{
				copyfiles(file.getAbsolutePath());
			}
		}

	}

	public static void copydirectory(String filePath) throws IOException
	{
		String copyFilePath;
		if (filePath.startsWith("C"))
		{
			copyFilePath = filePath.replaceFirst("C", "D");
			System.out.println(copyFilePath);
		}
		else
		{
			return;
		}
		File fileCopy = new File(copyFilePath);
		if ((!fileCopy.exists()) && (!fileCopy.mkdirs()))
		{
			throw new IOException("Unable to create directory "
					+ fileCopy.getAbsolutePath());
		}
	}

	public static void copyfiles(String filePath) throws IOException
	{
		String copyFilePath;
		if (filePath.startsWith("C"))
		{
			copyFilePath = filePath.replaceFirst("C", "D");
			System.out.println(copyFilePath);
		}
		else
		{
			return;
		}
		File fileCopy = new File(copyFilePath);

		fileCopy.createNewFile();
		copyJavaFile(filePath, fileCopy);

	}

	/**
	 * 去除每行前面的注释信息
	 * 
	 * @param filePath
	 * @param filecopy
	 * @throws IOException
	 * @author 尼凯
	 * @version 2017年3月3日上午11:56:03
	 */
	public static final void copyJavaFile(String filePath, File filecopy)
			throws IOException
	{
		// }
		FileReader fr = new FileReader(filePath);
		FileWriter fw = new FileWriter(filecopy);
		// FileReader frNew = new FileReader()
		BufferedReader bufferedreader = new BufferedReader(fr);
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		String instring;
		while ((instring = bufferedreader.readLine()) != null)
		{
			if (0 != instring.length())
			{
				System.out.print(instring);
				String suffix = "*/ ";
				String prefix = "/*";
				if (instring.startsWith(prefix) && instring.contains(suffix))
				{
					int end = instring.indexOf(suffix) + suffix.length();
					String substr = instring.substring(end);
					System.out.println("----------->>>>>>>>>" + substr);
					bufferedWriter.write(substr);
					bufferedWriter.newLine();

				}
				else
				{

					bufferedWriter.write(instring);
					bufferedWriter.newLine();
				}

			}
		}
		bufferedWriter.flush();
		fr.close();
		fw.close();
		// bufferedWriter.close();
	}

	/**
	 * 修改类包名
	 * 
	 * @param filePath
	 * @param filecopy
	 * @param targetPackage
	 * @throws IOException
	 * @author 尼凯
	 * @version 2017年3月3日上午11:56:03
	 */
	public static final void modifyPackageName(String filePath, File filecopy,
			File src) throws IOException
	{
		if (!filePath.endsWith(".java"))
			return;
		// }
		FileReader fr = new FileReader(filePath);
		FileWriter fw = new FileWriter(filecopy);
		// FileReader frNew = new FileReader()
		BufferedReader bufferedreader = new BufferedReader(fr);
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		String lastPackage = countPackage(src, new File(filePath));
		boolean isModify = false;
		String instring;
		String prefix = "package ";
		while ((instring = bufferedreader.readLine()) != null)
		{
			if (0 != instring.length())
			{
				if (instring.startsWith(prefix) && !isModify)
				{
					String substr = instring.substring(prefix.length(),
							instring.indexOf(";"));
					System.out.println(substr + "----------->>>>>>>>>"
							+ lastPackage);
					bufferedWriter.write(prefix + lastPackage+";");
					bufferedWriter.newLine();
					isModify = true;
				}
				else
				{
					bufferedWriter.write(instring);
					bufferedWriter.newLine();
				}

			}
		}
		bufferedWriter.flush();
		fr.close();
		fw.close();
		// bufferedWriter.close();

	}

	/**
	 * 计算包名
	 * 
	 * @param sourceDirectory
	 *            源码根目录
	 * @param javaFile
	 *            类文件
	 * @return
	 * @author 尼凯
	 * @version 2017年3月3日下午2:51:38
	 */
	public static String countPackage(File sourceDirectory, File javaFile)
	{
		String sourceDirPath = sourceDirectory.getAbsolutePath();
		String filePath = javaFile.getParentFile().getAbsolutePath();
		String packagePath = filePath.substring(sourceDirPath.length());
		if (packagePath.startsWith(File.separator))
		{
			packagePath = packagePath.substring(1);
		}
		if (packagePath.endsWith(File.separator))
		{
			packagePath = packagePath.substring(0, packagePath.length() - 1);
		}
		String packageName = packagePath.replace(Character.valueOf('\\'), '.');
		return packageName;
	}

	public static final void readF1(String filePath) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath)));

		for (String line = br.readLine(); line != null; line = br.readLine())
		{
			System.out.println(line);
		}
		br.close();

	}
}
