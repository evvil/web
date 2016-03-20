package test.lucene.process;


import org.apache.lucene.index.IndexWriter;



/**
 * Created by Evilina on 2016/3/20.
 */
public class IndexProcesser
{
    //成员变量，存储创建的索引文件的存放的位置
    private String INDEX_STORE_PATH = "d:\\index";

    //创建索引
    public void createIndex(String inputDir)
    {
        try
        {
            //MMAnlyzer作为分词工具创建一个IndexWriter
            //IndexWriter writer = new IndexWriter(INDEX_STORE_PATH, new MMAnalyzer(),true)
        }
        catch(Exception e){
                e.printStackTrace();
          }


    }

}
