package OPT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OPT {
	private int size;
	private int []num;
	private int N;
	private int xulie[];
	private String str[];
	private int strLen;
	private List<Integer>list=null; 
	private boolean is;
	private double queyelv;
	private int queyeshu;
	private double zongyeshu;
	
	public OPT(String s,int N){
		this.queyeshu=0;
		size=0;
		this.strLen=0;
//		Scanner scan=new Scanner(System.in);
//		System.out.println("请依次输入页面访问序列，中间以空格隔开：");
		list=new ArrayList<Integer>();  
//        String s=scan.nextLine();  
        String []s1=s.split(" ");  
        int m=s1.length;  
        xulie=new int[m];
        for (int i=0;i<m;i++)  
        	list.add(Integer.valueOf(s1[i]));  
        for(int i=0;i<m;i++)
        	xulie[i]=list.get(i);
//        System.out.println("请输入页框大小（3-10）：");
//    	N=scan.nextInt();
    	this.str=new String[50];
    	for(int i=0;i<50;i++){
    		this.str[i]="";
    	}
    	this.N=N;
    	this.num=new int[N];
    	for(int i=0;i<N;i++){
    		this.num[i]=-1;
    	}
    	this.zongyeshu=this.xulie.length;
	}
	
	public void execute(){
		int i=this.xulie.length;
		for(int j=0;j<i;j++){
			push(xulie, j);
			print();
		}
		System.out.println(queyeshu+" "+zongyeshu);
		this.queyelv=queyeshu/zongyeshu;
		str[strLen++]="缺页率为 ";
		str[strLen]=this.queyelv+"";
	}
	
	public boolean isExist(int n){
		int t=-1;
		for(int i=0;i<N;i++){
			if(num[i]==n){
				t=1;
			}
		}
		if(t==1){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean beyond(){
		if(size>=N){
			return true;
		}
		else
			return false;
	}
	public int findThePop(int []n,int k){
		int t[]=new int[3];int tt=0;
		for(int i=0;i<N;i++){
			t[i]=Integer.MAX_VALUE;
		}
		int s=n.length;
		for(int i=0;i<N;i++){
			for(int j=k+1;j<s;j++){
				if(num[i]==n[j]){
					t[i]=j;
					break;
				}
			}
		}
		int max=t[0];
		int ii=0;
		for(int i=1;i<N;i++){
			if(t[i]>max){
				max=t[i];
				ii=i;
			}
		}
		if(max==Integer.MAX_VALUE){
			this.is=true;
		}
		else{
			this.is=false;
		}
		return ii;
	}
	public void push(int []num,int k){
		if(beyond()==false){
			if(isExist(num[k])==false){
				this.queyeshu++;
				this.num[size]=num[k];
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.num[i]+"]";
				}
				str[strLen++]+="\t***页框有空***a！---即将访问页面“"+num[k]+"”---！b不存在页框内c将页面“"+num[k]+"”存入页框【"+(size+1)+"】";
				size++;
			}
			else{
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.num[i]+"]";
				}
				str[strLen++]+="\t***页框未满***a！---即将访问页面“"+num[k]+"”---！b已经存在c不做置换";
				;
			}
		}
		else{
			if(isExist(num[k])==true){
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.num[i]+"]";
				}
				str[strLen++]+="\t***页框满！***a！---即将访问页面“"+num[k]+"”---！b已经存在c不做置换";
				;
			}
			else{
				this.queyeshu++;
				int changeNum=findThePop(num,k);
				int temp=this.num[changeNum];
				this.num[changeNum]=num[k];
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.num[i]+"]";
				}
				if(this.is==true)
					str[strLen++]+="\t***页框满！***a！---即将访问页面“"+num[k]+"”---！b不存在页框内c页面“"+temp+"”在未来将不再被访问，置出，将页面“"+num[k]+"”存入页框【"+(changeNum+1)+"】中";
				else
					str[strLen++]+="\t***页框满！***a！---即将访问页面“"+num[k]+"”---！b不存在页框内c页面“"+temp+"”在未来将会很久再次被访问，先将其置出，将页面“"+num[k]+"”存入页框【"+(changeNum+1)+"】中";
			}
		}
	}
	public void print(){
		for(int i=0;i<num.length;i++){
			System.out.print(num[i]+"  ");
		}
		System.out.println();
	}
	public String[] getStr(){
		return this.str;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="1 2 2 4 3 5 1 1 2 0 2";
		OPT o=new OPT(s,3);
		o.execute();
		int i=0;
		String str[]=o.getStr();
		while(!str[i].equals("")){
			System.out.println(str[i++]);
		}
	}
}
