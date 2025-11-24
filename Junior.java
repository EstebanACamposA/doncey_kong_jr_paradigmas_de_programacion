// import java.util.ArrayList;

class Junior extends Entity
{
    Integer two_arm_ascend_speed = 1;
    Integer two_arm_ascend_cooldown = 0;
    Integer two_arm_descend_speed = 1;
    Integer two_arm_descend_cooldown = 0;
    
    Integer falling_time = 0;               // Frames since is_falling is true. Used to decide fall damage.
                                            // Is falling when falling off a ledge
    
    Integer air_time = -1;                  // Frames since a jump started.
    Integer air_speed_fast = 1;             // A positive or negative value Integer. Used for parabolic movement.
    Integer air_cooldown_fast = 0;          // Used for parabolic movement.
    Integer air_speed_slow = 1;             // A positive or negative value Integer. Used for parabolic movement.
    Integer air_cooldown_slow = 1;          // Used for parabolic movement.
    Integer horizontal_air_speed = 1;       // Used for parabolic movement.
    Integer horizontal_air_movement_cooldown = 0;  // Similar to movement_cooldown, but during a jump, vertical and horizontal movement are treated separately.
                                            // "vertical"_air_movement_cooldown does not exist because movement_cooldown is used for that during jumps.
    Boolean is_horizontal_jump = false;     // Makes jumps have horizontal movement.

    // Integer gravity;                     // A value substracted from air_velocity each succesful movement. 

    Boolean is_airborne = false;     // Is true when Junior is in the air and not touching any structure.

    Integer change_side_of_vine_movement = JuniorSizes.HANGING.value;
    Integer reach_out_movement = JuniorSizes.REACHING.value - JuniorSizes.HANGING.value;
    Integer move_through_vine_cooldown = 15;
    

    Singleton singleton;

    // // Controller variables.    // Moved to singleton
    // Boolean left_was_pressed = false;        // Old logic.
    // Boolean up_was_pressed = false;          // Old logic.
    // Boolean right_was_pressed = false;       // Old logic.
    // Boolean down_was_pressed = false;        // Old logic.
    // Boolean jump_was_pressed = false;        // Old logic.

    // Second structure to be able to climb with both arms.
    public SceneStructure second_arm_structure;     // This holds a structure when Junior is holding two vines.
    public Integer reach_vine_tolerance = 8;        // Distance in pixels at which Junior can Snap to a vine.
    public Integer touch_ground_tolerance = 6;      // Distance in pixels at which Junior can Snap to a platform.
    public Integer touch_ceiling_tolerance = 6;      // Distance in pixels at which Junior can Snap to a platform.
    public Integer detect_wall_tolerance = 14;      // Distance in pixels at which Junior can Snap to a platform.
    

    // Animation variables. // These booleans and the direction are sent to determine Junior's sprite
    // public Boolean standing_animation = false;
    // public Boolean extending_arm_animation = false;
    // public Boolean gripping_animation = false;
    // public Boolean jumping_animation = false;
    JuniorAnimations animation;



    // Constructor with all attributes.
    public Junior(Vector2D position, Integer id,
        Integer height, Integer width,
        Direction direction,
        Integer ground_speed,   Integer ground_cooldown,
        Integer ascend_speed,   Integer ascend_cooldown,
        Integer descend_speed,  Integer descend_cooldown,
        Integer fall_speed,     Integer fall_cooldown,
        Integer movement_cooldown, SceneStructure current_structure,
        Singleton singleton,
        JuniorAnimations animation)
                   {
        this.position                   = position;
        this.id                         = id;
        this.height                     = height;
        this.width                      = width;
        this.direction                  = direction;
        this.ground_speed               = ground_speed;
        this.ground_cooldown            = ground_cooldown;
        this.ascend_speed               = ascend_speed;
        this.ascend_cooldown            = ascend_cooldown;
        this.descend_speed              = descend_speed;
        this.descend_cooldown           = descend_cooldown;
        this.fall_speed                 = fall_speed;
        this.fall_cooldown              = fall_cooldown;
        this.movement_cooldown          = movement_cooldown;
        this.current_structure          = current_structure;
        this.singleton                  = singleton;
        this.animation                  = animation;
   
        UpdateCurrentStructures();

        System.out.println("Junior created through all attributes constructor.");
        System.out.println("this.air_cooldown_fast = " + this.air_cooldown_fast);
        System.out.println("this.air_cooldown_slow = " + this.air_cooldown_slow);
        System.out.println("this.air_speed_fast = " + this.air_speed_fast);
        System.out.println("this.air_time = " + this.air_time);
        System.out.println("this.ascend_cooldown = " + this.ascend_cooldown);
        System.out.println("this.ascend_speed = " + this.ascend_speed);
        System.out.println("this.change_side_of_vine_movement = " + this.change_side_of_vine_movement);
        System.out.println("this.descend_cooldown = " + this.descend_cooldown);
        System.out.println("this.descend_speed = " + this.descend_speed);
        System.out.println("this.fall_cooldown = " + this.fall_cooldown);
        System.out.println("this.fall_speed = " + this.fall_speed);
        System.out.println("this.falling_time = " + this.falling_time);
        System.out.println("this.ground_cooldown = " + this.ground_cooldown);
        System.out.println("this.ground_speed = " + this.ground_speed);
        System.out.println("this.height = " + this.height);
        System.out.println("this.horizontal_air_movement_cooldown = " + this.horizontal_air_movement_cooldown);
        System.out.println("this.horizontal_air_speed = " + this.horizontal_air_speed);
        System.out.println("this.id = " + this.id);
        System.out.println("this.move_through_vine_cooldown = " + this.move_through_vine_cooldown);
        System.out.println("this.movement_cooldown = " + this.movement_cooldown);
        System.out.println("this.reach_out_movement = " + this.reach_out_movement);
        System.out.println("this.reach_vine_tolerance = " + this.reach_vine_tolerance);
        System.out.println("this.touch_ground_tolerance = " + this.touch_ground_tolerance);
        System.out.println("this.two_arm_ascend_cooldown = " + this.two_arm_ascend_cooldown);
        System.out.println("this.two_arm_ascend_speed = " + this.two_arm_ascend_speed);
        System.out.println("this.width = " + this.width);
        System.out.println("this.animation = " + this.animation);
        System.out.println("this.current_structure = " + this.current_structure);
        System.out.println("this.direction = " + this.direction);
        System.out.println("this.is_airborne = " + this.is_airborne);
        System.out.println("this.is_falling = " + this.is_falling);
        System.out.println("this.is_horizontal_jump = " + this.is_horizontal_jump);
        System.out.println("this.position = " + this.position);
        System.out.println("this.second_arm_structure = " + this.second_arm_structure);
        System.out.println("this.singleton = " + this.singleton);

    }
    

    

    // Junior overrides this method because the one from Entity is for Snapjaws and Fruits. 
    public void EnterStructure(SceneStructure structure)
    {
        System.out.println("Enters EnterStructure(SceneStructure structure) (Junior)");
        if (structure.is_vine)
        {
            System.out.println("\tstructure.is_vine");
            if (IsVineOnTheLeft(structure))
            {
                System.out.println("\t\tIsVineOnTheLeft(structure) == true");
                this.position.x = structure.right_limit;
            }
            else if (IsVineOnTheRight(structure))
            {
                System.out.println("\t\tIsVineOnTheRight(structure) == true");
                this.position.x = structure.right_limit - this.width;
            }
            // right and left limits should be the same for vines
        } else
        {
            System.out.println("\tstructure.is_vine was false (else)");
            // stand on a platform
            this.position.y = structure.top_limit - this.height;
        }
        this.current_structure = structure;
    }

    // Controlls vertical parabolic movement acorrding to air_time.
    // Returns true if jump induced vertical movement was applied; if not, returns false.
    public Boolean ParabolicMovement()
    {
        System.out.println("Enters ParabolicMovement()");
        if (air_time > -1)
        {
            System.out.println("\tair_time > -1");
            if (air_time < 10)          // air_time -> [0, 10[
            {
                System.out.println("\t\tair_time < 10");
                this.position.y -= this.air_speed_fast;
                this.movement_cooldown += this.air_cooldown_fast;
            }
            else if (air_time < 20)     // air_time -> [10, 20[
            {
                System.out.println("\t\tair_time < 20");
                this.position.y -= this.air_speed_slow;
                this.movement_cooldown += this.air_cooldown_slow;
            }
            else if (air_time < 30)     // air_time -> [20, 30[
            {
                System.out.println("\t\tair_time < 30");
                this.position.y += this.air_speed_slow;
                this.movement_cooldown += this.air_cooldown_slow;
            }
            else                        // air_time -> [30, infinite[
            {
                System.out.println("\t\tair_time < 30 was false (else)");
                this.position.y += this.air_speed_fast;
                this.movement_cooldown += this.air_cooldown_fast;
            }
            air_time ++;
            return true;
        }
        else return false;
    }

    public Boolean IsHittingCeiling()
    {
        System.out.println("Enters IsHittingCeiling()");
        // Compare Junior's position and hitbox with all structures and find any collision with a ceiling.
        for (int i = 0; i < singleton.structures.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle) (IsHittingCeiling())");
            SceneStructure structure = singleton.structures.get(i);
            
            if (!structure.is_vine)
            {
                System.out.println("\t\t!structure.is_vine");
                if (IsCeilingAbove(structure))
                {
                    System.out.println("Exits IsHittingCeiling() with true");
                    return true;
                }   
            }
        }
        return false;
    }
    public Boolean IsHittingFloor()
    {
        System.out.println("Enters IsHittingFloor()");
        // Compare Junior's position and hitbox with all structures and find any collision with the top of a platform.
        for (int i = 0; i < singleton.structures.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle) (IsHittingFloor())");
            SceneStructure structure = singleton.structures.get(i);
            
            if (!structure.is_vine)
            {
                System.out.println("\t\t!structure.is_vine");
                if (IsFloorBelow(structure))
                {
                    System.out.println("Exits IsHittingFloor() with true");
                    return true;
                }   
            }
        }
        return false;
    }
    public Boolean IsHittingLeftWall()
    {
        System.out.println("Enters IsHittingLeftWall()");
        // Compare Junior's position and hitbox with all structures and find any collision with the top of a platform.
        for (int i = 0; i < singleton.structures.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle) (IsHittingLeftWall())");
            SceneStructure structure = singleton.structures.get(i);
            
            if (!structure.is_vine)
            {
                System.out.println("\t\t!structure.is_vine");
                if (IsWallOnTheLeft(structure))
                {
                    System.out.println("Exits IsHittingLeftWall() with true");
                    return true;
                }   
            }
        }
        return false;
    }
    public Boolean IsHittingRightWall()
    {
        System.out.println("Enters IsHittingRightWall()");
        // Compare Junior's position and hitbox with all structures and find any collision with the top of a platform.
        for (int i = 0; i < singleton.structures.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle) (IsHittingRightWall())");
            SceneStructure structure = singleton.structures.get(i);
            
            if (!structure.is_vine)
            {
                System.out.println("\t\t!structure.is_vine");
                if (IsWallOnTheRight(structure))
                {
                    System.out.println("Exits IsHittingRightWall() with true");
                    return true;
                }   
            }
        }
        return false;
    }
    // // Returns 0, for no collisions; 1, for ceiling; 2, for floor; 3, for right wall; and 4, for left wall.
    // public Integer IsHittingPlatforms()
    // {
    //     System.out.println("Enters IsHittingFloor()");
    //     // Compare Junior's position and hitbox with all structures and find any collision with the top of a platform.
    //     for (int i = 0; i < singleton.structures.size(); i++)
    //     {
    //         System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle) (IsHittingFloor())");
    //         SceneStructure structure = singleton.structures.get(i);
            
    //         if (!structure.is_vine)
    //         {
    //             System.out.println("\t\t!structure.is_vine");
    //             if (IsFloorBelow(structure))
    //             {
    //                 System.out.println("Exits IsHittingFloor() with true");
    //                 return true;
    //             }   
    //         }
    //     }
    //     return false;
    // }

    public Vector2D Fall()
    {
        System.out.println("Enters Fall()");
        // TODO: is horizontal_air_movement_cooldown never increased?
        if ((this.air_time > -1) && (this.horizontal_air_movement_cooldown > 0)) // Move only when enough frames have passed since the last movement.
        {
            System.out.println("\tthis.horizontal_air_movement_cooldown > 0");
            this.horizontal_air_movement_cooldown --;   
        }
        else if (is_horizontal_jump)    // Moves horizontally when necessary.
        {
            if (direction == Direction.RIGHT)   {this.position.x += horizontal_air_speed;}
            else                                {this.position.x -= horizontal_air_speed;}
        }
        if (this.movement_cooldown > 0) // Move only when enough frames have passed since the last movement.
        {
            System.out.println("\tthis.movement_cooldown > 0");
            this.movement_cooldown --;   
            return position;    
        }
        // Check for ceiling collisions.
        if (air_time > -1 && IsHittingCeiling())    // If is jumping, rising, and hit a ceiling, skip the rising section of the jump.
        {
            air_time = 20;
        }
        // Check for collisions.    Collisions stop falling.
        if (UpdateCurrentStructures(air_time>-1 && air_time < 20))  // UpdateCurrentStructures() finds structures. Returns true if any are found.
        {
            System.out.println("\tUpdateCurrentStructures() == true");
            EnterStructure(current_structure);  // Snaps Junior to the found structure.
                                                // This functions reassigns current_structure = current_structure unnecessarily.
            this.is_airborne = false;
            this.is_falling = false;
            this.air_time = -1;
            this.is_horizontal_jump = false;
        }
        else if (!ParabolicMovement())    // if no structure was found, keep falling.
        {
            this.position.y = this.position.y + this.fall_speed;
            this.movement_cooldown += this.fall_cooldown;
        }

        // TODO: Add logic for colliding with structures while airborne.
        return this.position; // returning final position after falling frame.
    }


    // Detect a change in the horizontal key presses in the last frame.
    public Boolean HorizontalKeyChange()
    {
        if (this.singleton.right_is_pressed != this.singleton.right_was_pressed)
        {
            return true;
        }
        if (this.singleton.left_is_pressed != this.singleton.left_was_pressed)
        {
            return true;    
        }
        return false;
    }
    // Detect a change in the vertical key presses in the last frame.
    public Boolean VerticalKeyChange()
    {
        if (this.singleton.up_is_pressed != this.singleton.up_was_pressed)
        {
            return true;    
        }
        if (this.singleton.down_is_pressed != this.singleton.down_was_pressed)
        {
            return true;    
        }
        return false;
    }
    public Boolean CurrentStructureIsVine()
    {
        if (current_structure.is_vine)
        {
            return true;    
        }
        return false;
    }

    public void SetHangingAnimation()
    {
        System.out.println("SetHangingAnimation()");
        this.animation = JuniorAnimations.GRIPPING_ANIMATION;
        this.width = JuniorSizes.HANGING.value;
    }
    public void SetReachingAnimation()
    {
        System.out.println("SetReachingAnimation()");
        this.animation = JuniorAnimations.EXTENDING_ARM_ANIMATION;
        this.width = JuniorSizes.REACHING.value;
    }
    public void SetStandingAnimation()
    {
        System.out.println("SetStandingAnimation()");
        this.animation = JuniorAnimations.STANDING_ANIMATION;
        this.width = JuniorSizes.GROUNDED.value;
    }
        public void SetJumpingAnimation()
    {
        System.out.println("SetJumpingAnimation()");
        this.animation = JuniorAnimations.JUMPING_ANIMATION;
        this.width = JuniorSizes.GROUNDED.value;
    }
    

    // This is true if the horizontal distance to a vine is small enough and if Junior's position.y is within the top and bottom of the vine.
    public Boolean IsVineOnTheLeft(SceneStructure structure)
    {
        return (Utils.Distance(structure.right_limit, this.position.x) <= reach_vine_tolerance)
            && this.position.y >= structure.top_limit
            && this.position.y <= structure.bottom_limit;
    }
    public Boolean IsVineOnTheRight(SceneStructure structure)
    {
        return Utils.Distance(structure.left_limit, this.position.x + this.width) <= reach_vine_tolerance
            && this.position.y >= structure.top_limit
            && this.position.y <= structure.bottom_limit;
    }
    public Boolean IsFloorBelow(SceneStructure structure)
    {
        return Utils.Distance(structure.top_limit, this.position.y + this.height - touch_ground_tolerance) <= touch_ground_tolerance
            && this.position.x >= structure.left_limit - this.width
            && this.position.x <= structure.right_limit;
    }
    public Boolean IsCeilingAbove(SceneStructure structure)
    {
        return Utils.Distance(structure.bottom_limit, this.position.y + touch_ceiling_tolerance) <= touch_ceiling_tolerance
            && this.position.x >= structure.left_limit - this.width
            && this.position.x <= structure.right_limit;
    }
    public Boolean IsWallOnTheRight(SceneStructure structure)
    {
        return Utils.Distance(structure.left_limit, this.position.x + this.width) <= detect_wall_tolerance
            && this.position.y >= structure.top_limit - this.height
            && this.position.y <= structure.bottom_limit;
    }
    public Boolean IsWallOnTheLeft(SceneStructure structure)
    {
        System.out.println("Utils.Distance(structure.right_limit, this.position.x) = " + Utils.Distance(structure.right_limit, this.position.x));
        return Utils.Distance(structure.right_limit, this.position.x) <= detect_wall_tolerance
            && this.position.y >= structure.top_limit - this.height
            && this.position.y <= structure.bottom_limit;
    }
    

    // Looks for structures at Junior's sides and feet.
    // Called when reaching out of a vine and when dropping from a vine.
    // Updates current_structure and second_arm_structure.
    // Returnes true if any structures are found; false, otherwise.
    public Boolean UpdateCurrentStructures(){return UpdateCurrentStructures(false);}
    public Boolean UpdateCurrentStructures(Boolean ignore_floors)
    {
        System.out.println("Enters UpdateCurrentStructures()");
        Integer index_of_structure_on_the_left = -1;
        Integer index_of_structure_on_the_right = -1;
        Integer index_of_structure_below = -1;

        // Clear attached structures.
        current_structure = null;
        second_arm_structure = null;

        // Compare Junior's position and hitbox with all structures and find the indexes of the nearest ones.
        for (int i = 0; i < singleton.structures.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.structures.size(); i++ (for cycle)");
            SceneStructure structure = singleton.structures.get(i);
            
            // Looking for vines to attach to.
            if (structure.is_vine)
            {
                System.out.println("\t\tstructure.is_vine");
                // If a vine on the left is close enough.
                if (IsVineOnTheLeft(structure))
                {
                    index_of_structure_on_the_left = i;
                }
                // If a vine on the right is close enough.
                if (IsVineOnTheRight(structure))
                {
                    index_of_structure_on_the_right = i;
                }   
            }
            else
            {
                System.out.println("\t\tstructure.is_vine is false (else)");
                if (!ignore_floors && IsFloorBelow(structure))
                {
                    index_of_structure_below = i;
                }
            }
        }

        // Assign structures based on the indexes found.
        if (index_of_structure_on_the_left != -1)   // If a vine was found on the left.
        {
            System.out.println("\tindex_of_structure_on_the_left != -1");
            this.current_structure = singleton.structures.get(index_of_structure_on_the_left);
            if (index_of_structure_on_the_right != -1)  // If a vine was also found on the right as well as in the left.
            {
                System.out.println("\t\tindex_of_structure_on_the_right != -1");
                this.second_arm_structure = singleton.structures.get(index_of_structure_on_the_right);
            }
        }
        else if (index_of_structure_on_the_right != -1) // If a vine was only found on the right.
        {
            System.out.println("\tindex_of_structure_on_the_right != -1");
            this.current_structure = singleton.structures.get(index_of_structure_on_the_right);
        }
        else if (index_of_structure_below != -1) // If no vines were found, but is touching ground.
        {
            System.out.println("\tindex_of_structure_below != -1");
            this.current_structure = singleton.structures.get(index_of_structure_below);
        }

        // Updates animations and hitboxes.
        // Return Boolean value.
        if (current_structure != null)
        {
            System.out.println("\tcurrent_structure != null");
            if (second_arm_structure != null && (this.is_airborne || this.is_falling))
            {
                System.out.println("\t\tsecond_arm_structure != null");
                SetReachingAnimation();
            }
            // Also takes into account grabbing a vine while on the ground.
            else if (this.is_airborne || this.is_falling || animation == JuniorAnimations.STANDING_ANIMATION)   // This means it found a single structure while airborne (Briefly becomes airborne when extending arms).
            // else                                            // Junior can update animation and hitbox for any found structure.
            {
                System.out.println("\t\tsecond_arm_structure != null is false (else)");
                if (current_structure.is_vine)  {SetHangingAnimation();}        // Chose an animation based on the found structure's type.
                else                            {SetStandingAnimation();}
            }
            System.out.println("Exits UpdateCurrentStructures() with true");
            return true;    
        }
        System.out.println("Exits UpdateCurrentStructures() with false");
        return false;
        
    }

    // Controls for moving through vines horizontally and updating current_structure and second_arm_structure.
    public Boolean ChangeGrip()
    {
        Boolean found_vine = null;  // Used to know if UpdateCurrentStructures() was true after moving.
        System.out.println("Enters ChangeGrip()");
        if (animation == JuniorAnimations.EXTENDING_ARM_ANIMATION || !(this.singleton.right_is_pressed && IsHittingRightWall()) && !(this.singleton.left_is_pressed && IsHittingLeftWall()))
        {

            if (second_arm_structure == null && animation == JuniorAnimations.GRIPPING_ANIMATION) // If hanging from a single vine closely.
            {
                System.out.println("\tsecond_arm_structure == null");
                if (direction == Direction.RIGHT)   // Junior is facing RIGHT when on the left side of a vine and vice versa.
                {
                    System.out.println("\t\tdirection == Direction.RIGHT");
                    if (this.singleton.right_is_pressed)    // If right is pressed, change sides.
                    {
                        System.out.println("\t\t\tthis.singleton.right_is_pressed");
                        this.position.x = this.position.x + this.change_side_of_vine_movement;
                        this.movement_cooldown += this.move_through_vine_cooldown;
                        direction = Direction.LEFT;         // Change to ReverseDirection() ?
                        found_vine = true;      // found_vine must be true because this movement doesn't cause dropping or searching of new vines.
                        // animation = JuniorAnimations.GRIPPING_ANIMATION;
                        // width = JuniorSizes.HANGING.value;
                    }
                    if (this.singleton.left_is_pressed)     // If left is pressed, extend arm and look for another vine.
                    {
                        System.out.println("\t\t\tthis.singleton.left_is_pressed");
                        this.position.x = this.position.x - this.reach_out_movement;
                        this.movement_cooldown += this.move_through_vine_cooldown;
                        direction = Direction.LEFT;
                        animation = JuniorAnimations.EXTENDING_ARM_ANIMATION;
                        width = JuniorSizes.REACHING.value;
                        found_vine = UpdateCurrentStructures();
                    }
                }
                else if (direction == Direction.LEFT)   // Junior is facing LEFT when on the right side of a vine and vice versa.
                {
                    System.out.println("\t\tdirection == Direction.LEFT");
                    if (this.singleton.right_is_pressed)    // If right is pressed, extend arm and look for another vine.
                    {
                        System.out.println("\t\t\tthis.singleton.right_is_pressed");
                        // this.position.x = this.position.x;  // Doesn't move, only extends hitbox.
                        this.movement_cooldown += this.move_through_vine_cooldown;
                        direction = Direction.RIGHT;
                        animation = JuniorAnimations.EXTENDING_ARM_ANIMATION;
                        width = JuniorSizes.REACHING.value;
                        found_vine = UpdateCurrentStructures();
                    }
                    if (this.singleton.left_is_pressed)     // If left is pressed, change sides.
                    {
                        System.out.println("\t\t\tthis.singleton.left_is_pressed");
                        this.position.x = this.position.x - this.change_side_of_vine_movement;
                        this.movement_cooldown += this.move_through_vine_cooldown;
                        direction = Direction.RIGHT;
                        found_vine = true;      // found_vine must be true because this movement doesn't cause dropping or searching of new vines.
                        // animation = JuniorAnimations.GRIPPING_ANIMATION;
                        // width = JuniorSizes.HANGING.value;
                    }
                }
            }
            else    // If Junior is hanging from two vines or is not holding closely to a vine (arms extended)
            {
                System.out.println("\tsecond_arm_structure was not == null or animation was not GRIPPING_ANIMATION (else)");
                if (this.singleton.right_is_pressed)    // If right is pressed, move to the right vine and UpdateCurrentStructures().
                {
                    System.out.println("\t\tthis.singleton.right_is_pressed");
                    this.position.x = this.position.x + this.reach_out_movement;
                    this.movement_cooldown += this.move_through_vine_cooldown;
                    direction = Direction.RIGHT;
                    animation = JuniorAnimations.GRIPPING_ANIMATION;
                    width = JuniorSizes.HANGING.value;
                    found_vine = UpdateCurrentStructures();
                }
                if (this.singleton.left_is_pressed)     // If left is pressed, stay in the left vine and UpdateCurrentStructures().
                {
                    System.out.println("\t\tthis.singleton.left_is_pressed");
                    // this.position.x = 
                    this.movement_cooldown += this.move_through_vine_cooldown;
                    direction = Direction.LEFT;
                    animation = JuniorAnimations.GRIPPING_ANIMATION;
                    width = JuniorSizes.HANGING.value;
                    found_vine = UpdateCurrentStructures();
                }
                if (!found_vine)
                {
                    System.out.println("\tdidn't find another vine after moving.");
                }
            }
        }
        else
        {
            return true;
        }
        System.out.println("Exits ChangeGrip() with " + found_vine);
        return found_vine;
    }


    // Makes Junior get into HANGING vine animation if ascend or descend are used while extending an arm but not holding another vine.
    // Checks that the animation is EXTENDING_ARM_ANIMATION.
    public void AutoCorrectAnimationOnAscendAndDescend()
    {
        if (animation == JuniorAnimations.EXTENDING_ARM_ANIMATION)
        {
            SetHangingAnimation();
            if (IsVineOnTheLeft(current_structure))
            {
                this.direction = Direction.LEFT;    
            }
            else
            {
                this.direction = Direction.RIGHT;
                this.position.x += reach_out_movement;    // Only right side needs position correction since position points at the top left corner of an Entity. 
            }
        }
    }
    // Vertical movement. Only on vines.
    public Boolean AscendAndDescend()
    {
        // If Junior is not trying to pass a ceiling or a floor.
        if (!(this.singleton.up_is_pressed && IsHittingCeiling()) && !(this.singleton.down_is_pressed && IsHittingFloor()))
        {
            if (second_arm_structure != null)   // If he's holding two vines.
            {
                System.out.println("\tsecond_arm_structure != null");
                if (this.singleton.up_is_pressed)
                {
                    System.out.println("\t\tthis.singleton.up_is_pressed");
                    this.position.y = this.position.y - this.two_arm_ascend_speed;
                    this.movement_cooldown += this.two_arm_ascend_cooldown;
                }
                if (this.singleton.down_is_pressed)
                {
                    System.out.println("\t\tthis.singleton.down_is_pressed");
                    this.position.y = this.position.y + this.two_arm_descend_speed;
                    this.movement_cooldown += this.two_arm_descend_cooldown;
                }
                ReverseDirection(); // For animation purpose.
            }
            else if (CurrentStructureIsVine())   // If he's holding only one vine.
            {
                AutoCorrectAnimationOnAscendAndDescend();
                System.out.println("\tCurrentStructureIsVine()");
                if (this.singleton.up_is_pressed)
                {
                    System.out.println("\t\tthis.singleton.up_is_pressed");
                    this.position.y = this.position.y - this.ascend_speed;
                    this.movement_cooldown += this.ascend_cooldown;
                }
                if (this.singleton.down_is_pressed)
                {
                    System.out.println("\t\tthis.singleton.down_is_pressed");
                    this.position.y = this.position.y + this.descend_speed;
                    this.movement_cooldown += this.descend_cooldown;
                }
            }
        }
        return UpdateCurrentStructures();
    }
    
    // Trigger fall. A fall retains the previous animation and hitbox.
    // Keeping that state helps to enter structures as intended.
    public void TriggerFall()
    {
        System.out.println("Enters TriggerFall()");
        is_airborne = true;
        is_falling = true;
        current_structure = null;
        second_arm_structure = null;
        movement_cooldown = 0;  // So he starts falling immediately.
        
        // Chooses a falling animation depending on the last one
        if (animation == JuniorAnimations.GRIPPING_ANIMATION)
        {
            // animation = JuniorAnimations.JUMPING_ANIMATION;
            SetJumpingAnimation();
        }
        if (animation == JuniorAnimations.WALKING_1_ANIMATION || animation == JuniorAnimations.WALKING_2_ANIMATION)
        {
            // animation = JuniorAnimations.STANDING_ANIMATION;
            SetStandingAnimation();
        }
    }

    public Boolean Walk()
    {
        if (!CurrentStructureIsVine())   // If he's on the ground.
        {
            System.out.println("\t\t!CurrentStructureIsVine()");
            if (this.singleton.right_is_pressed)
            {
                System.out.println("\t\t\tthis.singleton.right_is_pressed");
                this.position.x = this.position.x + this.ground_speed;
                this.direction = Direction.RIGHT;
                this.movement_cooldown += this.ground_cooldown;
            }
            if (this.singleton.left_is_pressed)
            {
                System.out.println("\t\t\tthis.singleton.left_is_pressed");
                this.position.x = this.position.x - this.ground_speed;
                this.direction = Direction.LEFT;
                this.movement_cooldown += this.ground_cooldown;
            }
        }
        if (UpdateCurrentStructures() && this.current_structure.is_vine)  // UpdateCurrentStructures() finds structures. Returns true if any are found.
        {
            System.out.println("\tUpdateCurrentStructures() == true in Walk()");
            EnterStructure(current_structure);  // Snaps Junior to the found structure.
            return true;
        }
        return false;
    }

    // Checks if jumping is possible.
    // If possible, initiates a jump that will be executed for many following frames until reaching a structure.
    public Boolean JumpTriggerer()
    {
        System.out.println("Enters JumpTriggerer()");
        if (!CurrentStructureIsVine())   // If he's on the ground.
        {
            System.out.println("\t!CurrentStructureIsVine()");
            if (this.singleton.right_is_pressed)
            {
                System.out.println("\t\tthis.singleton.right_is_pressed");
                this.direction = Direction.RIGHT;
                is_horizontal_jump = true;
            }
            if (this.singleton.left_is_pressed)
            {
                System.out.println("\t\tthis.singleton.left_is_pressed");
                this.direction = Direction.LEFT;
                is_horizontal_jump = true;
            }
            is_airborne = true;
            air_time = 0;
            // is_falling = true;
            SetJumpingAnimation();
            return true;
        }
        return false;
    }




    // Moves according to direction, checks end of structures.
    // @Override
    public Vector2D Move()
    {
        // This values is assumed to be true since being false would have resulted in falling, and falling makes this check unnecessary.
        Boolean found_structures = true;  // Used to know if UpdateCurrentStructures() was true after moving.
   
        // There's horizontal and vertical movement. Only one type of movement is allowed and vertical has priority.
        // In one direction, movement can only happen if no conflicting keys are pressed.
        // e. g.: wont move if left and right are simultaneously pressed.
        
        System.out.println("this.direction = " + this.direction);
        

        // // If jump is pressed.
        // if (this.singleton.jump_is_pressed)
        // {
        //     System.out.println("\tthis.singleton.jump_is_pressed");
        // }
        // Can only act when on the ground or in a vine.
        if ((is_airborne || is_falling || (this.singleton.jump_is_pressed && JumpTriggerer())))
        {
            System.out.println("\tis_airborne || is_falling");
            // TODO: add jumping logic.
            Fall();
            return position;    
        }
        
        // Vertical Movement:
        if (this.singleton.up_is_pressed ^ this.singleton.down_is_pressed)    // If only one key is pressed.
        {
            System.out.println("\tEnters Vertical movement.");
            if (VerticalKeyChange())    // If there's a key change.
            {
                System.out.println("\t\tVerticalKeyChange()");
                // fdknfsfnao   ??????????????
                movement_cooldown = 0;
            }
            if (this.movement_cooldown > 0) // Move only when enough frames have passed since the last movement.
            {
                System.out.println("\t\tthis.movement_cooldown > 0");
                this.movement_cooldown --;   
                return position;    
            }
            found_structures = AscendAndDescend();
        }

        // Horizontal Movement:
        else if (this.singleton.right_is_pressed ^ this.singleton.left_is_pressed)    // If only one key is pressed.
        {
            System.out.println("\tthis.singleton.right_is_pressed ^ this.singleton.left_is_pressed");
            if (HorizontalKeyChange())    // If there's a key change.
            {
                System.out.println("\t\tHorizontalKeyChange()");
                // fdknfsfnao
                movement_cooldown = 0;
            }
            if (this.movement_cooldown > 0) // Move only when enough frames have passed since the last movement.
            {
                System.out.println("\t\tthis.movement_cooldown > 0");
                this.movement_cooldown --;   
                return position;    
            }
            if (current_structure.is_vine)   // If he's at least holding one vine.
            {
                System.out.println("\t\tcurrent_structure.is_vine");
                found_structures = ChangeGrip();
            }
            else    // Is not airborne, and !current_structure.is_vine.
            {
                System.out.println("\t\tcurrent_structure.is_vine was false (else)");
                found_structures = Walk();
            }
        }
        
        // After moving, check for falling off structures.
        // Direction current_structure_end_of_structure_direction = HasReachedEndOfStructure(current_structure);        //Old method
        // Direction right_vine_end_of_structure_direction = HasReachedEndOfStructure(second_arm_structure);            //Old method
        // Checks for falling off structures with UpdateCurrentStructures(). If it's false, then Junior fell from a structure.
        
        // If falling off any vine (this is independent of whether Junior was holding one or two vines).
        // If two vines were being held. Both are dropped, and one of the two may be regrabbed the next frame.
        // This also works for falling of a ledge.
        // if (current_structure_end_of_structure_direction != null || right_vine_end_of_structure_direction != null)   // Old method
        if (found_structures == false)
        {
            System.out.println("\tfound_structures == false");
            TriggerFall();
        }
        
        System.out.println("Reached end of Move()");
        return position;
    }



    public void TriggerDeath()
    {
        // TODO: make a death animation. Similar to jump logic?
        System.out.println("Enters TriggerDeath()");
    }

    public Boolean EntitiesCollide(Entity entity_1, Entity entity_2) 
    {
        Integer delta_y = entity_1.position.y - entity_2.position.y;
        Integer delta_x = entity_1.position.x - entity_2.position.x;
        return (delta_y >= -1*entity_1.height && delta_y <= entity_2.height) && (delta_x >= -1*entity_1.width && delta_x <= entity_2.width);
    }

    // Compares Junior's position and hitbox with all entities and act based on the entity type.
    public Boolean UpdateCollisions()
    {
        Boolean found_fruit = false;
        System.out.println("Enters UpdateCollisions()");
        for (int i = 0; i < singleton.entities.size(); i++)
        {
            System.out.println("\tint i = 0; i < singleton.entities.size(); i++ (for cycle) (UpdateCollisions())");
            Entity entity = singleton.entities.get(i);
            if (EntitiesCollide(this, entity))
            {
                if (entity instanceof Snapjaw)
                {
                    System.out.println("\t\t\tentity instanceof Snapjaw");
                    TriggerDeath();
                    return true;
                }
                if (entity instanceof Fruit)
                {
                    Fruit fruit = (Fruit) entity;
                    System.out.println("\t\t\tentity instanceof Fruit");
                    singleton.score += fruit.points;
                    // TODO: add some logic to add a visual cue or sound or to signal to refresh the screen when gaining points.
                    found_fruit = true;
                    fruit.is_falling = true;
                }

            }
        }
        return found_fruit;
    }



    public void ShowPosition()
    {
        Boolean has_current_structure = false;
        if (current_structure != null)
        {
            has_current_structure = true;
        }
        Boolean has_second_structure = false;
        if (second_arm_structure != null)
        {
            has_second_structure = true;
        }
        System.out.println("Junior (" + this.id + ") at (" + this.position.x + ", " + this.position.y + ")" + "; width," + this.width + "; animation, " + this.animation + "; current_structure, " + has_current_structure + "; second_structure, " + has_second_structure);
    }
}
