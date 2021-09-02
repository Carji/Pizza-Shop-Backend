package com.example.demo.domain.commentDomain;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.example.demo.core.EntityBase;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;
import org.hibernate.annotations.Type;
import lombok.*;

@Entity
public @NoArgsConstructor @Getter @Setter class Comment extends EntityBase {
    @Column(nullable = false)
    @NotNull 
    public String text;
    @Column(nullable = false)
    @NotNull 
    public int rating;
    @Type(type = "uuid-binary")
    @Column(columnDefinition = "binary(16)" , name ="user_id")
    private UUID userId;
    @NotNull
    @Type(type = "uuid-binary")
    @Column(columnDefinition = "binary(16)" , name ="pizza_id", nullable = false)
    private UUID pizzaId;

    private @ManyToOne @JoinColumn (name="pizza_id", insertable = false, updatable=false) Pizza pizza;
    private @ManyToOne @JoinColumn (name ="user_id", insertable = false, updatable=false) User user;
    
    @Override
    public String toString() {
        return String.format("Comment {id: %s, text: %s, rating: %s, user: %s, pizza: %s}", 
        this.getId(), this.getText(), this.getRating(), this.getUserId(), this.getPizzaId());
    }
    
}