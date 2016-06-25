package ro.robert.licenta.events.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Item")
public class Item extends BaseEntity{

    public static final String GET_ALL = "select i from Item i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from Item i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from Item i where i.id = :id";

    @Column(name = "text")
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
  
}
