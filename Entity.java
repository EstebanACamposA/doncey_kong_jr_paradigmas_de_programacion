
public abstract class Entity
{

    // public Integer x;       
    // public Integer y;
    public Vector2D position;                   // top-left corner of the entity.

    public Integer id;

    public Integer height;                      // Hitbox size.
    public Integer width;

    public Direction direction;                 // UP, DOWN, LEFT, RIGHT.

    public Integer ground_speed;                // Speed is in pixels. 
    public Integer ground_cooldown;             // Cooldowns are in frames. 
    public Integer ascend_speed;                // Effective speed = speed/cooldown
    public Integer ascend_cooldown;
    public Integer descend_speed;
    public Integer descend_cooldown;
    public Integer fall_speed;
    public Integer fall_cooldown;

    public Integer movement_cooldown;           // Holds the current cooldown produced by any movement option.

    public Boolean is_falling = false;

    public SceneStructure current_structure;    // The structure the entity is currently moving through.
                                                // This is used to determine if the entity has fallen off a ledge or down of a vine.

    // public Entity(Integer x, Integer y, Integer id, Integer height, Integer width,
    //               Integer direction, Integer ground_speed, Integer ascend_speed,
    //               Integer descend_speed, Integer fall_speed, SceneStructure current_structure) {

    //     this.x = x;
    //     this.y = y;
    //     this.id = id;
    //     this.height = height;
    //     this.width = width;
    //     this.direction = direction;
    //     this.ground_speed = ground_speed;
    //     this.ascend_speed = ascend_speed;
    //     this.descend_speed = descend_speed;
    //     this.fall_speed = fall_speed;
    //     this.current_structure = current_structure;
    // }
    public Entity(){}

    // Adjusts the entity's position to the given structure
    public void EnterStructure(SceneStructure structure)
    {
        if (structure.is_vine)
        {
            // right and left limits should be the same for vines
            this.position.x = structure.right_limit - (this.width / 2);
        } else
        {
            // stand on a platform
            this.position.y = structure.top_limit - this.height;
        }
        this.current_structure = structure;
    }

    public Vector2D Move()
    {
        System.out.println("Move method of the Entity class.");
        return new Vector2D(-1, -1);
    }

    // Checks if the entity has moved outside of the vine or floor they're at.
    // Returns the direction at which they fell out from or null if they haven't fallen.
    public Direction HasReachedEndOfStructure(SceneStructure structure)
    {
        if (structure == null)
        {
            return null;    
        }
        if (structure.is_vine)
        {
            if (this.position.y > structure.bottom_limit)   // Use height as part of the calculations?
            {
                return Direction.DOWN;                
            }
            if (this.position.y < structure.top_limit)      // Use height as part of the calculations? 
            {
                return Direction.UP;                
            }
        }
        else
        {
            if (this.position.x > structure.right_limit)    // Use width as part of the calculations?
            {
                return Direction.RIGHT;                
            }
            if (this.position.x < structure.left_limit)     // Use width as part of the calculations?
            {
                return Direction.LEFT;                
            }
        }
        return null;
    }

    // Reverses the direction of the entity.
    public void ReverseDirection()
    {
        
        switch (this.direction)
        {
            case RIGHT: // right
                this.direction = Direction.LEFT;
                break;
            case UP: // up (ascend)
                this.direction = Direction.DOWN;
                break;
            case LEFT: // left
                this.direction = Direction.RIGHT;
                break;
            case DOWN: // down (descend)
                this.direction = Direction.UP;
                break;
            default:
                System.out.println("Invalid direction in ReverseDirection()");
        }
    }
    // Return the opposite of the given direction.
    public Direction GetOppositeDirection(Direction direction)
    {   
        switch (direction)
        {
            case RIGHT: // right
                return Direction.LEFT;
            case UP: // up (ascend)
                return Direction.DOWN;
            case LEFT: // left
                return Direction.RIGHT;
            case DOWN: // down (descend)
                return Direction.UP;
            default:
                System.out.println("Invalid direction in ReverseDirection()");
                return null;
        }
    }


    public void ShowPosition()
    {
        System.out.println("ShowPosition() of Entity class.");
    }
}
