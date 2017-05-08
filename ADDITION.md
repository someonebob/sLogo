Estimation: before looking at the old code:

- How long do you think it will take you to complete this new feature?
    - I think it will take about 2 hours
- How many files will you need to add or update? Why?
    - I will need to display the stamps in the front-end (1 file), make a new class to represent the command (1 file), possibly make changes to the InstructionData class (1 file), and make a class for Stamp (1 file). In total, I will need to add/update 3-4 files.


Review: after completing the feature:
        
- How long did it take you to complete this new feature?
    - It took me 1 hour and 20 minutes to implement this new feature.
- How many files did you need to add or update? Why?
     - I needed to add/update 7 files (ClearStamps, Stamp, InstructionData, NumArgs, AnimatedSimulationView, SimulationView, and StampView). I needed the ClearStamps, Stamp, and StampView classes in order to actually implement the command and have a way of graphically representing a stamp. I needed to change the NumArgs class to ensure that stamp had 0 args instead of 1. Finally, I needed to update InstructionData, SimulationView, and AnimatedSimulationView in order to convey the addition of a stamp to the front-end.
     
- Did you get it completely right on the first try?
    - I actually came very close to getting it completely right on my first try, but I had a couple of errors that I couldn't figure out for a while. Firstly, it took me a while to remember that you needed to update the NumArgs properties file to account for the number of arguments that the command accepts. Secondly, I forgot to account for the turtle's rotation and color when making the stamp. Finally, I forgot that the stamp command needs to return the index of the turtle's image (I had originally forgotten what the index of the image means).

Analysis: what do you feel this exercise reveals about your project's design and documentation?
- Was it as good (or bad) as you remembered?
    - This exercise showed that it the project's design was as good as I had remembered. The flow of commands is very intuitive, making it easy to add new ones.
- What could be improved?
    - I feel that there is probably a way to communicate changes to the front-end in a more efficient/faster manner than it currently has. Right now, you need to change at least 3 classes (InstructionData, AnimatedSimulationView, and SimulationView) in order to relay new types of command information to the front end.
- What would it have been like if you were not familiar with the code at all?
    - If I was not familiar with the code at all, it certainly would have taken a much longer time to implement. However, I feel that by looking at previously created commands, a new programmer could easily add new ones. By understanding the hierarchy of commands, it would make implementing even more easy.