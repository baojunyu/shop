/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2019-03-19 09:29:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.v;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/v/./includes/include.jsp", Long.valueOf(1551855856000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Title</title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/layui/css/layui.css\"></link>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery-1.11.0.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/layui/layui.js\"></script>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<form class=\"layui-form\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/userloginshiro\" method=\"post\">\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <label class=\"layui-form-label\">用户账号</label>\r\n");
      out.write("        <div class=\"layui-input-block\">\r\n");
      out.write("            <input type=\"text\" name=\"userAccount\"   lay-verify=\"required\" placeholder=\"请输入用户名\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <label class=\"layui-form-label\">用户密码</label>\r\n");
      out.write("        <div class=\"layui-input-block\">\r\n");
      out.write("            <input type=\"password\" name=\"password\" required lay-verify=\"required\" placeholder=\"请输入密码\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <label class=\"layui-form-label\">验证码</label>\r\n");
      out.write("        <div class=\"layui-input-inline\">\r\n");
      out.write("            <input type=\"text\" name=\"imgCode\" required lay-verify=\"required\" placeholder=\"请输入验证码\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-input-inline\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/imgCode\"  id=\"imgCode\" onclick=\"getImgCode()\" ></div>\r\n");
      out.write("        <div><button  class=\"layui-btn lay-btn-info\" onclick=\"getImgCode()\">点击刷新验证码</button></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <div class=\"layui-input-block\">\r\n");
      out.write("            <button class=\"layui-btn\" lay-submit lay-filter=\"formDemo\">立即提交</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    //Demo\r\n");
      out.write("    layui.use('form', function(){\r\n");
      out.write("        var form = layui.form;\r\n");
      out.write("\r\n");
      out.write("        //监听提交 formDemo   lay-filter的值  用来过滤表单的\r\n");
      out.write("        form.on('submit(formDemo)', function(data){\r\n");
      out.write("\r\n");
      out.write("            //layer.msg  弹框\r\n");
      out.write("            //layer.msg(JSON.stringify(data.field));\r\n");
      out.write("\r\n");
      out.write("           // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}\r\n");
      out.write("            //return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。\r\n");
      out.write("            aler(\"-------\");\r\n");
      out.write("            return true;\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    /**\r\n");
      out.write("     * 改变验证码事件\r\n");
      out.write("     */\r\n");
      out.write("    function getImgCode() {\r\n");
      out.write("\r\n");
      out.write("        //带个参数  为了保证每次的url都不一样  每次都回去请求控制器\r\n");
      out.write("        $(\"#imgCode\").prop(\"src\",\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/imgCode?time=\"+Math.random())\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
