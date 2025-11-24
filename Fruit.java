class Fruit extends Entity
{
    Fruits fruit_type;
    Integer points;

    // Constructor with all attributes.
    public Fruit(Vector2D position, Integer id,
        Integer height, Integer width,
        Direction direction,
        Integer ground_speed,   Integer ground_cooldown,
        Integer ascend_speed,   Integer ascend_cooldown,
        Integer descend_speed,  Integer descend_cooldown,
        Integer fall_speed,     Integer fall_cooldown,
        Integer movement_cooldown, SceneStructure current_structure,
        Fruits fruit_type, Integer points)

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
        this.fruit_type         = fruit_type;
        this.points             = points;
        
        System.out.println("Fruit created through all attributes constructor.");
        
    }
    
    // Minimal constructor for fruits.
    public Fruit(Integer x, Integer y, Integer id, Fruits fruit_type)
    {
        // Fruit constants.
        height          = 16;
        width           = 16;
        // ground_speed    = -1;
        // ascend_speed    = -1;
        // descend_speed   = -1;
        fall_speed      = 1;
        
        this.position = new Vector2D(x, y);
        this.id = id;
        
        // this.ground_cooldown    = -1;
        // this.ascend_cooldown    = -1;
        // this.descend_cooldown   = -1;
        this.fall_cooldown      = 1;
        // this.movement_cooldown  = -1;
        
        this.fruit_type = fruit_type;

        System.out.println(this.fruit_type + " (" + this.id + ") created at: x=" + this.position.x + " y=" + this.position.y);
    }

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

        System.out.println("Fruit (" + this.id + ") did not move");
        return position;
    }

    public void ShowPosition()
    {
        System.out.println("Snapjaw (" + this.id + ") at (" + this.position.x + ", " + this.position.y + ")");
    }
}
