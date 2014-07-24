package control.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

  public Command createCommand(String method, String url, HttpServletRequest req){
    switch (method) {
    case "GET":{
      //recuperar uma rota
      if (url.contains("/route/")){
        String id = url.substring("/route/".length(), url.length());
        return new GetRouteCommand(id);
      }
      //recuperar um usuário
      else if (url.contains("/user/")){
        String email = url.substring("/user/".length(), url.length());
        return new GetUserCommand(email);
      }
      return new ErrorCommand();
    }
    case "POST":{
      //salvar um usuário
      if (url.contains("/user")){
        return new PostUserCommand();
      }
      return new ErrorCommand();
    }
    default:
      return new ErrorCommand();
    }
  }
  
}
