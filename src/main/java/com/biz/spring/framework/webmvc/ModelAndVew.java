package com.biz.spring.framework.webmvc;

import java.util.Map;

/**
 * @author xjn
 * @since 2020-03-10
 */
public class ModelAndVew {

    private String viewName;

    private Map<String, Object> model;

    public ModelAndVew(String viewName, Map<String, Object> map) {
        this.viewName = viewName;
        this.model = map;
    }

    public ModelAndVew(String view){
        this.viewName = view;
    }

    public String getView() {
        return viewName;
    }

    public void setView(String view) {
        this.viewName = view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
