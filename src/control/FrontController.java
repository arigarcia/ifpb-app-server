package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.command.Command;
import control.command.CommandFactory;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {
  private String GET = "GET";
  private String PUT = "PUT";
  private String POST = "POST";
  private String DELETE = "DELETE";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    process(GET, req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    process(POST, req, resp);
  }
  
  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    process(PUT, req, resp);
  }
  
  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    process(DELETE, req, resp);
  }
  
  public void process(String method, HttpServletRequest req, HttpServletResponse resp) throws IOException{
    //
    String url = req.getPathInfo();
    CommandFactory factory = new CommandFactory();
    Command command = factory.createCommand(method, url, req);
    //
    String jsontext = command.execute(req).asJSONObject().toString();
    //
    resp.setContentType("application/json");
    resp.setContentLength(jsontext.length());
    resp.getWriter().print(jsontext);
  }
  
}
