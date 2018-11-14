package com.d2c.boss.sys.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 包含读写文件的简单方法。
 * 
 * @author xuhua
 */
public class FileManager {

	private static Logger logger = LoggerFactory.getLogger(FileManager.class);
	public static String FILECATALOG = "file";
	public static String CAPTURE = "capture";
	// 模版文件
	public static String TEMPLATEFIEL = "template";
	// 文件类型
	public static String[] TEXTFILETYPE = { "txt", "html", "mht", "htm" };
	public static String[] imagesType = { "bmp" };
	public static String EXE_TYPE = "exe";
	public static String PDF_TYPE = "pdf";
	public static String WORD_TYPE = "doc";
	public static String TEMP = "temp";

	public FileManager() {
	}

	public static void main(String args[]) {
		InputStream inputStream = readFile("E:/Test.txt");
		writeFile("E:/Test1.xml", inputStream);
	}

	/**
	 * 判断文档是否是文本类型文档
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isTextOrHtmlFile(String type) {
		int len = TEXTFILETYPE.length;
		for (int i = 0; i < len; i++) {
			if (type.equalsIgnoreCase(TEXTFILETYPE[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isImageFile(String type) {
		int len = imagesType.length;
		for (int i = 0; i < len; i++) {
			if (imagesType[i].equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否pdf类型的文件
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isPdfFile(String type) {
		return PDF_TYPE.equalsIgnoreCase(type);
	}

	/**
	 * 判断是否word类型的文件
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isWORDFile(String type) {
		return WORD_TYPE.equalsIgnoreCase(type);
	}

	public static boolean isExeFile(String type) {
		return EXE_TYPE.equalsIgnoreCase(type);
	}

	/**
	 * 把原文件另存为
	 * 
	 */
	public static void saveasFile(String src, String dest) {
		InputStream is = readFile(src);
		ByteArrayOutputStream byteOut = getArrayOSfromFileIS(is);
		writeFile(dest, byteOut.toByteArray());
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath
	 * @param destFile
	 */
	public static void renameFile(String filePath, String destFile) {
		File file = new File(filePath);
		file.renameTo(new File(destFile));
	}

	/**
	 * 读取文件到流中
	 * 
	 * @param filePath
	 * @return
	 */
	public static InputStream readFile(String filePath) {
		File file = new File(filePath);
		try {
			InputStream is = new FileInputStream(file);
			return is;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从流中保存文件
	 * 
	 * @param filePath
	 * @param is
	 */
	public static void writeFile(String filePath, InputStream is) {
		FileOutputStream out = null;
		try {
			File file = new File(filePath);
			if (!file.exists())
				file.createNewFile();
			out = new FileOutputStream(filePath);
			byte bytes[] = new byte[10240];
			for (int s = 0; (s = is.read(bytes)) > 0;) {
				out.write(bytes, 0, s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void writeFile(String filename, byte fileBytes[]) {
		InputStream is = new ByteArrayInputStream(fileBytes);
		FileOutputStream out = null;
		try {
			File file = new File(filename);
			if (!file.exists())
				file.createNewFile();
			out = new FileOutputStream(filename);
			byte bytes[] = new byte[10240];
			for (int s = 0; (s = is.read(bytes)) > 0;) {
				out.write(bytes, 0, s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 转化文件输入流到ByteArray输出流中
	 * 
	 * @param fIS
	 * @param byteOS
	 */
	public static ByteArrayOutputStream getArrayOSfromFileIS(InputStream is) {
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
		int b = 0;
		try {
			while ((b = is.read()) != -1) {
				byteOS.write(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteOS;
	}

	public static OutputStream getOsFromBytes(OutputStream os, byte bytes[]) {
		InputStream is = new ByteArrayInputStream(bytes);
		byte buf[] = new byte[10240];
		try {
			for (int s = 0; (s = is.read(buf)) > 0;)
				os.write(buf, 0, s);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return os;
	}

	public static String readTextFileIntoStringUTF8(File file) {
		StringBuffer stringbuffer = new StringBuffer();
		InputStreamReader inputstreamreader = null;
		FileInputStream fileinputstream = null;
		try {
			fileinputstream = new FileInputStream(file);
			inputstreamreader = new InputStreamReader(fileinputstream, "UTF-8");
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			do {
				String s1 = bufferedreader.readLine();
				if (s1 != null) {
					stringbuffer.append(s1);
					stringbuffer.append('\r');
					stringbuffer.append('\n');
				} else {
					fileinputstream.close();
					return stringbuffer.toString();
				}
			} while (true);
		} catch (Exception exception) {
			try {
				if (inputstreamreader != null)
					inputstreamreader.close();
			} catch (Throwable throwable) {
				logger.error(throwable.getMessage());
				throwable.printStackTrace();
			}
		} finally {
			try {
				if (fileinputstream != null)
					fileinputstream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 这个方法读取文本文件使用UTF8编码格式转换内容。
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readTextFileIntoStringUTF8(String fileName) {
		File file = new File(fileName);
		return readTextFileIntoStringUTF8(file);
	}

	/**
	 * 把字符串用配置文件中的编码格式存入文件中。
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStringIntoTextFile(String fileName, String content, boolean append) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			byte abyte0[] = content.getBytes(getTextEncoding());
			writeFileIntoByteArray(fileName, abyte0, true, append);
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 用UTF-8编码格式把字符串写到文件中
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStringIntoTextFileUTF8(String fileName, String content) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			byte abyte0[] = content.getBytes("UTF-8");
			writeFileIntoByteArray(fileName, abyte0, true, false);
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 用UTF-8编码格式把字符串写到文件中
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStringIntoTextFileUTF8(String fileName, String content, boolean append) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			byte abyte0[] = content.getBytes("UTF-8");
			writeFileIntoByteArray(fileName, abyte0, true, append);
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 使用默认编码格式把字符串写到文件中
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStringIntoTextFileDEFENCODING(String fileName, String content) {
		writeStringIntoTextFileDEFENCODING(fileName, content, true);
	}

	/**
	 * 使用默认编码格式把字符串写到文件中
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStringIntoTextFileDEFENCODING(String fileName, String content, boolean append) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			byte abyte0[] = content.getBytes();
			writeFileIntoByteArray(fileName, abyte0, true, append);
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 在一个文本文件中添加信息。使用UTF-8格式
	 * 
	 * @param fileName
	 * @param text
	 * @param withNewLine
	 *            信息后是否新起一行
	 */
	public static void appendTextToTextFile(String fileName, String text, boolean withNewLine) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(fileName, true);
			if (withNewLine) {
				text = text + '\r' + '\n';
			}
			byte abyte0[] = text.getBytes(getTextEncoding());
			fileoutputstream.write(abyte0);
			fileoutputstream.close();
		} catch (Exception exception) {
			throw new Error("Could not append data to file " + fileName);
		}
	}

	/**
	 * 使用配置文件中定义的编码格式把字符串数组写到文件中，数组中的一个元素对应文件中的一行
	 * 
	 * @param fileName
	 * @param lines
	 */
	public static void writeStringsIntoTextFile(String fileName, String lines[]) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(fileName);
			StringBuffer stringbuffer = new StringBuffer();
			for (int i = 0; i < lines.length; i++) {
				stringbuffer.append(lines[i] + '\r' + '\n');
			}

			fileoutputstream.write(stringbuffer.toString().getBytes(getTextEncoding()));
			fileoutputstream.close();
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 使用系统默认编码格式把字符串数组写到一个文件中，数组的每个元素对应一行。
	 * 
	 * @param fileName
	 * @param lines
	 */
	public static void writeStringsIntoTextFileDEFENCODING(String fileName, String lines[]) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(fileName);
			StringBuffer stringbuffer = new StringBuffer();
			for (int i = 0; i < lines.length; i++) {
				stringbuffer.append(lines[i] + '\r' + '\n');
			}

			fileoutputstream.write(stringbuffer.toString().getBytes());
			fileoutputstream.close();
		} catch (Exception exception) {
			throw new Error("Could not write file " + fileName);
		}
	}

	/**
	 * 获得文件夹下的所有文件。
	 * 
	 * @param directoryName
	 * @param extension
	 *            扩展名
	 * @return
	 */
	public static File[] getFilesOfDirectory(String directoryName, String extension) {
		directoryName = StringUtil.convertFileSeparators(directoryName);
		try {
			File file = new File(directoryName);
			File afile[] = file.listFiles();
			Vector vector = new Vector();
			for (int i = 0; i < afile.length; i++) {
				if (!afile[i].isDirectory()) {
					String s2 = afile[i].getName();
					if (extension == null || s2.endsWith(extension)) {
						vector.addElement(afile[i]);
					}
				}
			}

			File afile1[] = new File[vector.size()];
			vector.copyInto(afile1);
			sort(afile1);
			return afile1;
		} catch (Exception exception) {
			return new File[0];
		}
	}

	/**
	 * 取得文件最后更新时间
	 * 
	 * @param fileName
	 * @return
	 */
	public static long lastModified(String fileName) {
		fileName = StringUtil.convertFileSeparators(fileName);
		long l = 0x8000000000000000L;
		try {
			if (checkIfFileExists(fileName)) {
				l = (new File(fileName)).lastModified();
			}
		} catch (Throwable throwable) {
		}
		return l;
	}

	/**
	 * 获得文件夹下所有文件名
	 * 
	 * @param directoryName
	 *            文件夹名
	 * @param extension
	 *            扩展名
	 * @return
	 */
	public static String[] getFileNamesOfDiretory(String directoryName, String extension) {
		directoryName = StringUtil.convertFileSeparators(directoryName);
		if (extension == null) {
			File afile[] = getFilesOfDirectory(directoryName, extension);
			String as[] = new String[afile.length];
			for (int i = 0; i < as.length; i++) {
				as[i] = afile[i].getName();
			}

			return as;
		}
		try {
			Vector vector = new Vector();
			File file = new File(directoryName);
			String as1[] = file.list();
			for (int j = 0; j < as1.length; j++) {
				if (as1[j].endsWith(extension)) {
					vector.addElement(as1[j]);
				}
			}

			String as2[] = new String[vector.size()];
			vector.copyInto(as2);
			sort(as2);
			return as2;
		} catch (Exception exception) {
			return new String[0];
		}
	}

	/**
	 * 取得文件夹下的所有自文件夹
	 * 
	 * @param directoryName
	 * @return
	 */
	public static File[] getDirectoriesOfDirectory(String directoryName) {
		directoryName = StringUtil.convertFileSeparators(directoryName);
		try {
			File file = new File(directoryName);
			File afile[] = file.listFiles();
			Vector vector = new Vector();
			for (int i = 0; i < afile.length; i++) {
				if (afile[i].isDirectory()) {
					vector.addElement(afile[i]);
				}
			}

			File afile1[] = new File[vector.size()];
			vector.copyInto(afile1);
			sort(afile1);
			return afile1;
		} catch (Exception exception) {
			return new File[0];
		}
	}

	/**
	 * 获得文件夹下的子文件夹名
	 * 
	 * @param directoryName
	 * @return
	 */
	public static String[] getDirectoryNamesOfDirectory(String directoryName) {
		directoryName = StringUtil.convertFileSeparators(directoryName);
		File afile[] = getDirectoriesOfDirectory(directoryName);
		String as[] = new String[afile.length];
		for (int i = 0; i < as.length; i++) {
			as[i] = afile[i].getName();
		}

		return as;
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean checkIfFileExists(String fileName) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			File file = new File(fileName);
			return file.exists();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 拷贝文本文件
	 * 
	 * @param fromFileName
	 * @param toFileName
	 */
	public static void copyTextFile(String fromFileName, String toFileName) {
		fromFileName = StringUtil.convertFileSeparators(fromFileName);
		toFileName = StringUtil.convertFileSeparators(toFileName);
		String text = readTextFileIntoStringUTF8(fromFileName);
		writeStringIntoTextFile(toFileName, text, true);
	}

	/**
	 * 拷贝字节码文件
	 * 
	 * @param fromFileName
	 * @param toFileName
	 */
	public static void copyBinaryFile(String fromFileName, String toFileName) {
		fromFileName = StringUtil.convertFileSeparators(fromFileName);
		toFileName = StringUtil.convertFileSeparators(toFileName);
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(fromFileName);
			out = new FileOutputStream(toFileName);
			byte[] buf = new byte[10240];
			for (int s = 0; (s = in.read(buf)) > 0;)
				out.write(buf, 0, s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteFile(String fileName) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			File file = new File(fileName);
			file.delete();
		} catch (Exception exception) {
			throw new Error("Could not delete file. " + exception.toString());
		}
	}

	public static void deltree(String directory) {
		deltree(new File(directory));
	}

	public static void deltree(File directory) {
		if (directory.exists() && directory.isDirectory()) {
			File[] fileArray = directory.listFiles();

			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i].isDirectory()) {
					deltree(fileArray[i]);
				} else {
					fileArray[i].delete();
				}
			}

			directory.delete();
		}
	}

	/**
	 * 创建目录，内部调用createDirectory方法
	 * 
	 * @param directory
	 */
	public static void ensureThatDirectoryExists(String directory) {
		directory = StringUtil.convertFileSeparators(directory);
		try {
			createDirectory(directory);
		} catch (Throwable throwable) {
		}
	}

	/**
	 * 创建目录。
	 * 
	 * @param directory
	 */
	public static void createDirectory(String directory) {
		directory = StringUtil.convertFileSeparators(directory);
		try {
			File file = new File(directory);
			if (!file.exists())
				file.mkdirs();
		} catch (Exception exception) {
			throw new Error("Could not create directory " + directory + ".");
		}
	}

	public static void removeDirectory(String s) {
		s = StringUtil.convertFileSeparators(s);
		try {
			String as[] = getDirectoryNamesOfDirectory(s);
			for (int i = 0; i < as.length; i++) {
				removeDirectory(s + "/" + as[i]);
			}

			File afile[] = getFilesOfDirectory(s, null);
			for (int j = 0; j < afile.length; j++) {
				boolean flag = afile[j].delete();
				if (!flag) {
					throw new Error("Could not delete file " + afile[j].getAbsolutePath());
				}
			}

			File file = new File(s);
			file.delete();
		} catch (Exception exception) {
			throw new Error("Error happened whene removing directory " + s);
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static boolean newFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			writeStringIntoTextFileUTF8(filePathAndName, fileContent, true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			return myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void removeFile(String s) {
		s = StringUtil.convertFileSeparators(s);
		try {
			File file = new File(s);
			file.delete();
		} catch (Exception exception) {
			throw new Error("Error happened when removing file " + s);
		}
	}

	public static String[] readTextFileViaClassLoader(String fileName, ClassLoader cl) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			InputStream inputstream = ClassLoader.getSystemResourceAsStream(fileName);
			if (inputstream == null) {
				return new String[0];
			}
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
			Vector vector = new Vector();
			do {
				String s1 = bufferedreader.readLine();
				if (s1 != null) {
					vector.addElement(s1);
				} else {
					String as[] = new String[vector.size()];
					vector.copyInto(as);
					return as;
				}
			} while (true);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return new String[0];
	}

	/**
	 * 读取文件到字节码中
	 * 
	 * @param fileName
	 * @param withError
	 *            错误时处理方式，true:抛出异常，false：byte[0]
	 * @return
	 */
	public static byte[] readFileIntoByteArray(String fileName, boolean withError) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			File file = new File(fileName);
			int i = (int) file.length();
			FileInputStream fileinputstream = new FileInputStream(fileName);
			byte abyte0[] = new byte[i];
			fileinputstream.read(abyte0, 0, i);
			fileinputstream.close();
			return abyte0;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		if (withError) {
			throw new Error("Could not read byte array from file " + fileName);
		} else {
			return new byte[0];
		}
	}

	/**
	 * 把比特数组写到文件中
	 * 
	 * @param fileName
	 * @param bytes
	 * @param withError
	 */
	public static void writeFileIntoByteArray(String fileName, byte bytes[], boolean withError, boolean append) {
		fileName = StringUtil.convertFileSeparators(fileName);
		try {
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			// file.mkdirs();
			if (!file.exists())
				file.createNewFile();
			FileOutputStream fileoutputstream = new FileOutputStream(fileName, append);
			fileoutputstream.write(bytes);
			fileoutputstream.close();
		} catch (Throwable throwable) {
			if (withError) {
				throw new Error("Could not write byte array into file " + fileName);
			}
		}
	}

	/**
	 * 排序
	 * 
	 * @param o
	 */
	private static void sort(Object[] o) {
		for (boolean flag = false; !flag;) {
			flag = true;
			for (int i = 0; i < o.length - 1; i++) {
				if (o[i].toString().compareToIgnoreCase(o[i + 1].toString()) > 0) {
					Object obj = o[i];
					o[i] = o[i + 1];
					o[i + 1] = obj;
					flag = false;
				}
			}

		}

	}

	/**
	 * 取得编码格式
	 * 
	 * @return
	 */
	private static String getTextEncoding() {
		return "UTF-8";
	}

	/**
	 * 检查文件名是否合法
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean validateFileName(String fileName) {
		if ((fileName.indexOf("\\\\") != -1) || (fileName.indexOf("//") != -1) || (fileName.indexOf(":") != -1)
				|| (fileName.indexOf("*") != -1) || (fileName.indexOf("?") != -1) || (fileName.indexOf("\"") != -1)
				|| (fileName.indexOf("<") != -1) || (fileName.indexOf(">") != -1) || (fileName.indexOf("|") != -1)
				|| (fileName.indexOf("&") != -1))
			return false;
		else
			return true;
	}

	public static void writeFile(String filename, byte fileBytes[], String encoding) {
		File file = new File(filename);
		Writer writer = null;
		FileOutputStream outStream = null;
		try {
			if (file.exists() == false)
				file.createNewFile();
			outStream = new FileOutputStream(file);
			writer = new OutputStreamWriter(outStream, encoding);
			writer.write(new String(fileBytes));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<String> split(String str, String splitter, String groupString) {
		// 首先把"除掉
		String[] stra = str.split(groupString);
		int i = 0;
		String[] temp;
		List<String> result = new ArrayList<String>();
		for (String s : stra) {
			// 如果是""内的字符串则直接写入到结果集中，否则分隔,号，获取子字符串
			if (i % 2 == 0) {
				temp = s.split(splitter);
				if (temp.length > 0) {
					for (String ts : temp)
						result.add(ts);
				}
			} else {
				result.add(s);
			}
			i++;
		}
		return result;

	}
}