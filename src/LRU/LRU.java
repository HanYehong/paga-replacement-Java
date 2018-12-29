package LRU;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LRU {
	private int N;  //页框大小
	private int xulie[];  //访问页面序列数组
	private int size;  //判断页框是否已满
	private int[][] yekuang;
	private List<Integer>list=null; 
	private double queyelv;
	
	public LRU(String str,int N){
//		Scanner scan=new Scanner(System.in);
//		System.out.println("请依次输入页面访问序列，中间以空格隔开：");
		list=new ArrayList<Integer>();  
//        String s=scan.nextLine();  
        String []s1=str.split(" ");  
        int m=s1.length;  
        xulie=new int[m];
        for (int i=0;i<m;i++)  
        	list.add(Integer.valueOf(s1[i]));  
        for(int i=0;i<m;i++)
        	xulie[i]=list.get(i);
 //       System.out.println("请输入页框大小（3-10）：");
//    	N=scan.nextInt();
        this.N=N;
		size=0;
		this.yekuang=new int[N][2];
		for(int i=0;i<N;i++){
			this.yekuang[i][0]=-1;
		}
	}
	
	public int isExist(int num){  //判断页框中是否存在该数
		for(int i=0;i<N;i++){
			if(yekuang[i][0]==num){
				return i;
			}
		}
		return -1;
	}
	
	public int findMax(){
		int max=yekuang[0][1];
		int m=0;
		for(int i=0;i<N;i++){
			if(yekuang[i][1]>max){
				max=yekuang[i][1];
				m=i;
			}
		}
		return m;
	}
	
	public String[] execute(){
		int queyeshu=0;
		double zongyeshu=this.xulie.length;
		String str[]=new String[50];
		for(int i=0;i<50;i++){
			str[i]="";
		}
		int strLen=0;
		int k=0;  //访问页面序列计数
		while(size<N&&size<this.xulie.length){  //当页面未满
			int temp=isExist(xulie[k]);
			if(temp==-1){  //若该数不存在
				queyeshu++;
				this.yekuang[size][0]=xulie[k];  //将数存入页框，页框计数size加一
				this.yekuang[size][1]=0;
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***页框有空***a！---即将访问页面“"+xulie[k]+"”---！b不存在页框内c将页面“"+xulie[k]+"”存入页框【"+(size+1)+"】";
				size++;
				for(int i=0;i<size-1;i++){
					this.yekuang[i][1]++;
				}
				k++;
			}
			else{  //该数已存在于页框中
				this.yekuang[temp][1]=0;
				for(int i=0;i<size;i++){
					if(i!=temp)
						this.yekuang[i][1]++;
				}
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***页框有空***a！---即将访问页面“"+xulie[k]+"”---！b已经存在c不做置换";
				k++;  //将访问页面序列加一
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i][0]+" ");
			}
			System.out.println();
			*/
		}
		while(k<xulie.length){   //当访问未结束
			int temp=isExist(xulie[k]);
			if(temp==-1){   //不存在
				queyeshu++;
				int max=findMax();
				int t=this.yekuang[max][0];
				this.yekuang[max][0]=xulie[k];   //将数放入指定页框
				this.yekuang[max][1]=0;
				for(int i=0;i<size;i++){
					if(i!=max)
						this.yekuang[i][1]++;
				}
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***页框满！***a！---即将访问页面“"+xulie[k]+"”---！b不存在页框内c页面“"+t+"”最近最久未被访问，置出，将页面“"+xulie[k]+"”存入页框【"+(max+1)+"】中";
				k++;
			}
			else{
				this.yekuang[temp][1]=0;
				for(int i=0;i<size;i++){
					if(i!=temp)
						this.yekuang[i][1]++;
				}
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***页框满！***a！---即将访问页面“"+xulie[k]+"”---！b已经存在c不做置换";
				k++;
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i][0]+" ");
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
		String s="1 2 2 4 3 5 1 1 2 0 2";
		LRU l=new LRU(s,3);
		String str[]=l.execute();
		int i=0;
		while(!str[i].equals("")){
			System.out.println(str[i++]);
		}
	}

}
