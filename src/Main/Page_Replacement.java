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
	private String suanfa[]={"OPT最佳置换算法","FIFO先进先出算法","LRU最近最久未访问算法"};
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
		super("页面置换算法模拟界面");
		GridBagLayout gbl=new GridBagLayout();
		this.setLayout(gbl);
		this.setBounds(200, 100, 1000, 500);
		
		//************以下为各个控件初始化************
		Label label1=new Label("页面置换算法模拟界面");
		Label label2=new Label("请选择算法");
		Label label3=new Label("请选择页框个数");
		Label label4=new Label("页面访问序列");
		Label label5=new Label("个（1-20）");
		this.combox_suanfa=new JComboBox<String>(suanfa);
		this.combox_ykNum=new JComboBox<String>(ykNum);
		this.button_suiji=new JButton("随机生成");
		this.button_start=new JButton("开始模拟");
		this.num=new TextField(5);
		this.xulie=new TextField(25);
		this.result=new TextArea();
		this.queyelv=new Label("");
		this.queyelv.setText("                        ");
		
		//************设置标题“页面置换算法模拟界面”的位置************
		GridBagConstraints con1=new GridBagConstraints();
		con1.fill=GridBagConstraints.CENTER;
		con1.gridx=0;
		con1.gridy=0;
		con1.gridwidth=10;
		con1.gridheight=1;
		gbl.setConstraints(label1, con1);
		this.add(label1);
		Font f1=new Font("宋体",Font.BOLD,25);
		label1.setFont(f1);
		label1.setForeground(Color.blue);
		
		//************设置标签“请选择算法”的位置************
		GridBagConstraints con2=new GridBagConstraints();
		con2.insets=new Insets(60,0,0,0);
		con2.fill=GridBagConstraints.BOTH;
		con2.gridx=0;
		con2.gridy=1;
		con2.gridwidth=1;
		con2.gridheight=1;
		gbl.setConstraints(label2, con2);
		this.add(label2);
		Font f2=new Font("宋体",Font.BOLD,15);
		label2.setFont(f2);
		label2.setBackground(Color.yellow);
		
		//************设置算法组合框的位置************
		GridBagConstraints con3=new GridBagConstraints();
		con3.insets=new Insets(60,20,0,0);
		con3.fill=GridBagConstraints.BOTH;
		con3.gridx=1;
		con3.gridy=1;
		con3.gridwidth=2;
		con3.gridheight=1;
		gbl.setConstraints(this.combox_suanfa, con3);
		this.add(this.combox_suanfa);
		
		//************设置标签“请选择页框个数”的位置************
		GridBagConstraints con4=new GridBagConstraints();
		con4.insets=new Insets(10,0,0,0);
		con4.fill=GridBagConstraints.BOTH;
		con4.gridx=0;
		con4.gridy=2;
		con4.gridwidth=1;
		con4.gridheight=1;
		gbl.setConstraints(label3, con4);
		this.add(label3);
		Font f3=new Font("宋体",Font.BOLD,15);
		label3.setFont(f3);
		label3.setBackground(Color.yellow);
		
		//************设置页框个数组合框的位置************
		GridBagConstraints con5=new GridBagConstraints();
		con5.insets=new Insets(10,20,0,0);
		con5.fill=GridBagConstraints.BOTH;
		con5.gridx=1;
		con5.gridy=2;
		con5.gridwidth=2;
		con5.gridheight=1;
		gbl.setConstraints(this.combox_ykNum, con5);
		this.add(this.combox_ykNum);
		
		//************设置标签“页面访问序列”的位置************
		GridBagConstraints con6=new GridBagConstraints();
		con6.insets=new Insets(10,0,0,0);
		con6.fill=GridBagConstraints.BOTH;
		con6.gridx=0;
		con6.gridy=3;
		con6.gridwidth=1;
		con6.gridheight=1;
		gbl.setConstraints(label4, con6);
		this.add(label4);
		Font f4=new Font("宋体",Font.BOLD,15);
		label4.setFont(f4);
		label4.setBackground(Color.yellow);
		
		//************设置页面访问序列文本框的位置************
		GridBagConstraints con7=new GridBagConstraints();
		con7.insets=new Insets(10,20,0,0);
		con7.fill=GridBagConstraints.BOTH;
		con7.gridx=1;
		con7.gridy=3;
		con7.gridwidth=3;
		con7.gridheight=1;
		gbl.setConstraints(this.xulie, con7);
		this.add(this.xulie);
		
		//************设置“随机生成”按钮的位置************
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
		
		//************设置随机个数文本框的位置************
		GridBagConstraints con9=new GridBagConstraints();
		con9.insets=new Insets(5,10,0,0);
		con9.fill=GridBagConstraints.BOTH;
		con9.gridx=2;
		con9.gridy=4;
		con9.gridwidth=1;
		con9.gridheight=1;
		gbl.setConstraints(this.num, con9);
		this.add(this.num);
		
		//************设置标签“个（0-20）”的位置************
		GridBagConstraints con10=new GridBagConstraints();
		con10.insets=new Insets(5,10,0,0);
		con10.fill=GridBagConstraints.BOTH;
		con10.gridx=3;
		con10.gridy=4;
		con10.gridwidth=1;
		con10.gridheight=1;
		gbl.setConstraints(label5, con10);
		this.add(label5);
		Font f5=new Font("宋体",Font.BOLD,12);
		label5.setFont(f5);
		
		//************设置输出结果显示区的位置************
		GridBagConstraints con11=new GridBagConstraints();
		con11.insets=new Insets(50,20,0,0);
		con11.fill=GridBagConstraints.BOTH;
		con11.gridx=5;
		con11.gridy=1;
		con11.gridwidth=4;
		con11.gridheight=6;
		gbl.setConstraints(this.result, con11);
		this.add(this.result);
		
		//************设置按钮“开始模拟”的位置************
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
		
		//************设置标签“缺页率”的位置************
		GridBagConstraints con13=new GridBagConstraints();
		con13.fill=GridBagConstraints.BOTH;
		con13.insets=new Insets(10,20,0,0);
		con13.gridx=5;
		con13.gridy=7;
		con13.gridwidth=3;
		con13.gridheight=1;
		gbl.setConstraints(queyelv, con13);
		this.add(queyelv);
		Font f6=new Font("宋体",Font.ITALIC,13);
		queyelv.setFont(f6);
		
		//************以下为控件添加监听以及设置初始默认值************
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
			int i=0;  //字符串数组计数
			while(!this.str_result[i].equals("缺页率为 ")){ //当字符串数组没有读完
				//把第i个字符串中的“\t”到“a”的子字符串截出，并输出到结果显示框中
				this.result.append(this.str_result[i].substring(this.str_result[i].indexOf("\t")+1,this.str_result[i].indexOf("a")));
				Thread.sleep(1000);
				//把第i个字符串中的“a”到“b”的子字符串截出，并输出到结果显示框中
				this.result.append("\n"+this.str_result[i].substring(this.str_result[i].indexOf("a")+1,this.str_result[i].indexOf("b")));
				Thread.sleep(1000);
				//线程输出三个点
				for(int j=0;j<3;j++){
					this.result.append("·");
					Thread.sleep(1000);
				}
				//把第i个字符串中的“b”到“c”的子字符串截出，并输出到结果显示框中
				this.result.append(this.str_result[i].substring(this.str_result[i].indexOf("b")+1,this.str_result[i].indexOf("c")));
				Thread.sleep(1000);
				this.result.append("\n");
				String temp="";
				//把第i个字符串中的“c”到结尾的子字符串截出，并输出到结果显示框中
				this.result.append(temp=this.str_result[i].substring(this.str_result[i].indexOf("c")+1));
				if(temp.equals("不做置换")){ //若“c”到结尾的子字符串等于“不做置换”，则不输出三个点
					Thread.sleep(1000);
				}
				else{  //若“c”到结尾的子字符串不等于“不做置换”，则线程输出三个点
					Thread.sleep(1000);
					for(int j=0;j<3;j++){
						this.result.append("·");
						Thread.sleep(1000);
					}
				}
				this.result.append("\n");
				//把第i个字符串中从0开始到“\t”的子字符串截出，并输出到结果显示框中
				this.result.append(this.str_result[i].substring(0,this.str_result[i].indexOf("\t")));
				Thread.sleep(1000);
				this.result.append("\n\n");
				i++;
			}
			String s=this.str_result[i++];
			s=s+this.str_result[i];
			this.queyelv.setText(s);
			this.result.append("\n--------------模拟完成！-------------");
			this.result.append("\n\n\n");
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==this.button_start){  //当点击开始模拟时
			this.queyelv.setText("                        ");
			String list=this.xulie.getText();  //获得页面访问序列字符串（包含空格）
			String []s1=list.split(" ");    //去掉空格
			int check=0;      //检查是否含有非数字的标志，若有则check==1.否则check==0
			for(int i=0;i<s1.length;i++){
				//正则表达式判断是否有非数字
				Pattern pattern=Pattern.compile("[0~9]*");
				Matcher isNum=pattern.matcher(s1[i]);
				if(!isNum.matches()){
					check=1;     //含有非数字，设置check==1
					break;
				}
				else
					;
			}
		/*	if(check==1){    //提醒用户含有非数字，重新输入
				JOptionPane.showMessageDialog(this, "访问的页面序列包含非数字，请检查！");
			}
			else{*/
				int n=this.combox_suanfa.getSelectedIndex();  //得到算法组合框的选择
				String m=this.combox_ykNum.getItemAt(this.combox_ykNum.getSelectedIndex());//得到页框个数
				int mm=Integer.parseInt(m);
				Object object=null;
				if(n==0){   //OPT算法
					object=new OPT(list,mm);
					((OPT)object).execute();
					this.str_result=((OPT)object).getStr(); //得到类OPT返回的字符串
				}
				else if(n==1){  //FIFO算法
					object=new FIFO(list,mm);
					this.str_result=((FIFO)object).execute();  //得到类FIFO返回的字符串
				}
				else if(n==2){  //LRU算法
					object=new LRU(list,mm);
					this.str_result=((LRU)object).execute();  //得到类LRU返回的字符串
				}
				thread=new Thread(this);   //创建线程
				thread.start();     //启动线程
			}
	//	}
		if(ev.getSource()==button_suiji){   //当点击“随机生成”按钮时
		/*	Pattern pattern=Pattern.compile("[0~9]*");
			Matcher isNum=pattern.matcher(this.num.getText());
			if(!isNum.matches()){
				JOptionPane.showMessageDialog(this, "输入的随机数为非数字，请重新输入！");
			}
			else{*/
				int NUM=Integer.parseInt(this.num.getText());   //得到随机个数
				if(NUM>0&&NUM<=20)  //若在0-20的返回内
				{
					//产生随机数，存入字符串xl中
					int number[]=new int[NUM];
					int len=number.length;
					Random r=new Random();
					String xl="";
					for(int i=0;i<len;i++){
						number[i]=r.nextInt(10);
						xl+=number[i]+" ";  //每个随机数后面加一个空格
					}
					this.xulie.setText(xl);   //设置生成的随机数字符串到页面访问序列的文本框中
				}
				else  //若超出范围，弹出对话框
				{
					JOptionPane.showMessageDialog(this, "输入的随机数个数不在范围1~20内，请重新输入！");
				}
			}
		//}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Page_Replacement pr=new Page_Replacement();
	}
}
