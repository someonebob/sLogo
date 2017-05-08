### Addition to SLogo
**Maddie Briere**
---

### Before looking at the old code (Friday May 5)

**How long do you think it will take you to complete this new feature?**

In all honesty, both of the possible additions for this assignment are in realms of the project I didn't work on. We split our team into the following parts:

1. Interpretation (ME)
2. Instruction (MATT)
3. Front-end (JESSE & JIMMY)

The add-ons feel into the latter two categories. 

Being back-end, I have decided to do the additional instructions, because I am at least somewhat familiar with the back-end architecture. 

I can make the following guesses for time management:
1. Reading over instruction hierarchy (20 minutes)
2. Reading over relevant front-end display like drawing the pen (40 minutes; unfamiliar code)
3. Adding to the views to allow Actors to keep track of stamps (an hour; unfamiliar code)
4. Adding the new instructions in the instruction package (30 minutes)
5. Looking for bugs (an hour)

This sums to a total of **3 1/2 hours.**

**How many files will you need to add or update? Why?**

I will need to completely add the following:

1. A *Stamp* instruction
2. A *Clearstamps* instruction
3. Some sort of view in the frontend for stamps (StampView?)

Likewise, I will probably have to add code to display stamps on the screen/update them. This will depend upon how the front-end implement displays/updates.

I would estimate the total number of files changed or added as **4 files.**

### After working on assignment (Sunday May 7)

**Review: after completing the feature:**

*How long did it take you to complete this new feature?*

In the end, it took me approximately 5 hours to implement this feature. This was slightly longer than I anticipated, just because I struggled to understand the way in which Jesse and Jimmy implemented front end display. I didn't have huge problems understanding instructions because I had talked to Matt about how he did this, but I did run into a road-block in terms of turtle display on the front-end.

*How many files did you need to add or update? Why?*

As expected, I created the following classes:
1. A *Stamp* instruction
2. A *Clearstamps* instruction
3. A *StampView* class: holding stamp information for an individual actor

I also had to modify the *newActor* method in the AnimatedSimulationView class, by adding in the following lines of code:

```java
		actor.getStamp().getCanvas().toBack();
		actor.getStamp().getCanvas().widthProperty().bind(root.widthProperty());
		actor.getStamp().getCanvas().heightProperty().bind(root.heightProperty());
		root.getChildren().add(actor.getStamp().getCanvas());
```

This fit the stamp canvas to the correct region and added the stamp canvas for that actor to the root so that updates would be displayed.

I also had to add methods to the InstructionData object to allow for access to actor stamps in the instructions. These were very similar to the methods implemented for the pen instructions.

Hence, I edited **5 files** in the end.

*Did you get it completely right on the first try?*

Absolutely not! The first time I created a stamp (after doing a little "green dance"), but then I realized that the stamp didn't appear in the correct place. Instead, it spawned in the top left corner of the screen. This happened because I hadn't properly understood to grid indexing for the front-end and had assumed that (0,0) would be the starting location of the turtle. To fix this, I had to add in an offset based on the size of the canvas. 

Similar things happened with actually implementing the stamps in the first place. I accidentally updated the wrong images at points, and access the wrong actors (active vs. non-active). This took some experimentation. Many of these problems arose because certain assumptions were not listed with the relevant methods.

**Analysis:**

*Was it as good (or bad) as you remembered?*

Implementing an instruction was definitely as simple as I remember Matthew describing. I had created the InstructionData object for interface between the front and backend and this provided a good amount of power to the instructions, allowing most types of instructions to be implemented without having to pass new parameters to the instructions (this would require major overhaul of the instruction hierarchy,

I was pleased to see also that the frontend was relatively easy to interact with. Because we relied so heavily upon observable relationship that modifications to the relevant backend structures actually triggered updates in the frontend. I only had to make a couple of addition to display my backend changes via the stamp/clearstamps calls. Otherwise, I was not required to maintain or update anything.

*What could be improved?*

Most of my complaints come down to documentation. I had trouble discerning what was meant to be in the execute method for my instructions and what would already be taken care of by the front end. This just wasn't well-documented in the code. 

Likewise, many important methods in the AnimatedSimulationView were uncommented. The one I had to modify the most, *newActor()*, had no description. It was a monolith of code with undetermined function. Hence, it took some digging to make everything work.

*What would it have been like if you were not familiar with the code at all?*

I was already somewhat familiar with the instructions hierarchy and the front end display and I had trouble locating where certain functions were being fulfilled. Certainly, if I were not at all familiar with how this type of SLogo code works, I would have had more trouble. 

The Instruction class, for instance, only has brief documentation at the start, and then a small blurb with the execute method (the most important method in Instruction). If I did not know what was expected of these executions, I might try to implement additional functionality (e.g., adding an actor to the screen). More documentation of this hierarchy and what it does would certainly make it more readable. 
