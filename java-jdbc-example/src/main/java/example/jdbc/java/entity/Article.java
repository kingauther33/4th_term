package example.jdbc.java.entity;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;

@Table(name = "bai_viet")
public class Article {
    @Id(autoIncrement = true)
    private int id;
    @Column(type = "varchar(100)")
    private String title;
    @Column(type = "varchar(100)")
    private String content;
    @Column(type = "int")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
