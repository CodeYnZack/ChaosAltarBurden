package ChaosAltarBurden;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Bury extends Task {


     private static final String BONE_NAME = "Bones";
     private static final String BURY_ACTION = "Bury";

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        buryBone();

        return 300;
    }

    private void buryBone() {
        for (Item boneInInventory: Inventory.getItems(item -> item.getName().equals(BONE_NAME))) {

            if (Time.sleepUntil(() -> !Players.getLocal().isAnimating(), 2000)) {
                Time.sleep(200, 300);
                boneInInventory.interact(BURY_ACTION);
            }
            Time.sleepUntil(() -> Players.getLocal().isAnimating(), 2000);
        }

        if(!Inventory.isFull()) {
            Log.info("Starting to Pickup the Bones");
            ChaosAltarBurden.status = "Picking up Bones";
        }
    }
}
