public void init() throws ServletException{}

public void service(ServletRequest req,SerletResponse res) throws ServletException,IOException{}

public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{}

public void doPost(HttpServletRequest ,HttpServletResponse) throws ServletException, IOException{}

public void destroy(){}//  only used once
// Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。


//   第一个到达服务器的 HTTP 请求被委派到 Servlet 容器。
//   Servlet 容器在调用 service() 方法之前加载 Servlet。
//   然后 Servlet 容器处理由多个线程产生的多个请求，每个线程执行一个单一的 Servlet 实例的 service() 方法。


//Servlet 是服务 HTTP 请求并实现 javax.servlet.Servlet 接口的 Java 类。Web 应用程序开发人员通常编写 Servlet 来扩展 javax.servlet.http.HttpServlet，并实现 Servlet 接口的抽象类专门用来处理 HTTP 请求。

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet{

	private String s ;
	public void init()throws ServletException{
		 s= 'HelloWorld';

	} 
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
	  // 设置响应内容类型
      response.setContentType("text/html");

      // 实际的逻辑是在这里
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");

	}

	public void destroy(){}

}
//初始化是对对象数据成员--message，传递给浏览器的message的初始化
//doGet实现response--根据html的response
//即在html里print <h1>" + message + "</h1>
//同时print的对象要 绑定到response.getWriter()

/* <servlet>
        <servlet-name>HelloWorld</servlet-name>
        <servlet-class>HelloWorld</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloWorld</servlet-name>
        <url-pattern>/HelloWorld</url-pattern>
    </servlet-mapping>*/

  //  在xml里实现
    //WEB服务器也称为WWW(WORLD WIDE WEB)服务器，主要功能是提供网上信息浏览服务。 WWW 是 Internet 的多媒体信息查询工具，是 Internet 上近年才发展起来的服务，也是发展最快和目前用的最广泛的服务。正是因为有了WWW工具，才使得近年来 Internet 迅速发展，且用户数量飞速增长。
//Web服务器是可以向发出请求的浏览器提供文档的程序。

//　　1、服务器是一种被动程序：只有当Internet上运行在其他计算机中的浏览器发出请求时，服务器才会响应。

//　　2 、最常用的Web服务器是Apache和Microsoft的Internet信息服务器（Internet Information Server，ⅡS）。

//　　3、Internet上的服务器也称为Web服务器，是一台在Internet上具有独立IP地址的计算机，可以向Internet上的客户机提供WWW、Email和FTP等各种Internet服务。

//通俗的讲，Web服务器传送（serves）页面使浏览器可以浏览，然而应用程序服务器提供的是客户端应用程序可以调用（call）的方法（methods）。确切一点，你可以说：Web服务器专门处理HTTP请求(request），但是应用程序服务器是通过很多协议来为应用程序提供（serves）商业逻辑（business logic）。
//动态响应（dynamic response）的产生委托（delegate）给一些其它的程序例如CGI脚本，JSP(JavaServer Pages）脚本，servlets，ASP(Active Server Pages）脚本，服务器端（server-side)JavaScript，或者一些其它的服务器端（server-side）技术。无论它们（译者注：脚本）的目的如何，这些服务器端（server-side）的程序通常产生一个HTML的响应（response）来让浏览器可以浏览。
    








