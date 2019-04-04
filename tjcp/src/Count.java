

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Count {
	
	public String filename;
	
	public Count(String str){
		filename = str;
	}
	
	//方法，统计字符个数
	public void countChar(){
		int a;
		char ch,c;
		int[] num_upper = new int[26];		//存放26个大写字母的个数
		int[] num_lower = new int[26];		//存放26个小写字母的个数，java中int初始化自动为0，不用手动设置

		
		try{
			FileReader in = new FileReader(filename);		//字符流，输入
         
			while ((a = in.read()) != -1)
			{
				ch = (char)a;
				for(int i=0; i<26; i++)
				{
					//小写字母
					c = (char)('a'+i);
					if (ch == c)
					{
						num_lower[i]++;			//判断是哪个字母，并给对应字母的个数加1
						break;
					}
					
					//大写字母
					c = (char)('A'+i);
					if (ch == c)
					{
						num_upper[i]++;
						break;
					}
				}
			}
			
			//打印输出
			System.out.println("对各字符进行统计,各个字符数量分别为:");
			for (int i=0; i<26; i++)
			{
				c = (char)('a'+i);
				System.out.print((i+1)%5 == 0 ? c + ":" + num_lower[i] + "\n" : c + ":" + num_lower[i] + "\t");		//每行放5个
			}
			System.out.println();
			
		
			for (int i=0; i<26; i++)
			{
				c = (char)('A'+i);
				System.out.print((i+1)%5 == 0 ? c + ":" + num_upper[i] + "\n" : c + ":" + num_upper[i] + "\t");
			}
			System.out.println();
		}
		catch (Exception e){
		}
		
	}
	
	//方法，统计单词个数
	public void countWord(){
		char ch;
		File myFile=new File(filename);
		 try 
	        {
	            BufferedReader in = new BufferedReader(new FileReader(myFile));
	            String str;
	            StringBuffer str1 = new StringBuffer("");	//将文本文件中的字符串全部放入内存中，此处定义str1，存放全部字符串，便于操作
	            while ((str = in.readLine()) != null) 
	            {
	            	str1.append(str);
	            }
	            in.close();
	            
	            /*统计总的单词个数*/
	            int sign_w = 1;
	            int number_w = 0;
	            for(int i = 0; i < str1.length(); i++)
	            {
	                ch = str1.charAt(i);
	                if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
	                {
	                    sign_w = 0;
	                }
	                else if(sign_w == 0)
	                {
	                    number_w++;
	                    sign_w = 1;
	                }
	            }
	            
	            System.out.println("\n对单词进行统计,单词总数为:" + number_w + "个. " + "各个单词的数量分别为:");	            	            
	            StringBuffer[] word = new StringBuffer[1000];		//存放单词
	            for(int i=0; i<1000; i++)
	            {
	            	word[i] = new StringBuffer();		//初始化，非配内存
	            }
	            
	            
	            /*获取所有单词*/
	            sign_w = 1;
	            int k = 0;      
	            for(int i = 0; i < str1.length(); i++)
	            {
	                ch = str1.charAt(i);	              
	                if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
	                {	                	
	                    word[k].append(ch);
	                    sign_w = 0;	                 
	                }
	                else if(sign_w == 0)
	                {
	                    k++;
	                    sign_w = 1;	                 
	                }
	           
	            }
	            
	       
	            /*计算每个单词的个数*/
	            for(int i = 0; i < number_w; i++)
	            {	            	
	                /*判断此单词是否和前面统计过的单词相同*/
	                int same = 0;
	                //先看该单词前面的所有单词，如果有一样的，不处理，如果都不一样，说明是新单词，进行下一步
	                for(int j = 0; j < i; j++)
	                {	                   
	                    if((word[i].toString().equals(word[j].toString())))		//必须要用toString()方法，否则不对，为什么？？？
	                    {	                    	
	                        same = 1;
	                        break;
	                    }
	                }
	                
	                /*已经保证都是新单词，统计从没有统计过的单词个数，往后找，看看有多少一样的单词，从而统计出数量*/
	                if(same == 0)
	                {		                
	                    int num = 0;
	                    for(int j = i; j < number_w; j++)
	                    {
	                        if(word[i].toString().equals(word[j].toString()))
	                        {
	                            num++;
	                        }
	                    }
	                   System.out.println( word[i] + ":" + "\t" + num + "个");	                   
	                }
	            }	            	            
	        } 
	        catch (Exception e) 
	        {
	            //e.getStackTrace();
	        }
		
	}
}
