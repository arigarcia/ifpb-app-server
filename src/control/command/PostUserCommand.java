package control.command;

import infra.ConnectionFactory;
import infra.UserGateway;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import domain.User;

/**
 * Para testar:
 * curl -X POST 
 * --data "json={\"email\":\"aristofanio@hotmail.com\", \"name\": \"Ari Garcia\", \"passkey\": \"123456\"}" 
 * http://localhost:8080/ifpb-app-server/user
 * 
 * @author ari
 *
 */
public class PostUserCommand implements Command {

  @Override
  public JSONResult execute(HttpServletRequest req) {
    //instancia as classes de persistência
    ConnectionFactory factory = new ConnectionFactory();
    UserGateway gateway = new UserGateway(factory);
    //
    String jsontext = req.getParameter("json");
    //
    JSONResult result = new JSONResult();
    //
    try {
      JSONObject json = new JSONObject(jsontext);
      String name = json.getString("name");
      String email = json.getString("email");
      String passkey = json.getString("passkey");
      //
      User user = new User();
      user.setEmail(email);
      user.setName(name);
      user.setPasskey(passkey);
      //
      gateway.store(user);
      //
      result.setMessage(JSONResult.MESSAGE_SUCCESS);
    }
    catch (JSONException e) {
      result.setSuccess(false);
      result.setMessage(e.getMessage());
    }
    //
    return result;
  }

}
