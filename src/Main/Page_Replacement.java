package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import FIFO.FIFO;
import LRU.LRU;
import OPT.OPT;

public class Page_Replacement extends JFrame implements ActionListener,Runnable{
	private String suanfa[]={"OPT����û��㷨","FIFO�Ƚ��ȳ��㷨","LRU������δ�����㷨"};
	private String ykNum[]={"3","4","5"};
	private JButton button_suiji;
	private TextField xulie;
	private TextField num;
	private TextArea result;
	private JComboBox<String> combox_suanfa,combox_ykNum;
	private JButton button_start;
	private String str_result[];
	private Thread thread;
	private Label queyelv;
	
	public Page_Replacement(){
		super("ҳ���û��㷨ģ�����");
		GridBagLayout gbl=new GridBagLayout();
		this.setLayout(gbl);
		this.setBounds(200, 100, 1000, 500);
		
		//************����Ϊ�����ؼ���ʼ��************
		Label label1=new Label("ҳ���û��㷨ģ�����");
		Label label2=new Label("��ѡ���㷨");
		Label label3=new Label("��ѡ��ҳ�����");
		Label label4=new Label("ҳ���������");
		Label label5=new Label("����1-20��");
		this.combox_suanfa=new JComboBox<String>(suanfa);
		this.combox_ykNum=new JComboBox<String>(ykNum);
		this.button_suiji=new JButton("�������");
		this.button_start=new JButton("��ʼģ��");
		this.num=new TextField(5);
		this.xulie=new TextField(25);
		this.result=new TextArea();
		this.queyelv=new Label("");
		this.queyelv.setText("                        ");
		
		//************���ñ��⡰ҳ���û��㷨ģ����桱��λ��************
		GridBagConstraints con1=new GridBagConstraints();
		con1.fill=GridBagConstraints.CENTER;
		con1.gridx=0;
		con1.gridy=0;
		con1.gridwidth=10;
		con1.gridheight=1;
		gbl.setConstraints(label1, con1);
		this.add(label1);
		Font f1=new Font("����",Font.BOLD,25);
		label1.setFont(f1);
		label1.setForeground(Color.blue);
		
		//************���ñ�ǩ����ѡ���㷨����λ��************
		GridBagConstraints con2=new GridBagConstraints();
		con2.insets=new Insets(60,0,0,0);
		con2.fill=GridBagConstraints.BOTH;
		con2.gridx=0;
		con2.gridy=1;
		con2.gridwidth=1;
		con2.gridheight=1;
		gbl.setConstraints(label2, con2);
		this.add(label2);
		Font f2=new Font("����",Font.BOLD,15);
		label2.setFont(f2);
		label2.setBackground(Color.yellow);
		
		//************�����㷨��Ͽ��λ��************
		GridBagConstraints con3=new GridBagConstraints();
		con3.insets=new Insets(60,20,0,0);
		con3.fill=GridBagConstraints.BOTH;
		con3.gridx=1;
		con3.gridy=1;
		con3.gridwidth=2;
		con3.gridheight=1;
		gbl.setConstraints(this.combox_suanfa, con3);
		this.add(this.combox_suanfa);
		
		//************���ñ�ǩ����ѡ��ҳ���������λ��************
		GridBagConstraints con4=new GridBagConstraints();
		con4.insets=new Insets(10,0,0,0);
		con4.fill=GridBagConstraints.BOTH;
		con4.gridx=0;
		con4.gridy=2;
		con4.gridwidth=1;
		con4.gridheight=1;
		gbl.setConstraints(label3, con4);
		this.add(label3);
		Font f3=new Font("����",Font.BOLD,15);
		label3.setFont(f3);
		label3.setBackground(Color.yellow);
		
		//************����ҳ�������Ͽ��λ��************
		GridBagConstraints con5=new GridBagConstraints();
		con5.insets=new Insets(10,20,0,0);
		con5.fill=GridBagConstraints.BOTH;
		con5.gridx=1;
		con5.gridy=2;
		con5.gridwidth=2;
		con5.gridheight=1;
		gbl.setConstraints(this.combox_ykNum, con5);
		this.add(this.combox_ykNum);
		
		//************���ñ�ǩ��ҳ��������С���λ��************
		GridBagConstraints con6=new GridBagConstraints();
		con6.insets=new Insets(10,0,0,0);
		con6.fill=GridBagConstraints.BOTH;
		con6.gridx=0;
		con6.gridy=3;
		con6.gridwidth=1;
		con6.gridheight=1;
		gbl.setConstraints(label4, con6);
		this.add(label4);
		Font f4=new Font("����",Font.BOLD,15);
		label4.setFont(f4);
		label4.setBackground(Color.yellow);
		
		//************����ҳ����������ı����λ��************
		GridBagConstraints con7=new GridBagConstraints();
		con7.insets=new Insets(10,20,0,0);
		con7.fill=GridBagConstraints.BOTH;
		con7.gridx=1;
		con7.gridy=3;
		con7.gridwidth=3;
		con7.gridheight=1;
		gbl.setConstraints(this.xulie, con7);
		this.add(this.xulie);
		
		//************���á�������ɡ���ť��λ��************
		GridBagConstraints con8=new GridBagConstraints();
		con8.insets=new Insets(5,20,0,0);
		con8.fill=GridBagConstraints.BOTH;
		con8.gridx=1;
		con8.gridy=4;
		con8.gridwidth=1;
		con8.gridheight=1;
		this.button_suiji.setForeground(Color.red);
		gbl.setConstraints(this.button_suiji, con8);
		this.add(this.button_suiji);
		
		//************������������ı����λ��************
		GridBagConstraints con9=new GridBagConstraints();
		con9.insets=new Insets(5,10,0,0);
		con9.fill=GridBagConstraints.BOTH;
		con9.gridx=2;
		con9.gridy=4;
		con9.gridwidth=1;
		con9.gridheight=1;
		gbl.setConstraints(this.num, con9);
		this.add(this.num);
		
		//************���ñ�ǩ������0-20������λ��************
		GridBagConstraints con10=new GridBagConstraints();
		con10.insets=new Insets(5,10,0,0);
		con10.fill=GridBagConstraints.BOTH;
		con10.gridx=3;
		con10.gridy=4;
		con10.gridwidth=1;
		con10.gridheight=1;
		gbl.setConstraints(label5, con10);
		this.add(label5);
		Font f5=new Font("����",Font.BOLD,12);
		label5.setFont(f5);
		
		//************������������ʾ����λ��************
		GridBagConstraints con11=new GridBagConstraints();
		con11.insets=new Insets(50,20,0,0);
		con11.fill=GridBagConstraints.BOTH;
		con11.gridx=5;
		con11.gridy=1;
		con11.gridwidth=4;
		con11.gridheight=6;
		gbl.setConstraints(this.result, con11);
		this.add(this.result);
		
		//************���ð�ť����ʼģ�⡱��λ��************
		GridBagConstraints con12=new GridBagConstraints();
		con12.insets=new Insets(20,20,0,0);
		con12.gridx=8;
		con12.gridy=7;
		con12.gridwidth=1;
		con12.gridheight=1;
		this.button_start.setForeground(Color.CYAN);
		this.button_start.setBackground(Color.magenta);
		gbl.setConstraints(this.button_start, con12);
		this.add(this.button_start);
		
		//************���ñ�ǩ��ȱҳ�ʡ���λ��************
		GridBagConstraints con13=new GridBagConstraints();
		con13.fill=GridBagConstraints.BOTH;
		con13.insets=new Insets(10,20,0,0);
		con13.gridx=5;
		con13.gridy=7;
		con13.gridwidth=3;
		con13.gridheight=1;
		gbl.setConstraints(queyelv, con13);
		this.add(queyelv);
		Font f6=new Font("����",Font.ITALIC,13);
		queyelv.setFont(f6);
		
		//************����Ϊ�ؼ���Ӽ����Լ����ó�ʼĬ��ֵ************
		this.num.setText("10");
		this.combox_suanfa.addActionListener(this);
		this.combox_ykNum.addActionListener(this);
		this.button_suiji.addActionListener(this);
		this.button_start.addActionListener(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void run(){
		try {
			int i=0;  //�ַ����������
			while(!this.str_result[i].equals("ȱҳ��Ϊ ")){ //���ַ�������û�ж���
				//�ѵ�i���ַ����еġ�\t������a�������ַ����س���������������ʾ����
				this.result.append(this.str_result[i].substring(this.str_result[i].indexOf("\t")+1,this.str_result[i].indexOf("a")));
				Thread.sleep(1000);
				//�ѵ�i���ַ����еġ�a������b�������ַ����س���������������ʾ����
				this.result.append("\n"+this.str_result[i].substring(this.str_result[i].indexOf("a")+1,this.str_result[i].indexOf("b")));
				Thread.sleep(1000);
				//�߳����������
				for(int j=0;j<3;j++){
					this.result.append("��");
					Thread.sleep(1000);
				}
				//�ѵ�i���ַ����еġ�b������c�������ַ����س���������������ʾ����
				this.result.append(this.str_result[i].substring(this.str_result[i].indexOf("b")+1,this.str_result[i].indexOf("c")));
				Thread.sleep(1000);
				this.result.append("\n");
				String temp="";
				//�ѵ�i���ַ����еġ�c������β�����ַ����س���������������ʾ����
				this.result.append(temp=this.str_result[i].substring(this.str_result[i].indexOf("c")+1));
				if(temp.equals("�����û�")){ //����c������β�����ַ������ڡ������û����������������
					Thread.sleep(1000);
				}
				else{  //����c������β�����ַ��������ڡ������û��������߳����������
					Thread.sleep(1000);
					for(int j=0;j<3;j++){
						this.result.append("��");
						Thread.sleep(1000);
					}
				}
				this.result.append("\n");
				//�ѵ�i���ַ����д�0��ʼ����\t�������ַ����س���������������ʾ����
				this.result.append(this.str_result[i].substring(0,this.str_result[i].indexOf("\t")));
				Thread.sleep(1000);
				this.result.append("\n\n");
				i++;
			}
			String s=this.str_result[i++];
			s=s+this.str_result[i];
			this.queyelv.setText(s);
			this.result.append("\n--------------ģ����ɣ�-------------");
			this.result.append("\n\n\n");
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==this.button_start){  //�������ʼģ��ʱ
			this.queyelv.setText("                        ");
			String list=this.xulie.getText();  //���ҳ����������ַ����������ո�
			String []s1=list.split(" ");    //ȥ���ո�
			int check=0;      //����Ƿ��з����ֵı�־��������check==1.����check==0
			for(int i=0;i<s1.length;i++){
				//������ʽ�ж��Ƿ��з�����
				Pattern pattern=Pattern.compile("[0~9]*");
				Matcher isNum=pattern.matcher(s1[i]);
				if(!isNum.matches()){
					check=1;     //���з����֣�����check==1
					break;
				}
				else
					;
			}
		/*	if(check==1){    //�����û����з����֣���������
				JOptionPane.showMessageDialog(this, "���ʵ�ҳ�����а��������֣����飡");
			}
			else{*/
				int n=this.combox_suanfa.getSelectedIndex();  //�õ��㷨��Ͽ��ѡ��
				String m=this.combox_ykNum.getItemAt(this.combox_ykNum.getSelectedIndex());//�õ�ҳ�����
				int mm=Integer.parseInt(m);
				Object object=null;
				if(n==0){   //OPT�㷨
					object=new OPT(list,mm);
					((OPT)object).execute();
					this.str_result=((OPT)object).getStr(); //�õ���OPT���ص��ַ���
				}
				else if(n==1){  //FIFO�㷨
					object=new FIFO(list,mm);
					this.str_result=((FIFO)object).execute();  //�õ���FIFO���ص��ַ���
				}
				else if(n==2){  //LRU�㷨
					object=new LRU(list,mm);
					this.str_result=((LRU)object).execute();  //�õ���LRU���ص��ַ���
				}
				thread=new Thread(this);   //�����߳�
				thread.start();     //�����߳�
			}
	//	}
		if(ev.getSource()==button_suiji){   //�������������ɡ���ťʱ
		/*	Pattern pattern=Pattern.compile("[0~9]*");
			Matcher isNum=pattern.matcher(this.num.getText());
			if(!isNum.matches()){
				JOptionPane.showMessageDialog(this, "����������Ϊ�����֣����������룡");
			}
			else{*/
				int NUM=Integer.parseInt(this.num.getText());   //�õ��������
				if(NUM>0&&NUM<=20)  //����0-20�ķ�����
				{
					//����������������ַ���xl��
					int number[]=new int[NUM];
					int len=number.length;
					Random r=new Random();
					String xl="";
					for(int i=0;i<len;i++){
						number[i]=r.nextInt(10);
						xl+=number[i]+" ";  //ÿ������������һ���ո�
					}
					this.xulie.setText(xl);   //�������ɵ�������ַ�����ҳ��������е��ı�����
				}
				else  //��������Χ�������Ի���
				{
					JOptionPane.showMessageDialog(this, "�����������������ڷ�Χ1~20�ڣ����������룡");
				}
			}
		//}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Page_Replacement pr=new Page_Replacement();
	}
}
