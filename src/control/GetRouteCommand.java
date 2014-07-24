package control;

import java.util.Arrays;

import infra.ConnectionFactory;
import infra.RouteGateway;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.Route;

public class GetRouteCommand implements Command {
  private final String id;
  
  public GetRouteCommand(String id) {
    this.id = id;
  }

  @Override
  public JSONResult execute(HttpServletRequest req) {
    //criar conexão e gateway
    ConnectionFactory factory = new ConnectionFactory();
    RouteGateway gateway = new RouteGateway(factory);
    //recuperando uma rota
    Route route = gateway.find(new Integer(id));
    //
    JSONResult result = new JSONResult();
    JSONObject json = new JSONObject();
    //
    if (route != null && route.getCoordinates() != null){
      JSONArray ja = new JSONArray(
          Arrays.asList(route.getCoordinates())
      );
      // 
      try {
        //
        json.put("identity", route.getIdentity());
        json.put("time", route.getTime());
        json.put("coordinates", ja);
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
