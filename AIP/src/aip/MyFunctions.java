package aip;

import java.text.*;
import java.util.Date;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyFunctions {

	public static String formatDate(String date){
		String a_dob="";
		SimpleDateFormat sdf;
		if(date.contains("-")){
		sdf=new SimpleDateFormat("dd-mm-yyyy");
		}
		else{
			sdf=new SimpleDateFormat("dd/mm/yyyy");
		}
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/mm/dd");
		 
		try { 
		Date  d= sdf.parse(date);
		 a_dob=sdf1.format(d); 
		} catch (ParseException e) {
		e.printStackTrace();
		}
		return a_dob;
	}
	public static String admID(){
		int len=0;
		String a_id="";
		while(len!=8){
			a_id="ADMIN"+Math.round(Math.random()*1000);
			len=a_id.length();
		}
		
  		return a_id;
	}
	public static String usrID(String id){
		int len=0;
		Long rnd_No = null;
		String substr="BU"+(id.substring(0,3)).toUpperCase();
		while(len!=3){
			rnd_No=Math.round(Math.random()*1000);
			len=(rnd_No.toString()).length();
		}
		substr=substr+rnd_No;
  		return substr;
	}
	public  static boolean checkCaptcha(String captcha,String code)
	{
		String str="";
		boolean status=false;
		String newCap[]=captcha.split(" ");
		for(int i=0;i<newCap.length;i++)
		{
			str=str+newCap[i];
		}
		if(str!=null&&code!=null)
		{
			if(str.equals(code))
			{
				status=true;
			}
			else
			{
				return false;
			}
		}
		return status;		
	}
	public static String passwordGen()
	{
	String data="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	String str="";
	char ch[]=data.toCharArray();
	for(int i=0;i<8;i++)
	{
		int j=(int)((Math.random())*62);
		str=str+ch[j];
	}
	return str;
	}
	public static boolean sendMail(String to,String generatedPassword) throws MessagingException
	{
		String host="smtp.gmail.com";
		String username="gaddeniharika88@gmail.com";//emailid
		String password="omganeshayanamaha";// emailid password
		String from="gaddeniharika88@gmail.com";// email from which u have to send
		String subject="Reset Password";
		
		String link="http://localhost:8025/AIP/JSP_Pages/resetPassword.jsp";
		String body="Your temporary password is " +generatedPassword+ " please use the given link to reset"+" "+link;
		boolean sessionDebug=false;
		Properties props=System.getProperties();
		props.put("mail.host",host);
		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); 
		Session mailSession=Session.getDefaultInstance(props,null);
		mailSession.setDebug(sessionDebug);
		Message msg=new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(from));
		InternetAddress [] address={new InternetAddress(to)};
		msg.setRecipients(Message.RecipientType.TO,address);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(body);
		Transport tr=mailSession.getTransport("smtp");
		tr.connect(host,username,password);
		msg.saveChanges();
		tr.sendMessage(msg,msg.getAllRecipients());
		tr.close();
		return true;
	}

	/*public static String accNo(){
		System.out.println("Hello");
		long len=0,len1=0;
		Long rnd_No = null,rnd_No1=null;
		String substr="";
		while(len!=8 && len1!=8){
			rnd_No=Math.round(Math.random()*100000000);
			rnd_No1=Math.round(Math.random()*100000000);
			len=(rnd_No.toString()).length();
			len1=(rnd_No.toString()).length();
		}
		System.out.println(rnd_No+""+rnd_No1);
  		return substr;
	}*/
/*	public static void main(String[] args) {
		MyFunctions mf=new MyFunctions();
		//	mf.admID();
		//mf.usrID("Niharika");
		mf.accNo();
	}*/
	
}
