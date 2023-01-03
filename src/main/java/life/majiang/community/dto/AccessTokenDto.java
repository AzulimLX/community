package life.majiang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDto {
  private String client_id;
  private String client_secret;
  private String code;
  private String redirect_uri;
  private String state;




}
