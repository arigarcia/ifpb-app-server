package control.command;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResult {
  private JSONObject json;
  
  public static final String MESSAGE_SUCCESS = "Requisição realizada com sucesso.";
  
  public JSONResult() {
    json = new JSONObject();
  }

  public void setMessage(String message){
    try {
      json.put("message", message);
    } 
    catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  public void setSuccess(Boolean success){
    try {
      json.put("success", success);
    } 
    catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  public void setData(JSONObject object){
    try {
      json.put("data", object);
    } 
    catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  public JSONObject asJSONObject(){
    return json;
  }
  
}
