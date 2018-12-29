package FIFO;

import java.util.*;

public class FIFO {
	private int N;  //页框大小
	private int yekuang[]; //页框数组
	private int xulie[];  //访问页面序列数组
	private int size;  //判断页框是否已满
	private List<Integer>list=null; 
	private double queyelv;
	
	public FIFO(String str,int N){
		size=0;
		Scanner scan=new Scanner(System.in);
	//	System.out.println("请依次输入页面访问序列，中间以空格隔开：");
		list=new ArrayList<Integer>();  
   //     String s=scan.nextLine();  
   //     String []s1=s.split(" ");  
		String []s1=str.split(" ");
        int m=s1.length;  
        xulie=new int[m];
        for (int i=0;i<m;i++)  
        	list.add(Integer.valueOf(s1[i]));  
        for(int i=0;i<m;i++)
        	xulie[i]=list.get(i);
    //    System.out.println("请输入页框大小（3-10）：");
    //	N=scan.nextInt();
        this.N=N;
    	this.yekuang=new int[N];
    	for(int i=0;i<N;i++){
    		this.yekuang[i]=-1;
    	}
	}
	
	public boolean isExist(int num){  //判断页框中是否存在该数
		for(int i=0;i<N;i++){
			if(yekuang[i]==num){
				return true;
			}
		}
		return false;
	}
	public String[] execute(){  //主执行方法
		int queyeshu=0;
		double zongyeshu=this.xulie.length;
		String str[]=new String[50];
		for(int i=0;i<50;i++){
			str[i]="";
		}
		int strLen=0;
		int k=0;  //访问页面序列计数
		while(size<N&&k<this.xulie.length){  //当页面未满
			if(isExist(xulie[k])==false){  //若该数不存在
				queyeshu++;
				this.yekuang[size]=xulie[k];  //将数存入页框，页框计数size加一
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***页框有空***a！---即将访问页面“"+xulie[k]+"”---！b不存在页框内c将页面“"+xulie[k]+"”存入页框【"+(size+1)+"】";
				k++;
				size++;
			}
			else{  //该数已存在于页框中
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***页框未满***a！---即将访问页面“"+xulie[k]+"”---！b已经存在c不做置换";
				k++;  //仅将访问页面序列加一
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i]+" ");
			}
			System.out.println();
			*/
		}
		int n=0;  //先进先出，从第一个页框开始置换
		while(k<xulie.length){   //当访问未结束
			if(isExist(xulie[k])==false){   //不存在
				queyeshu++;
				int temp=this.yekuang[n];
				this.yekuang[n]=xulie[k];   //将数放入指定页框
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***页框满！***a！---即将访问页面“"+xulie[k]+"”---！b不存在页框内c将页面“"+xulie[k]+"”存入页框【"+(n+1)+"】，置换出页面“"+temp+"”";
				k++;
				n++;
			}
			else{
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***页框满！***a！---即将访问页面“"+xulie[k]+"”---！b已经存在c不做置换";
				k++;
			}
			if(n==N){   //当交换三次后，归零，又从第一个页框开始置换
				n=0;
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i]+" ");
			}
			System.out.println();
			*/
		}
		System.out.println(queyeshu+" "+zongyeshu);
		this.queyelv=queyeshu/zongyeshu;
		str[strLen++]="缺页率为 ";
		str[strLen]=this.queyelv+"";
		return str;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int xulie[]={1,2,2,4,3,5,1,1,2,0,2};
		String str="1 2 2 4 3 5 1 1 2 0 2";
		FIFO f=new FIFO(str,3);
		String s[]=f.execute();
		int i=0;
		while(!s[i].equals("")){
			System.out.println(s[i++]);
		}
		
	}

}
