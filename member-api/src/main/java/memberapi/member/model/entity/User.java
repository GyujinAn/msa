package memberapi.member.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */
@Entity
@Getter
@Setter
public class User extends Member{
    private String etc;
}
