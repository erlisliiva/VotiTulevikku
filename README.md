# VotiTulevikku

This is my working solution, when I want to get out of the prison.

--work proccess here--

If my name comes up via hashcode, I start the prossess of giving it access to all the rooms.
In order to alter the private field I first need to change the accessibility to true.
Then get all the other names who have access to the room, and finally give access to myself and other owner.
Do that until all the rooms have your name connected to it.
Finally return the person with all the access.

--Changes that can be made later--

In order to get newPerson "Erlis", "Liiva" and hide it, I need a new method that returns that newPerson as my name.
That can be done, if I alter the toString method. I need to get newPerson from it and check if it == to newPerson's hascode.
If "Erlis", "Liiva" equals to its hashcode I can return it and use that method instead of newPerson to hide it in the code.
