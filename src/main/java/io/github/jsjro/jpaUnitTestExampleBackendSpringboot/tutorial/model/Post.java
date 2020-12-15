package io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", columnDefinition="TEXT")
    private String title;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    @Column(name = "status")
    private boolean status;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "modified_date")
    private Date modified_date;

    public Post() {

    }

    public Post(String title, String content, boolean status, Date create_date, Date modified_date) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.create_date = create_date;
        this.modified_date = modified_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + ", create_date=" + create_date + ", modified_date=" + modified_date + "]";
    }
}