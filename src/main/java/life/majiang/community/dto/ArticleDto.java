package life.majiang.community.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import life.majiang.community.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    Integer id;

    String title;

    String description;

    Long gmtCreate;

    Long gmtModified;

    Integer creator;

    Long commentCount;

    Long viewCount;

    Long likeCount;

    String tag;

    User user;
}
