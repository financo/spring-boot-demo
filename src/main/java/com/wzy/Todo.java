package com.wzy;
import java.util.Date;  
  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.Table;  
  
import org.hibernate.validator.constraints.NotBlank;  
  
@Entity  
@Table(name="todos")  
public class Todo {  
      
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private long id;  
      
    @NotBlank  
    private String title;  
    
    private String todos;
      
    private Date createdOn = new Date();  
      
    private Boolean completed = false;  
  
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
  
    public Date getCreateOn() {  
        return createdOn;  
    }  
  
    public void setCreateOn(Date createOn) {  
        this.createdOn = createOn;  
    }  
  
    public Boolean getCompleted() {  
        return completed;  
    }  
  
    public void setCompleted(Boolean completed) {  
        this.completed = completed;  
    }  
  
    
    public String getTodos() {
		return todos;
	}

	public void setTodos(String todos) {
		this.todos = todos;
	}

	public Todo(String title) {  
        super();  
        this.title = title;  
    }  
  
    public Todo() {  
        super();  
    }  
      
      
  
} 