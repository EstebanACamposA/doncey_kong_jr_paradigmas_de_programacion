class Snapjaw extends Entity
{

    // public Boolean is_falling = false;       // This is set in Entity class.
    public Boolean is_blue = false;

    // Constructor with all attributes.
    public Snapjaw(Vector2D position, Integer id,
        Integer height, Integer width,
        Direction direction,
        Integer ground_speed,   Integer ground_cooldown,
        Integer ascend_speed,   Integer ascend_cooldown,
        Integer descend_speed,  Integer descend_cooldown,
        Integer fall_speed,     Integer fall_cooldown,
        Integer movement_cooldown, SceneStructure current_structure, Boolean is_blue)
                   {
        this.position           = position;
        this.id                 = id;
        this.height             = height;
        this.width              = width;
        this.direction          = direction;
        this.ground_speed       = ground_speed;
        this.ground_cooldown    = ground_cooldown;
        this.ascend_speed       = ascend_speed;
        this.ascend_cooldown    = ascend_cooldown;
        this.descend_speed      = descend_speed;
        this.descend_cooldown   = descend_cooldown;
        this.fall_speed         = fall_speed;
        this.fall_cooldown      = fall_cooldown;
        this.movement_cooldown  = movement_cooldown;
        this.current_structure  = current_structure;
        this.is_blue            = is_blue;
   
        System.out.println("snapjaw created through all attributes constructor.");

    }
    // Constructor that snaps the snapjaw to the vine structure it's given.
    public Snapjaw(Integer y, Integer id, Direction direction, SceneStructure current_structure, Boolean is_blue)
    {
        // Snapjaw constants.
        height          = 27;   // This assumes the snapjaw is in a vertical position.
        width           = 17;
        ground_speed    = 2;    // Speed is added to the snapjaw's position each frame.
        ascend_speed    = 1;
        descend_speed   = 3;
        fall_speed      = 3;

        this.position = new Vector2D(0, y);
        this.id = id;
        this.direction = direction;

        this.ground_cooldown    = 1;
        this.ascend_cooldown    = 1;
        this.descend_cooldown   = 1;
        this.fall_cooldown      = 1;
        this.movement_cooldown  = 0;
        
        this.current_structure = current_structure;
        this.is_blue = is_blue;

        this.EnterStructure(current_structure);
        
        System.out.println("Snapjaw (" + this.id + ") created at: x=" + this.position.x + " y=" + this.position.y);
    }

    // public void IntersectionEvent(SnapjawIntersection intersection) {
    //     if (intersection.intersecting_scene_structures == null ||
    //         intersection.intersecting_scene_structures.isEmpty()) {
    //         return;
    //     }

    //     Random rng = new Random();
    //     Integer size = intersection.intersecting_scene_structures.size();
    //     Integer chosen_index = rng.nextInt(size);

    //     SceneStructure chosen = intersection.intersecting_scene_structures.get(chosen_index);
    //     this.EnterStructure(chosen);
    // }

    // Snapjaw keeps falling instead of other movement once falling
    public Vector2D Fall()
    {
        this.position.y = this.position.y + this.fall_speed;
        this.movement_cooldown += this.fall_cooldown;
        return this.position; // returning final position after falling frame.
    }


    // Moves according to direction, checks end of structures.
    // @Override
    public Vector2D Move()
    {
        // System.out.println("this.direction = " + this.direction);
        if (this.movement_cooldown > 0) // Move only when enough frames have passed since the last movement.
        {
            this.movement_cooldown --;   
            return position;    
        }
        
        if (this.is_falling)
        {
            return this.Fall();
        }

        switch (this.direction)
        {
            case Direction.RIGHT: // right
                this.position.x = this.position.x + this.ground_speed;
                this.movement_cooldown += this.ground_cooldown;

                break;

            case Direction.UP: // up (ascend)
                this.position.y = this.position.y - this.ascend_speed;
                this.movement_cooldown += this.ascend_cooldown;
                break;

            case Direction.LEFT: // left
                this.position.x = this.position.x - this.ground_speed;
                this.movement_cooldown += this.ground_cooldown;
                break;

            case Direction.DOWN: // down (descend)
                // System.out.println("snapjaw is looking down. with y = " + this.position.y);
                this.position.y = this.position.y + this.descend_speed;
                this.movement_cooldown += this.descend_cooldown;
                // System.out.println("snapjaw is still looking down. with y = " + this.position.y);
                break;

            default:
                // System.out.println("Invalid direction in Snapjaw Move()");
        }

        // The direction at which the snapjaw has exited its current structure. equals to null if it has not exited the structure (most of the time).
        Direction end_of_structure_direction = HasReachedEndOfStructure(current_structure);
        if (end_of_structure_direction != null)
        {
            System.out.println("end_of_structure_direction = " + end_of_structure_direction);
            if (is_blue)
            {
                this.is_falling = true; // Activate falling state.
            }
            end_of_structure_direction = GetOppositeDirection(end_of_structure_direction);  // Reuse variable to hold the opposite direction.
            this.direction = end_of_structure_direction;    // Red snapjaws turn around when reaching the end of vines.
            // For as long as the snapjaw is outside its structure, it sets its direction to return to it.
        }
        
        return position;
    }

    // TODO: add logic to kill snapjaws when they collide with a falling fruit. Maybe it's necessary to move collision logic to the singleton or the Entity class. 

    public void ShowPosition()
    {
        System.out.println("Snapjaw (" + this.id + ") at (" + this.position.x + ", " + this.position.y + ")");
    }
}
