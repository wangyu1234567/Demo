package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

	/** 
	 * 将一个list均分成n个list,主要通过偏移量来实现的 
	 * @param source 
	 * @return 
	 */  
	public static <T> List<List<T>> averageAssign(List<T> source,int n){  
	    List<List<T>> result=new ArrayList<List<T>>();  
	    int remaider=source.size()%n;  //(先计算出余数)  
	    int number=source.size()/n;  //然后是商  
	    int offset=0;//偏移量  
	    for(int i=0;i<n;i++){  
	        List<T> value=null;  
	        if(remaider>0){  
	            value=source.subList(i*number+offset, (i+1)*number+offset+1);  
	            remaider--;  
	            offset++;  
	        }else{  
	            value=source.subList(i*number+offset, (i+1)*number+offset);  
	        }  
	        result.add(value);  
	    }  
	    return result;  
	}
	
    /**
     * 
     * 从txt文件读取字符串
     * @return
     */
    public static List<String> readOutFile(String path) 
    {
	    File file = new File(path);
	    FileInputStream is;
	    List<String> list = new ArrayList<String>();
	    try {
	      if (file.length() != 0) {
	        is = new FileInputStream(file);
	        InputStreamReader streamReader = new InputStreamReader(is);
	        BufferedReader reader = new BufferedReader(streamReader);
	        String line;
	        while ((line = reader.readLine()) != null) {
	          list.add(line);
	        }
	        reader.close();
	        is.close();
	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return list;
	 }
    
    /**
     *向文件写入字符串，并换行
     * @param path
     * @param list
     */
    public static void writeFile(String path, List<String> list)
    {
    	PrintWriter fw;  
    	try {  
    	    fw = new PrintWriter(path);  
    	    BufferedWriter bw = new BufferedWriter (fw);  
    	    for(String str : list)
    	    {  
    	    	bw.write(str);  
    	    	bw.write("\r\n");
    	    }  
    	    bw.flush();  
    	    bw.close();  
    	} 
    	catch (IOException e) 
    	{  
    	    e.printStackTrace();  
    	}        
    }
    
    /**
     * 归并排序
     * 
     * @param leftArray
     * @param rightArray
     * @param intArr
     */
    public static List<String> merge(List<String> leftArray, List<String> rightArray) 
    {
    	 List<String> intArr = new ArrayList<String>();
        // i：leftArray数组索引，j：rightArray数组索引，k：intArr数组索引
        int i = 0, j = 0, k = 0;
        while (i < leftArray.size() && j < rightArray.size()) 
        {
            // 当两个数组中都有值的时候，比较当前元素进行选择
            if (leftArray.get(i).compareTo( rightArray.get(j)) < 0)
            {
            	intArr.add(leftArray.get(i));
                i++;
            }
            else 
            {
            	intArr.add(rightArray.get(j));
                j++;
            }
            k++;
        }

        // 将还剩余元素没有遍历完的数组直接追加到intArr后面
        if (i == leftArray.size()) 
        {
            for (; j < rightArray.size(); j++, k++)
            {
            	intArr.add(rightArray.get(j));
            }
        } 
        else
        {
            for (; i < leftArray.size(); i++, k++) 
            {
            	intArr.add(leftArray.get(i));
            }
        }  
        return intArr;
    }
}
