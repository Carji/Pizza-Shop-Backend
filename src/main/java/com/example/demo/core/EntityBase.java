package com.example.demo.core;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;
import com.example.demo.core.Exceptions.BadRequestException;
import org.hibernate.annotations.Type;
import lombok.*;

@MappedSuperclass
public @Getter @Setter @NoArgsConstructor abstract class EntityBase {
    @Id
    @Type(type = "uuid-binary")
    @Column(columnDefinition = "binary(16)")
    public UUID id;

    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EntityBase>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            BadRequestException badRequestException = new BadRequestException();
            for (ConstraintViolation<EntityBase> violation : violations) {
                badRequestException.addException(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw badRequestException;
        }
    }

    public void validate(String key, String value, ExistsByField existsByField) {
        this.validate();
        if (existsByField.exists(value)) {
            BadRequestException badRequestException = new BadRequestException();
            badRequestException.addException(key, String.format("Value %s for key %s is duplicated.", value, key));
            throw badRequestException;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntityBase)) {
            return false;
        }
        EntityBase tmp = (EntityBase) obj;
        return tmp.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.toString().hashCode();
    }

}
