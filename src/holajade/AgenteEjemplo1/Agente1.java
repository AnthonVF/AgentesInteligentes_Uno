package holajade.AgenteEjemplo1;
import Comunicacion.EnviarMSJ;
import holajade.Contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ZAMBOY
 */
public class Agente1 extends Agent {
    AgentContainer ac;
    Contenedor c;
    @Override
    protected void setup(){
        addBehaviour(new Comportamiento());
    }
    @Override
    protected void takeDown(){
        ac = c.getAgentContainer();
        try {
            ac.createNewAgent("AG2", Agente2.class.getName(), new Object[]{c,2}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Agente1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    class Comportamiento extends Behaviour{
        boolean terminarComportamiento = false;
        @Override
        public void action() {
            System.out.println(getName());
            c = (Contenedor)getArguments()[0];
            doDelete(); // Muy necesario en este caso
//            terminarComportamiento = false;
            //blockingReceive(5000);
//            EnviarMSJ.enviarMSJ(ACLMessage.REQUEST, "AG2", 
//                    getAgent(), "CODAG1-AG2", 
//                    "Hola agente, soy "+getLocalName(), true, null);
//            ACLMessage acl = blockingReceive();
//            System.out.println(acl);
            //terminarComportamiento = true;
        }
        @Override
        public boolean done() {
            return terminarComportamiento;
        }
    }
}