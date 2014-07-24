package control;

import infra.ConnectionFactory;
import infra.UserGateway;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import domain.User;

public class GetUserCommand implements Command {
  private final String email;
  
  public GetUserCommand(String email) {
    this.email = email;
  }

  @Override
  public JSONResult execute(HttpServletRequest req) {
  //criar conexão e gateway
    ConnectionFactory factory = new ConnectionFactory();
    UserGateway gateway = new UserGateway(factory);
    //recuperando uma rota
    User user = gateway.find(email);
    //
    JSONResult result = new JSONResult();
    JSONObject json = new JSONObject();
    //
    if (user != null){
      // 
      try {
        //
        json.put("email", user.getEmail());
        json.put("name", user.getName());
        //
        result.setSuccess(true);
        result.setMessage("Requisição realizada com sucesso.");
        result.setData(json);
      }
      catch (Exception e) {
        result.setSuccess(false);
        result.setMessage(e.getMessage());
      }
    }
    else {
      result.setSuccess(false);
      result.setMessage("Rota inexistente.");
    }
    //
    return result;
  }

}
