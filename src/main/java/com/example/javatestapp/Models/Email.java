package com.example.javatestapp.Models;

import java.util.Map;

public class Email {
    private String from;
    private String to;
    private String subject;
    private String template;
    private Map<String, Object> props;

//    public static class HtmlTemplate {
//        private String template;
//        private Map<String, Object> props;
//
//        public HtmlTemplate(String template, Map<String, Object> props) {
//            this.template = template;
//            this.props = props;
//        }
//    }

    public Email (String to, String subject, String template, Map<String, Object> props) {
        this.from = "yuri.raduntcev@gmail.com";
        this.to = to;
        this.subject = subject;
        this.template = template;
        this.props = props;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
