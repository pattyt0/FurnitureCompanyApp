package com.furnituremanager.errormanager;

import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;

/**
 * RFC 7807 specification
 */
public class ErrorMessage {
    private String detail;
    private String type;
    private String title;
    private String status;
    private String instance;

    /**
     * Default ctor
     */
    public ErrorMessage() {
        this.detail = LocalDateTime.now().toString();
    }

    /**
     * TODO: implement
     * @param status
     */
    public ErrorMessage(String status) {
        this();
        this.status = status;
    }

    /**
     * TODO: implement constructor
     * @param status
     * @param error
     * @param ex
     */
    public ErrorMessage(String status, String error, HttpMessageNotReadableException ex) {
        this();
        this.status = status;
        this.instance = ex.getMessage().toString();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
