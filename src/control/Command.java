package control;

import javax.servlet.http.HttpServletRequest;

public interface Command {
  
  JSONResult execute(HttpServletRequest req);
  
}
