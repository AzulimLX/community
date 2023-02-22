package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
         //OKHTTP书写post请求
         public String getAccessToken(AccessTokenDto accessTokenDto)
         {
              MediaType json = MediaType.get("application/json; charset=utf-8");

             OkHttpClient client = new OkHttpClient();

             RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto),json);
             Request request = new Request.Builder()
                     .url("https://github.com/login/oauth/access_token")
                     .post(body)
                     .build();
             try (Response response = client.newCall(request).execute()) {
                 String string = response.body().string();
                 String token = string.split("&")[0].split("=")[1];//先用&拆分，前面是token ，第二个为=，为真正的token
                 return token;
         } catch (Exception e) {
                 e.printStackTrace();
             }
             return null;
         }

         //OKHTTP书写get方法
      public GithubUser getUser(String accessToken)
      {   OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
                  .url("https://api.github.com/user")
                  .header("Authorization","token "+accessToken)
                  .build();
          try (Response response = client.newCall(request).execute()) {
              String string = response.body().string();
              GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
              return githubUser;
          } catch (IOException e) {
              e.printStackTrace();
          }

         return null;
      }

}
