# AntSimulation
A simple simulated environment where ants roam freely, collecting food and leaving behind pheremones.

Ants will roam randomly around the screen, occasionally leaving behind blue "to home" pheremones. These will fade over time. If an ant reaches the edge of the screen, they will bounce.

If ants have food in their vision, they will immediately redirect towards it. Once they collect the food, they will start to leave behind red "to food" pheremones.

Every frame, ants process the area around them. First, they check for food in their vision. If no food is found, they will begin to sample pheremones. Ants holding food follow blue pheremones, and ants not holding food follow red pheremones. Each ant sample 3 areas in front of them within their vision range ((L)eft, (R)ight, (C)entre). Whichever area has the most desireable pheremones is the direction the ant will follow.

Ants will also be given a "freedom factor". I'm performing some research on other ant simulation projects to see what this fully entails, but my current understanding is that it is a mild variation in the ant's direction whenever it is following a pheremone trail. This is meant to permit path optimization.
