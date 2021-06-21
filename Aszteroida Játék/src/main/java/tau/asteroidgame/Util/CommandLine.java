package tau.asteroidgame.Util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Prototípus koncepcióban taglalt parancsok lebonyolításáért felelős osztály.
 * Maga a játék ciklus is innen indítható el ( game startloop ).
 */
public class CommandLine {
    //! Elérhető parancsokat tartalmazza.
    private Map<String, ICommand> commands = new HashMap<>();
    
    public CommandLine() {
    	commands.put("asteroidfield", new AsteroidFieldCommand());
    	commands.put("asteroid", new AsteroidCommand());
    	commands.put("settler", new SettlerCommand());
    	commands.put("robot", new RobotCommand());
    	commands.put("teleport", new TeleportCommand());
    	commands.put("ufo", new UFOCommand());
    	commands.put("game", new GameCommand());
    	
    }

    /**
     * A paraméterben kapott szöveget soronkénti parancsnak értelmezve végrehajtja azokat. A parancsok futtatása során megjelenő kimenet a standard kimenetre megy.
     * @param script A futtatandó szkript
     */
    public void runScript(String script){
        String[] script_commands = script.split("\n");
        for (int i = 0; i < script_commands.length;++i){
            String [] params = script_commands[i].strip().split(" ");
            if(commands.containsKey(params[0])){
                ICommand command =commands.get(params[0]);
                //System.out.println(script_commands[i].strip());
                command.run(params);
            }else {
                System.out.println("Error: Unknown command");
                return;
            }
        }
    }

    /**
     * Beírt parancsokat értelmezi mindaddig, amíg nem jön “exit” parancs. A parancsok futtatása során megjelenő kimenet a standard kimenetre megy.
     */
    public void commandLoop(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = reader.readLine();
            while (line != null){
                final String[] params = line.split(" ");
                if(params[0].equals("exit"))
                    return;
                if(commands.containsKey(params[0])){
                    ICommand command =commands.get(params[0]);
                    command.run(params);
                }else {
                    System.out.println("Error: Unknown command");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
