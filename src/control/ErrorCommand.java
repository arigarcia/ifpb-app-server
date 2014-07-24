package control;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

  @Override
  public JSONResult execute(HttpServletRequest req) {
    JSONResult result = new JSONResult();
    result.setMessage("Requisição incorreta.");
    result.setSuccess(false);
    return result;
  }

}
