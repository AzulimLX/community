package life.majiang.community.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("User")
public class User {
    @TableField("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("account_id")
    private String accountId;
    @TableField("token")
    private String token;
    @TableField("gmt_create")
    private Long gmtCreate;
    @TableField("gmt_modified")
    private Long getModified;
    @TableField("avatar_url")
    private String avatarUrl;

}
