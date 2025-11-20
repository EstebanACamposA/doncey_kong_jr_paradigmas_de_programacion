import java.util.ArrayList;

public class Singleton
{
    Integer last_id;
    public ArrayList<SceneStructure> structures;
    public ArrayList<Entity> entities;

    public Boolean left_is_pressed;
    public Boolean up_is_pressed;
    public Boolean right_is_pressed;
    public Boolean down_is_pressed;
    public Boolean jump_is_pressed;

    public Boolean left_was_pressed = false;
    public Boolean up_was_pressed = false;
    public Boolean right_was_pressed = false;
    public Boolean down_was_pressed = false;
    public Boolean jump_was_pressed = false;

    public Singleton(Integer last_id, ArrayList<SceneStructure> structures, ArrayList<Entity> entities)
    {
        this.last_id = last_id;
        this.structures = structures;
        
        this.left_is_pressed = false;
        this.up_is_pressed = false;
        this.right_is_pressed = false;
        this.down_is_pressed = false;
        this.jump_is_pressed = false;
    }

    public Integer GenerateID()
    {
        
        return ++this.last_id;  // Returns the current value and then increments it.
    }    

}