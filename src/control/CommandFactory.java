package control;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

  public Command createCommand(String method, String url, HttpServletRequest req){
    switch (method) {
    case "GET":{
      if (url.contains("/route/")){
        String id = url.substring("/route/".length(), url.length());
        return new GetRouteCommand(id);
      }
      return new ErrorCommand();
    }
    default:
      return new ErrorCommand();
    }
  }
  
}
