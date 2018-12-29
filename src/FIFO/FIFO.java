package FIFO;

import java.util.*;

public class FIFO {
	private int N;  //ҳ���С
	private int yekuang[]; //ҳ������
	private int xulie[];  //����ҳ����������
	private int size;  //�ж�ҳ���Ƿ�����
	private List<Integer>list=null; 
	private double queyelv;
	
	public FIFO(String str,int N){
		size=0;
		Scanner scan=new Scanner(System.in);
	//	System.out.println("����������ҳ��������У��м��Կո������");
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
    //    System.out.println("������ҳ���С��3-10����");
    //	N=scan.nextInt();
        this.N=N;
    	this.yekuang=new int[N];
    	for(int i=0;i<N;i++){
    		this.yekuang[i]=-1;
    	}
	}
	
	public boolean isExist(int num){  //�ж�ҳ�����Ƿ���ڸ���
		for(int i=0;i<N;i++){
			if(yekuang[i]==num){
				return true;
			}
		}
		return false;
	}
	public String[] execute(){  //��ִ�з���
		int queyeshu=0;
		double zongyeshu=this.xulie.length;
		String str[]=new String[50];
		for(int i=0;i<50;i++){
			str[i]="";
		}
		int strLen=0;
		int k=0;  //����ҳ�����м���
		while(size<N&&k<this.xulie.length){  //��ҳ��δ��
			if(isExist(xulie[k])==false){  //������������
				queyeshu++;
				this.yekuang[size]=xulie[k];  //��������ҳ��ҳ�����size��һ
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***ҳ���п�***a��---��������ҳ�桰"+xulie[k]+"��---��b������ҳ����c��ҳ�桰"+xulie[k]+"������ҳ��"+(size+1)+"��";
				k++;
				size++;
			}
			else{  //�����Ѵ�����ҳ����
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***ҳ��δ��***a��---��������ҳ�桰"+xulie[k]+"��---��b�Ѿ�����c�����û�";
				k++;  //��������ҳ�����м�һ
			}
		/*	for(int i=0;i<N;i++){
				System.out.print(yekuang[i]+" ");
			}
			System.out.println();
			*/
		}
		int n=0;  //�Ƚ��ȳ����ӵ�һ��ҳ��ʼ�û�
		while(k<xulie.length){   //������δ����
			if(isExist(xulie[k])==false){   //������
				queyeshu++;
				int temp=this.yekuang[n];
				this.yekuang[n]=xulie[k];   //��������ָ��ҳ��
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***ҳ������***a��---��������ҳ�桰"+xulie[k]+"��---��b������ҳ����c��ҳ�桰"+xulie[k]+"������ҳ��"+(n+1)+"�����û���ҳ�桰"+temp+"��";
				k++;
				n++;
			}
			else{
				for(int i=0;i<N;i++){
					str[strLen]+="["+this.yekuang[i]+"]";
				}
				str[strLen++]+="\t***ҳ������***a��---��������ҳ�桰"+xulie[k]+"��---��b�Ѿ�����c�����û�";
				k++;
			}
			if(n==N){   //���������κ󣬹��㣬�ִӵ�һ��ҳ��ʼ�û�
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
		str[strLen++]="ȱҳ��Ϊ ";
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
