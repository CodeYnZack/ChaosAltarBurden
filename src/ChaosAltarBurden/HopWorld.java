package ChaosAltarBurden;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import ChaosAltarBurden.util.State;




public class HopWorld extends Task {


    @Override
    public boolean validate() {
        return State.worldIsEmpty;
    }

    @Override
    public int execute() {
        Hop();
        return 600;
    }


    private static void Hop() {


        Time.sleepUntil(WorldHopper::open, 2400, 4800);
        ChaosAltarBurden.status = "WAITING 10 SECONDS.";

        randomSafeF2P();
        Time.sleep(1000);
        Time.sleepUntil(() -> Game.getState() == Game.STATE_HOPPING_WORLD, 5000);
        Time.sleepUntil(() -> Game.getState() != Game.STATE_HOPPING_WORLD, 5000);
    }


    private static void randomSafeF2P() {

        ChaosAltarBurden.status = "HOPPING.";
        Log.info("Hopping worlds");
            State.setEmpty(false);

        WorldHopper.randomHop(world -> world.getId() != Game.getClient().getCurrentWorld() && !world.isMembers()
                && !world.isPVP() && !world.isSkillTotal() && !world.isTournament() && !world.isHighRisk()
                && !world.isDeadman() && !world.isSeasonDeadman());


    }

}
