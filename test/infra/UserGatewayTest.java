package infra;

import domain.User;
import junit.framework.TestCase;

public class UserGatewayTest extends TestCase {

    public void testSaveUser(){
        //instancia as classes de persistência
        ConnectionFactory factory = new ConnectionFactory();
        UserGateway gateway = new UserGateway(factory);
        //cria o usuário
        User user = new User();
        user.setEmail("email@email.com");
        user.setName("My Name");
        user.setPasskey("0000000000000000");
        //salva o usuário
        gateway.store(user);
        //verifica se o usuário existe no banco
        User user1 = gateway.find("email@email.com");
        assertNotNull(user1);
        //altera o nome
        user1.setName("My Name 2");
        //salva as alterações
        gateway.update(user1);
        //verifica se o nome do usuário foi alterado
        User user2 = gateway.find("email@email.com");
        assertEquals("My Name 2", user2.getName());
        //remove o usuário
//        gateway.remove(user2);
//        //verficiar se o usuário foi removido
//        User user3 = gateway.find("email@email.com");
//        assertNull(user3);
    }
    
}
