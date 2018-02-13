/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Asmaa
 */
public class Message {
    String from;
    String to;
    XMLGregorianCalendar date;
    String content;
    String fontColor;
    String fontFamily;
    int fontSize;

    public Message() {
    }

    public Message(String from, String to, String content, XMLGregorianCalendar date, String fontColor, String fontFamily, int fontSize) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
        this.fontColor = fontColor;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }

    public String getFrom() {
        return from;
    }
    
    
    
    public String getContent() {
        return content;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public String getFontColor() {
        return fontColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getTo() {
        return to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontFamily(String fontStyle) {
        this.fontFamily = fontStyle;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    
    
    
}
