package com.example.demo.domain.imageDomain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import com.example.demo.core.EntityBase;
import org.springframework.data.redis.core.RedisHash;
import lombok.*;

@Entity
@RedisHash("Image")
@Embeddable
public @Getter @Setter @NoArgsConstructor class Image extends EntityBase{
    @Size(min = 1)
    @Transient
    private byte[] data;
}
