package serversidecomponent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;


public class MultiThreadsServer extends Thread{
	
        private HashMap ipstorage=new HashMap();
	
	
        public HashMap ExamServerList;
        public HashMap QpaperLocation;
        public HashMap SubjectOutputLocation;
        ServerSocket s;
	MultiThreadsServer(HashMap ExamServerList, HashMap QpaperLocation,HashMap SubjectOutputLocation){
            this.ExamServerList=ExamServerList;
            this.QpaperLocation=QpaperLocation;
            this.SubjectOutputLocation=SubjectOutputLocation;
	}
        public void run(){
            try{
			
			s=new ServerSocket(9999);
			
			while(true){
				Socket connectToClient=s.accept();
                                BufferedReader isFromClient=new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));
                                StringTokenizer st=new StringTokenizer(isFromClient.readLine());
				String Sub=st.nextToken();
                                if(this.ExamServerList.containsKey(Sub)){
				if (!(ipstorage.containsKey(String.valueOf(connectToClient.getInetAddress())))){
					
                                    ipstorage.put(String.valueOf(connectToClient.getInetAddress()), 1);
                                    PrintWriter osToClient=new PrintWriter(connectToClient.getOutputStream(),true);
                                    osToClient.println(String.valueOf(this.ExamServerList.get(Sub)));
                                    connectToClient.close();
				}
				else{
                                    ThreadHandler t=new ThreadHandler(connectToClient,Integer.parseInt(String.valueOf(ExamServerList.get(Sub))),(String) QpaperLocation.get(Sub),ipstorage,Sub);
                                    t.start();
                                    
                                    
				}
                            }else if(Sub.equals("Answers")){
                                StringTokenizer stID=new StringTokenizer(isFromClient.readLine());
                                StringTokenizer stSubject=new StringTokenizer(isFromClient.readLine());
                                StringTokenizer stAnswers=new StringTokenizer(isFromClient.readLine());
                                String IDNo,Subj,Answersz;
                                IDNo=new String(stID.nextToken());
                                Subj=new String(stSubject.nextToken());
                                Answersz=new String(stAnswers.nextToken());
                                DataOutputStream out = null;
                                
                                out = new DataOutputStream(new FileOutputStream(SubjectOutputLocation.get(Subj).toString().trim()+"/"+IDNo+"_"+Subj+".tst"));
		
                		for(int i=0;i<Answersz.length();i++)
                                {
                        	try {
				for(int j=0;j<9;j++){
					out.writeChars(String.valueOf((int)(Math.random()*10)));
					
				}
				if(Answersz.substring(i, i+1).equalsIgnoreCase("A")){out.writeChars(String.valueOf(0));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("B")){out.writeChars(String.valueOf(1));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("C")){out.writeChars(String.valueOf(2));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("D")){out.writeChars(String.valueOf(3));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("T")){out.writeChars(String.valueOf(4));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("F")){out.writeChars(String.valueOf(5));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("N")){out.writeChars(String.valueOf(6));}
				else if(Answersz.substring(i, i+1).equalsIgnoreCase("U")){out.writeChars(String.valueOf(7));}
				
			} catch (IOException e) {e.printStackTrace();}
			
		}
		try {
			out.close();
                        connectToClient.close();
		} catch (IOException e) {e.printStackTrace();}

        }else{
            
            connectToClient.close();
    }
			}
			
			
		}catch(IOException e){
			System.err.println(e);
		}
        }
	int check(Socket connectToClient){
		int length=connectToClient.getLocalAddress().toString().length();
		
		String ip=new String(connectToClient.getLocalAddress().toString());
		int ipadd = 0;
		if((ip.length()-(ip.lastIndexOf(".")+1))==3){
			ipadd=Integer.parseInt(ip.substring(length-3,length));
		}
		else if((ip.length()-(ip.lastIndexOf(".")+1))==2){
			ipadd=Integer.parseInt(ip.substring(length-2,length));
		}
		else if((ip.length()-(ip.lastIndexOf(".")+1))==1){
			ipadd=Integer.parseInt(ip.substring(length-1,length));
		}
		
		return ipadd;
	}
}
class ThreadHandler extends Thread{
	private Socket connectToClient;
	private int noofqs;
	
        private String Location,Sub;
        HashMap ipstorage;
	public ThreadHandler(Socket c,int i,String Location,HashMap ipstorage,String Sub){
		connectToClient=c;
                this.ipstorage=ipstorage;
                this.Location=Location;
		
                this.Sub=Sub;
		noofqs=i;
	}
	public void run(){
		try{  
			int num2=Integer.parseInt(String.valueOf(ipstorage.get(String.valueOf(connectToClient.getInetAddress()))));
                        
                    if((num2<=noofqs)){
                      InputStream in = new FileInputStream(Location+"/"+num2+".png");
		      OutputStream out = connectToClient.getOutputStream();
		      byte[] buf = new byte[1024];
		      int len = 0;
		      while ((len = in.read(buf)) != -1) {
		          out.write(buf, 0, len);
		      }
		      out.flush();    	      
		      in.close();     
		      connectToClient.close();
		      ipstorage.put(String.valueOf(connectToClient.getInetAddress()),(num2+1));
		      
		      }
                    else if(num2==(noofqs+1)){
                        
			PrintWriter osToClient2=new PrintWriter(connectToClient.getOutputStream(),true);
                        
			osToClient2.println("ExamDetails.txt");	
                      
                        InputStream in = new FileInputStream(Location+"/ExamDetails.txt");
			OutputStream out = connectToClient.getOutputStream();
                        
			byte[] buf = new byte[1024];
                        int len = 0;
			while ((len = in.read(buf)) != -1) {
                             out.write(buf, 0, len);    
                        }
                       
			out.flush();    	      
			in.close();     
                        connectToClient.close();
                        ipstorage.put(String.valueOf(connectToClient.getInetAddress()),(num2+1));
			  
			}
                    else if(num2==(noofqs+2)){
					PrintWriter osToClient=new PrintWriter(connectToClient.getOutputStream(),true);
					osToClient.println("passwd.txt");					
					InputStream in = new FileInputStream(Location+"/passwd.txt");
				      OutputStream out = connectToClient.getOutputStream();
				      byte[] buf = new byte[1024];
				      int len = 0;
				      while ((len = in.read(buf)) != -1) {
				          out.write(buf, 0, len);
				      }
				      out.flush();    	      
				      in.close();     
				      connectToClient.close();
				      ipstorage.put(String.valueOf(connectToClient.getInetAddress()),(num2+1));
		  
                        }
                    else if(num2==(noofqs+3)){
					PrintWriter osToClient=new PrintWriter(connectToClient.getOutputStream(),true);
					osToClient.println("qtype.txt");					
					InputStream in = new FileInputStream(Location+"/qtype.txt");
				      OutputStream out = connectToClient.getOutputStream();
				      byte[] buf = new byte[1024];
				      int len = 0;
				      while ((len = in.read(buf)) != -1) {
				          out.write(buf, 0, len);
				      }
				      out.flush();    	      
				      in.close();     
				      connectToClient.close();
				     ipstorage.remove(String.valueOf(connectToClient.getInetAddress()));
				     
		  
		}	  
		}catch(IOException e){
			
		}
	}
	int check(){
		int length=connectToClient.getLocalAddress().toString().length();
		
		String ip=new String(connectToClient.getLocalAddress().toString());
		int ipadd = 0;
		if((ip.length()-(ip.lastIndexOf(".")+1))==3){
			ipadd=Integer.parseInt(ip.substring(length-3,length));
		}
		else if((ip.length()-(ip.lastIndexOf(".")+1))==2){
			ipadd=Integer.parseInt(ip.substring(length-2,length));
		}
		else if((ip.length()-(ip.lastIndexOf(".")+1))==1){
			ipadd=Integer.parseInt(ip.substring(length-1,length));
		}
		
		return ipadd;
	}
}
