package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width=155;
		int height=50;
		BufferedImage bufferimage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d=bufferimage.createGraphics();
		Font f=new Font("Tahoma",Font.BOLD,22);
		g2d.setFont(f);
		GradientPaint gp=new GradientPaint(0, 0, Color.BLUE, 0,height/2,Color.BLACK,true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width,height);
		g2d.setColor(new Color(0,153,255));
		String data="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String captcha="";
		char ch[]=data.toCharArray();
		for(int i=0;i<6;i++)
		{
			int j=(int)((Math.random())*62);
			captcha=captcha+" "+ch[j];
		}
		HttpSession session=request.getSession();
		session.setAttribute("captcha",captcha);
		g2d.drawString(captcha, 10, 35);
		g2d.dispose();
		response.setContentType("image/png");
		OutputStream os=response.getOutputStream();
		ImageIO.write(bufferimage, "png", os);
		os.close();

	}

}
