


import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;


/**
 * @author Marco Steffens
 * @version 0.1
 */
public class WumpusAgent extends Agent {

    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getMyLogger(this.getClass().getName());

    private transient World world; 

    private DFAgentDescription gameAgent = null;
    private DFAgentDescription dfd = null;
    private AID topic = null;
    private ServiceDescription sd = null;

    protected void setup() {
        if(log.isLoggable(Logger.INFO))
            log.log(Logger.INFO, "Hello World! This is Agent '" + getLocalName() + "'!");

        world = new World();

        dfd = new DFAgentDescription();
        dfd.setName(this.getAID());


        sd = new ServiceDescription();
        dfd.addServices(sd);
               }




    protected void takeDown() {
        if(log.isLoggable(Logger.INFO))
            log.log(Logger.INFO, "takeDown() WumpusAgent");

        try {
            if(log.isLoggable(Logger.INFO))
                log.log(Logger.INFO, "DEregister game '" + WumpusConsts.GAME_SERVICE_TYPE + "'");
            DFService.deregister(this, dfd);
        } catch (FIPAException e) {
            if(log.isLoggable(Logger.WARNING))
                log.log(Logger.WARNING, "ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public WumpusPerception processAction(AID agentAID, String action) {
        WumpusPerception perception = null;


        if (action.equals(WumpusConsts.ACTION_REGISTER)) {

            perception = null;

            if (action.equals(WumpusConsts.ACTION_DEREGISTER)) {

                world.removeHunter(agentAID);

            } else if (action.equals(WumpusConsts.ACTION_TURN_LEFT)) {

                perception = world.eventTurnLeft(agentAID);

            } else if (action.equals(WumpusConsts.ACTION_TURN_RIGHT)) {

                perception = world.eventTurnRight(agentAID);

            } else if (action.equals(WumpusConsts.ACTION_MOVE)) {

                perception = world.eventMove(agentAID);

            } else if (action.equals(WumpusConsts.ACTION_GRAB)) {

                perception = world.eventGrab(agentAID);

            } else if (action.equals(WumpusConsts.ACTION_SHOOT)) {

                perception = world.eventShoot(agentAID);

                if (perception.getScream() == WumpusConsts.SENSOR_SCREAM) {


                    ACLMessage msgTopic = new ACLMessage(ACLMessage.INFORM);
                    msgTopic.setSender(this.getAID());
                    msgTopic.addReceiver(this.topic);
                    msgTopic.setContent(WumpusConsts.SENSOR_SCREAM);
                    this.send(msgTopic);
                }

            } else {
                throw new RuntimeException();
            }
        }
        return perception;
    }

    public World getWorld() {
        return this.world;
    }





}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
