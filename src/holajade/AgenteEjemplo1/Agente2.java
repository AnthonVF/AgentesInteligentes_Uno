package holajade.AgenteEjemplo1;
import Comunicacion.EnviarMSJ;
import holajade.Contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ZAMBOY
 */
public class Agente2 extends Agent {
    AgentContainer ac;
    Contenedor c;
    @Override
    protected void setup(){
        addBehaviour(new Comportamiento());
    }
    @Override
    protected void takeDown(){
        int i = (int) getArguments()[1];
        i++;
        ac = c .getAgentContainer();
        try {
            ac.createNewAgent("AG"+i, Agente2.class.getName(), new Object[]{c,i}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    class Comportamiento extends CyclicBehaviour{
        @Override
        public void action() {
            System.out.println(getName());
            c = (Contenedor) getArguments()[0];
            doDelete(); // Muy necesario
//            ACLMessage acl = blockingReceive();
//            System.out.println(acl);
//            EnviarMSJ.enviarMSJ(ACLMessage.REQUEST, "AG1", 
//                    getAgent(), "CODAG2-AG1", 
//                    "Hola agente, soy "+getLocalName(), true, null);
        }
    }
}