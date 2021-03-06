package commands.withMessage;

import commands.AbstractCommandWhichRequiresCollection;
import data.ClientRequest;
import data.MessageFromServerToClient;
import data.MusicBand;
import utils.sql.DataBaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Command 'info'. Prints a short piece of information about the collection
 */

public class Info extends AbstractCommandWhichRequiresCollection {

    public Info(LinkedBlockingQueue<MusicBand> collection, ClientRequest request) {
        super(collection, request);
    }

    public void invoke(){
        ArrayList<String> words = getClientRequest().getWords();
        getClientRequest().setMessage(MessageFromServerToClient.INFO);
        try{
            words.add(getCollection().getClass().getSimpleName());
            words.add(Integer.toString(getCollection().size()));
            words.add(getLastInit());

            /*getMessages().recordMessage( "Collection type: " + getCollection().getClass().getSimpleName() +
                    "\nCollection size: " + getCollection().size() +
                    "\nInitialization date: " + getLastInit());*/

        }catch (NullPointerException e){

            words.add("-");
            words.add(Integer.toString(0));
            words.add("-");

            /*getMessages().recordMessage("Collection type: undefined" +
                    "\nCollection size: 0" +
                    "\nInitialization date: undefined");*/
        }
    }
    private String getLastInit(){
        String lastInit = "-";
        try {
            //Statement statement = DataBaseConnector.getConnection().prepareStatement("SELECT creation_date FROM music_bands WHERE owner = ? ORDER BY creation_date  desc LIMIT 1;");
            Statement statement = DataBaseConnector.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT creation_date FROM music_bands ORDER BY creation_date  desc LIMIT 1;");
            rs.next();
            lastInit = rs.getTimestamp(1).toLocalDateTime().toString();

        } catch (SQLException throwables) {
            //getMessages().recordMessage(throwables.getMessage());
        }
        return lastInit;
    }
}
