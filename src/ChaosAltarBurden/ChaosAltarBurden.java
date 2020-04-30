package ChaosAltarBurden;


import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;



@ScriptMeta(developer = "Effigy", name = "ChaosAltarBurden", desc = "Buries bones at Chaos altar in Wilderness", category = ScriptCategory.PRAYER)
public class ChaosAltarBurden extends TaskScript  {

    private static final Task[] TASKS = {new PickupBones(), new Bury(), new HopWorld()};


    public static String status = "Standby";


    public void onStart() {
        Log.fine("Starting the bury");
        submit(TASKS);
    }


    public void onStop() {
        Log.fine("Script is shutting down");
        super.onStop();
    }


}
