package com.spring.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xjn
 * @since 2020-03-10
 */
public class ViewResolver {

    private String viewName;
    private File templateFile;

    public ViewResolver(String viewName, File template) {
        this.templateFile = template;
        this.viewName = viewName;
    }

    public String viewResolver(ModelAndVew modelAndVew) {
        return null;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }

    protected String parse(ModelAndVew mv) throws Exception {

        StringBuffer sb = new StringBuffer();

        RandomAccessFile ra = new RandomAccessFile(this.templateFile, "r");

        try {
            //模板框架的语法是非常复杂，但是，原理是一样的
            //无非都是用正则表达式来处理字符串而已
            //就这么简单，不要认为这个模板框架的语法是有多么的高大上
            //来我现在来做一个最接地气的模板，也就是咕泡学院独创的模板语法
            String line = null;
            while (null != (line = ra.readLine())) {
                Matcher m = matcher(line);
                while (m.find()) {
                    for (int i = 1; i <= m.groupCount(); i++) {
                        String paramName = m.group(i);
                        Object paramValue = mv.getModel().get(paramName);
                        if (null == paramValue) {
                            continue;
                        }
                        line = line.replaceAll("@\\{" + paramName + "\\}", paramValue.toString());
                    }
                }

                sb.append(line);
            }
        } finally {
            ra.close();
        }
        return sb.toString();
    }

    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("@\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(str);
        return m;
    }


}
