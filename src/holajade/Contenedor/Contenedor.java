package holajade.Contenedor;
import holajade.AgenteEjemplo1.Agente1;
import holajade.AgenteEjemplo1.Agente2;
import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ZAMBOY
 */
public class Contenedor {
    AgentContainer contenedorAgentes;
    public void IniciarContenedor(){
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        Profile profile = new ProfileImpl(null, 1099, null);
        contenedorAgentes = runtime.createMainContainer(profile);
        iniciarAgentes();
    }
    private void iniciarAgentes(){
        try {
            //contenedorAgentes.createNewAgent("AG2", Agente2.class.getName(), null).start();
            contenedorAgentes.createNewAgent("AG1", Agente1.class.getName(), new Object[]{this}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public AgentContainer getAgentContainer(){
        return contenedorAgentes;
    }
}