package ChaosAltarBurden;

import ChaosAltarBurden.util.State;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;



public class PickupBones extends Task {


    private static final String PICKUP_BONES = "Take";

    private static final Area chaosArea = Area.polygonal(
            new Position(3234, 3608, 0),
            new Position(3234, 3606, 0),
            new Position(3235, 3605, 0),
            new Position(3236, 3604, 0),
            new Position(3237, 3603, 0),
            new Position(3242, 3603, 0),
            new Position(3245, 3605, 0),
            new Position(3245, 3613, 0),
            new Position(3235, 3613, 0));




    @Override
    public boolean validate() {
        return !Inventory.isFull() && !Players.getLocal().isMoving() && chaosArea.contains(Players.getLocal()) && !State.worldIsEmpty;
    }

    @Override
    public int execute() {

        pickupNearBone();

        return 300;
    }




    private void pickupNearBone() {
        final Pickable nearBone = Pickables.newQuery().within(chaosArea).names("Bones").results().nearest();

        if(nearBone != null) {
            nearBone.interact(PICKUP_BONES);
        } else {
            State.setEmpty(true);
            Log.info("No near bone");
        }


        if(Inventory.isFull()) {
            Log.info("Starting to bury the bones");
            ChaosAltarBurden.status = "Going to bury";
        }
    }
}
