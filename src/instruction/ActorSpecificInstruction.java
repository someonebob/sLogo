package instruction;

/**
 * <p>
 * Used to identify Instructions needed to execute once per Actor. During the
 * execution of executeAllToldTurtles(), if the current Instruction is an
 * instance of ActorSpecificInstruction, the method iterates through the Actor
 * List and sets the Active Actor to each existing actor, calling execute() each
 * time. There are currently no assumptions or dependencies for this interface.
 * It was decided to identify actor-specific instructions via in interface
 * rather than a private instance variable so abstract methods specific to
 * actor-specific instructions can be added here in the future. As such, this
 * interface is open to extension.
 * </p>
 * 
 * <p>
 * Example of future use:
 * 
 * <pre>
 * {@code
 * if(this instanceof ActorSpecificInstruction){
 *  	someFutureMethodInActorSpecificInstruction();
 *  	anotherSimilarMethod();
 *  }
 * 
 * </pre>
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public interface ActorSpecificInstruction {

}
