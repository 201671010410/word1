/*
 * 在Count类的基础上，实现一个类KeywordIdentifier，读入一个java程序源文件，输出各个关键字的个数（注意，注释中出现的关键字不计入关键字个数）*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class KeywordIdentifier {
	public String filename;
	public String[] keywords = new String[]{"abstract","assert","boolean","break","byte",
			"case","catch","char","class","const",
			"continue","default","do","double","else",
			"enum","extends","final","finally","float",
			"for","goto","if","implements","import",
			"instanceof","int","interface","long","native",
			"new","package","private","protected","public",
			"return","strictfp","short","static","super",
			"switch","synchronized","this","throw","throws",
			"transient","try","void","volatile","while"};
	
	public KeywordIdentifier(String str)
	{
		filename = str;
	}
	
	//定义一个方法，用于统计关键字的个数
	public void countKeyWords(){
		char ch;
		File myFile=new File(filename);

		try 
	        {
	            BufferedReader in = new BufferedReader(new FileReader(myFile));
	            String str;
	            StringBuffer str1 = new StringBuffer("");
	            while ((str = in.readLine()) != null) 
	            {	
	            	int flag1 = 0;
	        		int flag2 = 0;
	            	for (int i=0; i<str.length(); i++){
	            		
	            		//遇到第一种注释时的处理方法
	            		if (str.charAt(i) == '/' && str.charAt(i+1) == '/')
	            		{
	            			flag2 = 1;
	            			str = str.substring(0, i);
	            			str1.append(str);		//截取注释之前的部分
	            			
	            		}
	            		//遇到第二种注释/**/时的处理方法
	            		else if (str.charAt(i) == '/' && str.charAt(i+1) == '*')
	            		{
	            			flag2 = 1;
	            			while ((str = in.readLine()) != null){		//读出去，不保留
	            				//System.out.println(str);
		            			for (int j=0; j<str.length(); j++){
		            				if (str.charAt(j) == '*' && str.charAt(j+1) == '/'){
		            					flag1 = 1;
		            					//break;
		            				}
		            			}
		            			if (flag1 == 1){
		            				
		            				break;
		            			}	            					
	            			}
	            			if (flag1 == 1){
	            				
	            				break;
	            			}
	            			
	            		}	
	            	}

	               if (flag2 == 0){
            			str1.append(str);
            		}       
	            }

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
	            
	            StringBuffer[] word = new StringBuffer[1000];
	            for(int i=0; i<1000; i++)
	            {
	            	word[i] = new StringBuffer();
	            }
	            
	            /*获取所有单词*/
	            sign_w = 1;
	            int k = 0;
	            int j = 0;
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
	                    j = 0;
	                    sign_w = 1;	                 
	                }	           
	            }
	            
	       
	            /*计算每个单词的个数*/
	            System.out.println("\n对java源文件中的关键字进行统计，各关键字的数量分别为:");
	            for(int i = 0; i < number_w; i++)
	            {
	                /*判断此单词是否和前面统计过的单词相同*/
	                int same = 0;
	                for(j = 0; j < i; j++)
	                {	                   
	                    if((word[i].toString().equals(word[j].toString())) == true)
	                    {	                    	
	                        same = 1;
	                        break;
	                    }
	                }
	                
	                /*统计从没有统计过的单词个数*/
	                if(same == 0)
	                {
	                    int num = 0;
	                    for(j = i; j < number_w; j++)
	                    {
	                        if(word[i].toString().equals(word[j].toString()))
	                        {
	                            num++;
	                        }
	                    }
	                    //System.out.println(word[i] + ":" + num);
	                   //判断是不是关键字
	                    for (j=0; j<keywords.length; j++)
	                    {
	                    	if (word[i].toString().equals(keywords[j])){
	                    		System.out.println(word[i] + "  \t：" + num + "个");
	                    	}
	                    	else{
	                    		//System.out.println(keywords[j] + ":" + new Integer(0));
	                    	}
	                    	
	                    }
	                }
	            }	            	            
	        } 
	        catch (Exception e) 
	        {
	            //e.getStackTrace();
	        }
		
	}

}
