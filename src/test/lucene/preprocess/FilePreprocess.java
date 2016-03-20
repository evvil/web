package test.lucene.preprocess;

import java.io.*;
import java.util.*;


/**
 * Created by Evilina on 2016/3/19.
 */
public class FilePreprocess
{
    /**
     * 两个参数，一个是要被处理的源，另一个是处理后的文件输出路径
     */
    public static void preprocess(File file, String outputDir)
    {
        try
        {
            spiltToSmallFiles(charactorProcess(file, outputDir + "output.all"), outputDir);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 对文件字符进行全角/半角处理
     * @throws Exception
     */
    public static File charactorProcess(File file, String destFile)
            throws Exception
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line =reader.readLine();
        while (line != null)
        {
            if (!line.equals("\r\n"))
            {
                String newline = replace(line);
                writer.write(newline);
                writer.newLine();
            }
            line = reader.readLine();

        }
        reader.close();
        writer.close();
        return new File(destFile);
    }

    /**
     * 拆分成小文件
     * @throws IOException
     */
    public static void spiltToSmallFiles(File file,String outputpath)
    throws IOException
    {
        int filePointer = 0;
        int MAX_SIZE = 10240;
        BufferedWriter writer = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null)
        {
            buffer.append(line).append("\r\n");
            if (buffer.toString().getBytes().length >= MAX_SIZE)
            {
                writer = new BufferedWriter(new FileWriter(outputpath + "output" + filePointer + ".txt"));
                writer.write(buffer.toString());
                writer.close();
                filePointer++;
                buffer = new StringBuffer();

            }
            line = reader.readLine();
        }
        writer = new BufferedWriter(new FileWriter(outputpath + "output" + filePointer + ".txt"));
        writer.write(buffer.toString());
        writer.close();
    }

    private static String replace(String line)
    {
        HashMap map = new HashMap();
        map.put(",","!");
        int length = line.length();
        for (int i = 0; i < length; i++)
        {
            String charat = line.substring(i, i + 1);
            if (map.get(charat) != null)
            {
                line = line.replace(charat,(String) map.get(charat));
            }
        }
        return line;
    }

    public void doIt()
    {
        String inputFile = "d:\\test.txt";
        String outputDir = "d:\\test\\";
        if (!new File(outputDir).exists())
        {
            new File(outputDir).mkdirs();
        }
        FilePreprocess filePreprocess = new FilePreprocess();
        filePreprocess.preprocess(new File(inputFile),outputDir);
    }

}
