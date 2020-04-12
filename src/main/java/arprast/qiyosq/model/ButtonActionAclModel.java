package arprast.qiyosq.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="sys_button_action")
public class ButtonActionAclModel extends ModelNoIdEntity {

    @Id
    @Column(name ="menu_name", length = 100, nullable = false)
    private String menuName;

    @Column(columnDefinition = "TEXT")
    private String desc;
}
