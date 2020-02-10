package com.epam.finalproject.pharmacy.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    private int pageNumber;
    private int maxPages;
    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setPageNumber(String inputPageNumber){
        this.pageNumber = Integer.parseInt(inputPageNumber);
    }

    public void setMaxPages(String inputMaxPages) {
        this.maxPages = Integer.parseInt(inputMaxPages);
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<a href=\"controller?command=" + command + "&page=1\">  |< </a>\n");
            if (pageNumber - 2 >= 1) {
                out.write("<a href=\"controller?command=" + command + "&page=" + (pageNumber - 2) + "\">"
                        + (pageNumber - 2) + "</a>\n");
            }
            if (pageNumber - 1 >= 1) {
                out.write("<a href=\"controller?command=" + command + "&page=" + (pageNumber - 1) + "\">"
                        + (pageNumber - 1) + "</a>\n");
            }
            out.write("<b>" + pageNumber +"</b>");
            if (pageNumber + 1 <= maxPages) {
                out.write("<a href=\"controller?command=" + command + "&page=" + (pageNumber + 1) + "\">"
                        + (pageNumber + 1) + "</a>\n");
            }
            if (pageNumber + 2 <= maxPages) {
                out.write("<a href=\"controller?command=" + command + "&page=" + (pageNumber + 2) + "\">"
                        + (pageNumber + 2) + "</a>\n");
            }
            out.write("<a href=\"controller?command=" + command + "&page=" + maxPages + "\"> >|  </a>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}

