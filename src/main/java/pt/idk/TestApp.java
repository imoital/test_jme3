package pt.idk;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;

public class TestApp extends SimpleApplication {

    private static final String PLAYER_UP = "Up";
    private static final String PLAYER_DOWN = "Down";
    private static final String PLAYER_RIGHT = "Right";
    private static final String PLAYER_LEFT = "Left";
    private Geometry player;

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        player = new Geometry("Player", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Orange);
        player.setMaterial(mat);

        rootNode.attachChild(player);
        initKeys();
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        inputManager.addMapping(PLAYER_UP, new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping(PLAYER_DOWN, new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping(PLAYER_LEFT, new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping(PLAYER_RIGHT, new KeyTrigger(KeyInput.KEY_L));
        inputManager.addListener(analogListener, PLAYER_UP, PLAYER_DOWN, PLAYER_LEFT, PLAYER_RIGHT);

    }
    private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals(PLAYER_UP)) {
                Vector3f v = player.getLocalTranslation();
                player.setLocalTranslation(v.x, v.y + value * speed * 2, v.z);
            }

            if (name.equals(PLAYER_DOWN)) {
                Vector3f v = player.getLocalTranslation();
                player.setLocalTranslation(v.x, v.y - value * speed * 2, v.z);
            }

            if (name.equals(PLAYER_RIGHT)) {
                Vector3f v = player.getLocalTranslation();
                player.setLocalTranslation(v.x + value * speed * 2, v.y, v.z);
            }

            if (name.equals(PLAYER_LEFT)) {
                Vector3f v = player.getLocalTranslation();
                player.setLocalTranslation(v.x - value * speed * 2, v.y, v.z);
            }
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
        player.rotate(0, 2 * tpf, 0);
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.start();
    }
}
