package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class clients_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/WEB-INF/pages/include.jsp");
    _jspx_dependants.add("/resources/styles.css");
    _jspx_dependants.add("/WEB-INF/pages/header.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_url_value_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_url_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_url_value_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<style>");
      out.write("\n");
      out.write("table {\n");
      out.write("    border: 1px solid rgb(48, 46, 46);\n");
      out.write("}\n");
      out.write("th {\n");
      out.write("    border: 1px solid rgb(48, 46, 46);\n");
      out.write("}\n");
      out.write("td {\n");
      out.write("    border: 1px solid rgb(48, 46, 46);\n");
      out.write("}");
      out.write("</style>\n");
      out.write("<html>\n");
      out.write("    <title>\n");
      out.write("        Клиенты\n");
      out.write("    </title>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<header>\n");
      out.write("    <center>\n");
      out.write("        <a href=\"");
      if (_jspx_meth_c_url_0(_jspx_page_context))
        return;
      out.write("\" id=\"main-page\">\n");
      out.write("            <input type=\"button\" value=\"Главная\">\n");
      out.write("        </a>\n");
      out.write("        <a href=\"services\" id=\"services-page\">\n");
      out.write("            <input type=\"button\" value=\"Услуги\">\n");
      out.write("        </a>\n");
      out.write("        <a href=\"clients\" id=\"clients-page\">\n");
      out.write("            <input type=\"button\" value=\"Клиенты\">\n");
      out.write("        </a>\n");
      out.write("    </center>\n");
      out.write("    <br>\n");
      out.write("</header>");
      out.write("\n");
      out.write("        <main>\n");
      out.write("            <table id=\"clients-table\">\n");
      out.write("                <caption>\n");
      out.write("                    <h2>Клиенты</h2>\n");
      out.write("                    <input class=\"form-control\" type=\"text\" placeholder=\"Поиск...\" id=\"search-text\" onkeyup=\"tableSearch()\">\n");
      out.write("                </caption>\n");
      out.write("                <thead>\n");
      out.write("                    <th>ID</th>\n");
      out.write("                    <th>ФИО</th>\n");
      out.write("                    <th>Телефон</th>\n");
      out.write("                    <th>Номер счета</th>\n");
      out.write("                </thead>\n");
      out.write("                ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("            </table>\n");
      out.write("            <br>\n");
      out.write("            <a href=\"add_client\" id=\"add_client\">\n");
      out.write("                <form>\n");
      out.write("                    <input type=\"button\" value=\"Добавить...\"/>\n");
      out.write("                </form>\n");
      out.write("            </a>\n");
      out.write("        </main>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("<script>\n");
      out.write("    function tableSearch() {\n");
      out.write("        var phrase = document.getElementById('search-text');\n");
      out.write("        var table = document.getElementById('clients-table');\n");
      out.write("        var regPhrase = new RegExp(phrase.value, 'i');\n");
      out.write("        var flag = false;\n");
      out.write("        for (var i = 0; i < table.rows.length; i++) {\n");
      out.write("            flag = false;\n");
      out.write("            for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {\n");
      out.write("                flag = regPhrase.test(table.rows[i].cells[j].innerHTML);\n");
      out.write("                if (flag) break;\n");
      out.write("            }\n");
      out.write("            if (flag) {\n");
      out.write("                table.rows[i].style.display = \"\";\n");
      out.write("            } else {\n");
      out.write("                table.rows[i].style.display = \"none\";\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_url_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_0.setPageContext(_jspx_page_context);
    _jspx_th_c_url_0.setParent(null);
    _jspx_th_c_url_0.setValue("/");
    int _jspx_eval_c_url_0 = _jspx_th_c_url_0.doStartTag();
    if (_jspx_th_c_url_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_0);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_0);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setVar("client");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${clientsList}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                    <tr>\n");
          out.write("                        <td>\n");
          out.write("                            ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                        </td>\n");
          out.write("                        <td>\n");
          out.write("                            <a href=\"client?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.surname}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.middle_name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                            </a>\n");
          out.write("                        </td>\n");
          out.write("                        <td>\n");
          out.write("                            <a href=\"client?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.phone_number}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                            </a>\n");
          out.write("                        </td>\n");
          out.write("                        <td>\n");
          out.write("                            <a href=\"bill?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.bill.number}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.bill.number}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                            </a>\n");
          out.write("                        </td>\n");
          out.write("                        <td>\n");
          out.write("                            <a href=\"delete_client?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                <form action=\"deleteClient.jsp\">\n");
          out.write("                                    <input type=\"button\" value=\"Удалить\"/>\n");
          out.write("                                </form>\n");
          out.write("                            </a>\n");
          out.write("                        </td>\n");
          out.write("                        <td>\n");
          out.write("                            <a href=\"edit_client?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${client.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                <form>\n");
          out.write("                                    <input type=\"button\" value=\"Изменить\"/>\n");
          out.write("                                </form>\n");
          out.write("                            </a>\n");
          out.write("                        </td>\n");
          out.write("                    </tr>\n");
          out.write("                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
