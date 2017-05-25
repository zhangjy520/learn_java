package cn.gukeer.common.tag;

import cn.gukeer.common.utils.SpringContextHolder;
import cn.gukeer.divideStudent.sys.persistence.entity.Menu;
import cn.gukeer.divideStudent.sys.utils.UserUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


/**
 * 
 * 类描述：菜单标签
 * 
 * 刘高峰
 * @date： 日期：2015-1-23 时间：上午10:17:45
 * @version 1.0
 */
public class MenuTagHengxiang extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected Menu menu;//菜单Map
	
	

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			String menu = (String) this.pageContext.getSession().getAttribute("menu");
			if(menu!=null){
				out.print(menu);
			}else{
				menu=end().toString();
				out.print(menu);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		sb.append(getChildOfTree(menu,0, UserUtils.getMenuList()));
		
		System.out.println(sb);
		return sb;
		
	}
	
	private static String getChildOfTree(Menu parent, int level, List<Menu> menuList) {
		StringBuffer menuString = new StringBuffer();
		String href = "#";
		if (!parent.hasPermisson())
			return "";
		if (level > 0) {//level 为0是功能菜单

			if(level == 1){
				menuString.append("<li class=\"dropdown\">");
			}else{
				menuString.append("<li>");
			}

			ServletContext context = SpringContextHolder
					.getBean(ServletContext.class);
			if (parent.getHref() != null && parent.getHref().length() > 0) {
				
				
				if(parent.getHref().endsWith(".html")&&!parent.getHref().endsWith("ckfinder.html")){//如果是静态资源并且不是ckfinder.html，直接访问不加adminPath
					href = context.getContextPath() + parent.getHref();
				}else{
					href = context.getContextPath()
					+ parent.getHref();
				}
			}
		}


		if ((parent.getHref() == null || parent.getHref().trim().equals("")) && parent.getIsShow().equals("1")) {//如果是父节点且显示
			if (level > 0) {
			menuString
					.append("<a aria-expanded=\"false\" role=\"button\" href=\""
							+ href
							+ "\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+ parent.getName() +"<span class=\"caret\">"
							+ "</span></a>");
			}
			if (level == 1) {
				menuString.append("<ul role=\"menu\" class=\"dropdown-menu\">");
			} else if (level == 2) {
				menuString.append("<ul role=\"menu\" class=\"dropdown-menu\">");
			} else if (level == 3) {
				menuString.append("<ul role=\"menu\" class=\"dropdown-menu\">");
			} else if (level == 4) {
				menuString.append("<ul role=\"menu\" class=\"dropdown-menu\">");
			}
			for (Menu child : menuList) {
				if (child.getParentId().equals(parent.getId())&&child.getIsShow().equals("1")) {
					menuString.append(getChildOfTree(child, level + 1, menuList));
				}
			}
			if (level > 0) {
			menuString.append("</ul>");
			}
		} else {
			menuString.append("<a href=\""
					+ href
					+ "\">" + parent.getName() +"</a>");
		}
		if (level > 0) {
			menuString.append("</li>");
		}
		return menuString.toString();
	}
	
	

}
