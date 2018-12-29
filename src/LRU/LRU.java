package LRU;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LRU {
	private int N;  //ҳ���С
	private int xulie[];  //����ҳ����������
	private int size;  //�ж�ҳ���Ƿ�����
	private int[][] yekuang;
	private List<Integer>list=null; 
	private double queyelv;
	
	public LRU(String str,int N){
//		Scanner scan=new Scanner(System.in);
//		System.out.println("����������ҳ��������У��м��Կո������");
		list=new ArrayList<Integer>();  
//        String s=scan.nextLine();  
        String []s1=str.split(" ");  
        int m=s1.length;  
        xulie=new int[m];
        for (int i=0;i<m;i++)  
        	list.add(Integer.valueOf(s1[i]));  
        for(int i=0;i<m;i++)
        	xulie[i]=list.get(i);
 //       System.out.println("������ҳ���С��3-10����");
//    	N=scan.nextInt();
        this.N=N;
		size=0;
		this.yekuang=new int[N][2];
		for(int i=0;i<N;i++){
			this.yekuang[i][0]=-1;
		}
	}
	
	public int isExist(int num){  //�ж�ҳ�����Ƿ���ڸ���
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
		int k=0;  //����ҳ�����м���
		while(size<N&&size<this.xulie.length){  //��ҳ��δ��
			int temp=isExist(xulie[k]);
			if(temp==-1){  //������������
				queyeshu++;
				this.yekuang[size][0]=xulie[k];  //��������ҳ��ҳ�����size��һ
				this.yekuang[size][1]=0;
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***ҳ���п�***a��---��������ҳ�桰"+xulie[k]+"��---��b������ҳ����c��ҳ�桰"+xulie[k]+"������ҳ��"+(size+1)+"��";
				size++;
				for(int i=0;i<size-1;i++){
					this.yekuang[i][1]++;
				}
				k++;
			}
			else{  //�����Ѵ�����ҳ����
				this.yekuang[temp][1]=0;
				for(int i=0;i<size;i++){
					if(i!=temp)
						this.yekuang[i][1]++;
				}
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***ҳ���п�***a��---��������ҳ�桰"+xulie[k]+"��---��b�Ѿ�����c�����û�";
				k++;  //������ҳ�����м�һ
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i][0]+" ");
			}
			System.out.println();
			*/
		}
		while(k<xulie.length){   //������δ����
			int temp=isExist(xulie[k]);
			if(temp==-1){   //������
				queyeshu++;
				int max=findMax();
				int t=this.yekuang[max][0];
				this.yekuang[max][0]=xulie[k];   //��������ָ��ҳ��
				this.yekuang[max][1]=0;
				for(int i=0;i<size;i++){
					if(i!=max)
						this.yekuang[i][1]++;
				}
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i][0]+"]";
				}
				str[strLen++]+="\t***ҳ������***a��---��������ҳ�桰"+xulie[k]+"��---��b������ҳ����cҳ�桰"+t+"��������δ�����ʣ��ó�����ҳ�桰"+xulie[k]+"������ҳ��"+(max+1)+"����";
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
				str[strLen++]+="\t***ҳ������***a��---��������ҳ�桰"+xulie[k]+"��---��b�Ѿ�����c�����û�";
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
		str[strLen++]="ȱҳ��Ϊ ";
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
