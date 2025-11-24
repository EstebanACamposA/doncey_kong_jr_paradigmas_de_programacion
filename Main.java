import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// import java.util.Vector;

import javax.swing.JFrame;



// class SnapjawIntersection
// {
//     public Integer x;
//     public Integer y;
//     public List<SceneStructure> intersecting_scene_structures;

//     public SnapjawIntersection(Integer x, Integer y, List<SceneStructure> intersecting_scene_structures) {
//         this.x = x;
//         this.y = y;
//         this.intersecting_scene_structures = intersecting_scene_structures;
//     }
// }



//////////////////////////////////// MAIN ////////////////////////////////////

public class Main   // Use 60 fps to better adjust speeds?
{
    public static void main(String[] args) {
        System.out.println("Enter Main");

        /////// RECEIVE KEYBOARD LOGIC ///////
        JFrame window = new JFrame("Keyboard Test");
        Keyboard listener = new Keyboard();
        
        window.addKeyListener(listener);
        window.setSize(400, 400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /////// RECEIVE KEYBOARD LOGIC ///////

        // Create Singleton
        Singleton singleton = new Singleton(0, new ArrayList<SceneStructure>(), new ArrayList<Entity>()); 

        // Create SceneStructure objects for the platforms and vines
        SceneStructure vine_1           = new SceneStructure(110, 110, 119, 376, singleton.GenerateID(), true);
        SceneStructure vine_2           = new SceneStructure(133, 133, 119, 376, singleton.GenerateID(), true);
        SceneStructure platform_1       = new SceneStructure(200, 10, 250, 376, singleton.GenerateID(), false);
        SceneStructure platform_2       = new SceneStructure(105, 55, 220, 230, singleton.GenerateID(), false);
        singleton.structures.add(vine_1);
        singleton.structures.add(vine_2);
        singleton.structures.add(platform_1);
        singleton.structures.add(platform_2);

        // Example: create a Snapjaw
        // public Snapjaw(Integer y, Integer id, Direction direction, SceneStructure current_structure, Boolean is_blue)
        Snapjaw snapjaw = new Snapjaw(220, singleton.GenerateID(), Direction.DOWN, vine_1, false);
        singleton.entities.add(snapjaw);
        


        // Instantiate Junior.
        // public Junior(Vector2D position, Integer id,
        // Integer height, Integer width,
        // Direction direction,
        // Integer ground_speed,   Integer ground_cooldown,
        // Integer ascend_speed,   Integer ascend_cooldown,
        // Integer descend_speed,  Integer descend_cooldown,
        // Integer fall_speed,     Integer fall_cooldown,
        // Integer movement_cooldown, SceneStructure current_structure,
        // Singleton singleton)
        //            {
        // this.position           = position;
        // this.id                 = id;
        // this.height             = height;
        // this.width              = width;
        // this.direction          = direction;
        // this.ground_speed       = ground_speed;
        // this.ground_cooldown    = ground_cooldown;
        // this.ascend_speed       = ascend_speed;
        // this.ascend_cooldown    = ascend_cooldown;
        // this.descend_speed      = descend_speed;
        // this.descend_cooldown   = descend_cooldown;
        // this.fall_speed         = fall_speed;
        // this.fall_cooldown      = fall_cooldown;
        // this.movement_cooldown  = movement_cooldown;
        // this.current_structure  = current_structure;
        // this.singleton  = singleton;
        Junior dk_junior = new Junior(new Vector2D(96, 200), singleton.GenerateID(), 16, 14, Direction.RIGHT, 1, 1, 1, 2, 2, 0, 1, 0, 0, vine_1, singleton, JuniorAnimations.GRIPPING_ANIMATION);

        final Integer TICK_MS = 50; // Period in ms. -> 20 frames per second.
        while (true)
        {
            /////// RECEIVE KEYBOARD LOGIC ///////
            singleton.left_was_pressed      = singleton.left_is_pressed; 
            singleton.up_was_pressed        = singleton.up_is_pressed; 
            singleton.right_was_pressed     = singleton.right_is_pressed; 
            singleton.down_was_pressed      = singleton.down_is_pressed; 
            singleton.jump_was_pressed      = singleton.jump_is_pressed;
            
            singleton.left_is_pressed       = Keyboard.a;
            singleton.up_is_pressed         = Keyboard.w;
            singleton.right_is_pressed      = Keyboard.d;
            singleton.down_is_pressed       = Keyboard.s;
            singleton.jump_is_pressed       = Keyboard.x;
            /////// RECEIVE KEYBOARD LOGIC ///////



            long start = System.currentTimeMillis();

            // Game logic //
            snapjaw.Move();
            // snapjaw.ShowPosition();

            dk_junior.Move();
            dk_junior.ShowPosition();



            // Fram rate logic //
            // // Sleep enough to be on time for the next cycle according to the elapsed time and the 
            // long elapsed = System.currentTimeMillis() - start;
            // long sleep = TICK_MS - elapsed;
            // if (sleep > 0) {
            //     try {
            //         Thread.sleep(sleep);
            //     } catch (InterruptedException e) {
            //         // ignorar o loggear
            //     }

            Utils.sleep(250);          
        }

        // Enums test //
        // System.out.println("Direction.UP = " + Direction.UP);
        // Direction mi_direccion = Direction.UP;
        // if (mi_direccion == Direction.UP)
        // {
        //     System.out.println("mi_direccion == Direction.UP");   
        // }
    }
}


//////////////////////////////////// GAMELOOP ////////////////////////////////////
// public void gameLoop() {
//         final int TICK_MS = 50; // 20 updates por segundo
//         while (running) {
//             long start = System.currentTimeMillis();

//             // 1. Procesar comandos recibidos de los jugadores
//             processPlayerCommands();

//             // 2. Actualizar lógica del mundo
//             world.update();

//             // 3. Enviar estado del mundo a todos los clientes
//             broadcastGameState();

//             long elapsed = System.currentTimeMillis() - start;
//             long sleep = TICK_MS - elapsed;
//             if (sleep > 0) {
//                 try {
//                     Thread.sleep(sleep);
//                 } catch (InterruptedException e) {
//                     // ignorar o loggear
//                 }
//             }





// Y que se guarde en un array de structs de largo "count"
// {
//     int x;
//     int y;
//     int sprite;
//     int direction;
// }