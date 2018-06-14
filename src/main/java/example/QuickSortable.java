package example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 
 * 快速排序类
 * @author wangyu
 *
 */
public class QuickSortable implements Callable<List<String>>{
	
	
	private List<String> alist = null;
	
	private int n = 0;

	public QuickSortable(List<String> list, int i)
	{
		alist = list;
		n = i;
	}
	
	/**
	 * 多线程
	 */
	public List<String> call() throws Exception {
		 //快速排序
		String[] A = new String[n];
		if(alist != null && n > 0)
		{
			alist.toArray(A);
			qSort(A,0,n-1);
	        return Arrays.asList(A);
		}
		return null;
	}
	
	/**
	 * 单线程
	 * @param alist
	 * @param n
	 * @return
	 */
	public List<String> quickSort(List<String> alist, int n) {
        //快速排序
		String[] A = new String[n];
		if(alist != null && n > 0)
		{
			alist.toArray(A);
			qSort(A,0,n-1);
	        return Arrays.asList(A);
		}
		return null;
    }
	
    public void qSort(String[] A,int left,int right){

         //枢轴
        int pivot;
        if(left<right){

            pivot = partition(A,left,right);

            qSort(A,left,pivot-1);
            qSort(A,pivot+1,right);

        }      

    }

    //优化选取一个枢轴，想尽办法把它放到一个位置，使它左边的值都比它小，右边的值都比它大
    public int partition(String[] A,int left,int right){

        //优化选取枢轴，采用三数取中的方法
        String pivotKey = median3(A,left,right);
        //从表的俩边交替向中间扫描
        //枢轴用pivotKey给备份了
        while(left<right)
        {
           while(left<right && (A[right].compareTo(pivotKey) >= 0))
           {
               right--;
           }
           //用替换方式，因为枢轴给备份了，多出一个存储空间
           A[left]=A[right];
           while(left<right && (A[left].compareTo(pivotKey) <= 0))
           {
               left++;
           }
           A[right]=A[left];

        }

        //把枢轴放到它真正的地方
        A[left]=pivotKey;
        return left;
    }
    //三数取中
    public String median3(String[] A,int left,int right){

        int mid=(right-left)/2;
        if(A[left].compareTo(A[right]) > 0){
            swap(A,left,right);
        }
        if(A[mid].compareTo(A[left]) > 0){
            swap(A,mid,left);
        }
        if(A[mid].compareTo(A[right]) > 0){
            swap(A,mid,right);
        }

        return A[left];
    }

    public void swap(String[] A,int i,int j){
        String temp =A[i];
        A[i]=A[j];
        A[j]=temp;
    }




}
